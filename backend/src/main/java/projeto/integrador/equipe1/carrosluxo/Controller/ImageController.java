package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.image.InputImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageReadDto;
import projeto.integrador.equipe1.carrosluxo.Service.ImageService;

@RestController
@Tag(name = "Image", description = "Controle de Imagens")

public class ImageController {
    Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputImageDto.class)))}),
    })
    @Operation(summary = "Exibir lista de todas as imagens", tags = {"Image"})
    public ResponseEntity<?> all() {
        logger.trace("Controle: ALL / GET /image");
        return new ResponseEntity<>(imageService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/image")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputImageCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorImageDto.class))}),
    })
    @Operation(summary = "Registrar uma nova imagem", tags = {"Image"})
    public ResponseEntity<?> create(@RequestBody InputImageDto image) throws Exception {
        logger.trace("Controle: CREATE / POST /image");
        return new ResponseEntity<>(imageService.create(image), HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputImageReadDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta imagem não está registrada!",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir uma imagem especifica", tags = {"Image"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        logger.trace("Controle: READ / GET /image/{id}");
        return new ResponseEntity<>(imageService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/image/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputImageCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorImageDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta imagem não está registrada!",
                    content = {@Content}),
    })
    @Operation(summary = "Atualizar uma imagem especifica", tags = {"Image"})
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InputImageDto image) throws Exception {
        logger.trace("Controle: UPDATE / PUT /image/{id}");
        return new ResponseEntity<>(imageService.update(id, image), HttpStatus.OK);
    }

    @DeleteMapping(value = "/image/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esta imagem foi deletado com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Esta imagem não está registrado!",
                    content = {@Content}),
    })
    @Operation(summary = "Remover uma imagem especifica", tags = {"Image"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /image/{id}");
        return new ResponseEntity<>(imageService.delete(id), HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/image/{id}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não existir está imagem!",
                    content = {@Content}),
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCategoryReadDto.class))}),
    })
    @Operation(summary = "Mudar a imagem", tags = {"Image"})
    public ResponseEntity<?> upload(@PathVariable Long id, @Schema(type = "string", format = "binary") @RequestParam("file") MultipartFile file) throws Exception {
        logger.trace("Controle: UPLOAD / POST /image/{id}/upload");
        return new ResponseEntity<>(imageService.upload(id, file), HttpStatus.OK);
    }
}
