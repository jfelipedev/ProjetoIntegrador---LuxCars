package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;

import java.util.Set;

@Entity(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private String descritpion;

    @Column(name = "url_image")
    private String urlImage;
    private String qualification;

    @OneToMany(mappedBy = "category")
    private Set<CarEntity> cars;

    public CategoryEntity() {
    }

    public CategoryEntity(long id, String descritpion, String urlImage, String qualification, Set<CarEntity> cars) {
        this.id = id;
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.qualification = qualification;
        this.cars = cars;
    }

    public CategoryEntity(InputCategoryDto category) {
        this.descritpion = category.getDescritpion();
        this.qualification = category.getQualification();
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

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }
}
