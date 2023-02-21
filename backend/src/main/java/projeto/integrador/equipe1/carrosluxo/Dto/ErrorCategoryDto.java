package projeto.integrador.equipe1.carrosluxo.Dto;

public class ErrorCategoryDto {
    String descritpion;
    String model;
    String urlImage;

    public ErrorCategoryDto() {
    }

    public ErrorCategoryDto(String descritpion, String model, String urlImage) {
        this.descritpion = descritpion;
        this.model = model;
        this.urlImage = urlImage;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "ErrorCategoryDto{" +
                "descritpion='" + descritpion + '\'' +
                ", model='" + model + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
