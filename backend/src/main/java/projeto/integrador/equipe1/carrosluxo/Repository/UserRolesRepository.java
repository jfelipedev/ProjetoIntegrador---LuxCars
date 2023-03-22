package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoleEntity;

public interface UserRolesRepository extends CrudRepository<UserRoleEntity, Long> {
}
