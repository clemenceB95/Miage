/**
 * Ma class d'exemple.
 */
public class Exemple {
    /**
     * Chaîne de caractères stockée dans l'attribut t.
     */
    private final String t;
    /**
     * Valeur entière stockée dans l'attribut v.
     */
    private int v;

    /**
     * Constructeur de la classe Exemple.
     * @param valeurT la chaîne de caractères à stocker dans l'attribut t
     */
    public Exemple(final String valeurT) {
        this.t = valeurT;
    }
    /**
     * @return la valeur de v
     */
    public int getV() {
        return this.v;
    }

    /**
     * Modifie la valeur de l'attribut v.
     * @param valeurV la nouvelle valeur entière à stocker
     */
    public final void setV(final int valeurV) {
        this.v = valeurV;
    }
    /**
     * @return t si v est positif
     */
    public final String getT() {
        if (v > 0) {
            return t;
        }
        return "";
    }
}
