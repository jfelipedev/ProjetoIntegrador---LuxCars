package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CarDto;

@Entity(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "name_car")
    private String nameCar;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public CarEntity() {
    }

    public CarEntity(int id, String nameCar, CategoryEntity category) {
        this.id = id;
        this.nameCar = nameCar;
        this.category = category;
    }

    public CarEntity(CarDto carDto){
        this.nameCar = carDto.getNameCar();
        this.category = new CategoryEntity();
        this.category.setId(carDto.getCategory().getId());
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", nameCar='" + nameCar + '\'' +
                ", category=" + category +
                '}';
    }
}
