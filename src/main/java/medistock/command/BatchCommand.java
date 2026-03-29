package medistock.command;

import medistock.exception.MediStockException;
import medistock.inventory.Batch;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

import java.time.LocalDate;
import java.util.List;

public class BatchCommand extends String {
    private final java.lang.String name;
    private final int quantity;
    private final LocalDate expiryDate;

    public BatchCommand(java.lang.String name, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {

        if (!inventory.hasItem(this.name)) {
            throw new MediStockException("Item '" + this.name + "' does not exist in inventory." +
                            " Please add the item first.");
        }

        InventoryItem item = inventory.getItem(name);
        int batchNumber = item.getBatchQuantity() + 1;
        Batch newBatch = new Batch(batchNumber, quantity, expiryDate);
        item.addBatch(newBatch);
        ui.printBatch(inventory, item, quantity, expiryDate);
        histories.add(toHistoryString(item.getUnit()));
    }

    public java.lang.String toHistoryString(java.lang.String unit) {
        return "Added a batch of " + quantity + " " + unit + " of " + name + " with expiry date "
                + expiryDate + ".";
    }
}
