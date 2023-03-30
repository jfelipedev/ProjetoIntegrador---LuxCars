package projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic;

import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

import java.util.Objects;

public class OutputCaracteristicCreateOrUpdateDto {
    private long id;
    private String name;
    private String icon;

    public OutputCaracteristicCreateOrUpdateDto() {
    }

    public OutputCaracteristicCreateOrUpdateDto(long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public OutputCaracteristicCreateOrUpdateDto(CaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
        if (Objects.equals(caracteristic.getIcon(), "")) {
            this.icon = "Imagem ainda não foi inserida!";
        } else {
            this.icon = caracteristic.getIcon();
        }
        this.name = caracteristic.getName();
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
