package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

public class OutputCarCityDto {
    private long id;
    private String nameCity;

    public OutputCarCityDto() {
    }

    public OutputCarCityDto(long id, String nameCity) {
        this.id = id;
        this.nameCity = nameCity;
    }

    public OutputCarCityDto(CitiesEntity city) {
        this.id = city.getId();
        this.nameCity = city.getNameCity();
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
}
