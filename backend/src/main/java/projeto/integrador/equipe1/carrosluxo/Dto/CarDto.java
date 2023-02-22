package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class CarDto {
    private String nameCar;
    private long idCategory;

    public CarDto() {
    }

    public CarDto(String nameCar, long idCategory) {
        this.nameCar = nameCar;
        this.idCategory = idCategory;
    }

    public CarDto(CarEntity carEntity) {
        this.nameCar = carEntity.getNameCar();
        this.idCategory = carEntity.getCategory().getId();
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "nameCar='" + nameCar + '\'' +
                ", idCategory=" + idCategory +
                '}';
    }

    public CarEntity toEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setNameCar(this.getNameCar());
        carEntity.setCategory(new CategoryEntity());
        carEntity.getCategory().setId(this.getIdCategory());
        return carEntity;
    }
}
