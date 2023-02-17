package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.ProducerEntity;

public class ProducerDto {
    private String nameProducer;
    private String origin;

    public ProducerDto(String nameProducer, String origin) {
        this.nameProducer = nameProducer;
        this.origin = origin;
    }

    public ProducerDto() {
    }

    public ProducerDto(ProducerEntity producer) {
        this.nameProducer = producer.getNameProducer();
        this.origin = producer.getOrigin();
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

    public ProducerEntity toEntity(){
        ProducerEntity producer = new ProducerEntity();
        producer.setNameProducer(this.getNameProducer());
        producer.setOrigin(this.getOrigin());
        return producer;
    }
}
