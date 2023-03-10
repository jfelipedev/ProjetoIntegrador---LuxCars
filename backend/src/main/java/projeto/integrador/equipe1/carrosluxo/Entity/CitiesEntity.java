package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;

import java.util.Set;

@Entity(name = "cities")
public class CitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name_city")
    private String nameCity;

    private String country;

    @OneToMany(mappedBy = "cities")
    private Set<CarEntity> cars;

    public CitiesEntity() {
    }

    public CitiesEntity(long id, String nameCity, String country, Set<CarEntity> cars) {
        this.id = id;
        this.nameCity = nameCity;
        this.country = country;
        this.cars = cars;
    }

    public CitiesEntity(InputCityDto city) {
        this.nameCity = city.getNameCity();
        this.country = city.getCountry();
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

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }
}
