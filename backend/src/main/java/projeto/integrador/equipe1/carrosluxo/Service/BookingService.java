package projeto.integrador.equipe1.carrosluxo.Service;

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
import projeto.integrador.equipe1.carrosluxo.Validation.BookingValidation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(BookingService.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public OutputBookingDto create(InputBookingDto inputBookingDto, Long idUser) throws Exception {
        if (userRepository.existsById(idUser)) {
            new BookingValidation(inputBookingDto);
            if (carRepository.existsById(inputBookingDto.getIdCar())) {
                CarEntity car = carRepository.findById(inputBookingDto.getIdCar()).get();
                checkAvailability(car, dateFormat.parse(inputBookingDto.getStartDate()), dateFormat.parse(inputBookingDto.getEndDate()));
                UserEntity user = userRepository.findById(idUser).get();
                return new OutputBookingDto(bookingRepository.save(new BookingEntity(inputBookingDto, user, car)));
            } else {
                ErrorBookingDto error = new ErrorBookingDto(null, null, null, "Este carro Não existir", null);
                throw new BadRequestException(objectMapper.writeValueAsString(error));
            }
        } else {
            ErrorBookingDto error = new ErrorBookingDto(null, null, null, null, "Não foi possivel indetificar o usuario!");
            throw new ForbiddenException(objectMapper.writeValueAsString(error));
        }
    }

    public String delete(long idUser, Long idBooking) throws Exception {
        if (userRepository.existsById(idUser)) {
            if (userRepository.existsById(idBooking)) {
                UserEntity user = userRepository.findById(idUser).get();
                if (user.getRole().getRoleName() == UserRoles.ROLE_ADMIN) {
                    bookingRepository.deleteById(idBooking);
                    logger.info("A reserva com a id " + idBooking + " foi deletado!");
                    return "Esta reserva foi deletada com sucesso!";
                } else {
                    BookingEntity booking = bookingRepository.findById(idBooking).get();
                    if (booking.getUser().getId() == idUser) {
                        bookingRepository.deleteById(idBooking);
                        logger.info("A reserva com a id " + idBooking + " foi deletado!");
                        return "Esta reserva foi deletada com sucesso!";
                    } else {
                        throw new ForbiddenException("Você não tem permissão para excluir está reserva!");
                    }
                }
            } else {
                throw new ResourceNotFoundException("Esta reserva não está registrado!");
            }
        } else {
            throw new ForbiddenException("Não foi possivel indetificar o usuario!");
        }
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

    public List<OutputBookingDto> allByIdUser(Long idUser) throws Exception {
        if (userRepository.existsById(idUser)) {
            logger.trace("Todos as reservas do usuario do id" + idUser + " foram exibidas!");
            List<OutputBookingDto> list = new ArrayList();
            UserEntity user = userRepository.findById(idUser).get();
            for (BookingEntity booking : bookingRepository.findAllByUser(user).get()) {
                list.add(new OutputBookingDto(booking));
            }
            return list;
        } else {
            throw new ForbiddenException("Não foi possivel indetificar o usuario!");
        }
    }

    public void checkAvailability(CarEntity car, Date dateStart, Date dateEnd) throws Exception {
        if (dateStart.before(new Date())) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Não pode reserva para antes do dia atual!", null, null, null, null)));
        }
        Date deadline = Date.from(new Date().toInstant().plus(365, ChronoUnit.DAYS));
        if (dateStart.after(deadline)) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Não pode reserva para daqui um ano!", null, null, null, null)));
        }
        if (ChronoUnit.DAYS.between(dateStart.toInstant(), dateEnd.toInstant()) > 31) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto(null, null, "A reserva só pode ser até 31 dias.", null, null)));
        }
        List<BookingEntity> bookings = List.of(bookingRepository.findAllByCar(car).get());
        for (BookingEntity booking : bookings) {
            Date start = booking.getStartDate();
            Date end = booking.getEndDate();
            if (dateStart.before(end) && dateEnd.after(start)) {
                throw new BadRequestException(objectMapper.writeValueAsString(new ErrorBookingDto("Este intervalo de tempo está indisponivel!", null, null, null, null)));
            }
        }
    }

    public List<Date[]> readAllAvailabilityCar(CarEntity car) {
        List<BookingEntity> bookings = new ArrayList<>(List.of(bookingRepository.findAllByCar(car).orElse(new ArrayList<>().toArray(new BookingEntity[0]))));
        Date today = new Date();
        Date deadline = Date.from(today.toInstant().plus(365, ChronoUnit.DAYS));
        List<Date[]> availableIntervals = new ArrayList<>();
        if (bookings.isEmpty()) {
            Date[] interval = {today, deadline};
            availableIntervals.add(interval);
            return availableIntervals;
        }
        bookings.sort(Comparator.comparing(BookingEntity::getStartDate));
        Date nextAvailableStart = today;
        for (BookingEntity booking : bookings) {
            Date bookingStartDate = booking.getStartDate();
            Date bookingEndDate = booking.getEndDate();

            if (bookingStartDate.after(deadline)) {
                break;
            }
            if (nextAvailableStart.before(bookingStartDate)) {
                Date[] availableInterval = {nextAvailableStart, bookingStartDate};
                availableIntervals.add(availableInterval);
            }
            nextAvailableStart = bookingEndDate.after(today) ? bookingEndDate : today;
        }
        if (nextAvailableStart.before(deadline)) {
            Date[] lastAvailableInterval = {nextAvailableStart, deadline};
            availableIntervals.add(lastAvailableInterval);
        }
        return availableIntervals;
    }

    public List<Date[]> readAllAvailability() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate deadline = tomorrow.plusYears(1);
        List<Date[]> interval = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        LocalDate currentDate = tomorrow;
        while (currentDate.isBefore(deadline)) {
            logger.info(currentDate.toString());
            if (checkDayAvailability(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                dates.add(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else if (!dates.isEmpty()) {
                interval.add(new Date[]{dates.get(0), Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())});
                dates.clear();
            }
            currentDate = currentDate.plusDays(1);
        }
        if (currentDate.isEqual(deadline)) {
            interval.add(new Date[]{dates.get(0), dates.get(dates.size() - 1)});
        }
        return interval;
    }

    public boolean checkDayAvailability(Date date) {
        if (date.before(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            return false;
        }
        if (date.after(Date.from(LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            return false;
        }
        List<BookingEntity> bookings = (List<BookingEntity>) bookingRepository.findAll();
        List<CarEntity> cars = (List<CarEntity>) carRepository.findAll();
        for (CarEntity car : cars) {
            boolean available = true;
            for (BookingEntity booking : bookings) {
                if (booking.getCar().equals(car) && date.compareTo(booking.getStartDate()) >= 0 && date.compareTo(booking.getEndDate()) <= 0) {
                    available = false;
                    break;
                }
            }
            if (available) {
                return true;
            }
        }
        return false;
    }
}