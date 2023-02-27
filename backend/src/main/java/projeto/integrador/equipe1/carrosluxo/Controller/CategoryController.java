package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CategoryFullDto> all() {
        return categoryService.all();
    }

    @PostMapping(value = "/category")
    @Operation(summary = "Registrar uma nova categoria", tags = {"Category"})
    public String create(@RequestBody CategoryDto categoryDto) throws Exception {
        return categoryService.create(categoryDto);
    }

    @GetMapping(value = "/category/{id}")
    @Operation(summary = "Exibir uma categoria especifica", tags = {"Category"})
    public CategoryDto read(@PathVariable int id) throws Exception {
        return categoryService.read(id);
    }

    @PutMapping(value = "/category/{id}")
    @Operation(summary = "Atualizar uma categoria especifica", tags = {"Category"})
    public String update(@PathVariable int id, @RequestBody CategoryDto categoryDto) throws Exception {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping(value = "/category/{id}")
    @Operation(summary = "Removerd uma categoria especifica", tags = {"Category"})
    public String delete(@PathVariable int id) throws Exception {
        return categoryService.delete(id);
    }
}
