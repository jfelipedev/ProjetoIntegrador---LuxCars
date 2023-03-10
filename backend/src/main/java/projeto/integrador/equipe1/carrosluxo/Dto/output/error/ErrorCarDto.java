package projeto.integrador.equipe1.carrosluxo.Dto.output.error;

public class ErrorCarDto {
    private String nameCar;
    private String category;
    private String city;
    private String caracteristics;

    public ErrorCarDto(String nameCar, String category, String city, String caracteristics) {
        this.nameCar = nameCar;
        this.category = category;
        this.city = city;
        this.caracteristics = caracteristics;
    }

    public ErrorCarDto() {
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCaracteristics() {
        return caracteristics;
    }

    public void setCaracteristics(String caracteristics) {
        this.caracteristics = caracteristics;
    }
}
