package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Service.CustomerService;

@RestController
@Tag(name = "Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/auth")
    @Operation(summary = "Entrar", tags = { "Customer" })
    String login(@RequestBody LoginDto loginDto) throws Exception {
        return customerService.login(loginDto);
    }
    @PostMapping("/register")
    @Operation(summary = "Registro", tags = { "Customer" })
    String register(@RequestBody RegisterDto registerDto) throws Exception {
        return customerService.register(registerDto);
    }
}
