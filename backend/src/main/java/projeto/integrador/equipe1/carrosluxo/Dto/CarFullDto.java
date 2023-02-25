package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class CarFullDto {
    private long id;
    private String nameCar;
    private CategoryFullDto Category;

    public CarFullDto() {
    }

    public CarFullDto(long id, String nameCar, CategoryFullDto category) {
        this.id = id;
        this.nameCar = nameCar;
        this.Category = category;
    }

    public CarFullDto(CarEntity carEntity) {
        this.id = carEntity.getId();
        this.nameCar = carEntity.getNameCar();
        this.Category = new CategoryFullDto(carEntity.getCategory());
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

    public CategoryFullDto getCategory() {
        return Category;
    }

    public void setCategory(CategoryFullDto category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "CarFullDto{" +
                "id=" + id +
                ", nameCar='" + nameCar + '\'' +
                ", Category=" + Category +
                '}';
    }

    public CarEntity toEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(this.getId());
        carEntity.setNameCar(this.getNameCar());
        carEntity.setCategory(new CategoryEntity());
        carEntity.setCategory(this.getCategory().toEntity());
        return carEntity;
    }
}
