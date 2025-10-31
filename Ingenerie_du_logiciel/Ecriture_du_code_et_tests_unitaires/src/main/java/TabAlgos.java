/**
 * Classe utilitaire proposant des algorithmes sur les tableaux d'entiers.
 */
public final class TabAlgos {

    /** Constructeur privé pour éviter l'instanciation. */
    private TabAlgos() { }

    /**
     * Renvoie la valeur la plus grande d'un tableau.
     * @param tab le tableau d'entiers
     * @return la plus grande valeur
     * @throws IllegalArgumentException si tab est null ou vide
     */
    public static int plusGrand(final int[] tab) {
        if (tab == null || tab.length == 0) {
            throw new IllegalArgumentException("Tableau vide ou nul");
        }
        int max = tab[0];
        for (int val : tab) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * Calcule la moyenne des valeurs d'un tableau.
     * @param tab le tableau d'entiers
     * @return la moyenne
     * @throws IllegalArgumentException si tab est null ou vide
     */
    public static double moyenne(final int[] tab) {
        if (tab == null || tab.length == 0) {
            throw new IllegalArgumentException("Tableau vide ou nul");
        }
        int somme = 0;
        for (int val : tab) {
            somme += val;
        }
        return (double) somme / tab.length;
    }

    /**
     * Teste si deux tableaux sont identiques
     * (même taille et mêmes éléments dans le même ordre).
     * @param tab1 premier tableau
     * @param tab2 deuxième tableau
     * @return true si identiques, false sinon
     */
    public static boolean egaux(final int[] tab1, final int[] tab2) {
        if (tab1 == tab2) {
            return true; // même référence
        }
        if (tab1 == null || tab2 == null || tab1.length != tab2.length) {
            return false;
        }
        for (int i = 0; i < tab1.length; i++) {
            if (tab1[i] != tab2[i]) {
                return false;
            }
        }
        return true;
    }
}
