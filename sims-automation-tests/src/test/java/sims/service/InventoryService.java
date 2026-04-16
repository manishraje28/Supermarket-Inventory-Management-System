package sims.service;

public class InventoryService {
    
    /**
     * Calculates the quantity to reorder based on current stock and reorder level.
     * Minimum Order Quantity (MOQ) is set to 50 for this example.
     *
     * @param productId     ID of the product (not used in logic, but kept for realism)
     * @param currentStock  current available stock
     * @param reorderLevel  threshold below which reorder is triggered
     * @return              quantity to reorder (0 if no reorder needed)
     */
    public int calculateReorderQuantity(int productId, int currentStock, int reorderLevel) {
        // Node A: Start
        int reorderQty = 0;
        
        // Node B: Decision 1 – Check if reorder is needed
        if (currentStock < reorderLevel) {
            // Node C: Decision 2 – Calculate shortage
            int shortage = reorderLevel - currentStock;
            
            // Node D: Decision 3 – Check against MOQ (Minimum Order Quantity = 50)
            if (shortage < 50) {
                // Node E: Use MOQ
                reorderQty = 50;
            } else {
                // Node F: Use calculated shortage
                reorderQty = shortage;
            }
        } else {
            // Node G: No reorder needed
            reorderQty = 0;
        }
        
        // Node H: Return
        return reorderQty;
    }
}