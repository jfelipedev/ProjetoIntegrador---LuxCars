package projeto.integrador.equipe1.carrosluxo.Dto;

import jakarta.persistence.Column;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class CategoryDto {
    private String descritpion;

    private String urlImage;
    private String model;

    public CategoryDto(String descritpion, String urlImage, String model) {
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.model = model;
    }

    public CategoryDto() {
    }

    public CategoryDto(CategoryEntity category) {
        this.descritpion = category.getDescritpion();
        this.model = category.getModel();
        this.urlImage = category.getUrlImage();
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "descritpion='" + descritpion + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public CategoryEntity toEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setModel(this.getModel());
        categoryEntity.setDescritpion(this.getDescritpion());
        categoryEntity.setUrlImage(this.getUrlImage());
        return categoryEntity;
    }
}
