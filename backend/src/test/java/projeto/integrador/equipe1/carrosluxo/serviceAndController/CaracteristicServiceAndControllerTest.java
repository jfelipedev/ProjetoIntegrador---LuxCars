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
import projeto.integrador.equipe1.carrosluxo.Controller.CaracteristicController;
import projeto.integrador.equipe1.carrosluxo.Controller.CategoryController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryAllDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityReadDto;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Service.CaracteristicService;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CaracteristicServiceAndControllerTest {
    @Autowired
    CaracteristicService caracteristicService;

    @Autowired
    CaracteristicController caracteristicController;

    @Test
    void ServiceCreateTest() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicCreateOrUpdateDto caracteristic = caracteristicService.create(new InputCaracteristicDto("Tem iceberg", "Iceberg"));
            Assertions.assertEquals(2, caracteristic.getId());
        });
    }

    @Test
    void readValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicReadDto caracteristic = caracteristicService.read(1);
            Assertions.assertEquals("testeICon", caracteristic.getIcon());
        });
    }

    @Test
    void readInvalid() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.read(10);
        }).getMessage());
    }

    @Test
    void updateValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicCreateOrUpdateDto caracteristic = caracteristicService.update(1, new InputCaracteristicDto("Tem iceberg", "Iceberg"));
            Assertions.assertEquals("Iceberg", caracteristic.getIcon());
            Assertions.assertEquals("Tem iceberg", caracteristic.getName());
        });
    }

    @Test
    void updateInvalidIdCategory() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.update(10, new InputCaracteristicDto("Tem iceberg", "Iceberg"));
        }).getMessage());
    }

    @Test
    void deleteValid() {
        Assertions.assertDoesNotThrow(() -> {
            String categoty = caracteristicService.delete(1);
            Assertions.assertEquals("Esta caracteristica foi deletado com sucesso!", categoty);
        });
    }

    @Test
    void deleteInvalid() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.delete(10);
        }).getMessage());
    }

    @Test
    void serviceAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCaracteristicDto> list = caracteristicService.all();
            Assertions.assertEquals(1,list.size());
            Assertions.assertEquals(1,list.get(0).getId());
            Assertions.assertEquals("testeICon", list.get(0).getIcon());
        });
    }


    @Test
    void ControllerAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCaracteristicDto>> response = (ResponseEntity<List<OutputCaracteristicDto>>) caracteristicController.all();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().get(0).getId());
            Assertions.assertEquals("testeICon", response.getBody().get(0).getIcon());
        });
    }

    @Test
    void controllerCreateTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicCreateOrUpdateDto> response = (ResponseEntity<OutputCaracteristicCreateOrUpdateDto>) caracteristicController.create(new InputCaracteristicDto("Tem iceberg", "Iceberg"));
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(2, response.getBody().getId());
        });
    }

    @Test
    void controllerReadTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicReadDto> response = (ResponseEntity<OutputCaracteristicReadDto>) caracteristicController.read(1);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("testeICon", response.getBody().getIcon());
        });
    }

    @Test
    void controllerUpdateTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicCreateOrUpdateDto> response = (ResponseEntity<OutputCaracteristicCreateOrUpdateDto>) caracteristicController.update(1, new InputCaracteristicDto("Tem iceberg", "Iceberg"));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Iceberg", response.getBody().getIcon());
        });
    }

    @Test
    void controllerDeleteTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<String> response = (ResponseEntity<String>) caracteristicController.delete(1);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertEquals("Esta caracteristica foi deletado com sucesso!", response.getBody());
        });
    }
}
