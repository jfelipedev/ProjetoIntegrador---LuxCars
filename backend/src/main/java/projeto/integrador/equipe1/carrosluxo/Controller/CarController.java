package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CarFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Service.CarService;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Car", description = "Carros")

public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/car")
    @Operation(summary = "Exibir lista de todas as carros", tags = {"Car"})
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(carService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/car")
    @Operation(summary = "Registrar uma novo carro", tags = {"Car"})
    public ResponseEntity<?> create(@RequestBody CarDto carDto) throws Exception {
        return new ResponseEntity<>(carService.create(carDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/car/{id}")
    @Operation(summary = "Exibir um carro especifico", tags = {"Car"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(carService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/car/{id}")
    @Operation(summary = "Atualizar um carro especificp", tags = {"Car"})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CarDto carDto) throws Exception {
        return new ResponseEntity<>(carService.update(id, carDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/{id}")
    @Operation(summary = "Remover uma carro especificp", tags = {"Car"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(carService.delete(id), HttpStatus.NOT_FOUND);
    }
}
