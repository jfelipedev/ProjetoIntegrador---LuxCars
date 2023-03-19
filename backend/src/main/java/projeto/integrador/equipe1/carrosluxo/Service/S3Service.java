package projeto.integrador.equipe1.carrosluxo.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Configuraton.MD5Generator;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class S3Service {
    Logger logger = LoggerFactory.getLogger(S3Service.class);

    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName = "";

    @Autowired
    private MD5Generator md5Generator;

    @Autowired
    public S3Service(@Value("${cloud.aws.s3.endpoint}") String endpoint,
                     @Value("${cloud.aws.credentials.accessKey}") String accessKey,
                     @Value("${cloud.aws.credentials.secretKey}") String secretKey,
                     @Value("$isAwsS3") String isAwsS3) {
        if (isAwsS3 == "True") {
            amazonS3 = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-east-1"))
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                    .build();
        } else {
            amazonS3 = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-east-1"))
                    .withPathStyleAccessEnabled(true)
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                    .build();
        }
    }

    public String uploadFile(MultipartFile file, String path) throws IOException, NoSuchAlgorithmException {
        logger.trace("OriginalFilename: " + file.getName());
        String fileName = md5Generator.generate(file) + "." + StringUtils.getFilenameExtension(file.getName());
        logger.trace("File: " + fileName);
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        file.transferTo(tempFile);
        logger.trace("Bucket: " + bucketName);
        amazonS3.putObject(new PutObjectRequest(bucketName, path + "/" + fileName, tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucketName, path + "/" + fileName).toString();
    }

    public void deleteFile(String key) {
        amazonS3.deleteObject(bucketName, key);
    }

    public S3Object getObject(String urlObject) {
        return amazonS3.getObject(bucketName, urlObject);
    }

    public boolean doesObjectExist(String urlObject) {
        return amazonS3.doesObjectExist(bucketName, urlObject);
    }
}

