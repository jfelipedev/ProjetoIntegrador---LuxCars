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
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarReadDto;
import projeto.integrador.equipe1.carrosluxo.Service.CarService;

@RestController
@Tag(name = "Car", description = "Controle de Carros")

public class CarController {
    Logger logger = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarService carService;

    @GetMapping(value = "/car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputCarDto.class)))}),
            @ApiResponse(responseCode = "404", description = "Esta categoria não existir! | Esta cidade não existir!",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir lista de todas as carros", tags = {"Car"})
    public ResponseEntity<?> all(@RequestParam(required = false) Long idCategory, @RequestParam(required = false) Long idCity) throws Exception {
        logger.trace("Controle: ALL / GET /car");
        return new ResponseEntity<>(carService.all(idCategory, idCity), HttpStatus.OK);
    }

    @GetMapping(value = "/car/highlight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputCarDto.class)))}),
    })
    @Operation(summary = "Exibir lista de destaques do carros", tags = {"Car"})
    public ResponseEntity<?> allHighlight() throws Exception {
        logger.trace("Controle: ALL HIGHLIGHT / GET /car");
        return new ResponseEntity<>(carService.allHighlight(), HttpStatus.OK);
    }

    @PostMapping(value = "/car")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCarCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCarDto.class))}),
    })
    @Operation(summary = "Registrar uma novo carro", tags = {"Car"})
    public ResponseEntity<?> create(@RequestBody InputCarDto car) throws Exception {
        logger.trace("Controle: CREATE / POST /car");
        return new ResponseEntity<>(carService.create(car), HttpStatus.CREATED);
    }

    @GetMapping(value = "/car/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCarReadDto.class))}),
            @ApiResponse(responseCode = "404", description = "Este carro não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir um carro especifico", tags = {"Car"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        logger.trace("Controle: READ / GET /car/{id}");
        return new ResponseEntity<>(carService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/car/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCarCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCarDto.class))}),
            @ApiResponse(responseCode = "404", description = "Este carro não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Atualizar um carro especifico", tags = {"Car"})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InputCarDto car) throws Exception {
        logger.trace("Controle: UPDATE / PUT /car/{id}");
        return new ResponseEntity<>(carService.update(id, car), HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Este carro foi deletado com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Este carro não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Remover um carro especifico", tags = {"Car"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /car/{id}");
        return new ResponseEntity<>(carService.delete(id), HttpStatus.NO_CONTENT);
    }
}
