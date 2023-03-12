package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<Boolean> existsByEmail(String email);
}
