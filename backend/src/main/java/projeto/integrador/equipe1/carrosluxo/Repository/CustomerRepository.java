package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findById(long id);
    CustomerEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
