package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
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

    @PostMapping("/auth")
    @Operation(summary = "Entrar", tags = {"User"})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        final UserDetails userDetails =  userService.readByEmail(loginDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro", tags = {"User"})
    public String register(@RequestBody RegisterDto registerDto) throws Exception {
        return userService.register(registerDto);
    }
}
