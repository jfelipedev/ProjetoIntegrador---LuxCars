package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;

public class OutputImageCarDto {
    private long id;
    private String nameCar;

    public OutputImageCarDto() {
    }

    public OutputImageCarDto(long id, String nameCar) {
        this.id = id;
        this.nameCar = nameCar;
    }

    public OutputImageCarDto(CarEntity car) {
        this.id = car.getId();
        this.nameCar = car.getNameCar();
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
}
