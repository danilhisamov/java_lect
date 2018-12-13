package SPPR;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Sppr extends JFrame {
    private JTextField nameTextField;
    private JComboBox<AirplaneTypeEnum> airplaneComboBox;
    private static final String helpMessage = "Какое-то описание программы";

    private JDialog roadDialog;
    private JDialog timeDialog;

    private XLSDataBase db = new XLSDataBase();

    private AirplaneRow newAirplaneRow = new AirplaneRow();

    public Sppr() throws HeadlessException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("СППР \"ДИСПЕТЧЕР ПОЛОС\"");
        setNimbusLAF();
        SwingUtilities.updateComponentTreeUI(Sppr.this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // centralize
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        initAirplaneComboBox();
        initAirplaneNameTextField();
        initAirplaneSubmitBtn();
        initHelpBtn();

        initCleanThread();

        setVisible(true);
    }

    private void initHelpBtn() {
        JButton helpBtn = new JButton("Помощь");
        helpBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(Sppr.this,
                    helpMessage,
                    "Справка", JOptionPane.INFORMATION_MESSAGE);
        });
        this.add(helpBtn);
    }

    private void initCleanThread() {
        new Thread(() -> {
            while (true) {
                db.cleanExpiredAirplanes();
                try {
                    Thread.sleep(60_000); // 60 sec
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initAirplaneComboBox() {
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(new JLabel("Тип самолета: "));
        airplaneComboBox = new JComboBox<>(AirplaneTypeEnum.values());
        airplaneComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, airplaneComboBox.getMinimumSize().height));
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(airplaneComboBox);
        box.add(Box.createRigidArea(new Dimension(10, 0)));

        this.getContentPane().add(box);
    }

    private void initAirplaneNameTextField() {
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(new JLabel("Название самолета: "));
        nameTextField = new JTextField();
        nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameTextField.getMinimumSize().height));
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(nameTextField);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        this.getContentPane().add(box);
    }

    private void initAirplaneSubmitBtn() {
        JButton submit = new JButton("Подобрать полосу");
        submit.addActionListener(e -> {
            int[] freeRoads = db.getFreeRoad();
            if (freeRoads == null) {
                JOptionPane.showMessageDialog(Sppr.this,
                        "Свободные полосы отсутствуют, пожалуйста подождите",
                        "Свободные полосы отсутствуют",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            newAirplaneRow.setType((AirplaneTypeEnum) airplaneComboBox.getSelectedItem());
            newAirplaneRow.setName(nameTextField.getText());
            showRoadsDialog(freeRoads);
        });
        this.getContentPane().add(submit);
    }

    private void showRoadsDialog(int[] freeRoads) {
        roadDialog = new JDialog(Sppr.this, "Выбор полосы", true);
        roadDialog.setSize(300, 300);
        roadDialog.setLocationRelativeTo(null); // centralize
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,1));
        for (int roadNum : db.getRoads()) {
            JButton jButton = new JButton(String.valueOf(roadNum));
            jButton.setSize(jPanel.getWidth(), jPanel.getHeight() / 3);
            boolean isFree = roadIsFree(freeRoads, roadNum);
            jButton.setBackground(isFree ? Color.GREEN : Color.RED);
            jButton.setEnabled(isFree);
            jButton.addActionListener(e -> {
                newAirplaneRow.setRoadNum(roadNum);
                showTimeDialog();
            });
            jPanel.add(jButton);
        }
        roadDialog.setContentPane(jPanel);
        roadDialog.setVisible(true);
    }

    private boolean roadIsFree(int[] freeRoads, int road) {
        for (int freeRoad : freeRoads) {
            if (freeRoad == road) return true;
        }

        return false;
    }

    private void showTimeDialog() {
        timeDialog = new JDialog(Sppr.this, "Назначить полосу", true);
        timeDialog.setSize(300, 300);
        timeDialog.setLocationRelativeTo(null); // centralize
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        Box fromBox = new Box(BoxLayout.X_AXIS);
        fromBox.add(new JLabel("С: "));
        JSpinner from = new JSpinner(new SpinnerDateModel(new Date(), null, null,
                Calendar.MINUTE));
        JSpinner.DateEditor fromDE = new JSpinner.DateEditor(from, "HH:mm");
        from.setEditor(fromDE);
        fromBox.add(from);
        jPanel.add(fromBox);

        Box toBox = new Box(BoxLayout.X_AXIS);
        toBox.add(new JLabel("По: "));
        JSpinner to = new JSpinner(new SpinnerDateModel(new Date(), null, null,
                Calendar.MINUTE));
        JSpinner.DateEditor toDE = new JSpinner.DateEditor(to, "HH:mm");
        to.setEditor(toDE);
        toBox.add(to);
        jPanel.add(toBox);

        JButton submit = new JButton("OK");
        submit.addActionListener(e -> {
            newAirplaneRow.setFrom(LocalDateTime.ofInstant(Instant.ofEpochMilli(((Date) from.getValue()).getTime()), ZoneId.systemDefault()).toLocalTime());
            newAirplaneRow.setTo(LocalDateTime.ofInstant(Instant.ofEpochMilli(((Date) to.getValue()).getTime()), ZoneId.systemDefault()).toLocalTime());
            db.addAirplane(newAirplaneRow);

            newAirplaneRow = new AirplaneRow();

            timeDialog.dispose();
            roadDialog.dispose();
            JOptionPane.showMessageDialog(Sppr.this, "Самолет успешно добавлен");
        });
        jPanel.add(submit);
        timeDialog.setContentPane(jPanel);
        timeDialog.pack();
        timeDialog.setVisible(true);
    }

    private void setNimbusLAF() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        new Sppr();
    }
}
