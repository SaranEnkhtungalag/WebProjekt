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


@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository repo;


    private ItemService itemService;

    public ShoppingList findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
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


    public ShoppingList update(Long id, ShoppingList updatedShoppingList) {
        ShoppingList existingShoppingList = get(id);

        if (updatedShoppingList.getShoppingName() != null) {
            existingShoppingList.setShoppingName(updatedShoppingList.getShoppingName());
        }
        if (updatedShoppingList.isDone() != null) {
            existingShoppingList.setDone(updatedShoppingList.isDone());
        }
        if (updatedShoppingList.getDeadline() != null) {
            existingShoppingList.setDeadline(updatedShoppingList.getDeadline());
        }
        return repo.save(existingShoppingList);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAllShoppinglists() {
        repo.deleteAll();
    }

    /*
    @Scheduled(cron = "0 59 23 ? * SUN") // Cron expression for every Sunday at 23:59
    @Transactional
    public void performWeeklyCleanup() {
        ShoppingList currentShoppingList = getCurrentShoppingList();

        if (currentShoppingList != null) {
            currentShoppingList.setDone(true);
            repo.save(currentShoppingList);

            // Update items for the new shopping list or delete items
            itemService.updateItemsForNewShoppingList(currentShoppingList.getItems());
        }
    }

    public ShoppingList getCurrentShoppingList() {
        // Fetch the current shopping list based on some criteria, e.g., not done and closest deadline
        return repo.findFirstByDoneOrderByDeadlineAsc(false);
    }

     */



}