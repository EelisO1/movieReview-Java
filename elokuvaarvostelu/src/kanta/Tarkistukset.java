package kanta;

// Mahdolliset tarkistukset mikäli tarvitaan
/**
 * @author eelis
 * @version 1.3.2024
 *
 */
public class Tarkistukset {

    
    /**
     * @param ala alaraja
     * @param yla yläraja
     * @return satunnainen nro
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    /**
     * @param eka Yläraja
     * @param toka  Alaraja
     * @return satunnaisen vuoden
     */
    public static int arvoLuku(int eka, int toka) {
        int vuosi = rand(eka, toka);
        return vuosi;
    }
}
