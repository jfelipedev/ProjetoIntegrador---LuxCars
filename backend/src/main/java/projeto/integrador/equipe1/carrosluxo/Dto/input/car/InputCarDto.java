package projeto.integrador.equipe1.carrosluxo.Dto.input.car;

import java.util.HashSet;

public class InputCarDto {
    private String nameCar;
    private String descritpion;
    private Double price;
    private Integer year;
    private Boolean highlight;
    private Long idCategory;
    private Long idCity;
    private HashSet<InputCarCaracteristicDTO> inputCarCaracteristicDTO = new HashSet<>();

    public InputCarDto(String nameCar, String descritpion, Double price, Integer year, Boolean highlight, Long idCategory, Long idCity, HashSet<InputCarCaracteristicDTO> inputCarCaracteristicDTO) {
        this.nameCar = nameCar;
        this.descritpion = descritpion;
        this.price = price;
        this.year = year;
        this.highlight = highlight;
        this.idCategory = idCategory;
        this.idCity = idCity;
        this.inputCarCaracteristicDTO = inputCarCaracteristicDTO;
    }

    public InputCarDto() {
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

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public HashSet<InputCarCaracteristicDTO> getInputCarCaracteristicDTO() {
        return inputCarCaracteristicDTO;
    }

    public void setInputCarCaracteristicDTO(HashSet<InputCarCaracteristicDTO> inputCarCaracteristicDTO) {
        this.inputCarCaracteristicDTO = inputCarCaracteristicDTO;
    }
}
