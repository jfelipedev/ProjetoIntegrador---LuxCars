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
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.error.ErrorCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Service.CaracteristicService;

@RestController
@Tag(name = "Caracteristic", description = "Controle de Características")

public class CaracteristicController {
    Logger logger = LoggerFactory.getLogger(CaracteristicController.class);
    @Autowired
    private CaracteristicService caracteristicService;

    @GetMapping(value = "/caracteristic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputCaracteristicDto.class)))}),
    })
    @Operation(summary = "Exibir lista de todas as características", tags = {"Caracteristic"})
    public ResponseEntity<?> all() {
        logger.trace("Controle: ALL / GET /caracteristic");
        return new ResponseEntity<>(caracteristicService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/caracteristic")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCaracteristicCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCaracteristicDto.class))}),
    })
    @Operation(summary = "Registrar uma nova característica", tags = {"Caracteristic"})
    public ResponseEntity<?> create(@RequestBody InputCaracteristicDto caracteristic) throws Exception {
        logger.trace("Controle: CREATE / POST /car");
        return new ResponseEntity<>(caracteristicService.create(caracteristic), HttpStatus.CREATED);
    }

    @GetMapping(value = "/caracteristic/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCaracteristicReadDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta caracteristica não está registrado!",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir uma característica especifica", tags = {"Caracteristic"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        logger.trace("Controle: READ / GET /caracteristic/{id}");
        return new ResponseEntity<>(caracteristicService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/caracteristic/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCaracteristicCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCaracteristicDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta caracteristica não está registrado!",
                    content = {@Content}),
    })
    @Operation(summary = "Atualizar uma característica especifica", tags = {"Caracteristic"})
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InputCaracteristicDto caracteristic) throws Exception {
        logger.trace("Controle: UPDATE / PUT /caracteristic/{id}");
        return new ResponseEntity<>(caracteristicService.update(id, caracteristic), HttpStatus.OK);
    }

    @DeleteMapping(value = "/caracteristic/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esta caracteristica foi deleto com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Esta caracteristica não está registrado!",
                    content = {@Content}),
    })
    @Operation(summary = "Remover uma característica especifica", tags = {"Caracteristic"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /caracteristic/{id}");
        return new ResponseEntity<>(caracteristicService.delete(id), HttpStatus.NO_CONTENT);
    }
}
