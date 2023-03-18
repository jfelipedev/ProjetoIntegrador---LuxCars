package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.CityController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityReadDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Service.CityService;

import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class cityServiceAndControllerTest {
    @Autowired
    CityService cityService;

    @Autowired
    CityController cityController;

    @Test
    void createValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCityCreateOrUpdateDto city = cityService.create(new InputCityDto("Rio de Janeiro", "Brasil"));
            Assertions.assertEquals(3, city.getId());
        });
    }

    @Test
    void createInvalid() {
        Assertions.assertEquals("{\"nameCity\":\"Esta cidade já está cadastrada!\",\"country\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            cityService.create(new InputCityDto("Napoli", "Italia"));
        }).getMessage());
    }

    @Test
    void readValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCityReadDto city = cityService.read(1);
            Assertions.assertEquals("Napoli", city.getNameCity());
        });
    }

    @Test
    void readInvalid() {
        Assertions.assertEquals("Esta cidade não está registrada!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cityService.read(10);
        }).getMessage());
    }

    @Test
    void updateValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCityCreateOrUpdateDto city = cityService.update(1, new InputCityDto("Rio de Janeiro", "Italia"));
            Assertions.assertEquals("Rio de Janeiro", city.getNameCity());
            Assertions.assertEquals("Italia", city.getCountry());
            city = cityService.update(1, new InputCityDto("Rio de Janeiro", "Brasil"));
            Assertions.assertEquals("Rio de Janeiro", city.getNameCity());
            Assertions.assertEquals("Brasil", city.getCountry());
            city = cityService.update(1, new InputCityDto("Napoli", "Italia"));
            Assertions.assertEquals("Napoli", city.getNameCity());
            Assertions.assertEquals("Italia", city.getCountry());
        });
    }

    @Test
    void updateInvalidIdCity() {
        Assertions.assertEquals("Esta cidade não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cityService.update(10, new InputCityDto("Rio de Janeiro", "Brasil"));
        }).getMessage());
    }

    @Test
    void updateInvalidNameCity() {
        Assertions.assertEquals("{\"nameCity\":\"Já tem o nome registrado nessa cidade!\",\"country\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            cityService.update(1, new InputCityDto("São Paulo", "Brasil"));
        }).getMessage());
    }

    @Test
    void deleteValid() {
        Assertions.assertDoesNotThrow(() -> {
            String image = cityService.delete(1);
            Assertions.assertEquals("Esta cidade foi deletado com sucesso!", image);
        });
    }

    @Test
    void deleteInvalid() {
        Assertions.assertEquals("Esta cidade não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cityService.delete(5);
        }).getMessage());
    }

    @Test
    void serviceAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCityDto> list = cityService.all();
            Assertions.assertEquals(2, list.size());
            Assertions.assertEquals(1, list.get(0).getId());
            Assertions.assertEquals(2, list.get(1).getId());
            Assertions.assertEquals("Napoli", list.get(0).getNameCity());
            Assertions.assertEquals("São Paulo", list.get(1).getNameCity());
        });
    }

    @Test
    void ControllerAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCityDto>> response = (ResponseEntity<List<OutputCityDto>>) cityController.all();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().get(0).getId());
            Assertions.assertEquals("Napoli", response.getBody().get(0).getNameCity());
        });
    }

    @Test
    void controllerCreateTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCityCreateOrUpdateDto> response = (ResponseEntity<OutputCityCreateOrUpdateDto>) cityController.create(new InputCityDto("São José", "Brasil"));
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(3, response.getBody().getId());
        });
    }

    @Test
    void controllerReadTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCityReadDto> response = (ResponseEntity<OutputCityReadDto>) cityController.read(1);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Napoli", response.getBody().getNameCity());
        });
    }

    @Test
    void controllerUpdateTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCityCreateOrUpdateDto> response = (ResponseEntity<OutputCityCreateOrUpdateDto>) cityController.update(1, new InputCityDto("Biguaçu", "Brasil"));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Biguaçu", response.getBody().getNameCity());
        });
    }

    @Test
    void controllerDeleteTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<String> response = (ResponseEntity<String>) cityController.delete(1);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertEquals("Esta cidade foi deletado com sucesso!", response.getBody());
        });
    }
}
