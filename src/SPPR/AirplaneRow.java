package SPPR;

import java.time.LocalTime;
import java.util.Objects;

public class AirplaneRow {
    private String name;
    private AirplaneTypeEnum type;
    private int roadNum;
    private LocalTime from;
    private LocalTime to;

    public AirplaneRow() {
    }

    public AirplaneRow(String name, AirplaneTypeEnum type, int roadNum, LocalTime from, LocalTime to) {
        this.name = name;
        this.type = type;
        this.roadNum = roadNum;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirplaneTypeEnum getType() {
        return type;
    }

    public void setType(AirplaneTypeEnum type) {
        this.type = type;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public int getRoadNum() {
        return roadNum;
    }

    public void setRoadNum(int roadNum) {
        this.roadNum = roadNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneRow that = (AirplaneRow) o;
        return roadNum == that.roadNum &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, roadNum, from, to);
    }
}
