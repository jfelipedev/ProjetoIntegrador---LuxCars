package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;

import java.util.Set;

@Entity(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_category")
    private long id;
    private String descritpion;

    @Column(name = "url_image")
    private String urlImage;
    private String model;

    @OneToMany(mappedBy="category")
    private Set<CarEntity> cars;

    public CategoryEntity() {
    }

    public CategoryEntity(long id, String descritpion, String urlImage, String model) {
        this.id = id;
        this.descritpion = descritpion;
        this.urlImage = urlImage;
        this.model = model;
    }

    public CategoryEntity(CategoryDto category){
        this.descritpion = category.getDescritpion();
        this.model = category.getModel();
        this.urlImage = category.getUrlImage();
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
