package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class CategoryFullDto {
    private long id;
    private String descritpion;

    private String urlImage;
    private String qualification;

    public CategoryFullDto() {
    }

    public CategoryFullDto(long id, String descritpion, String urlImage, String qualification) {
        this.id = id;
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
    }

    public CategoryFullDto(CategoryEntity categoryEntity) {
        this.setId(categoryEntity.getId());
        this.setQualification(categoryEntity.getQualification());
        this.setUrlImage(categoryEntity.getUrlImage());
        this.setDescritpion(categoryEntity.getDescritpion());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "CategoryFullDto{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }

    public CategoryEntity toEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(this.getId());
        categoryEntity.setQualification(this.getQualification());
        categoryEntity.setDescritpion(this.getDescritpion());
        categoryEntity.setUrlImage(this.getUrlImage());
        return categoryEntity;
    }
}
