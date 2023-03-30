package projeto.integrador.equipe1.carrosluxo.Dto.error;

public class ErrorCaracteristicDto {
    private String name;
    private String unitOfMeasurement;

    public ErrorCaracteristicDto() {
    }

    public ErrorCaracteristicDto(String name, String unitOfMeasurement) {
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
