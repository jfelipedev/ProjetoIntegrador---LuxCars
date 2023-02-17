package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.UserDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserDto read(int id){
        //if(userRepository.existsById(id)){
            return new UserDto(userRepository.findById(id));
        //}
    }

    public String register(RegisterDto register){
        if(!userRepository.existsByEmail(register.getEmail())){
            userRepository.save(register.toEntity());
            return "Usuário foi cadastrado com sucesso!";
        }
        return "Não foi possivel registrar, este usuário!";
    }

    public String login(LoginDto login){
        if(userRepository.existsByEmail(login.getEmail())){
            UserEntity user = userRepository.findByEmail(login.getEmail());
            if(user.getPassword().compareTo(login.getPassword().toString()) == 0){
                return "Acesso realizado com sucesso!";
            }
        }
        return "As crendeciais inseridas estão incorretas!";
    }
}
