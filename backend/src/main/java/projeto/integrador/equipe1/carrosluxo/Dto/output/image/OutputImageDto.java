package projeto.integrador.equipe1.carrosluxo.Dto.output.image;

import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;

public class OutputImageDto {
    private Long id;
    private String title;

    public OutputImageDto() {
    }

    public OutputImageDto(Long id, String title, String url, OutputImageCarDto car) {
        this.id = id;
        this.title = title;
    }

    public OutputImageDto(ImagesEntity images) {
        this.id = images.getId();
        this.title = images.getTitle();
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
}
