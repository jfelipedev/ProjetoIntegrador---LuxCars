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
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.error.ErrorCityDto;
import projeto.integrador.equipe1.carrosluxo.Service.CityService;

@RestController
@Tag(name = "City", description = "Controle de Cidades")

public class CityController {
    Logger logger = LoggerFactory.getLogger(CityController.class);
    @Autowired
    private CityService cityService;

    @GetMapping(value = "/city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputCityDto.class)))}),
    })
    @Operation(summary = "Exibir lista de todas as cidades", tags = {"City"})
    public ResponseEntity<?> all() {
        logger.trace("Controle: ALL / GET /city");
        return new ResponseEntity<>(cityService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/city")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCityCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCityDto.class))}),
    })
    @Operation(summary = "Registrar uma nova cidade", tags = {"City"})
    public ResponseEntity<?> create(@RequestBody InputCityDto city) throws Exception {
        logger.trace("Controle: CREATE / POST /car");
        return new ResponseEntity<>(cityService.create(city), HttpStatus.CREATED);
    }

    @GetMapping(value = "/city/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCityReadDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta cidade não está registrada!",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir uma cidade especifica", tags = {"City"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        logger.trace("Controle: READ / GET /city/{id}");
        return new ResponseEntity<>(cityService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/city/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCityCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCityDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta cidade não está registrada!",
                    content = {@Content}),
    })
    @Operation(summary = "Atualizar uma cidade especifica", tags = {"City"})
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InputCityDto city) throws Exception {
        logger.trace("Controle: UPDATE / PUT /city/{id}");
        return new ResponseEntity<>(cityService.update(id, city), HttpStatus.OK);
    }

    @DeleteMapping(value = "/city/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esta cidade foi deletado com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Esta cidade não está registrado!",
                    content = {@Content}),
    })
    @Operation(summary = "Remover uma cidade especifica", tags = {"City"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /city/{id}");
        return new ResponseEntity<>(cityService.delete(id), HttpStatus.NO_CONTENT);
    }
}
