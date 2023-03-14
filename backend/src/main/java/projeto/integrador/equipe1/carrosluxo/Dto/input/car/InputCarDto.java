package projeto.integrador.equipe1.carrosluxo.Dto.input.car;

import java.util.HashSet;
import java.util.Set;

public class InputCarDto {
    private String nameCar;

    private String descritpion;

    private Double price;

    private Integer year;

    private Boolean highlight;
    private long idCategory;
    private long idCity;
    private Set<Long> idCaracteristics = new HashSet<>();

    public InputCarDto() {
    }

    public InputCarDto(String nameCar, String descritpion, Double price, Integer year, Boolean highlight, long idCategory, long idCity, Set<Long> idCaracteristics) {
        this.nameCar = nameCar;
        this.descritpion = descritpion;
        this.price = price;
        this.year = year;
        this.highlight = highlight;
        this.idCategory = idCategory;
        this.idCity = idCity;
        this.idCaracteristics = idCaracteristics;
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

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public long getIdCity() {
        return idCity;
    }

    public void setIdCity(long idCity) {
        this.idCity = idCity;
    }

    public Set<Long> getIdCaracteristics() {
        return idCaracteristics;
    }

    public void setIdCaracteristics(Set<Long> idCaracteristics) {
        this.idCaracteristics = idCaracteristics;
    }
}
