package projeto.integrador.equipe1.carrosluxo.Dto.input.image;

public class InputImageDto {
    private String title;
    private Long idCar;

    public InputImageDto(String title, Long idCar) {
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

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }
}
