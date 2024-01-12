package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import htwberlin.webtech_Projekt.web.Entities.ShoppingList;

@RestController
@CrossOrigin(origins = "*")
public class ItemRestController {

    private final ItemService itemService;
    private final ShoppingListService shoppingListService;

    @Autowired
    public ItemRestController(ItemService itemService, ShoppingListService shoppingListService) {
        this.itemService = itemService;
        this.shoppingListService = shoppingListService;
    }

    // to show all items
    @GetMapping("/items")
    public ResponseEntity<List<ItemEntity>> getAllItems() {
        List<ItemEntity> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }

    // to show an item
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        try {
            ItemEntity item = itemService.findById(id);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }

    /*
    // this is a basic "createItem" method
    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        ItemEntity createdItem = itemService.save(item);
        return ResponseEntity.ok(createdItem);
    }
     */

    // this "createitem" method is modified to assign the item to the only shopping list that
    // exists directly upon creating the item
    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        try {
            ShoppingList automaticallyCreatedShoppingList = shoppingListService.getAutomaticallyCreatedShoppingList();
            item.setShopid(automaticallyCreatedShoppingList);
            ItemEntity createdItem = itemService.save(item);
            return ResponseEntity.ok(createdItem);
        } finally {

        }
    }

    // to update an item
    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemEntity updatedItem) {
        try {
            ItemEntity updatedItemResult = itemService.update(id, updatedItem);
            return ResponseEntity.ok(updatedItemResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }




    // to delete one item
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            ItemEntity existingItem = itemService.findById(id);
            if (existingItem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Item with ID " + id + " doesn't exist");
            }
            itemService.delete(id);
            return ResponseEntity.ok("Item deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Item with ID " + id + " doesn't exist");
        }
    }

    // to delete all items
    @DeleteMapping("/items/deleteAll")
    // to delete all items
    public ResponseEntity<String> deleteAllItems() {
        itemService.deleteAllItems();
        return ResponseEntity.ok("All items were deleted successfully");
    }


}

