package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;

@Entity(name = "caracteristics")
public class CaracteristicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name_caracteristcs")
    private String name;

    private String icon;

    public CaracteristicEntity() {
    }

    public CaracteristicEntity(long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public CaracteristicEntity(InputCaracteristicDto caracteristic) {
        this.icon = caracteristic.getIcon();
        this.name = caracteristic.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
