package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.ProducerEntity;

public interface ProducerRepository extends CrudRepository<ProducerEntity, Integer> {
    ProducerEntity findById(int id);
    boolean existsByNameProducer(String nameProducer);
}
