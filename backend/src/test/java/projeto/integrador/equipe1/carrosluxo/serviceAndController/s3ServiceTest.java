package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Service.S3Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class s3ServiceTest {
    @Autowired
    private S3Service s3Service;

    @Test
    public void testUploadFileAndDeleteFile() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            String objectfile = "/test/65a8e27d8879283831b664bd8b7f0ad4.2.txt";
            String content = "Hello, World!";
            String filename = "teste.txt";
            String contentType = "text/plain";
            MultipartFile file = new MockMultipartFile(filename, filename, contentType, content.getBytes(StandardCharsets.UTF_8));
            String url = s3Service.uploadFile(file, "test", 2L);
            Assertions.assertEquals(objectfile, url);
            S3Object object = s3Service.getObject(objectfile);
            String objectContent = IOUtils.toString(object.getObjectContent(), StandardCharsets.UTF_8);
            Assertions.assertEquals(objectContent, content);
            s3Service.deleteFile(objectfile);
            Assertions.assertFalse(s3Service.doesObjectExist(objectfile));
        });
    }
}
