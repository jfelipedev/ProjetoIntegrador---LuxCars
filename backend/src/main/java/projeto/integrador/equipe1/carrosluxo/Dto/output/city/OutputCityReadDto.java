package projeto.integrador.equipe1.carrosluxo.Dto.output.city;

import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

public class OutputCityReadDto {
    private String nameCity;
    private String country;

    public OutputCityReadDto() {
    }

    public OutputCityReadDto(String nameCity, String country) {
        this.nameCity = nameCity;
        this.country = country;
    }

    public OutputCityReadDto(CitiesEntity cities) {
        this.nameCity = cities.getNameCity();
        this.country = cities.getCountry();
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
