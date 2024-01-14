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
@CrossOrigin(origins = "*")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    private List<CategoryEntity> categories;


    public CategoryRestController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        // method to add default categories

        // Check if categories already exist
        if (!categoryService.existsByName("Groceries")) {
            categoryService.save(new CategoryEntity(1L,"Groceries"));
        }

        if (!categoryService.existsByName("Cleaning-Supplies")) {
            categoryService.save(new CategoryEntity(2L,"Cleaning-Supplies"));
        }

        if (!categoryService.existsByName("Clothes")) {
            categoryService.save(new CategoryEntity(3L,"Clothes"));
        }

    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        try {
            CategoryEntity category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Category with ID " + id + " doesn't exist");
        }
    }


    @PostMapping("/categories")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
        CategoryEntity createdCategory = categoryService.save(category);
        return ResponseEntity.ok(createdCategory);
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity updatedCategory) {
        try {
            CategoryEntity updatedCategoryResult = categoryService.update(id, updatedCategory);
            return ResponseEntity.ok(updatedCategoryResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Category with ID " + id + " doesn't exist");
        }
    }


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
        return ResponseEntity.ok("All categories were deleted successfully");
    }


}
