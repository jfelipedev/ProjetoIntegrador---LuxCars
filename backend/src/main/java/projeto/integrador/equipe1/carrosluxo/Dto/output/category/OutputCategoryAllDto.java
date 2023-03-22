package projeto.integrador.equipe1.carrosluxo.Dto.output.category;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

import java.util.Objects;

public class OutputCategoryAllDto {
    private long id;
    private String qualification;
    private String urlImage;
    private int numberCars;

    public OutputCategoryAllDto() {
    }

    public OutputCategoryAllDto(long id, String qualification, String urlImage, int numberCars) {
        this.id = id;
        this.qualification = qualification;
        this.urlImage = urlImage;
        this.numberCars = numberCars;
    }

    public OutputCategoryAllDto(CategoryEntity category, int numberCars) {
        this.id = category.getId();
        this.qualification = category.getQualification();
        if (Objects.equals(category.getUrlImage(), "")) {
            this.urlImage = "Imagem ainda não foi inserida!";
        } else {
            this.urlImage = category.getUrlImage();
        }
        this.numberCars = numberCars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getNumberCars() {
        return numberCars;
    }

    public void setNumberCars(int numberCars) {
        this.numberCars = numberCars;
    }
}