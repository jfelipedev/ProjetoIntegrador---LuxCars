package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findById(long id);

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
