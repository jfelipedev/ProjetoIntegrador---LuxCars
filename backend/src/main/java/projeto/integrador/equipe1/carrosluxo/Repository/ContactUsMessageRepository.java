package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.ContactUsMessageEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoleEntity;

public interface ContactUsMessageRepository extends CrudRepository<ContactUsMessageEntity, Long> {
}