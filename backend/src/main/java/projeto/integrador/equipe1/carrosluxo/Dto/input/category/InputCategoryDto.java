package projeto.integrador.equipe1.carrosluxo.Dto.input.category;

public class InputCategoryDto {
    private String descritpion;
    private String qualification;

    public InputCategoryDto(String descritpion, String qualification) {
        this.descritpion = descritpion;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
