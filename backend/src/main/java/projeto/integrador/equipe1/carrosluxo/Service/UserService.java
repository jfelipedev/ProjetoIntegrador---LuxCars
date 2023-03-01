package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterFullDto;
import projeto.integrador.equipe1.carrosluxo.Dto.UserDto;
import projeto.integrador.equipe1.carrosluxo.Dto.UserFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.InternalServerErrorException;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.UserValidation;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserDto read(long id) throws Exception {
        if (userRepository.existsById(id)) {
            return new UserDto(userRepository.findById(id));
        }
        throw new InternalServerErrorException("Não foi possivel localizar o usuário com id " + id);
    }

    public UserEntity readByEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            return userRepository.findByEmail(email);
        }
        throw new InternalServerErrorException("Não foi possivel localizar o usuário com email: " + email);
    }

    public long register(RegisterDto register) throws Exception {
        new UserValidation(register);
        register.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        if (userRepository.existsByEmail(register.getEmail())) {
           throw new BadRequestException("Este email já está em uso!");
        }
        userRepository.save(register.toEntity());
        return userRepository.findByEmail(register.getEmail()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
