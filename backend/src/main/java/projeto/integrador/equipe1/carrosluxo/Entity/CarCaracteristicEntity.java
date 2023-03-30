package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;

@Entity(name = "car_caracteristics")
public class CarCaracteristicEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "caracteristics_id")
    private CaracteristicEntity caracteristic;

    @Column(name = "value_caracteristic")
    private String value;

    public CarCaracteristicEntity(Long id, CarEntity car, CaracteristicEntity caracteristic, String value) {
        this.id = id;
        this.car = car;
        this.caracteristic = caracteristic;
        this.value = value;
    }

    public CarCaracteristicEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public CaracteristicEntity getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(CaracteristicEntity caracteristic) {
        this.caracteristic = caracteristic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CarCaracteristicEntity{" +
                "id=" + id +
                ", car=" + car +
                ", caracteristic=" + caracteristic +
                ", value='" + value + '\'' +
                '}';
    }
}
