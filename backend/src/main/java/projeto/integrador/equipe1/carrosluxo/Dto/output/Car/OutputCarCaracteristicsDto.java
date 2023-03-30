package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CarCaracteristicEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

import java.util.Objects;

public class OutputCarCaracteristicsDto {
    private long id;
    private String name;
    private String icon;
    private String value;
    private String unit;

    public OutputCarCaracteristicsDto() {
    }

    public OutputCarCaracteristicsDto(long id, String name, String icon, String value, String unit) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.value = value;
        this.unit = unit;
    }

    public OutputCarCaracteristicsDto(CarCaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
        this.name = caracteristic.getCaracteristic().getName();
        if (Objects.equals(caracteristic.getCaracteristic().getIcon(), "")) {
            this.icon = "Imagem ainda não foi inserida!";
        } else {
            this.icon = caracteristic.getCaracteristic().getIcon();
        }
        this.unit = caracteristic.getCaracteristic().getUnitOfMeasurement();
        this.value = caracteristic.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
