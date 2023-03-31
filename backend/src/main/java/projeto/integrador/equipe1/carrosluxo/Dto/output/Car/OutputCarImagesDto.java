package projeto.integrador.equipe1.carrosluxo.Dto.output.Car;

import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

import java.util.Objects;

public class OutputCarImagesDto {
    private Long id;
    private String title;
    private String url;

    public OutputCarImagesDto() {
    }

    public OutputCarImagesDto(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public OutputCarImagesDto(ImagesEntity image) {
        this.id = image.getId();
        this.title = image.getTitle();
        if (Objects.equals(image.getUrl(), "")) {
            this.url = "Imagem ainda não foi inserida!";
        } else {
            this.url = image.getUrl();
        }
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
}
