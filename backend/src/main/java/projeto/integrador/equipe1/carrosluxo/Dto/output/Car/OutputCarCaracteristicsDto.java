package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

public class OutputCarCaracteristicsDto {
    private long id;
    private String name;
    private String icon;

    public OutputCarCaracteristicsDto() {
    }

    public OutputCarCaracteristicsDto(long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public OutputCarCaracteristicsDto(CaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
        this.name = caracteristic.getName();
        this.icon = caracteristic.getIcon();
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
}
