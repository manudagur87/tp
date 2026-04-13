package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

/**
 * Command to edit an existing inventory item's metadata (name, unit, or minimum threshold).
 * At least one field must be updated when executing this command.
 */
public class EditCommand extends Command {
    private final String oldName;
    private final String newName;
    private final String newUnit;
    private final Integer newMinimumThreshold;

    /**
     * Creates an EditCommand with the specified parameters.
     * At least one of newName, newUnit, or newMinimumThreshold must be non-null.
     *
     * @param oldName The current name of the item to edit.
     * @param newName The new name for the item, or null to keep unchanged.
     * @param newUnit The new unit for the item, or null to keep unchanged.
     * @param newMinimumThreshold The new minimum threshold, or null to keep unchanged.
     */
    public EditCommand(String oldName, String newName, String newUnit, Integer newMinimumThreshold) {
        this.oldName = oldName;
        this.newName = newName;
        this.newUnit = newUnit;
        this.newMinimumThreshold = newMinimumThreshold;
    }

    /**
     * Executes the edit command by updating the specified item's metadata.
     * The item's batches are preserved during the edit operation.
     *
     * @param inventory The inventory containing the item to edit.
     * @param ui The UI for displaying the result.
     * @param histories The command history list.
     * @throws MediStockException If the item is not found or the new name already exists.
     */
    @Override
    public void execute(Inventory inventory, Ui ui, List<String> histories) throws MediStockException {
        InventoryItem updatedItem = inventory.editItem(oldName, newName, newUnit, newMinimumThreshold);
        ui.printEdit(oldName, updatedItem);
        histories.add(toHistoryString(updatedItem));
    }

    /**
     * Generates a history string describing the edit operation.
     *
     * @param updatedItem The item after being updated.
     * @return A formatted history string.
     */
    public String toHistoryString(InventoryItem updatedItem) {
        return "Edited '" + oldName + "' to '" + updatedItem.getName()
                + "' (" + updatedItem.getUnit()
                + ") with minimum threshold of " + updatedItem.getMinimumThreshold() + ".";
    }
}
