package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
public class ItemRestController {

    private final ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemEntity>> getAllItems() {
        List<ItemEntity> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }


    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        try {
            ItemEntity item = itemService.findById(id);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }

    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        ItemEntity createdItem = itemService.save(item);
        return ResponseEntity.ok(createdItem);
    }


    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemEntity updatedItem) {
        try {
            ItemEntity updatedItemResult = itemService.update(id, updatedItem);
            return ResponseEntity.ok(updatedItemResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            ItemEntity existingItem = itemService.findById(id);

            if (existingItem == null) {
                // Item with the specified ID does not exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Item with ID " + id + " doesn't exist");
            }

            itemService.delete(id);
            return ResponseEntity.ok("Item deleted successfully");
        } catch (RuntimeException e) {
            // Handle other runtime exceptions if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }


    @DeleteMapping("/items/deleteAll")
    // to delete all items
    public ResponseEntity<String> deleteAllItems() {
        itemService.deleteAllItems();
        return ResponseEntity.ok("All items were deleted successfully");
    }


}

