package projeto.integrador.equipe1.carrosluxo.Dto.output.category;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

import java.util.Objects;

public class OutputCategoryCreateOrUpdateDto {
    private long id;
    private String descritpion;
    private String urlImage;
    private String qualification;

    public OutputCategoryCreateOrUpdateDto() {
    }

    public OutputCategoryCreateOrUpdateDto(long id, String descritpion, String urlImage, String qualification) {
        this.id = id;
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
    }

    public OutputCategoryCreateOrUpdateDto(CategoryEntity categoryEntity) {
        this.setId(categoryEntity.getId());
        this.setQualification(categoryEntity.getQualification());
        if (Objects.equals(categoryEntity.getUrlImage(), "")) {
            this.urlImage = "Imagem ainda não foi inserida!";
        } else {
            this.urlImage = categoryEntity.getUrlImage();
        }
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
}