package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.UserController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.InternalServerErrorException;
import projeto.integrador.equipe1.carrosluxo.Service.UserService;

import java.util.NoSuchElementException;

@SpringBootTest
@Transactional

public class userServiceAndControllerTest {
    @Autowired
    UserService userService;

    @Autowired
    UserController userController;

    @Test
    void readByEmailValid() {
        Assertions.assertDoesNotThrow( () -> {
            UserEntity userEntity = userService.readByEmail("joao@mail.com");
            Assertions.assertEquals(1,userEntity.getId());
        });
    }

    @Test
    void readByEmailInvalid() {
        Assertions.assertEquals("Não foi possivel localizar o usuário com email: joao3@mail.com", Assertions.assertThrows(InternalServerErrorException.class, () -> {
            UserEntity userEntity = userService.readByEmail("joao3@mail.com");
        }).getMessage());
    }

    @Test
    void registerEmailValid() {
        Assertions.assertDoesNotThrow( () -> {
            Long id = userService.register(new InputRegisterDto("Teste", "Teste", "teste@mail.com", "testeteste"));
            Assertions.assertEquals(3, id);
        });
    }

    @Test
    void registerEmailInvalid() {
        Assertions.assertEquals("{\"firstName\":null,\"surname\":null,\"email\":\"Este email já está em uso\",\"password\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            userService.register(new InputRegisterDto("Teste", "Teste", "joao@mail.com", "testeteste"));
        }).getMessage());
    }

    @Test
    void loadUserByUsernameValid() {
        UserEntity userEntity = (UserEntity) userService.loadUserByUsername("joao@mail.com");
        Long id = userEntity.getId();
        Assertions.assertEquals(1,id);
    }

    @Test
    void loadUserByUsernameInvalid() {
        Assertions.assertEquals("No value present", Assertions.assertThrows(NoSuchElementException.class, () -> {
            userService.loadUserByUsername("joao4@mail.com");
        }).getMessage());
    }

    @Test
    void loginValid(){
        Assertions.assertDoesNotThrow(()->{
            userController.login(new InputLoginDto("joao@mail.com", "12345678"));
        });
    }

    @Test
    void loginInvalid(){
        Assertions.assertEquals("{\"email\":null,\"password\":\"As credenciais inseridas são invalidas!\"}", Assertions.assertThrows(BadRequestException.class, ()->{
            userController.login(new InputLoginDto("joao4@mail.com", "12345678"));
        }).getMessage());
    }

    @Test
    void registerValid(){
        Assertions.assertDoesNotThrow(()->{
            userController.register(new InputRegisterDto("Maria", "Maria Maria", "mariamaria@maria.com","mariamaria"));
        });
    }

    @Test
    void registerInvalid(){
        Assertions.assertEquals("{\"firstName\":null,\"surname\":null,\"email\":\"Este email já está em uso\",\"password\":null}", Assertions.assertThrows(BadRequestException.class, ()->{
            userController.register(new InputRegisterDto("João", "Da silva", "joao@mail.com", "12345678"));
        }).getMessage());
    }
}
