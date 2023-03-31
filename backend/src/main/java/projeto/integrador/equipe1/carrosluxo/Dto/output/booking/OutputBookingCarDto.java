package projeto.integrador.equipe1.carrosluxo.Dto.output.booking;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;

public class OutputBookingCarDto {
    private Long id;
    private String nameCar;
    private Double price;
    private Integer year;
    private String category;
    private String city;
    private String country;

    public OutputBookingCarDto(Long id, String nameCar, Double price, Integer year, String category, String city, String country) {
        this.id = id;
        this.nameCar = nameCar;
        this.price = price;
        this.year = year;
        this.category = category;
        this.city = city;
        this.country = country;
    }

    public OutputBookingCarDto() {
    }

    public OutputBookingCarDto(CarEntity car) {
        this.id = car.getId();
        this.nameCar = car.getNameCar();
        this.price = car.getPrice();
        this.year = car.getYear();
        this.category = car.getCategory().getQualification();
        this.city = car.getCities().getNameCity();
        this.country = car.getCities().getCountry();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
