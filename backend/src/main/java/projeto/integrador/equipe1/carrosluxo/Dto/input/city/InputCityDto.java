package projeto.integrador.equipe1.carrosluxo.Dto.input.city;

public class InputCityDto {
    private String nameCity;
    private String country;

    public InputCityDto(String nameCity, String country) {
        this.nameCity = nameCity;
        this.country = country;
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
