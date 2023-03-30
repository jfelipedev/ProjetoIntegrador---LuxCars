package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.booking.OutputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.ForbiddenException;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Service.BookingService;

@RestController
@Tag(name = "Booking", description = "Controle de reservas")
public class BookingController {
    Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarController carController;

    @PostMapping(value = "/booking")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputBookingDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ForbiddenException.class))}),
            @ApiResponse(responseCode = "403",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ForbiddenException.class))}),
    })
    @Operation(summary = "Registrar uma nova reserva", tags = {"Booking"})
    public ResponseEntity<?> create(@RequestBody InputBookingDto booking) throws Exception {
        logger.trace("Controle: CREATE / POST /booking");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(auth.getName()).get();
        OutputBookingDto bookingDto = bookingService.create(booking, userEntity.getId());
        carController.availabilitySave();
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/booking/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esta reserva foi deletada com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Esta reserva foi deletada com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para excluir está reserva! / Não foi possivel indetificar o usuario!",
                    content = {@Content}),
    })
    @Operation(summary = "Excluir uma reserva", tags = {"Booking"})
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /booking/{id}");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(auth.getName()).get();
        String retorno = bookingService.delete(userEntity.getId(), id);
        carController.availabilitySave();
        return new ResponseEntity<>(retorno, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/booking/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputBookingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Esta reserva foi deletada com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para excluir está reserva! / Não foi possivel indetificar o usuario!",
                    content = {@Content}),
    })
    @Operation(summary = "Ver uma reserva", tags = {"Booking"})
    public ResponseEntity<?> read(@PathVariable long id) throws Exception {
        logger.trace("Controle: READ / GET /booking/{id}");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(auth.getName()).get();
        return new ResponseEntity<>(bookingService.read(userEntity.getId(), id), HttpStatus.OK);
    }

    @GetMapping(value = "/booking")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputBookingDto.class)))}),
    })
    @Operation(summary = "Exibir todas as reservas", tags = {"Booking"})
    public ResponseEntity<?> all(@RequestParam(required = false) Long idUser) throws Exception {
        logger.trace("Controle: ALL / GET /booking");
        if (idUser == null) {
            return new ResponseEntity<>(bookingService.all(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookingService.allByIdUser(idUser), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/mybooking")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputBookingDto.class)))}),
            @ApiResponse(responseCode = "403", description = "Não foi possivel indetificar o usuario!",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir as minhas reservas", tags = {"Booking"})
    public ResponseEntity<?> allByUser() throws Exception {
        logger.trace("Controle: allByUser / GET /mybooking");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByEmail(auth.getName()).get();
        return new ResponseEntity<>(bookingService.allByIdUser(userEntity.getId()), HttpStatus.OK);
    }
}
