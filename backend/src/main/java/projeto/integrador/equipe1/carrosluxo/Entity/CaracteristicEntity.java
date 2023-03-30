package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "caracteristics")
public class CaracteristicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name_caracteristcs")
    private String name;

    private String icon;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @OneToMany(mappedBy = "caracteristic")
    private Set<CarCaracteristicEntity> carCaracteristic = new HashSet<>();

    public CaracteristicEntity(long id, String name, String icon, String unitOfMeasurement, Set<CarCaracteristicEntity> carCaracteristic) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.unitOfMeasurement = unitOfMeasurement;
        this.carCaracteristic = carCaracteristic;
    }

    public CaracteristicEntity() {
    }

    public CaracteristicEntity(InputCaracteristicDto caracteristic) {
        this.icon = "";
        this.name = caracteristic.getName();
        this.unitOfMeasurement = caracteristic.getUnitOfMeasurement();
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

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Set<CarCaracteristicEntity> getCarCaracteristic() {
        return carCaracteristic;
    }

    public void setCarCaracteristic(Set<CarCaracteristicEntity> carCaracteristic) {
        this.carCaracteristic = carCaracteristic;
    }
}
