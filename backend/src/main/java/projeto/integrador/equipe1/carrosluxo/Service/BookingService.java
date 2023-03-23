package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.booking.OutputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Entity.BookingEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoles;
import projeto.integrador.equipe1.carrosluxo.Exception.ForbiddenException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.BookingRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    Logger logger = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

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

    //ver disponibilidade de um carro
    //ver disponibilidade de todos os carros

}
