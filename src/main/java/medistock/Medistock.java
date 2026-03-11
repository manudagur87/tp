package medistock;

import medistock.command.Command;
import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.parser.Parser;
import medistock.ui.Ui;

import java.util.Scanner;

public class Medistock {
    /**
     * Main entry-point for the java.medistick.Medistock application.
     */
    public static void main(String[] args) {
        Ui.greet();
        Inventory inventory = new Inventory();

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine().trim();

            if (input.equals("exit")) {
                Ui.exit();
                break;
            }

            try {
                Command command = Parser.parse(input);
                String result = command.execute(inventory);
                System.out.println(result);
            } catch (MediStockException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        in.close();
    }
}
