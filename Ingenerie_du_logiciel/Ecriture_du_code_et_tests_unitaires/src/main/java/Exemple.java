/**
 * Ma class d'exemple.
 */
public class Exemple {
    /** Chaîne de texte associée à l’objet. */
    private final String t;
    /** Valeur entière associée à l’objet. */
    private int v;

    /**
     * Constructeur de la classe Exemple.
     * @param t chaîne de caractères associée à cet objet
     */
    public Exemple(String t) {
        this.t = t;
    }

    /**
     * Retourne la valeur actuelle de v.
     * @return la valeur de v
     */
    public int getV() {
        return this.v;
    }

    /**
     * Modifie la valeur de v.
     * @param v nouvelle valeur entière
     */
    public final void setV(int v) {
        this.v = v;
    }

    /**
     * Retourne t si v est positif.
     * @return la chaîne t si v > 0, sinon une chaîne vide
     */
    public final String getT() {
        if (v > 0) return t;
        return "";
    }
}
