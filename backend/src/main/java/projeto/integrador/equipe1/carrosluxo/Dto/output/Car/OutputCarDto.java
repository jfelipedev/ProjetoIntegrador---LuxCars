package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

import java.util.Objects;

public class OutputCarDto {
    Logger logger = LoggerFactory.getLogger(OutputCarDto.class);

    private long id;
    private String nameCar;

    private String descritpion;

    private Double price;

    private Integer year;

    private Boolean highlight;
    private OutputCarCategoryDto category;
    private OutputCarCityDto city;
    private String urlImage;

    public OutputCarDto() {
    }

    public OutputCarDto(long id, String nameCar, String descritpion, Double price, Integer year, Boolean highlight, OutputCarCategoryDto category, OutputCarCityDto city, String urlImage) {
        this.id = id;
        this.nameCar = nameCar;
        this.descritpion = descritpion;
        this.price = price;
        this.year = year;
        this.highlight = highlight;
        this.category = category;
        this.city = city;
        this.urlImage = urlImage;
    }

    public OutputCarDto(CarEntity car) {
        this.id = car.getId();
        this.nameCar = car.getNameCar();
        this.descritpion = car.getDescritpion();
        this.price = car.getPrice();
        this.year = car.getYear();
        this.highlight = car.getHighlight();
        this.category = new OutputCarCategoryDto(car.getCategory());
        this.city = new OutputCarCityDto(car.getCities());
        if (car.getImages().size() >= 1) {
            ImagesEntity image = (ImagesEntity) car.getImages().toArray()[0];
            this.urlImage = image.getUrl();
            if (Objects.equals(image.getUrl(), "")) {
                this.urlImage = "Imagem ainda não foi inserida!";
            } else {
                this.urlImage = image.getUrl();
            }
        } else {
            this.urlImage = "Este carro não tem imagem anexado";
        }
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

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public OutputCarCategoryDto getCategory() {
        return category;
    }

    public void setCategory(OutputCarCategoryDto category) {
        this.category = category;
    }

    public OutputCarCityDto getCity() {
        return city;
    }

    public void setCity(OutputCarCityDto city) {
        this.city = city;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
