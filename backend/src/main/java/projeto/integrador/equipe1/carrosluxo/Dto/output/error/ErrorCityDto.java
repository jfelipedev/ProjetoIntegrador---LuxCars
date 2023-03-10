package projeto.integrador.equipe1.carrosluxo.Dto.output.error;

public class ErrorCityDto {
    private String nameCity;
    private String country;

    public ErrorCityDto(String nameCity, String country) {
        this.nameCity = nameCity;
        this.country = country;
    }

    public ErrorCityDto() {
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
