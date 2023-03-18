package projeto.integrador.equipe1.carrosluxo.Dto.error;

public class ErrorCarDto {
    private String nameCar;
    private String category;
    private String descritpion;
    private String price;
    private String year;
    private String city;
    private String caracteristics;

    private String highlight;

    public ErrorCarDto(String nameCar, String category, String descritpion, String price, String year, String city, String caracteristics, String highlight) {
        this.nameCar = nameCar;
        this.category = category;
        this.descritpion = descritpion;
        this.price = price;
        this.year = year;
        this.city = city;
        this.caracteristics = caracteristics;
        this.highlight = highlight;
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

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
