package Inventory;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Inventory {
    private String name;
    private List<List<String>> palletItemIds; // Nested structure: List of Pallets, where each Pallet is a List of Item IDs
    private static ItemPlaceholder itemPlaceholder = new ItemPlaceholder();

    public Inventory(String name, List<List<String>> palletItemIds) {
        this.name = name;
        this.palletItemIds = palletItemIds;
    }

    public List<List<String>> getPalletItemIds() { return palletItemIds; }

    // Simulates an expensive database lookup
    public static Optional<Inventory> findItem(String id) {
        if (id.equals("A100")) {
            return Optional.of(new Inventory("Main Inventory", List.of(
                    List.of("P10", "P20"),
                    List.of("P30", "P10", "P40")
            )));
        }
        return Optional.empty(); // Search fails
    }

    public static void main(String[] args) {
        Optional<Inventory> inventory = Inventory.findItem("A");
        if(inventory.isPresent()){
            System.out.println(inventory);
        }else {
            System.out.println(itemPlaceholder.getInfo());
        }
    }
}

// File: ItemPlaceholder.java
class ItemPlaceholder  {
    // This is a highly resource-intensive object to create.
    public ItemPlaceholder() {
        System.out.println("ALERT: Creating expensive placeholder object!");
    }
    public String getInfo() { return "ID-NOT-FOUND: Placeholder Item"; }

}
