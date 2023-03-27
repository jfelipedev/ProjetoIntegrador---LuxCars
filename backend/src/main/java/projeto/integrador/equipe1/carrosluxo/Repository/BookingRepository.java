package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.BookingEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
    Optional<BookingEntity[]> findAllByUser(UserEntity user);

    Optional<BookingEntity[]> findAllByCar(CarEntity car);

    List<BookingEntity> findAllByOrderByStartDateAsc();
}
