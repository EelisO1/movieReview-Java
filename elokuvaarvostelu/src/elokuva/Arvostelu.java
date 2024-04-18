package elokuva;

import static kanta.Tarkistukset.rand;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author eelis
 * @version 7.3.2024
 *
 */
public class Arvostelu {

    private int tunnusNro;
    private int arvostelijanId; //TODO: kuka on tehnyt
    private int elokuvanId;
    private int arvosana;
    private String kommentit = "";

    private static int seuraavaNro = 1;
    
    
    /**
     * Alustetaan Arvostelu
     */
    public Arvostelu() {
        // Vielä ei tarvita mitään
    }

    
    /**
     * @return tunnusnro
     */
    public int getArvostelijanId() {
        return arvostelijanId;
    }
    
    
    /**
     * Alustetaan tietyn henkilön arvostelu.  // TODO: MIETI TÄMÄ UUSIKSI
     * @param elokuvanId henkilön nimimerkille annettu id
     */
    public Arvostelu(int elokuvanId) {
        this.elokuvanId = elokuvanId;
    }

    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot arvostelulle.
     * Osa tiedoista arvotaan jotta kaikilla ei olisi samoja arvoja
     * @param nro viite henkilöön, jonka arvostelusta on kyse
     *  KUN MENNÄÄN TÄYTTÄMÄÄN ARVOSTELUA ELOKUVAN ID ON SEN ELOKUVAN ID MIHIN ARVOSTELUA JÄTETÄÄN
     */
    public void taytaArvostelu(int nro) {
        elokuvanId = nro;
        kommentit = "Tykkäsin näin paljon: " + rand(0, 100) + "%";
        arvosana = rand(0, 5);
    }

    
    /**
     * Tulostetaan arvostelun tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("indeksi " +tunnusNro + " " + kommentit + " " + arvosana +" tähteä");
    }
    
    
    /**
     * Tulostetaan arvostelijan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    /**
     * Antaa arvosteluille seuraavan numeron.
     * @return arvostelun uusi tunnus_nro
     * @example
     * <pre name="test">
     *   Arvostelu arv = new Arvostelu();
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
     * Palautetaan arvostelun oma id/nro
     * @return arvostelun id/nro
     */
    public int getTunnusNro() {
        return tunnusNro;
    }


    /**
     * @param nro tekijan nimen id
     */
    public void tekija(int nro) {
        arvostelijanId = nro;
    }
    
    /**
     * TODO: MUUTA GETELOKUVANRO
     * Palautetaan mille elokuvalle arvostelu kuuluu
     * @return jäsenen id
     */
    public int getElokuvaNro() {
        return elokuvanId;
    }

    
    /**
     * Testiohjelma Arvosteluille.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Arvostelu arv = new Arvostelu();
        arv.taytaArvostelu(2);
        arv.tulosta(System.out);
    }

}
