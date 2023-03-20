package projeto.integrador.equipe1.carrosluxo.Dto.output.category;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class OutputCategoryReadDto {
    private String descritpion;
    private String urlImage;
    private String qualification;

    public OutputCategoryReadDto() {
    }

    public OutputCategoryReadDto(String descritpion, String urlImage, String qualification) {
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
    }

    public OutputCategoryReadDto(CategoryEntity categoryEntity) {
        this.setQualification(categoryEntity.getQualification());
        if (categoryEntity.getUrlImage() == "") {
            this.urlImage = "Imagem ainda não foi inserida!";
        } else {
            this.urlImage = categoryEntity.getUrlImage();
        }
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
}