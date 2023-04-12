package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

public class OutputCarCityDto {
    private Long id;
    private String nameCity;

    public OutputCarCityDto() {
    }

    public OutputCarCityDto(Long id, String nameCity) {
        this.id = id;
        this.nameCity = nameCity;
    }

    public OutputCarCityDto(CitiesEntity city) {
        this.id = city.getId();
        this.nameCity = city.getNameCity();
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
