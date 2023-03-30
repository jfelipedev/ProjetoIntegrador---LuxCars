package projeto.integrador.equipe1.carrosluxo.Service;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final int MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    @Autowired
    private S3Service s3Service;

    public UploadService() {
    }


    public String uploadFile(MultipartFile file, String path, Long id, int minWidth, int maxWidth, int minHeight, int maxHeight) throws Exception {
        if (file.isEmpty()) {
            throw new BadRequestException("Nenhum arquivo foi enviado!");
        }
        byte[] part = new byte[MAX_FILE_SIZE];
        InputStream inputStream = file.getInputStream();
        int bytesRead = inputStream.read(part, 0, MAX_FILE_SIZE);
        if (inputStream.read() != -1) {
            throw new BadRequestException("O tamanho do arquivo excedeu o limite de 10 MB.");
        }
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new BadRequestException("Apenas arquivos JPG, JPEG e PNG são permitidos.");
        }
        Tika tika = new Tika();
        String fileType = tika.detect(file.getBytes());
        if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
            throw new BadRequestException("Arquivo inválido. Aceitamos somente imagens JPG, JPEG e PNG.");
        }
        BufferedImage image = ImageIO.read(file.getInputStream());
        int width = image.getWidth();
        int height = image.getHeight();
        if (width < minWidth || width > maxWidth) {
            throw new BadRequestException(String.format("A largura da imagem deve estar entre %d e %d pixels.", minWidth, maxWidth));
        }
        if (height < minHeight || height > maxHeight) {
            throw new BadRequestException(String.format("A altura da imagem deve estar entre %d e %d pixels.", minHeight, maxHeight));
        }
        return s3Service.uploadFile(file, path, id);
    }

    public String deleteFile(String url) {
        if (s3Service.doesObjectExist(url)) {
            s3Service.deleteFile(url);
            return "Imagem removida!";
        } else {
            return "Imagem Não Existe!";
        }
    }
}

