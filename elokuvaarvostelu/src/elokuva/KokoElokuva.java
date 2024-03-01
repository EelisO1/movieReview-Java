package elokuva;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   KokoElokuva                         | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Elokuvat        | 
 * | - huolehtii Elokuvat ja Arvostelut  - luokkien     | - Elokuva         | 
 * |   välisestä yhteistyöstä ja välittää näitä tietoja | - Arvostelu       | 
 * |   pyydettäessä                                     | - Arvostelut      | 
 * | - lukee ja kirjoittaa tähän  tiedostoon pyytämällä | - Nimimerkki      |
 * |   apua avustajiltaan                               | - Nimimerkit      | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * @author eelis
 * @version 1.3.2024
 *
 */
public class KokoElokuva {
    
    private Elokuvat elokuvat = new Elokuvat();
    
    
    
    /**
     * Lisätään uusi elokuva
     * @param elokuva Lisättävä elokuva
     * @throws SailoException Mitä poikkeusta käytetään mikäli sellainen sattuu / lisääminen ei onnistu
     */
    public void lisaa(Elokuva elokuva) throws SailoException {
        elokuvat.lisaa(elokuva);
    }
    
    /**
     * @return Palauttaa elokuvien määrän
     */
    public int getElokuvat() {
        return elokuvat.getLkm();
    }
    
    /**
     * @param i Mikä elokuva haetaan
     * @return palauttaa indeksissä i olevan elokuvan
     */
    public Elokuva annaElokuva(int i) {
        return elokuvat.anna(i);
    }
    
    /**
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        KokoElokuva kokoelokuva = new KokoElokuva();
        
        Elokuva elokuva1 = new Elokuva();
        Elokuva elokuva2 = new Elokuva();
        
        elokuva1.lisaaElokuva();
        elokuva1.taytaValmisLeffa();
        
        elokuva2.lisaaElokuva();
        elokuva2.taytaValmisLeffa();
        
        try {
            kokoelokuva.lisaa(elokuva1);
            kokoelokuva.lisaa(elokuva2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("===================== TESTAUS ==========================");

        for (int i = 0; i < kokoelokuva.getElokuvat(); i++) {
            Elokuva elokuva = kokoelokuva.annaElokuva(i);
            elokuva.tulosta(System.out);
        }
    }

}
