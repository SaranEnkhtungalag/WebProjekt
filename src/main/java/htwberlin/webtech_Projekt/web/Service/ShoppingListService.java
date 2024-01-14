package htwberlin.webtech_Projekt.web.Service;
//This is a service class responsible for implementing business logic related to shopping lists.


import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import htwberlin.webtech_Projekt.web.Repositories.ItemRepository;



@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository repo;

    @Autowired
    private ItemRepository itemRepository;

    ItemService itemService;


    public ShoppingList findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Shopping list not found"));
    }


    public ShoppingList save(ShoppingList shoppingList) {
        return repo.save(shoppingList);
    }

    public ShoppingList get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Shopping list not found"));
    }


    public List<ShoppingList> getAllShoppingLists() {
        return (List<ShoppingList>) repo.findAll();
    }


    // to update the shopping list name
    public ShoppingList update(Long id, ShoppingList updatedShoppingList) {
        ShoppingList existingShoppingList = get(id);

        if (updatedShoppingList.getShoppingName() != null) {
            existingShoppingList.setShoppingName(updatedShoppingList.getShoppingName());
        }
        return repo.save(existingShoppingList);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAllShoppinglists() {
        repo.deleteAll();
    }


    // automatically trigger the update of the deadline every weekend
    @Scheduled(cron = "0 0 0 * * SUN")
    public void updateShoppingListForNextWeekAutomatically() {
        // Assuming you always want to update the shopping list with ID 1
        updateShoppingListForNextWeek(1L);
    }


    // update the deadline to the next weekend
    @Transactional
    public void updateShoppingListForNextWeek(Long shoppingListId) {
        ShoppingList shoppingList = repo.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        // Set the deadline to the next Sunday
        LocalDate currentDeadline = shoppingList.getDeadline();
        LocalDate nextSunday = currentDeadline.plusDays(1).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        shoppingList.setDeadline(nextSunday);

        // Save the updated shopping list
        repo.save(shoppingList);
    }


    // this method checks for the method "initializeDefaultShoppingList" in ShoppingListRestController class
    // whether the default shopping list exists
    public boolean existsByName(String shoppingListName) {
        return repo.existsShoppingListByShoppingName(shoppingListName);
    }


    // this method checks for the method "initializeDefaultShoppingList" in ShoppingListRestController class
    // whether the default shopping list exists
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    // this method provides the method "createItem" in the ItemRestController class with the id of
    // the only shopping list exists
    public ShoppingList getAutomaticallyCreatedShoppingList() {
        // Assuming you always want to get the shopping list with ID 1
        return repo.findById(1L).orElseThrow(() -> new RuntimeException("Shopping List not found"));
    }


}