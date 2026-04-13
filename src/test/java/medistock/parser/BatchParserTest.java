package medistock.parser;

import medistock.exception.MediStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatchParserTest {

    @Test
    void parseCommand_bareBatch_throwsInvalidBatchFormat() {
        String input = "batch";

        MediStockException exception = assertThrows(MediStockException.class,
                () -> Parser.parseCommand(input));
        assertTrue(exception.getMessage().startsWith("Invalid batch format."));
    }

    @Test
    void prepareBatch_missingNameTag_throwsException() {
        String input = "batch q/10 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_incorrectTagOrder_throwsException() {
        String input = "batch q/10 n/Vyvanse 40mg d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_unexpectedTextBeforeNameTag_throwsException() {
        String input = "batch extra n/Aspirin q/10 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_duplicateNameTag_throwsException() {
        String input = "batch n/Aspirin n/Panadol q/10 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_duplicateQuantityTag_throwsException() {
        String input = "batch n/Aspirin q/10 q/20 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_duplicateExpiryDateTag_throwsException() {
        String input = "batch n/Aspirin q/10 d/2025-01-01 d/2026-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_quantityNegative_throwsException() {
        String input = "batch n/Aspirin q/-5 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_validFormat_doesNotThrow() {
        String input = "batch n/Aspirin q/10 d/2025-01-01";

        assertDoesNotThrow(() -> Parser.prepareBatch(input));
    }
}
