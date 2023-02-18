package projeto.integrador.equipe1.carrosluxo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Service.CustomerService;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/auth")
    String login(@RequestBody LoginDto loginDto) throws Exception {
        return customerService.login(loginDto);
    }
    @PostMapping("/register")
    String register(@RequestBody RegisterDto registerDto){
        return customerService.register(registerDto);
    }
}
