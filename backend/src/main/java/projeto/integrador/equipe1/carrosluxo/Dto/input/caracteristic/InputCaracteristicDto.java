package projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic;

public class InputCaracteristicDto {
    private String name;
    private String unitOfMeasurement;

    public InputCaracteristicDto() {
    }

    public InputCaracteristicDto(String name, String unitOfMeasurement) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
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
