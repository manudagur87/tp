package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.ui.Ui;

public class ExitCommand extends String {

    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
