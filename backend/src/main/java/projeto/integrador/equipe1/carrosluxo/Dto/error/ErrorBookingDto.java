package projeto.integrador.equipe1.carrosluxo.Dto.error;

public class ErrorBookingDto {
    private String startDate;
    private String startTime;
    private String endDate;
    private String idCar;
    private String idUser;

    public ErrorBookingDto(String startDate, String startTime, String endDate, String idCar, String idUser) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.idCar = idCar;
        this.idUser = idUser;
    }

    public ErrorBookingDto() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
