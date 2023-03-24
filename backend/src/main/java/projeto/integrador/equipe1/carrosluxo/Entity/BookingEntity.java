package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private LocalDate startTime;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BookingEntity(long id, LocalDate startDate, LocalDate startTime, LocalDate endDate, CarEntity car, UserEntity user) {
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.car = car;
        this.user = user;
    }

    public BookingEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
