package medistock.command;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(Inventory inventory, Ui ui) throws MediStockException {
        ui.printCommandList();
    }
}
