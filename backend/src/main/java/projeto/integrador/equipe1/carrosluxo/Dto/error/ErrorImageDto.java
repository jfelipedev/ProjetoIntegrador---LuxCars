package projeto.integrador.equipe1.carrosluxo.Dto.error;

public class ErrorImageDto {
    private String title;
    private String url;
    private String car;

    public ErrorImageDto(String title, String url, String car) {
        this.title = title;
        this.url = url;
        this.car = car;
    }

    public ErrorImageDto() {
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
