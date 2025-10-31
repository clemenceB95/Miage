import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TabAlgosTest {

    // ---- Tests pour plusGrand ----
    @Test
    public void testPlusGrandValeursPositives() {
        int[] tab = {1, 5, 3, 9, 2};
        assertEquals(9, TabAlgos.plusGrand(tab));
    }

    @Test
    public void testPlusGrandValeursNegatives() {
        int[] tab = {-7, -3, -10};
        assertEquals(-3, TabAlgos.plusGrand(tab));
    }

    @Test
    public void testPlusGrandUnElement() {
        int[] tab = {42};
        assertEquals(42, TabAlgos.plusGrand(tab));
    }

    @Test
    public void testPlusGrandExceptionVide() {
        int[] tab = {};
        assertThrows(IllegalArgumentException.class, () -> TabAlgos.plusGrand(tab));
    }

    @Test
    public void testPlusGrandExceptionNull() {
        assertThrows(IllegalArgumentException.class, () -> TabAlgos.plusGrand(null));
    }

    // ---- Tests pour moyenne ----
    @Test
    public void testMoyenneValeursPositives() {
        int[] tab = {2, 4, 6, 8};
        assertEquals(5.0, TabAlgos.moyenne(tab));
    }

    @Test
    public void testMoyenneValeursNegatives() {
        int[] tab = {-2, -4, -6};
        assertEquals(-4.0, TabAlgos.moyenne(tab));
    }

    @Test
    public void testMoyenneMixte() {
        int[] tab = {-2, 2};
        assertEquals(0.0, TabAlgos.moyenne(tab));
    }

    @Test
    public void testMoyenneExceptionVide() {
        int[] tab = {};
        assertThrows(IllegalArgumentException.class, () -> TabAlgos.moyenne(tab));
    }

    @Test
    public void testMoyenneExceptionNull() {
        assertThrows(IllegalArgumentException.class, () -> TabAlgos.moyenne(null));
    }

    // ---- Tests pour egaux ----
    @Test
    public void testEgauxMemeReference() {
        int[] tab = {1, 2, 3};
        assertTrue(TabAlgos.egaux(tab, tab));
    }

    @Test
    public void testEgauxIdentiques() {
        int[] tab1 = {1, 2, 3};
        int[] tab2 = {1, 2, 3};
        assertTrue(TabAlgos.egaux(tab1, tab2));
    }

    @Test
    public void testEgauxOrdreDifferent() {
        int[] tab1 = {1, 2, 3};
        int[] tab2 = {3, 2, 1};
        assertFalse(TabAlgos.egaux(tab1, tab2));
    }

    @Test
    public void testEgauxUnDifferent() {
        int[] tab1 = {1, 2, 3};
        int[] tab2 = {1, 2, 4};
        assertFalse(TabAlgos.egaux(tab1, tab2));
    }

    @Test
    public void testEgauxTailleDifferent() {
        int[] tab1 = {1, 2};
        int[] tab2 = {1, 2, 3};
        assertFalse(TabAlgos.egaux(tab1, tab2));
    }

    @Test
    public void testEgauxAvecNull() {
        assertFalse(TabAlgos.egaux(null, new int[]{1, 2, 3}));
        assertFalse(TabAlgos.egaux(new int[]{1, 2}, null));
        assertTrue(TabAlgos.egaux(null, null)); // deux null considérés comme égaux
    }
}
