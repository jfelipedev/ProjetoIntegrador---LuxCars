package projeto.integrador.equipe1.carrosluxo.Dto.error;

public class ErrorCategoryDto {
    String descritpion;
    String qualification;

    public ErrorCategoryDto(String descritpion, String qualification) {
        this.descritpion = descritpion;
        this.qualification = qualification;
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
}
