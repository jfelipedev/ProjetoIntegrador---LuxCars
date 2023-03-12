package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "name_car")
    private String nameCar;

    private String descritpion;

    private Boolean highlight;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CitiesEntity cities;

    @OneToMany(mappedBy = "car")
    private Set<ImagesEntity> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_caracteristics",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "caracteristics_ID")}
    )
    private Set<CaracteristicEntity> caracteristics = new HashSet<>();

    public CarEntity() {
    }

    public CarEntity(long id, String nameCar, String descritpion, Boolean highlight, CategoryEntity category, CitiesEntity cities, Set<ImagesEntity> images, Set<CaracteristicEntity> caracteristics) {
        this.id = id;
        this.nameCar = nameCar;
        this.descritpion = descritpion;
        this.highlight = highlight;
        this.category = category;
        this.cities = cities;
        this.images = images;
        this.caracteristics = caracteristics;
    }

    public CarEntity(InputCarDto car) {
        this.nameCar = car.getNameCar();
        this.descritpion = car.getDescritpion();
        this.highlight = car.getHighlight();
        this.category = new CategoryEntity();
        this.category.setId(car.getIdCategory());
        this.cities = new CitiesEntity();
        this.cities.setId(car.getIdCity());
        Set<CaracteristicEntity> list = new HashSet<>();
        for (Long idCaracteristic : car.getIdCaracteristics()) {
            CaracteristicEntity caracteristic = new CaracteristicEntity();
            caracteristic.setId(idCaracteristic);
            list.add(caracteristic);
        }
        this.caracteristics = list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public CitiesEntity getCities() {
        return cities;
    }

    public void setCities(CitiesEntity cities) {
        this.cities = cities;
    }

    public Set<ImagesEntity> getImages() {
        return images;
    }

    public void setImages(Set<ImagesEntity> images) {
        this.images = images;
    }

    public Set<CaracteristicEntity> getCaracteristics() {
        return caracteristics;
    }

    public void setCaracteristics(Set<CaracteristicEntity> caracteristics) {
        this.caracteristics = caracteristics;
    }
}
