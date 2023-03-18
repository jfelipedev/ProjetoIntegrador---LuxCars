package projeto.integrador.equipe1.carrosluxo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.user.OutputRegisterAndLoginDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Security.JwtUtil;
import projeto.integrador.equipe1.carrosluxo.Service.UserService;
import projeto.integrador.equipe1.carrosluxo.Validation.UserValidation;


@RestController
@Tag(name = "User", description = "Controle de usuário")
public class UserController {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputRegisterAndLoginDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorLoginDto.class))})
    })
    @Operation(summary = "Entrar", tags = {"User"})
    public ResponseEntity<?> login(@RequestBody InputLoginDto login) throws Exception {
        logger.trace("Controle: LOGIN / POST /auth");
        new UserValidation(login);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            final UserEntity userEntity = userService.readByEmail(login.getEmail());
            final String jwt = jwtUtil.generateToken(userEntity);
            return new ResponseEntity<>(new OutputRegisterAndLoginDto(userRepository.findById(userEntity.getId()).get(), jwt), HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorLoginDto(null, "As credenciais inseridas são invalidas!")));
        }
    }

    @PostMapping(value = "/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutputRegisterAndLoginDto.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorRegisterDto.class))}),
    })
    @Operation(summary = "Registro", tags = {"User"})
    public ResponseEntity<?> register(@RequestBody InputRegisterDto register) throws Exception {
        logger.trace("Controle: REGISTER / POST /register");
        long id = userService.register(register);
        return new ResponseEntity<>(new OutputRegisterAndLoginDto(userRepository.findById(id).get(), jwtUtil.generateToken(userRepository.findById(id).get())), HttpStatus.CREATED);
    }
}
