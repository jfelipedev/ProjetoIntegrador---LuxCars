package projeto.integrador.equipe1.carrosluxo.Dto.output.error;

public class ErrorCategoryDto {
    String descritpion;
    String qualification;
    String urlImage;

    public ErrorCategoryDto(String descritpion, String qualification, String urlImage) {
        this.descritpion = descritpion;
        this.qualification = qualification;
        this.urlImage = urlImage;
    }

    public ErrorCategoryDto() {
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
