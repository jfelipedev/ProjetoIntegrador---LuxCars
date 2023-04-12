package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.contactUsMessage.InputContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.contactUsMessage.OutputContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Entity.ContactUsMessageEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.ContactUsMessageRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.ContactUsMessageValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsMessageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);
    @Autowired
    private ContactUsMessageRepository contactUsMessageRepository;
    public OutputContactUsMessageDto create(InputContactUsMessageDto message) throws Exception {
        new ContactUsMessageValidation(message);
        return new OutputContactUsMessageDto(contactUsMessageRepository.save(new ContactUsMessageEntity(message)));
    }
    public List<OutputContactUsMessageDto> all() {
        logger.trace("Todas as messagens foram exibidas!");
        List<OutputContactUsMessageDto> list = new ArrayList();
        for (ContactUsMessageEntity message : contactUsMessageRepository.findAll()) {
            list.add(new OutputContactUsMessageDto(message));
        }
        return list;
    }
}
