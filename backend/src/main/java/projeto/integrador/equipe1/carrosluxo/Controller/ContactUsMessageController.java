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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.contactUsMessage.InputContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.contactUsMessage.OutputContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Service.ContactUsMessageService;

@RestController
@Tag(name = "Contact Us", description = "Controle de mensagem da partre entre em contato")
public class ContactUsMessageController {
    Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    private ContactUsMessageService contactUsMessageService;

    @PostMapping(value = "/contactUsMessage")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputContactUsMessageDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorContactUsMessageDto.class))}),
    })
    @Operation(summary = "Registrar uma nova imagem", tags = {"Contact us"})
    public ResponseEntity<?> create(@RequestBody InputContactUsMessageDto message) throws Exception {
        logger.trace("Controle: CREATE / POST /image");
        return new ResponseEntity<>(contactUsMessageService.create(message), HttpStatus.CREATED);
    }

    @GetMapping(value = "/contactUsMessage")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OutputContactUsMessageDto.class)))}),
    })
    @Operation(summary = "Exibir lista de todas as mensagens", tags = {"Contact us"})
    public ResponseEntity<?> all() {
        logger.trace("Controle: ALL / GET /image");
        return new ResponseEntity<>(contactUsMessageService.all(), HttpStatus.OK);
    }
}
