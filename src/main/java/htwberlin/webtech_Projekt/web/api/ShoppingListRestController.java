package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.springframework.http.HttpStatus;

@RestController
public class ShoppingListRestController {

    @Autowired
    ShoppingListService service;

    Logger logger = LoggerFactory.getLogger(ShoppingListRestController.class);

    @PostMapping("/shoppingList")
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return service.save(shoppingList);
    }

/*
    @PostMapping("/shoppingList")
    public ResponseEntity<ShoppingList> createShoppingListV2(@RequestBody ShoppingList shoppingListEntity) {
        try {
            ShoppingList createdShoppingList = service.save(shoppingListEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

 */

    @GetMapping("/shoppingList/{id}")
    public ShoppingList getShoppinList(@PathVariable String id) {
      logger.info("Get ...", id);
      Long shoppingId = Long.parseLong(id);
      return service.get(shoppingId);
    }

    // to see all the shopping lists
    @GetMapping("/shoppingLists")
    public ResponseEntity<List<ShoppingList>> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = service.getAllShoppingLists();
        return ResponseEntity.ok(shoppingLists);
    }

    @DeleteMapping("/shoppingList/{id}")
    public ResponseEntity<String> deleteShoppingList(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Shopping list item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting shopping list item");
        }
    }

    @PutMapping("/shoppingList/{id}")
    public ShoppingList updateShoppingList(@PathVariable Long id) {
        Scanner scanner = new Scanner(System.in);
        ShoppingList shoppingList = service.get(id);

        System.out.println("Which value do you want to update?");
        System.out.println("Write 1 to change the name");
        System.out.println("Write 2 to change the status done/not done");
        System.out.println("Write 3 to change the deadline");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter new name:");
                scanner.nextLine();
                String newName = scanner.nextLine();
                shoppingList.setShoppingName(newName);
                break;
            case 2:
                System.out.println("Set done (true/false):");
                boolean done = scanner.nextBoolean();
                shoppingList.setDone(done);
                break;
            case 3:
                System.out.println("Enter new deadline (yyyy-MM-dd):");
                String dateStr = scanner.next();
                LocalDate newDeadline = LocalDate.parse(dateStr);
                shoppingList.setDeadline(newDeadline);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        return service.update(id, shoppingList);
    }


}
