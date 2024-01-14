package htwberlin.webtech_Projekt;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import htwberlin.webtech_Projekt.web.api.ShoppingListRestController;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShoppingListRestControllerTests {

    @Mock
    private ShoppingListService shoppingListService;

    @InjectMocks
    private ShoppingListRestController shoppingListRestController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shoppingListRestController).build();
    }


    @Test
    void should_get_all_shopping_lists() throws Exception {

        ShoppingList shoppingList1 = new ShoppingList();
        ShoppingList shoppingList2 = new ShoppingList();
        doReturn(Arrays.asList(shoppingList1, shoppingList2)).when(shoppingListService).getAllShoppingLists();

        mockMvc.perform(get("/shoppingLists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }


    @Test
    void should_delete_all_shopping_lists() throws Exception {

        mockMvc.perform(delete("/shoppingLists/deleteAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
