package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;
import projeto.integrador.equipe1.carrosluxo.Dto.ProducerDto;

@Entity(name = "producers")
public class ProducerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_producer")
    private int id;

    @Column(name = "name_producer")
    private String nameProducer;

    private String origin;

    public ProducerEntity() {
    }

    public ProducerEntity(int id, String nameProducer, String origin) {
        this.id = id;
        this.nameProducer = nameProducer;
        this.origin = origin;
    }

    public ProducerEntity(ProducerDto producer){
        this.setOrigin(producer.getOrigin());
        this.setNameProducer(producer.getNameProducer());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ProducerEntity{" +
                "id=" + id +
                ", nameProducer='" + nameProducer + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
