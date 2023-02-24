package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class CategoryDto {
    private String descritpion;

    private String urlImage;
    private String qualification;

    public CategoryDto() {
    }

    public CategoryDto(String descritpion, String urlImage, String qualification) {
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
    }

    public CategoryDto(CategoryEntity categoryEntity) {
        this.setQualification(categoryEntity.getQualification());
        this.setUrlImage(categoryEntity.getUrlImage());
        this.setDescritpion(categoryEntity.getDescritpion());
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "descritpion='" + descritpion + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }

    public CategoryEntity toEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setQualification(this.getQualification());
        categoryEntity.setDescritpion(this.getDescritpion());
        categoryEntity.setUrlImage(this.getUrlImage());
        return categoryEntity;
    }
}
