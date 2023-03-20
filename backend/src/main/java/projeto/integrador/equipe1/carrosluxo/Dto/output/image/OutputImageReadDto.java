package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

public class OutputImageReadDto {
    private String title;
    private String url;
    private OutputImageCarDto car;

    public OutputImageReadDto() {
    }

    public OutputImageReadDto(String title, String url, OutputImageCarDto car) {
        this.title = title;
        this.url = url;
        this.car = car;
    }

    public OutputImageReadDto(ImagesEntity images) {
        this.title = images.getTitle();
        if (images.getUrl() == "") {
            this.url = "Imagem ainda não foi inserida!";
        } else {
            this.url = images.getUrl();
        }
        this.car = new OutputImageCarDto(images.getCar());
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
