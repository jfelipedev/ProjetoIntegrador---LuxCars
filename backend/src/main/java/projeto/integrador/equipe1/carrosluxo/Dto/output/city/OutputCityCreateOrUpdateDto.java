package projeto.integrador.equipe1.carrosluxo.Dto.output.city;

import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

public class OutputCityCreateOrUpdateDto {
    private long id;
    private String nameCity;
    private String country;

    public OutputCityCreateOrUpdateDto() {
    }

    public OutputCityCreateOrUpdateDto(long id, String nameCity, String country) {
        this.id = id;
        this.nameCity = nameCity;
        this.country = country;
    }

    public OutputCityCreateOrUpdateDto(CitiesEntity cities) {
        this.id = cities.getId();
        this.nameCity = cities.getNameCity();
        this.country = cities.getCountry();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
