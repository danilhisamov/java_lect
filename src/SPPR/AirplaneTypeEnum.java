package SPPR;

import java.util.Arrays;

public enum AirplaneTypeEnum {
    AIRBUS_320("Airbus 320"),
    AIRBUS_330("Airbus 330"),
    AIRBUS_340("Airbus 340"),
    BOEING_737("Boeing 737"),
    BOEING_767("Boeing 767"),
    BOEING_777("Boeing 777");
    private String name;

    AirplaneTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AirplaneTypeEnum fromString(String s) {
        return Arrays.stream(AirplaneTypeEnum.values()).filter(en -> en.name.equals(s)).findFirst().orElse(null);
    }
}
