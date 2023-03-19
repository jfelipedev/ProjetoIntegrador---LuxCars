package projeto.integrador.equipe1.carrosluxo.Dto.input.category;

public class InputCategoryDto {
    private String descritpion;
    private String urlImage;
    private String qualification;

    public InputCategoryDto(String descritpion, String urlImage, String qualification) {
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
    }

    public InputCategoryDto() {
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
