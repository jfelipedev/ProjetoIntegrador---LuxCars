package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_car")
    private int id;
    @Column(name = "name_car")
    private String nameCar;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
