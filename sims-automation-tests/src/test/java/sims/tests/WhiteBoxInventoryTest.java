package sims.tests;

import sims.service.InventoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhiteBoxInventoryTest {

    private InventoryService inventoryService = new InventoryService();

    @Test
    @DisplayName("WB-PATH-01: No reorder needed (currentStock >= reorderLevel)")
    public void testPath1_NoReorder() {
        int result = inventoryService.calculateReorderQuantity(101, 100, 50);
        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("WB-PATH-02: Shortage < MOQ, use MOQ value")
    public void testPath2_UseMOQ() {
        int result = inventoryService.calculateReorderQuantity(102, 30, 50);
        Assertions.assertEquals(50, result);
    }

    @Test
    @DisplayName("WB-PATH-03: Shortage >= MOQ, use shortage value")
    public void testPath3_UseShortage() {
        int result = inventoryService.calculateReorderQuantity(103, 10, 100);
        Assertions.assertEquals(90, result);
    }
}