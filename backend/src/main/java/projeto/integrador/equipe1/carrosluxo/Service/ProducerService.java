package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.ProducerDto;
import projeto.integrador.equipe1.carrosluxo.Entity.ProducerEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.ProducerRepository;

import java.util.List;

@Service
public class ProducerService {
    @Autowired
    public ProducerRepository producerRepository;

    public String create(ProducerDto producer){
        if(!producerRepository.existsByNameProducer(producer.getNameProducer())){
            producerRepository.save(producer.toEntity());
            return "O nome da fabricante foi cadastrado com sucesso!";
        }
        return "Esta fabricante já está cadastrado!";
    }

    public ProducerDto read(int id){
        //if(producerRepository.existsById(id)){
            return new ProducerDto(producerRepository.findById(id));
        //}
    }
    public String update(int id, ProducerDto producer){
        if(producerRepository.existsById(id)){
            ProducerEntity producerEntity = new ProducerEntity(producer);
            producerEntity.setId(id);
            producerRepository.save(producerEntity);
            return "Dados da fabricante foi atualizado!";
        }
        return "Fabricante não localizado!";
    }
    public String delete(int id){
        if(producerRepository.existsById(id)) {
            producerRepository.deleteById(id);
            return "Fabricante deletado!";
        }
        return "Fabricante não localizado!";
    }

    public List<ProducerEntity> all(){
        return (List<ProducerEntity>) producerRepository.findAll();
    }
}
