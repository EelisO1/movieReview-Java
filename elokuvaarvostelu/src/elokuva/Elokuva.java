package elokuva;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.Tarkistukset;

/**
 * @author eelis
 * @version 1.3.2024
 *
 */
public class Elokuva {
    private int     elokuvaId  = 0; 
    private int     vuosi      = 0;
    private double  pituus     = 0;
    private String  nimi       = "";
    private String  genre      = "";
    private int  ikaraja       = 0;
    
    private static int seuraavaId = 1;
    
    /**
     * Alustetaan elokuva tyhjäksi
     */
    public Elokuva() {}
    
    
    /**
     * @param out Tulostetaan käyttöliittymään tiedot
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", elokuvaId) + "  " + nimi + "  " + vuosi + "  " + pituus + "h  " + genre + "  k-" + ikaraja);
    }
    
    /**
     * @param os Tulostetaan käyttöliittymään tiedot
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * @return Elokuvan id
     */
    public int getElokuvaId() {
        return elokuvaId;
    }
    
    /**
     * @return Palauttaa "this." tämän elokuvan nimen
     */
    public String getElokuvaNimi() {
        return nimi;
    }
    
    /** Tehdään uusi id uudelle elokuvalle
     * @return Seuraava elokuvan ID
     * @example
     * <pre name="test">
     *   Elokuva elokuva1 = new Elokuva();
     *   elokuva1.getElokuvaId() === 0;
     *   elokuva1.lisaaElokuva();
     *   Elokuva elokuva2 = new Elokuva();
     *   elokuva2.lisaaElokuva();
     *   int n1 = elokuva1.getElokuvaId();
     *   int n2 = elokuva2.getElokuvaId();
     *   n1 === n2-1;
     * </pre>
     */
    public int lisaaElokuva() {
        this.elokuvaId = seuraavaId;
        seuraavaId++;
        return this.elokuvaId;
    }
    

    /**
     *  Rakennusteline ohjelmaa varten
     *  täyttää tiedot valmiilla keksityillä asioilla
     *  IKÄRAJA ARVOLUKU HIEMAN EPÄKÄYTÄNNÖLLINEN :)
     */
    public void taytaValmisLeffa() {
        vuosi      = Tarkistukset.arvoLuku(1900, 2024); 
        pituus     = 1.5; 
        nimi       = "Terminator " + Tarkistukset.arvoLuku(1, 10); 
        genre      = "Action";
        ikaraja    = Tarkistukset.arvoLuku(1,18);
    }
    
    /**
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Elokuva elokuva1 = new Elokuva();
        Elokuva elokuva2 = new Elokuva();
        
        elokuva1.lisaaElokuva();
        elokuva2.lisaaElokuva();
        
        elokuva1.tulosta(System.out);
        
        elokuva1.taytaValmisLeffa();
        elokuva2.taytaValmisLeffa();
        
        elokuva1.tulosta(System.out);
        elokuva2.tulosta(System.out);
    }    
}
