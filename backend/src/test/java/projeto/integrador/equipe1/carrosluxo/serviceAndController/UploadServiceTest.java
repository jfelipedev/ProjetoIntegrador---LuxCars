package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Service.UploadService;

import java.nio.file.Files;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UploadServiceTest {
    @Autowired
    UploadService uploadService;

    @Test
    void uploadFileValid() {
        Assertions.assertDoesNotThrow(() -> {
            ClassPathResource resource = new ClassPathResource("teste.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            Assertions.assertEquals("/test/ce089b77945c6519ad5d9b4fa408ed55.1.png", uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 1000));
            Assertions.assertEquals("Imagem removida!", uploadService.deleteFile("/test/ce089b77945c6519ad5d9b4fa408ed55.1.png"));
            Assertions.assertEquals("Imagem Não Existe!", uploadService.deleteFile("/test/ce089b77945c6519ad5d9b4fa408ed55.1.png"));
        });
    }

    @Test
    void uploadFileInvalidFileEmpty() {
        Assertions.assertEquals("Nenhum arquivo foi enviado!", Assertions.assertThrows(BadRequestException.class, () -> {
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = new byte[0];
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 1000);
        }).getMessage());
    }

    @Test
    void uploadFileInvalidMaxFile() {
        Assertions.assertEquals("O tamanho do arquivo excedeu o limite de 10 MB.", Assertions.assertThrows(BadRequestException.class, () -> {
            String filename = "teste.png";
            String contentType = "image/png";
            long maxFileSize = 10 * 1024 * 1024; // 10 MB
            byte[] content = new byte[(int) (maxFileSize + 1)];
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 1000);
            Mockito.verify(file, Mockito.atLeast(2)).getInputStream();
        }).getMessage());
    }

    @Test
    void uploadFileInvalidFileExtension() {
        Assertions.assertEquals("Apenas arquivos JPG, JPEG e PNG são permitidos.", Assertions.assertThrows(BadRequestException.class, () -> {
            ClassPathResource resource = new ClassPathResource("teste.png");
            String filename = "teste.gif";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 1000);
        }).getMessage());
    }

    @Test
    void uploadFileInvalidFileType() {
        Assertions.assertEquals("Arquivo inválido. Aceitamos somente imagens JPG, JPEG e PNG.", Assertions.assertThrows(BadRequestException.class, () -> {
            ClassPathResource resource = new ClassPathResource("teste.jpg");
            String filename = "teste.png";
            String contentType = "image/jpg";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 1000);
        }).getMessage());
    }

    @Test
    void uploadFileInvalidWidth() {
        Assertions.assertEquals("A largura da imagem deve estar entre 100 e 200 pixels.", Assertions.assertThrows(BadRequestException.class, () -> {
            ClassPathResource resource = new ClassPathResource("teste.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 200, 100, 1000);
        }).getMessage());
    }

    @Test
    void uploadFileInvalidHeight() {
        Assertions.assertEquals("A altura da imagem deve estar entre 100 e 200 pixels.", Assertions.assertThrows(BadRequestException.class, () -> {
            ClassPathResource resource = new ClassPathResource("teste.png");
            String filename = "teste.png";
            String contentType = "image/png";
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            MockMultipartFile file = new MockMultipartFile(filename, filename, contentType, content);
            uploadService.uploadFile(file, "test", 1L, 100, 1000, 100, 200);
        }).getMessage());
    }
}
