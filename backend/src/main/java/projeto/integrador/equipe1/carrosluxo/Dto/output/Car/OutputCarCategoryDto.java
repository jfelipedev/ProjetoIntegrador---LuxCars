package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public class OutputCarCategoryDto {
    private long id;
    private String qualification;

    public OutputCarCategoryDto() {
    }

    public OutputCarCategoryDto(long id, String qualification) {
        this.id = id;
        this.qualification = qualification;
    }

    public OutputCarCategoryDto(CategoryEntity category) {
        this.id = category.getId();
        this.qualification = category.getQualification();
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
}
