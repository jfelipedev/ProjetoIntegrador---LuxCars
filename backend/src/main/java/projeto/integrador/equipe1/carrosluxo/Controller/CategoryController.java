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
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryAllDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryReadDto;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

@RestController
@Tag(name = "Category", description = "Controle de Categoria")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputCategoryAllDto.class)))}),
    })
    @Operation(summary = "Exibir lista de todas as categorias", tags = {"Category"})
    public ResponseEntity<?> all() {
        logger.trace("Controle: ALL / GET /category");
        return new ResponseEntity<>(categoryService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/category")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCategoryCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCategoryDto.class))}),
    })
    @Operation(summary = "Registrar uma nova categoria", tags = {"Category"})
    public ResponseEntity<?> create(@RequestBody InputCategoryDto category) throws Exception {
        logger.trace("Controle: CREATE / POST /category");
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @GetMapping(value = "/category/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCategoryReadDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta categoria não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Exibir uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        logger.trace("Controle: READ / GET /category/{id}");
        return new ResponseEntity<>(categoryService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/category/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCategoryCreateOrUpdateDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorCategoryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Esta categoria não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Atualizar uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InputCategoryDto category) throws Exception {
        logger.trace("Controle: UPDATE / PUT /category/{id}");
        return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
    }

    @DeleteMapping(value = "/category/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esta categoria foi deletado com sucesso!",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Esta categoria não existir",
                    content = {@Content}),
    })
    @Operation(summary = "Remover uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        logger.trace("Controle: DELETE / DELETE /category/{id}");
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/category/{id}/upload")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não existir está categoria!",
                    content = {@Content}),
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputCategoryReadDto.class))}),
    })
    @Operation(summary = "Mudar de imagem da categoria", tags = {"Category"})
    public ResponseEntity<?> upload(@PathVariable Long id, @Schema(description = "Arquivo a ser enviado", type = "string", format = "binary") @RequestParam("file") MultipartFile file) throws Exception {
        logger.trace("Controle: UPDATE / POST /category/{id}/upload");
        return new ResponseEntity<>(categoryService.upload(id, file), HttpStatus.OK);
    }
}
