package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.InternalServerErrorException;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.UserValidation;

@Service
public class UserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserEntity readByEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email).get()) {
            return userRepository.findByEmail(email).get();
        }
        throw new InternalServerErrorException("Não foi possivel localizar o usuário com email: " + email);
    }

    public long register(InputRegisterDto register) throws Exception {
        new UserValidation(register);
        register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        if (userRepository.existsByEmail(register.getEmail()).get()) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorRegisterDto(null, null, "Este email já está em uso", null)));
        }
        userRepository.save(new UserEntity(register));
        return userRepository.findByEmail(register.getEmail()).get().getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }
}
