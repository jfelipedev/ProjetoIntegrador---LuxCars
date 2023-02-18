package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CustomerDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CustomerEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerDto read(long id){
        //if(userRepository.existsById(id)){
            return new CustomerDto(customerRepository.findById(id));
        //}
    }

    public String register(RegisterDto register){
        if(!customerRepository.existsByEmail(register.getEmail())){
            customerRepository.save(register.toEntity());
            return "Usuário foi cadastrado com sucesso!";
        }
        return "Não foi possivel registrar, este usuário!";
    }

    public String login(LoginDto login){
        if(customerRepository.existsByEmail(login.getEmail())){
            CustomerEntity user = customerRepository.findByEmail(login.getEmail());
            if(user.getPassword().compareTo(login.getPassword().toString()) == 0){
                return "Acesso realizado com sucesso!";
            }
        }
        return "As crendeciais inseridas estão incorretas!";
    }
}
