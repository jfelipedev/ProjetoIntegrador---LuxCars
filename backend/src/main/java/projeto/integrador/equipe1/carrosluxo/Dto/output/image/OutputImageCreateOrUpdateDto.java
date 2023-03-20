package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

public class OutputImageCreateOrUpdateDto {
    private long id;
    private String title;
    private String url;
    private OutputImageCarDto car;

    public OutputImageCreateOrUpdateDto() {
    }

    public OutputImageCreateOrUpdateDto(long id, String title, String url, OutputImageCarDto car) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.car = car;
    }

    public OutputImageCreateOrUpdateDto(ImagesEntity image) {
        this.id = image.getId();
        this.title = image.getTitle();
        if (image.getUrl() == "") {
            this.url = "Imagem ainda não foi inserida!";
        } else {
            this.url = image.getUrl();
        }
        this.car = new OutputImageCarDto(image.getCar());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
