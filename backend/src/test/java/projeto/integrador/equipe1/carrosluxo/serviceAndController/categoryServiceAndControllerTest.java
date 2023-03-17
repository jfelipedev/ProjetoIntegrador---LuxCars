package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.CategoryController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class categoryServiceAndControllerTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryController categoryController;

    @Test
    void createValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCategoryCreateOrUpdateDto category = categoryService.create(new InputCategoryDto("Carros SUVs","http://img.carlux.com/suvs.png","survs"));
            Assertions.assertEquals(2, category.getId());
        });
    }

    @Test
    void createInvalid() {
        Assertions.assertEquals("{\"descritpion\":null,\"qualification\":\"Esta categoria já está cadastrado!\",\"urlImage\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.create(new InputCategoryDto("Carros sem o teto", "http://teste.com/teste.png", "Conversivel"));
        }).getMessage());
    }

    //service
    //createValid
    //createInvalid
    //update
    //read
    //delete
    //all
    //controller
    //all
    //create
    //update
    //read
    //delete
}
