package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CustomerDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CustomerEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.InternalServerErrorException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerDto read(long id) throws Exception {
        if(customerRepository.existsById(id)){
            return new CustomerDto(customerRepository.findById(id));
        }
        throw new InternalServerErrorException("Não foi possivel localizar o usuário com id " + id);
    }

    public String register(RegisterDto register){
        if(!customerRepository.existsByEmail(register.getEmail())){
            customerRepository.save(register.toEntity());
        }
        return "Se o email não foi usado, então foi cadastrado com sucesso!";
    }

    public String login(LoginDto login) throws Exception {
        if(customerRepository.existsByEmail(login.getEmail())){
            CustomerEntity user = customerRepository.findByEmail(login.getEmail());
            if(user.getPassword().compareTo(login.getPassword().toString()) == 0){
                return "Acesso realizado com sucesso!";
            }
        }
        throw new BadRequestException("As crendeciais inseridas estão incorretas!");
    }
}
