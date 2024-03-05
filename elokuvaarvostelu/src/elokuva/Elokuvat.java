package elokuva;

/**
 * +--------------------------------------+--------------------------------------+
 * | Luokan nimi: Elokuvat -luokka        | Avustajat:                           |
 * +--------------------------------------+--------------------------------------+
 * | Vastuualueet:                        | -Elokuva                             |
 * |                                      |                                      |
 * | -osaa lukea ja kirjoittaa tiedostoon |                                      |
 * | - Osaa muuttaa elokuvat tiedoiksi    |                                      |
 * | - osaa etsiä ja lajitella            |                                      |                                      
 * | - osaa avata tietyn elokuvan tiedot  |                                      |
 * |                                      |                                      |
 * |                                      |                                      |
 * +--------------------------------------+--------------------------------------+
 * @author eelis
 * @version 1.3.2024
 *
 */
public class Elokuvat {
  
    private static final int MAX_ELOKUVAT   = 5;
    
    private int lkm = 0;
    private Elokuva[] alkiot;
    
    /**
     * Luodaan alustava taulukko
     */
    public Elokuvat() {
        alkiot = new Elokuva[MAX_ELOKUVAT];
    }
    
    /**
     * @param elokuva Mikä elokuva listataan
     * @throws SailoException Virhe joka heitetään jos tulee virhe
     * @example
     * <pre name="test">
     *   #THROWS SailoException
     *   Elokuvat elokuvat = new Elokuvat();
     *   Elokuva elokuva1 = new Elokuva(), elokuva2 = new Elokuva();
     *   elokuvat.getLkm() === 0;
     *   elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 1;
     *   elokuvat.lisaa(elokuva2); elokuvat.getLkm() === 2;
     *   elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 3;
     *   elokuvat.anna(0) === elokuva1;
     *   elokuvat.anna(1) === elokuva2;
     *   elokuvat.anna(2) === elokuva1;
     *   elokuvat.anna(1) == elokuva1 === false;
     *   elokuvat.anna(1) == elokuva2 === true;
     *   elokuvat.anna(3) === elokuva1; #THROWS IndexOutOfBoundsException
     *   elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 4;
     *   elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 5;
     *   elokuvat.lisaa(elokuva1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Elokuva elokuva) throws SailoException {
        if(lkm >= alkiot.length) throw new SailoException ("Liikaa alkioita ei voida lisätä");
        alkiot[lkm] = elokuva;
        lkm++;
    }
    
    /**
     * @return lkm
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * @param i Mikä indeksi?
     * @return Alkion i indeksissä
     * @throws IndexOutOfBoundsException Jos haetaan liian pitkältä alkiota
     */
    public Elokuva anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi " + i);
        return alkiot[i];
    }
    
    /**
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Elokuvat elokuvat = new Elokuvat();
        Elokuva elokuva1 = new Elokuva();
        Elokuva elokuva2 = new Elokuva();
        
        elokuva1.lisaaElokuva();
        elokuva1.taytaValmisLeffa();
        elokuva2.lisaaElokuva();
        elokuva2.taytaValmisLeffa();
        
        try {
            elokuvat.lisaa(elokuva1);
            elokuvat.lisaa(elokuva2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("===================== TESTAUS ==========================");

        for (int i = 0; i < elokuvat.getLkm(); i++) {
            Elokuva elokuva = elokuvat.anna(i);
            System.out.println("Elokuva indeksi: " + i);
            elokuva.tulosta(System.out);
        }
    }
    
}
