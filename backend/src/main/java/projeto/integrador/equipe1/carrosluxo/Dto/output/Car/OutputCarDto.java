package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

public class OutputCarDto {
    private long id;
    private String nameCar;

    private String descritpion;

    private Boolean highlight;
    private OutputCarCategoryDto category;
    private OutputCarCityDto city;
    private String urlImage;

    public OutputCarDto() {
    }

    public OutputCarDto(long id, String nameCar, String descritpion, Boolean highlight, OutputCarCategoryDto category, OutputCarCityDto city, String urlImage) {
        this.id = id;
        this.nameCar = nameCar;
        this.descritpion = descritpion;
        this.highlight = highlight;
        this.category = category;
        this.city = city;
        this.urlImage = urlImage;
    }

    public OutputCarDto(CarEntity car) {
        this.id = car.getId();
        this.nameCar = car.getNameCar();
        this.descritpion = car.getDescritpion();
        this.highlight = car.getHighlight();
        this.category = new OutputCarCategoryDto(car.getCategory());
        this.city = new OutputCarCityDto(car.getCities());
        if (car.getImages().size() <= 1) {
            ImagesEntity image = (ImagesEntity) car.getImages().toArray()[0];
            this.urlImage = image.getUrl();
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
