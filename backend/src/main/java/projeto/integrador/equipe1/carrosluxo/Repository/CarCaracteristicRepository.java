package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CarCaracteristicEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;

import java.util.Optional;

public interface CarCaracteristicRepository extends CrudRepository<CarCaracteristicEntity, Long> {
    Optional<CarCaracteristicEntity[]> findAllByCar(CarEntity car);
}