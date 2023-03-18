package projeto.integrador.equipe1.carrosluxo.Dto.input.image;

public class InputImageDto {
    private String title;
    private String url;
    private long idCar;

    public InputImageDto(String title, String url, long idCar) {
        this.title = title;
        this.url = url;
        this.idCar = idCar;
    }

    public InputImageDto() {
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

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }
}
