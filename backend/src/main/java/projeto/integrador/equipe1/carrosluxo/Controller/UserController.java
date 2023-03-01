package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.GetMeUserDto;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Security.AuthenticationResponse;
import projeto.integrador.equipe1.carrosluxo.Security.JwtUtil;
import projeto.integrador.equipe1.carrosluxo.Service.UserService;



@RestController
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/auth")
    @Operation(summary = "Entrar", tags = {"User"})
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            final UserDetails userDetails = userService.readByEmail(loginDto.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        catch (Exception e){
            throw new BadRequestException("As credenciais inseridas são invalidas");
        }
    }

    @PostMapping(value = "/register")
    @Operation(summary = "Registro", tags = {"User"})
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) throws Exception {
        long id = userService.register(registerDto);
        return new ResponseEntity<>(new RegisterFullDto(userRepository.findById(id), jwtUtil.generateToken(userRepository.findById(id))), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/getmeuser")
    @Operation(summary = "Exibir dados do proprio usurário", tags = {"User"})
    public ResponseEntity<?> getMeUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) auth.getPrincipal();
        return new ResponseEntity<>(new GetMeUserDto(userService.readByEmail(userEntity.getUsername())), HttpStatus.OK);
    }
}
