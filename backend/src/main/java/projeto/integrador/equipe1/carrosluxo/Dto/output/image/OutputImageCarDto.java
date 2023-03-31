package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;

public class OutputImageCarDto {
    private Long id;
    private String nameCar;

    public OutputImageCarDto() {
    }

    public OutputImageCarDto(Long id, String nameCar) {
        this.id = id;
        this.nameCar = nameCar;
    }

    public OutputImageCarDto(CarEntity car) {
        this.id = car.getId();
        this.nameCar = car.getNameCar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }
}
