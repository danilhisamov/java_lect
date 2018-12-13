package SPPR;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class XLSDataBase {
    private static final String[] titles = {"Имя", "Тип", "Полоса", "С", "По"};
    private static final int[] roads = {1, 2, 3};
    private final String filename = "timesheet.xls";
    private CopyOnWriteArrayList<AirplaneRow> db = new CopyOnWriteArrayList<>();

    public XLSDataBase() {
        readFromFile();
    }

    private void writeAirplaneToRow(Row row, AirplaneRow airplaneRow) {
        row.createCell(0).setCellValue(airplaneRow.getName());
        row.createCell(1).setCellValue(airplaneRow.getType().getName());
        row.createCell(2).setCellValue(airplaneRow.getRoadNum());
        row.createCell(3).setCellValue(airplaneRow.getFrom().toString());
        row.createCell(4).setCellValue(airplaneRow.getTo().toString());
    }

    private AirplaneRow readAirplaneFromRow(Row row) {
        if (row == null ||
                row.getCell(0).getStringCellValue() == null ||
                row.getCell(0).getStringCellValue().length() == 0 ||
                row.getCell(0).getStringCellValue().equals(""))
            return null;

        return new AirplaneRow(row.getCell(0).getStringCellValue(),
                AirplaneTypeEnum.fromString(row.getCell(1).getStringCellValue()),
                (int) row.getCell(2).getNumericCellValue(),
                LocalTime.parse(row.getCell(3).getStringCellValue()),
                LocalTime.parse(row.getCell(4).getStringCellValue()));
    }

    public boolean addAirplane(AirplaneRow airplaneRow) {
        boolean success = db.add(airplaneRow);
        new Thread(this::writeToFile).start();
        return success;
    }

    public void cleanExpiredAirplanes() {
        Set<AirplaneRow> toDelete = new HashSet<>();
        LocalTime time = LocalTime.now();
        db.forEach(ar -> {
            if (ar.getTo().isBefore(time)) toDelete.add(ar);
        });

        System.out.println("DB clean operation: " + toDelete.size() + " rows to delete");
        db.removeAll(toDelete);
        new Thread(this::writeToFile).start();
    }

    private void readFromFile() {
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filename));
            Sheet sheet = wb.getSheet("Timesheet");
            for (int i = 1; true; i++) {
                AirplaneRow airplaneRow = readAirplaneFromRow(sheet.getRow(i));
                if (airplaneRow == null)
                    return;
                else
                    db.add(airplaneRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Timesheet");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        sheet.setDefaultColumnWidth(10);

        Row headerRow = sheet.createRow(0);
        Cell headerCell;
        for (int i = 0; i < titles.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(titles[i]);
        }

        int rownum = 1;
        for (AirplaneRow airplaneRow : db) {
            writeAirplaneToRow(sheet.createRow(rownum++), airplaneRow);
        }

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getFreeRoad() {
        Set<Integer> set = new HashSet<>();
        for (int road : roads) set.add(road);
        for (AirplaneRow airplaneRow : db) {
            set.remove(airplaneRow.getRoadNum());
            if (set.size() == 0) return null;
        }

        int[] toReturn = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            toReturn[i++] = iterator.next();
        }
        return toReturn;
    }

    public int[] getRoads() {
        return roads;
    }
}
