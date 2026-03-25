package medistock.command;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

import java.util.List;

/**
 * Command to find a stock item in the inventory.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for items matching the keyword
     * and displaying the results.
     *
     * @param inventory The inventory to search in.
     * @param ui The UI for displaying the search results.
     * @throws MediStockException If an error occurs during execution.
     */
    @Override
    public void execute(Inventory inventory, Ui ui) throws MediStockException {
        List<InventoryItem> matchedItems =inventory.findItem(keyword);
        ui.showFindList(inventory, matchedItems);
    }
}
