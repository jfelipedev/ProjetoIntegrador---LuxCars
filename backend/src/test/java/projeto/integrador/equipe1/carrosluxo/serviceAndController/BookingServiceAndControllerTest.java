package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.booking.OutputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Entity.BookingEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ForbiddenException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.BookingRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Service.BookingService;
import projeto.integrador.equipe1.carrosluxo.Service.UserService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookingServiceAndControllerTest {
    Logger logger = LoggerFactory.getLogger(BookingServiceAndControllerTest.class);
    @Autowired
    CarRepository carRepository;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void all() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputBookingDto> list = bookingService.all();
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(2, list.get(0).getUser().getId());
        });
    }

    @Test
    void allByIdUserValid() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputBookingDto> list = bookingService.allByIdUser(2L);
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(2, list.get(0).getUser().getId());
        });
    }

    @Test
    void allByIdUserInvalid() {
        Assertions.assertEquals("Não foi possivel indetificar o usuario!", Assertions.assertThrows(ForbiddenException.class, () -> {
            bookingService.allByIdUser(5L);
        }).getMessage());
    }

    @Test
    void checkAvailabilityValid() {
        Assertions.assertDoesNotThrow(() -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(62, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(70, ChronoUnit.DAYS)));
        });
    }

    @Test
    void checkAvailabilityInvalidStart() {
        Assertions.assertEquals("{\"startDate\":\"Não pode reserva para antes do dia atual!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().minus(10, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(20, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidEnd() {
        Assertions.assertEquals("{\"startDate\":\"Não pode reserva para daqui um ano!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(400, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(450, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidbetween() {
        Assertions.assertEquals("{\"startDate\":null,\"startTime\":null,\"endDate\":\"A reserva só pode ser até 31 dias.\",\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(100, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(140, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidIntervalStart() {
        Assertions.assertEquals("{\"startDate\":\"Este intervalo de tempo está indisponivel!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(70, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(90, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidIntervalEnd() {
        Assertions.assertEquals("{\"startDate\":\"Este intervalo de tempo está indisponivel!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(90, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(100, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidIntervalInside() {
        Assertions.assertEquals("{\"startDate\":\"Este intervalo de tempo está indisponivel!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(90, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(92, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void checkAvailabilityInvalidIntervalOutside() {
        Assertions.assertEquals("{\"startDate\":\"Este intervalo de tempo está indisponivel!\",\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            CarEntity car = carRepository.findById(1L).get();
            bookingService.checkAvailability(car, Date.from(new Date().toInstant().plus(79, ChronoUnit.DAYS)), Date.from(new Date().toInstant().plus(96, ChronoUnit.DAYS)));
        }).getMessage());
    }

    @Test
    void createValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputBookingDto booking = bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            Assertions.assertEquals("joao@mail.com", booking.getUser().getEmail());
            Assertions.assertEquals("João", booking.getUser().getFirstName());
            Assertions.assertEquals("Silva", booking.getUser().getSurname());
            Assertions.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), booking.getStartDate());
            Assertions.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), booking.getEndDate());
            Assertions.assertEquals("08:00:00", booking.getStartTime());
            Assertions.assertEquals("Audi M6", booking.getCar().getNameCar());
        });
    }

    @Test
    void createInvalidUser() {
        Assertions.assertEquals("{\"startDate\":null,\"startTime\":null,\"endDate\":null,\"idCar\":null,\"idUser\":\"Não foi possivel indetificar o usuario!\"}", Assertions.assertThrows(ForbiddenException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 5L);
        }).getMessage());
    }

    @Test
    void createInvalidCar() {
        Assertions.assertEquals("{\"startDate\":null,\"startTime\":null,\"endDate\":null,\"idCar\":\"Este carro Não existir\",\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 5L), 1L);
        }).getMessage());
    }

    @Test
    void readValidAdmin() {
        Assertions.assertDoesNotThrow(() -> {
            OutputBookingDto booking = bookingService.read(1L, 1L);
            Assertions.assertEquals("joao2@mail.com", booking.getUser().getEmail());
            Assertions.assertEquals("João", booking.getUser().getFirstName());
            Assertions.assertEquals("Silva", booking.getUser().getSurname());
            Assertions.assertEquals("01/01/2023", booking.getStartDate());
            Assertions.assertEquals("25/01/2023", booking.getEndDate());
            Assertions.assertEquals("14:30:00", booking.getStartTime());
            Assertions.assertEquals("Audi M6", booking.getCar().getNameCar());
        });
    }

    @Test
    void readValidUser() {
        Assertions.assertDoesNotThrow(() -> {
            OutputBookingDto booking = bookingService.read(2L, 1L);
            Assertions.assertEquals("joao2@mail.com", booking.getUser().getEmail());
            Assertions.assertEquals("João", booking.getUser().getFirstName());
            Assertions.assertEquals("Silva", booking.getUser().getSurname());
            Assertions.assertEquals("01/01/2023", booking.getStartDate());
            Assertions.assertEquals("25/01/2023", booking.getEndDate());
            Assertions.assertEquals("14:30:00", booking.getStartTime());
            Assertions.assertEquals("Audi M6", booking.getCar().getNameCar());
        });
    }

    @Test
    void readInvalidIdUser() {
        Assertions.assertEquals("Não foi possivel indetificar o usuario!", Assertions.assertThrows(ForbiddenException.class, () -> {
            bookingService.read(3L, 1L);
        }).getMessage());
    }

    @Test
    void readInvalidIdBooking() {
        Assertions.assertEquals("Esta reserva não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookingService.read(2L, 5L);
        }).getMessage());
    }

    @Test
    void readInvalidOtherUser() {
        Assertions.assertEquals("Você não tem permissão para ver está reserva!", Assertions.assertThrows(ForbiddenException.class, () -> {
            userService.register(new InputRegisterDto("Teste", "teste", "teste@mail.com", "testeteste"));
            bookingService.read(3L, 1L);
        }).getMessage());
    }

    @Test
    void deleteValidAdmin() {
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals("Esta reserva foi deletada com sucesso!", bookingService.delete(1, 1L));
        });
    }

    @Test
    void deleteValidUser() {
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals("Esta reserva foi deletada com sucesso!", bookingService.delete(2, 1L));
        });
    }

    @Test
    void deleteInvalidIdUser() {
        Assertions.assertEquals("Não foi possivel indetificar o usuario!", Assertions.assertThrows(ForbiddenException.class, () -> {
            bookingService.delete(3L, 1L);
        }).getMessage());
    }

    @Test
    void deleteInvalidIdBooking() {
        Assertions.assertEquals("Esta reserva não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookingService.delete(2L, 5L);
        }).getMessage());
    }

    @Test
    void deleteInvalidOtherUser() {
        Assertions.assertEquals("Você não tem permissão para excluir está reserva!", Assertions.assertThrows(ForbiddenException.class, () -> {
            userService.register(new InputRegisterDto("Teste", "teste", "teste@mail.com", "testeteste"));
            bookingService.delete(3L, 1L);
        }).getMessage());
    }
    @Test
    void readAllAvailabilityCar(){
        Assertions.assertDoesNotThrow(() -> {
            CarEntity car = carRepository.findById(2L).get();
            List<Date[]> result = bookingService.readAllAvailabilityCar(car);
            Assertions.assertEquals(1,result.size());
            Assertions.assertEquals(2,result.get(0).length);
            Assertions.assertEquals(365, ChronoUnit.DAYS.between(result.get(0)[0].toInstant(),result.get(0)[1].toInstant()));
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(20, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(25, ChronoUnit.DAYS))), 2L), 1L);
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(50, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(60, ChronoUnit.DAYS))), 2L), 1L);
            result = bookingService.readAllAvailabilityCar(car);
            Assertions.assertEquals(3, result.size());
            Assertions.assertEquals(19, ChronoUnit.DAYS.between(result.get(0)[0].toInstant(),result.get(0)[1].toInstant()));
            Assertions.assertEquals(25, ChronoUnit.DAYS.between(result.get(1)[0].toInstant(),result.get(1)[1].toInstant()));
            Assertions.assertEquals(305, ChronoUnit.DAYS.between(result.get(2)[0].toInstant(),result.get(2)[1].toInstant()));
            bookingRepository.save(new BookingEntity(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(400, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(400, ChronoUnit.DAYS))), 1L), userRepository.findById(1L).get(), carRepository.findById(2L).get()));
            bookingService.readAllAvailabilityCar(car);
        });
    }
    @Test
    void checkDayAvailabilityTest(){
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(true, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(20,ChronoUnit.DAYS))));
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(15, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(30, ChronoUnit.DAYS))), 1L), 1L);
            Assertions.assertEquals(true, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(20,ChronoUnit.DAYS))));
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(15, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(30, ChronoUnit.DAYS))), 2L), 1L);
            Assertions.assertEquals(false, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(20,ChronoUnit.DAYS))));
            HashSet<Long> id = new HashSet<>();
            id.add(1L);
            carRepository.save(new CarEntity(new InputCarDto("TesteCar", "Carro de teste", 250.0, 2015, Boolean.FALSE, 1,1, id)));
            Assertions.assertEquals(true, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(20,ChronoUnit.DAYS))));
            Assertions.assertEquals(false, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(-20,ChronoUnit.DAYS))));
            Assertions.assertEquals(false, bookingService.checkDayAvailability(Date.from(new Date().toInstant().plus(400,ChronoUnit.DAYS))));
        });
    }

    @Test
    void readAllAvailability() {
        Assertions.assertDoesNotThrow(() -> {
            List<Date[]> list = bookingService.readAllAvailability();
            Assertions.assertEquals(1,list.size());
            Assertions.assertEquals(365, ChronoUnit.DAYS.between(list.get(0)[0].toInstant(),list.get(0)[1].toInstant()));
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(80, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(95, ChronoUnit.DAYS))), 1L), 1L);
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(100, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(105, ChronoUnit.DAYS))), 2L), 1L);
            List<Date[]> list1 = bookingService.readAllAvailability();
            Assertions.assertEquals(1,list1.size());
            Assertions.assertEquals(365, ChronoUnit.DAYS.between(list1.get(0)[0].toInstant(),list1.get(0)[1].toInstant()));
            bookingService.create(new InputBookingDto(new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(100, ChronoUnit.DAYS))), "08:00:00", new SimpleDateFormat("dd/MM/yyyy").format(Date.from(new Date().toInstant().plus(110, ChronoUnit.DAYS))), 1L), 1L);
            List<Date[]> list2 = bookingService.readAllAvailability();
            Assertions.assertEquals(2,list2.size());
            Assertions.assertEquals(99, ChronoUnit.DAYS.between(list2.get(0)[0].toInstant(),list2.get(0)[1].toInstant()));
            Assertions.assertEquals(260, ChronoUnit.DAYS.between(list2.get(1)[0].toInstant(),list2.get(1)[1].toInstant()));
            HashSet<Long> id = new HashSet<>();
            id.add(1L);
            CarEntity car = carRepository.save(new CarEntity(new InputCarDto("TesteCar", "Carro de teste", 250.0, 2015, Boolean.FALSE, 1,1, id)));
            List<Date[]> list4 = bookingService.readAllAvailabilityCar(car);
            Assertions.assertEquals(1,list4.size());
            Assertions.assertEquals(365, ChronoUnit.DAYS.between(list4.get(0)[0].toInstant(),list4.get(0)[1].toInstant()));
            List<Date[]> list3 = bookingService.readAllAvailability();
            Assertions.assertEquals(1,list3.size());
            Assertions.assertEquals(365, ChronoUnit.DAYS.between(list3.get(0)[0].toInstant(),list3.get(0)[1].toInstant()));
        });
    }
}
