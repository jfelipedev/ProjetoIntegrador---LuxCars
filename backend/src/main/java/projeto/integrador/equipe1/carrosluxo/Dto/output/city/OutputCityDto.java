package projeto.integrador.equipe1.carrosluxo.Dto.output.city;

import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

public class OutputCityDto {
    private Long id;
    private String nameCity;

    public OutputCityDto() {
    }

    public OutputCityDto(Long id, String nameCity) {
        this.id = id;
        this.nameCity = nameCity;
    }

    public OutputCityDto(CitiesEntity cities) {
        this.id = cities.getId();
        this.nameCity = cities.getNameCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
