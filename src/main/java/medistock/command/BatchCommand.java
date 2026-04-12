package medistock.command;

import medistock.exception.MediStockException;
import medistock.inventory.Batch;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.storage.Storage;
import medistock.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Command to add a new batch to an existing inventory item.
 */
public class BatchCommand extends Command {
    private final String name;
    private final int quantity;
    private final LocalDate expiryDate;

    public BatchCommand(String name, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, Storage storage, List<String> histories) throws MediStockException {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new MediStockException("Item name cannot be empty.\n"
                    + "Format: batch n/NAME q/QUANTITY d/EXPIRY_DATE(YYYY-MM-DD)");
        }
        if (!inventory.hasItem(this.name)) {
            throw new MediStockException("Item '" + this.name + "' does not exist in inventory.\n"
                    + "Use 'list' to see existing items, or 'create' to add a new one.");
        }
        try {
            InventoryItem item = inventory.getItem(name);
            item.sortAndMarkExpiredBatches();
            int batchNumber = item.getAndIncrementBatchNumber();
            if (expiryDate.isBefore(LocalDate.now())) {
                String errorMessage = "This batch is already expired (" + expiryDate + ").";
                if (!ui.wasMessageConfirm(errorMessage)) {
                    System.out.println("Batch not added.");
                    ui.printAbortCommand();
                    return;
                }
            }
            Batch newBatch = new Batch(batchNumber, quantity, expiryDate);
            item.addBatch(newBatch);
            item.sortAndMarkExpiredBatches();
            ui.printBatch(inventory, item, quantity, expiryDate);
            histories.add(toHistoryString(item.getUnit()));

            storage.saveToFile(inventory);
        } catch (IOException e) {
            throw new MediStockException("Failed to save to file: " + e.getMessage());
        }
    }

    public String toHistoryString(String unit) {
        return "Added a batch of " + quantity + " " + unit + " of " + name + " with expiry date "
                + expiryDate + ".";
    }
}

