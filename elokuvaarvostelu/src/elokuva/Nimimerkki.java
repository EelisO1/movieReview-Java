package elokuva;

import static kanta.Tarkistukset.rand;

import java.io.PrintStream;

/**
 * @author eelis
 * @version 27.3.2024
 *
 */
public class Nimimerkki {
    
    private int tunnusNro;
    private String nimi = "";
    private static int seuraavaNro = 1;

    
    /**
     * Alustetaan nimimerkki
     */
    public Nimimerkki() {
        //
    }
    
    
    /**
     * Tulostetaan arvostelun tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(nimi + " " + tunnusNro);
    }
    
    
    /**
     * TODO: MUUTA GETELOKUVANRO
     * Palautetaan mille elokuvalle arvostelu kuuluu
     * @return jäsenen id
     */
    public int getTunnusnro() {
        return tunnusNro;
    }
    
    
    
    
    /**
     * Antaa arvosteluille seuraavan numeron.
     * @return arvostelun uusi tunnus_nro
     * @example
     * <pre name="test">
     *   Nimimerkki arv = new Arvostelu();
     *   arv.getTunnusNro() === 0;
     *   arv.rekisteroi();
     *   Arvostelu arv2 = new Arvostelu();
     *   arv2.rekisteroi();
     *   int n1 = arv.getTunnusNro();
     *   int n2 = arv2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot arvostelulle.
     * Osa tiedoista arvotaan jotta kaikilla ei olisi samoja arvoja
     * TODO: KUN MENNÄÄN TÄYTTÄMÄÄN ARVOSTELUA ELOKUVAN ID ON SEN ELOKUVAN ID MIHIN ARVOSTELUA JÄTETÄÄN
     */
    public void taytaNimi() {
        nimi = "Aku " + rand(0, 100);
    }
    
    
}
