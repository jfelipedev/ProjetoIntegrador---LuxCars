package projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic;

import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

public class OutputCaracteristicDto {
    private long id;
    private String icon;

    public OutputCaracteristicDto() {
    }

    public OutputCaracteristicDto(long id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    public OutputCaracteristicDto(CaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
        this.icon = caracteristic.getIcon();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
