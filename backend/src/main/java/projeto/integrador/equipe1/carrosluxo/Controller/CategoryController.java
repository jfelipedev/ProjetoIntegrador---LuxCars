package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Category", description = "Categoria dos carros")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    @Operation(summary = "Exibir lista de todas as categorias", tags = {"Category"})
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(categoryService.all(), HttpStatus.OK);
    }

    @PostMapping(value = "/category")
    @Operation(summary = "Registrar uma nova categoria", tags = {"Category"})
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) throws Exception {
        return new ResponseEntity<>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/category/{id}")
    @Operation(summary = "Exibir uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> read(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(categoryService.read(id), HttpStatus.OK);
    }

    @PutMapping(value = "/category/{id}")
    @Operation(summary = "Atualizar uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CategoryDto categoryDto) throws Exception {
        return new ResponseEntity<>(categoryService.update(id, categoryDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/category/{id}")
    @Operation(summary = "Removerd uma categoria especifica", tags = {"Category"})
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.NOT_FOUND);
    }
}
