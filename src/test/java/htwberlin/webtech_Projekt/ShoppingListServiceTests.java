package htwberlin.webtech_Projekt;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Repositories.ShoppingListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTests {


    @Mock
    private ShoppingListRepository repo;

    @InjectMocks
   private ShoppingListService shoppingListService;


    @Test
    @DisplayName("should return shopping list by ID")
    void should_return_shopping_list_by_id() {

        Long givenId = 1L;
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setIdShoppingList(givenId);
        when(repo.findById(givenId)).thenReturn(java.util.Optional.of(shoppingList));

        ShoppingList result = shoppingListService.findById(givenId);

        verify(repo).findById(givenId);
        assertThat(result).isEqualTo(shoppingList);
    }

    @Test
    @DisplayName("should return all shopping lists")
    void should_return_all_shopping_lists() {

        ShoppingList shoppingList1 = new ShoppingList();
        ShoppingList shoppingList2 = new ShoppingList();
        when(repo.findAll()).thenReturn(Arrays.asList(shoppingList1, shoppingList2));

        List<ShoppingList> result = shoppingListService.getAllShoppingLists();

        verify(repo).findAll();
        assertThat(result).containsExactly(shoppingList1, shoppingList2);
    }

    @Test
    @DisplayName("should update shopping list")
    void should_update_shopping_list() {

        Long givenId = 1L;
        ShoppingList existingShoppingList = new ShoppingList();
        existingShoppingList.setIdShoppingList(givenId);
        when(repo.findById(givenId)).thenReturn(java.util.Optional.of(existingShoppingList));
        when(repo.save(existingShoppingList)).thenReturn(existingShoppingList);

        ShoppingList updatedShoppingList = new ShoppingList();
        updatedShoppingList.setIdShoppingList(givenId);
        updatedShoppingList.setShoppingName("Updated Shopping List");
        ShoppingList result = shoppingListService.update(givenId, updatedShoppingList);

        verify(repo).findById(givenId);
        verify(repo).save(existingShoppingList);
        assertThat(result).isEqualTo(existingShoppingList);
    }


    @Test
    @DisplayName("should delete all shopping lists")
    void should_delete_all_shopping_lists() {

        shoppingListService.deleteAllShoppinglists();

        verify(repo).deleteAll();
    }


}
