package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<ItemEntity> getItem(@PathVariable Long id) {
        ItemEntity item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        ItemEntity createdItem = itemService.save(item);
        return ResponseEntity.ok(createdItem);
    }


    @PutMapping("/items/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable Long id, @RequestBody ItemEntity updatedItem) {
        ItemEntity updatedItemResult = itemService.update(id, updatedItem);
        return ResponseEntity.ok(updatedItemResult);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok("Item deleted successfully");
    }


    @DeleteMapping("/items/deleteAll")
    // to delete all items
    public ResponseEntity<String> deleteAllItems() {
        itemService.deleteAllItems();
        return ResponseEntity.ok("All items were deleted successfully");
    }


}

