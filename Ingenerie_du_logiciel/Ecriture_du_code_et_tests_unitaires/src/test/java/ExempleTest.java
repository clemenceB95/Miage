import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExempleTest {

    @Test
    void testConstructeurEtGetV() {
        Exemple ex = new Exemple("test");
        ex.setV(5);
        assertEquals(5, ex.getV());
    }

    @Test
    void testGetTPositif() {
        Exemple ex = new Exemple("ok");
        ex.setV(2);
        assertEquals("ok", ex.getT());
    }

    @Test
    void testGetTNegatif() {
        Exemple ex = new Exemple("no");
        ex.setV(-1);
        assertEquals("", ex.getT());
    }
}
