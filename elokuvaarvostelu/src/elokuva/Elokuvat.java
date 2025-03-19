package elokuva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

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
        if(lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+20);
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
     * Tallentaa elokuvat tiedostoon
     * @param nimi tiedoston nimi
     * @throws SailoException jos virhe
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void tallenna(String nimi) throws SailoException {
        File tiedosto = new File(nimi + "/elokuvat.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Elokuva elokuva = anna(i);
                fo.println(elokuva.toString());
            }
        }
            catch (FileNotFoundException ex) {
                throw new SailoException("Tiedosto " + tiedosto.getAbsolutePath() + " ei aukia");
                
            }
            
    }
    
    
    /**
     * Poistaa elokuvan ID:n perusteella
     * @param id Tietyn elokuvan ID joka poistetaan
     * <pre name="test">
     *   #THROWS SailoException
     *   Elokuvat elokuvat = new Elokuvat();
     *   Elokuva elokuva1 = new Elokuva(); elokuva1.lisaaElokuva(); // ID 1
     *   Elokuva elokuva2 = new Elokuva(); elokuva2.lisaaElokuva(); // ID 2
     *   Elokuva elokuva3 = new Elokuva(); elokuva3.lisaaElokuva(); // ID 3
     *   elokuvat.lisaa(elokuva1); elokuvat.lisaa(elokuva2); elokuvat.lisaa(elokuva3);
     *   elokuvat.getLkm() === 3;
     *   elokuvat.poista(2); // Poistaa elokuvan jolla 2 id
     *   elokuvat.getLkm() === 2;
     *   elokuvat.anna(0) == elokuva1 === true;
     *   elokuvat.anna(1) == elokuva3 === true;
     *   elokuvat.anna(2); #THROWS IndexOutOfBoundsException
     *   elokuvat.poista(999); // Ei olemassa
     *   elokuvat.getLkm() === 2; Ei muutosta
     *   elokuvat.poista(1); // Poista 1 elokuva
     *   elokuvat.getLkm() === 1;
     *   elokuvat.anna(0) == elokuva3 === true;
     * </pre>
     */
    public void poista(int id) {
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i] != null && alkiot[i].getElokuvaId() == id) {
                // Liikuttaa alkiot vasemmalle 
                for (int j = i; j < lkm - 1; j++) {
                    alkiot[j] = alkiot[j + 1];
                }
                alkiot[lkm - 1] = null; // "Tyhjentää" vikan alkion paikan
                lkm--; 
                return;
            }
        }
    }
    
    
    /**
     * @param hakemisto nimi
     * @throws SailoException virhe
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/elokuvat.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext() ) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0) == ';') continue;
                Elokuva elokuva = new Elokuva();
                elokuva.parse(s);
                lisaa(elokuva);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("ei lue " + nimi);            
        }/** catch ( IOException e ) {
            throw new SailoException("Sisältö ei toimi " + nimi);       
        }**/ 
    }
    
    
    /**
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Elokuvat elokuvat = new Elokuvat();
        
        
        try {
            elokuvat.lueTiedostosta("data");
        } catch (SailoException e) {
            System.err.println("ei toimi " + e.getMessage());
        }
        
        
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
        
        try {
            elokuvat.tallenna("data");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
    
}
