package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

import java.util.Objects;

public class OutputImageCreateOrUpdateDto {
    private Long id;
    private String title;
    private String url;
    private OutputImageCarDto car;

    public OutputImageCreateOrUpdateDto() {
    }

    public OutputImageCreateOrUpdateDto(Long id, String title, String url, OutputImageCarDto car) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.car = car;
    }

    public OutputImageCreateOrUpdateDto(ImagesEntity image) {
        this.id = image.getId();
        this.title = image.getTitle();
        if (Objects.equals(image.getUrl(), "")) {
            this.url = "Imagem ainda não foi inserida!";
        } else {
            this.url = image.getUrl();
        }
        this.car = new OutputImageCarDto(image.getCar());
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

    public OutputImageCarDto getCar() {
        return car;
    }

    public void setCar(OutputImageCarDto car) {
        this.car = car;
    }
}
