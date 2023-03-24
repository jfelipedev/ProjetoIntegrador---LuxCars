package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.booking.OutputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Entity.BookingEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoles;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ForbiddenException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.BookingRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public OutputBookingDto create(InputBookingDto inputBookingDto) {
        return null;
    }

    public String delete(long id) throws Exception {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            logger.info("A reserva com a id " + id + " foi deletado!");
            return "Esta reserva foi deletada com sucesso!";
        }
        throw new ResourceNotFoundException("Esta reserva não está registrado!");
    }

    public OutputBookingDto read(long idUser, Long idBooking) throws Exception {
        if (userRepository.existsById(idUser)) {
            if (userRepository.existsById(idBooking)) {
                UserEntity user = userRepository.findById(idUser).get();
                if (user.getRole().getRoleName() == UserRoles.ROLE_ADMIN) {
                    logger.trace("A reserva com id + " + idBooking + " foi exibinda!");
                    return new OutputBookingDto(bookingRepository.findById(idBooking).get());
                } else {
                    BookingEntity booking = bookingRepository.findById(idBooking).get();
                    if (booking.getUser().getId() == idUser) {
                        logger.trace("A reserva com id + " + idBooking + " foi exibinda!");
                        return new OutputBookingDto(bookingRepository.findById(idBooking).get());
                    } else {
                        throw new ForbiddenException("Você não tem permissão para ver está reserva!");
                    }
                }
            } else {
                throw new ResourceNotFoundException("Esta reserva não está registrado!");
            }
        } else {
            throw new ForbiddenException("Não foi possivel indetificar o usuario!");
        }
    }

    public List<OutputBookingDto> all() {
        logger.trace("Todos as reservas foram exibidas!");
        List<OutputBookingDto> list = new ArrayList();
        for (BookingEntity booking : bookingRepository.findAll()) {
            list.add(new OutputBookingDto(booking));
        }
        return list;
    }

    public List<OutputBookingDto> allByIdUser(Long idUser) {
        logger.trace("Todos as reservas do usuario do id" + idUser + " foram exibidas!");
        List<OutputBookingDto> list = new ArrayList();
        UserEntity user = userRepository.findById(idUser).get();
        for (BookingEntity booking : bookingRepository.findAllByUser(user).get()) {
            list.add(new OutputBookingDto(booking));
        }
        return list;
    }

    public List<LocalDate[]> readAllAvailabilityCar(CarEntity car) {
        List<BookingEntity> bookings = List.of(bookingRepository.findAllByCar(car).get());
        LocalDate today = LocalDate.now();
        LocalDate deadline = today.plusYears(1);
        LocalDate dateAvailableStart = today;
        LocalDate dateAvailableEnd = null;
        List<LocalDate[]> availableIntervals = new ArrayList<>();

        for (BookingEntity booking : bookings) {
            if(booking.getEndDate().isBefore(today)){
                continue;
            }
            if (booking.getStartDate().isAfter(deadline)) {
                continue;
            }
            if (dateAvailableEnd == null) {
                dateAvailableEnd = booking.getStartDate();
            } else {
                if(booking.getStartDate().isAfter(dateAvailableEnd.plusDays(1))){
                    LocalDate[] intervalo = {dateAvailableStart, dateAvailableEnd};
                    availableIntervals.add(intervalo);
                    dateAvailableStart = dateAvailableEnd.plusDays(1);
                }
                dateAvailableEnd = booking.getStartDate();
            }
        }
        if(dateAvailableEnd == null || dateAvailableEnd.isBefore(deadline)){
            LocalDate[] interval = {dateAvailableStart,dateAvailableEnd};
            availableIntervals.add(interval);
        }
        return availableIntervals;
    }

    public void checkAvailability(CarEntity car, LocalDate dateStart, LocalDate dateEnd) throws Exception {
        if(dateStart.isBefore(LocalDate.now())){
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Não pode reserva para antes do dia atual!", null, null, null, null)));
        }
        LocalDate deadline = LocalDate.now().plusYears(1);
        if (dateStart.isAfter(deadline)) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Não pode reserva para daqui um ano!", null, null, null, null)));
        }
        if (ChronoUnit.DAYS.between(dateStart, dateEnd) > 31) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto(null, null, "Pode reserva até 31 dias.", null, null)));
        }
        List<BookingEntity> bookings = List.of(bookingRepository.findAllByCar(car).get());
        for (BookingEntity booking : bookings) {
            LocalDate start = booking.getStartDate();
            LocalDate end = booking.getEndDate();
            if (dateStart.isBefore(end) && dateEnd.isAfter(start)) {
                throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Este intervalo de tempo está indisponivel!", null, null, null, null)));
            }
        }
    }

    public List<LocalDate[]> readAllAvailability() {
        List<CarEntity> cars = (List<CarEntity>) carRepository.findAll();
        LocalDate today = LocalDate.now();
        LocalDate deadline = today.plusYears(1);
        List<LocalDate[]> availableIntervals = new ArrayList<>();
        for (CarEntity car : cars) {
            List<BookingEntity> bookings = List.of(bookingRepository.findAllByCar(car).get());
            LocalDate dateAvailableStart = today;
            LocalDate dateAvailableEnd = null;
            for (BookingEntity booking : bookings) {
                if(booking.getEndDate().isBefore(today)){
                    continue;
                }
                if (booking.getStartDate().isAfter(deadline)) {
                    continue;
                }
                if (dateAvailableEnd == null) {
                    dateAvailableEnd = booking.getStartDate().minusDays(1);
                } else {
                    LocalDate[] intervalo = {dateAvailableStart, dateAvailableEnd};
                    availableIntervals.add(intervalo);
                    dateAvailableStart = dateAvailableEnd.plusDays(1);
                }
                dateAvailableEnd = booking.getEndDate();
            }
            if(dateAvailableEnd == null || dateAvailableEnd.isBefore(deadline)){
                LocalDate[] interval = {dateAvailableStart, deadline};
                availableIntervals.add(interval);
            }
        }
        return availableIntervals;
    }
}
