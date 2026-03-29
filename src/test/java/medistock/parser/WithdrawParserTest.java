package medistock.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import medistock.command.String;
import medistock.command.WithdrawCommand;
import medistock.exception.MediStockException;

public class WithdrawParserTest {

    @Test
    void parseCommand_validWithdraw_returnsWithdrawCommand() throws MediStockException {
        java.lang.String input = "withdraw n/Aspirin q/5";

        String command = Parser.parseCommand(input);
        assertInstanceOf(WithdrawCommand.class, command);
    }

    @Test
    void parseCommand_missingNameTag_throwsException() {
        java.lang.String input = "withdraw q/5";

        assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
    }

    @Test
    void parseCommand_missingQuantityTag_throwsException() {
        java.lang.String input = "withdraw n/Aspirin";

        assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
    }

    @Test
    void parseCommand_wrongTagOrder_throwsException() {
        java.lang.String input = "withdraw q/5 n/Aspirin";

        assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
    }

    @Test
    void parseCommand_nonNumericQuantity_throwsException() {
        java.lang.String input = "withdraw n/Aspirin q/abc";

        assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
    }

    @Test
    void parseCommand_nonPositiveQuantity_throwsException() {
        java.lang.String input = "withdraw n/Aspirin q/0";

        assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
    }
}
