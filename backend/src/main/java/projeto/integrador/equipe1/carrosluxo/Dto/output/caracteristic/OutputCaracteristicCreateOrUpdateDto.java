package projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic;

import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

import java.util.Objects;

public class OutputCaracteristicCreateOrUpdateDto {
    private Long id;
    private String name;
    private String icon;
    private String unitOfMeasurement;

    public OutputCaracteristicCreateOrUpdateDto() {
    }

    public OutputCaracteristicCreateOrUpdateDto(Long id, String name, String icon, String unitOfMeasurement) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public OutputCaracteristicCreateOrUpdateDto(CaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
        if (Objects.equals(caracteristic.getIcon(), "")) {
            this.icon = "Imagem ainda não foi inserida!";
        } else {
            this.icon = caracteristic.getIcon();
        }
        this.name = caracteristic.getName();
        this.unitOfMeasurement = caracteristic.getUnitOfMeasurement();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
