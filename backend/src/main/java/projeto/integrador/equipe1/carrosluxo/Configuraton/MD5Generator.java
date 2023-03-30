package projeto.integrador.equipe1.carrosluxo.Configuraton;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MD5Generator {

    public String generate(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream is = file.getInputStream();
        DigestInputStream dis = new DigestInputStream(is, md);
        byte[] buffer = new byte[1024];
        while (dis.read(buffer) != -1) ;
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

