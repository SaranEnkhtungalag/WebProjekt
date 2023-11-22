package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.Category;
import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;


@RestController
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    private List<CategoryEntity> categories;

    // the constructor can be deleted if we don't want to define an already existing categories
    public CategoryRestController(CategoryService categoryService) {
        //categories = new ArrayList<>();
        //categories.add(new Category(1L,"Lebensmittel"));
        //categories.add(new Category(2L, "Reinigunsmittel"));
        //categories.add(new Category(3L, "Kleidung"));

        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        // method to add default categories

        // Check if categories already exist
        if (!categoryService.existsByName("Lebensmittel")) {
            categoryService.save(new CategoryEntity(1L,"Lebensmittel"));
        }

        if (!categoryService.existsByName("Reinigungsmittel")) {
            categoryService.save(new CategoryEntity(2L,"Reinigungsmittel"));
        }

        if (!categoryService.existsByName("Kleidung")) {
            categoryService.save(new CategoryEntity(3L,"Kleidung"));
        }

    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable Long id) {
        CategoryEntity category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }


    @PostMapping("/categories")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
        CategoryEntity createdCategory = categoryService.save(category);
        return ResponseEntity.ok(createdCategory);
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity updatedCategory) {
        CategoryEntity updatedCategoryResult = categoryService.update(id, updatedCategory);
        return ResponseEntity.ok(updatedCategoryResult);
    }

    /*
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
     */

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            CategoryEntity existingCategory = categoryService.findById(id);

            if (existingCategory == null) {
                // Category with the specified ID does not exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Category with ID " + id + " doesn't exist");
            }

            categoryService.delete(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (RuntimeException e) {
            // Handle other runtime exceptions if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Category with ID " + id + " doesn't exist");
        }
    }



    @DeleteMapping("/categories/deleteAll")
    // to delete all categories
    public ResponseEntity<String> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return ResponseEntity.ok("All categories deleted successfully");
    }



}
