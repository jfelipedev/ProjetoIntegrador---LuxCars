package projeto.integrador.equipe1.carrosluxo.Dto.output.booking;

import projeto.integrador.equipe1.carrosluxo.Entity.BookingEntity;

import java.text.SimpleDateFormat;

public class OutputBookingDto {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private String startDate;
    private String startTime;
    private String endDate;
    private OutputBookingCarDto car;
    private OutputBookingUserDto user;

    public OutputBookingDto(String startDate, String startTime, String endDate, OutputBookingCarDto car, OutputBookingUserDto user) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.car = car;
        this.user = user;
    }

    public OutputBookingDto() {
    }

    public OutputBookingDto(BookingEntity bookingEntity) {
        this.startDate = dateFormat.format(bookingEntity.getStartDate());
        this.startTime = timeFormat.format(bookingEntity.getStartTime());
        this.endDate = dateFormat.format(bookingEntity.getEndDate());
        this.car = new OutputBookingCarDto(bookingEntity.getCar());
        this.user = new OutputBookingUserDto(bookingEntity.getUser());
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

    public OutputBookingCarDto getCar() {
        return car;
    }

    public void setCar(OutputBookingCarDto car) {
        this.car = car;
    }

    public OutputBookingUserDto getUser() {
        return user;
    }

    public void setUser(OutputBookingUserDto user) {
        this.user = user;
    }
}
