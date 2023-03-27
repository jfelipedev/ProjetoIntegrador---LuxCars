package projeto.integrador.equipe1.carrosluxo.Dto.input.booking;

public class InputBookingDto {
    private String startDate;
    private String startTime;
    private String endDate;
    private Long idCar;

    public InputBookingDto(String startDate, String startTime, String endDate, Long idCar) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.idCar = idCar;
    }

    public InputBookingDto() {
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

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }
}
