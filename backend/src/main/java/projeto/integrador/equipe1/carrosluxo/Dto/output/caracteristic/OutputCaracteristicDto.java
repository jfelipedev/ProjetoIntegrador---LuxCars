package projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic;

import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;

public class OutputCaracteristicDto {
    private Long id;
    private String name;
    private String unitOfMeasurement;

    public OutputCaracteristicDto() {
    }

    public OutputCaracteristicDto(Long id, String name, String unitOfMeasurement) {
        this.id = id;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public OutputCaracteristicDto(CaracteristicEntity caracteristic) {
        this.id = caracteristic.getId();
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

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
