package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findById(int id);
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
