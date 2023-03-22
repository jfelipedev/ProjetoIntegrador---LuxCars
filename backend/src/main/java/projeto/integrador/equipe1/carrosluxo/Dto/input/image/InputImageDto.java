package projeto.integrador.equipe1.carrosluxo.Dto.input.image;

public class InputImageDto {
    private String title;
    private long idCar;

    public InputImageDto(String title, long idCar) {
        this.title = title;
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

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }
}
