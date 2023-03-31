package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.image.InputImageDto;

@Entity(name = "images")
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String title;
    private String url;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    public ImagesEntity() {
    }

    public ImagesEntity(Long id, String title, String url, CarEntity car) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.car = car;
    }

    public ImagesEntity(InputImageDto image) {
        this.title = image.getTitle();
        this.url = "";
        CarEntity car = new CarEntity();
        car.setId(image.getIdCar());
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
