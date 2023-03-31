package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.CaracteristicController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageReadDto;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Service.CaracteristicService;
import projeto.integrador.equipe1.carrosluxo.Service.UploadService;

import java.nio.file.Files;
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

    @Autowired
    private UploadService uploadService;

    @Test
    void ServiceCreateTest() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicCreateOrUpdateDto caracteristic = caracteristicService.create(new InputCaracteristicDto("Tem iceberg", null));
            Assertions.assertEquals(2, caracteristic.getId());
        });
    }

    @Test
    void readValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicReadDto caracteristic = caracteristicService.read(1L);
            Assertions.assertEquals("testeICon", caracteristic.getIcon());
        });
    }

    @Test
    void readInvalid() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.read(10L);
        }).getMessage());
    }

    @Test
    void updateValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCaracteristicCreateOrUpdateDto caracteristic = caracteristicService.update(1L, new InputCaracteristicDto("Tem iceberg",null));
            Assertions.assertEquals("Imagem ainda não foi inserida!", caracteristic.getIcon());
            Assertions.assertEquals("Tem iceberg", caracteristic.getName());
        });
    }

    @Test
    void updateInvalidIdCategory() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.update(10L, new InputCaracteristicDto("Tem iceberg", null));
        }).getMessage());
    }

    @Test
    void deleteValid() {
        Assertions.assertDoesNotThrow(() -> {
            String categoty = caracteristicService.delete(1L);
            Assertions.assertEquals("Esta caracteristica foi deletado com sucesso!", categoty);
        });
    }

    @Test
    void deleteInvalid() {
        Assertions.assertEquals("Esta caracteristica não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            caracteristicService.delete(10L);
        }).getMessage());
    }

    @Test
    void serviceAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCaracteristicDto> list = caracteristicService.all();
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(1, list.get(0).getId());
            Assertions.assertEquals("potência de 340 cv e torque de 44,1 m", list.get(0).getName());
        });
    }

    @Test
    void uploadInvalid() {
        Assertions.assertEquals("Não existir está caracteristica!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ClassPathResource resource = new ClassPathResource("testeicon.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            caracteristicService.upload(10L, file);
        }).getMessage());
    }

    @Test
    void uploadValid() {
        Assertions.assertDoesNotThrow(() -> {
            ClassPathResource resource = new ClassPathResource("testeicon.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            OutputCaracteristicReadDto image = caracteristicService.upload(1L, file);
            Assertions.assertEquals("/image/f458ead9929c22b7bdbb8f64575c06b2.1.png", image.getIcon());
            image = caracteristicService.upload(1L, file);
            Assertions.assertEquals("/image/f458ead9929c22b7bdbb8f64575c06b2.1.png", image.getIcon());
            uploadService.deleteFile("/image/f458ead9929c22b7bdbb8f64575c06b2.1.png");
        });
    }

    @Test
    void ControllerAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCaracteristicDto>> response = (ResponseEntity<List<OutputCaracteristicDto>>) caracteristicController.all();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().get(0).getId());
            Assertions.assertEquals("potência de 340 cv e torque de 44,1 m", response.getBody().get(0).getName());
        });
    }

    @Test
    void controllerCreateTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicCreateOrUpdateDto> response = (ResponseEntity<OutputCaracteristicCreateOrUpdateDto>) caracteristicController.create(new InputCaracteristicDto("Tem iceberg", null));
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(2, response.getBody().getId());
        });
    }

    @Test
    void controllerReadTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicReadDto> response = (ResponseEntity<OutputCaracteristicReadDto>) caracteristicController.read(1L);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("testeICon", response.getBody().getIcon());
        });
    }

    @Test
    void controllerUpdateTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCaracteristicCreateOrUpdateDto> response = (ResponseEntity<OutputCaracteristicCreateOrUpdateDto>) caracteristicController.update(1L, new InputCaracteristicDto("Tem iceberg", null));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Tem iceberg", response.getBody().getName());
        });
    }

    @Test
    void controllerDeleteTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<String> response = (ResponseEntity<String>) caracteristicController.delete(1L);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertEquals("Esta caracteristica foi deletado com sucesso!", response.getBody());
        });
    }

    @Test
    void controllerUploadTest() {
        Assertions.assertDoesNotThrow(() -> {
            ClassPathResource resource = new ClassPathResource("testeicon.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            ResponseEntity<OutputCaracteristicReadDto> response = (ResponseEntity<OutputCaracteristicReadDto>) caracteristicController.upload(1L, file);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("/image/f458ead9929c22b7bdbb8f64575c06b2.1.png", response.getBody().getIcon());
            uploadService.deleteFile("/image/f458ead9929c22b7bdbb8f64575c06b2.1.png");
        });
    }
}
