package elokuva;

import static kanta.Tarkistukset.rand;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
     * @param id id
     */
    public void setElokuvanId(int id) {
        elokuvanId = id;
    }
    
    
    /**
     * @param id id
     */
    public void setArvostelijanId(int id) {
        arvostelijanId = id;;
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
     * @param tekijanid tekijanid
     */
    public void taytaArvostelu(int nro, int tekijanid) {
        arvostelijanId = tekijanid;
        elokuvanId = nro;
        kommentit = "Tykkäsin näin paljon: " + rand(0, 100) + "%";
        arvosana = rand(0, 5);
    }

    
    /**
     * @param nro nro
     */
    public void taytaArvostelu(int nro) {
        elokuvanId = nro;
        kommentit = "Tykkäsin näin paljon: " + rand(0, 100) + "%";
        arvosana = rand(0, 5);
    }
    
    /**
     * Tulostetaan arvostelun tiedot
     * @param out tietovirta johon tulostetaan
     * @param nimi nimi
     */
    public void tulosta(PrintStream out, String nimi) {
        out.println("Arvostelija: " + nimi);
        out.println(kommentit);
        out.println(arvosana +" tähteä");
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
     * Asettaa tunnusnumeron ja varmistaa että seuraava on suurempi kuin tähän mennessä suurin
     * @param nr asetettava numero
     */
    public void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }

    /**
     * @param rivi josta arvostelun tiedot otetaan
     * @example
     * <pre name="test">
     *  Arvostelu arv = new Arvostelu();
     *  arv.parse("   2 | 3   | 3 | 10 | asd");
     *  arv.getArvostelijanId() === 3;
     *  arv.toString()          === "2|3|3|10|asd"
     *  // TODO:jos vielä täyttö + tunnus test.
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        arvostelijanId = Mjonot.erota(sb, '|', arvostelijanId);
        elokuvanId = Mjonot.erota(sb, '|', elokuvanId);
        arvosana = Mjonot.erota(sb, '|', arvosana);
        kommentit = Mjonot.erota(sb, '|', kommentit);
    }
    
    
    /**     
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     */
     public String aseta(int k, String jono) {
         String tjono = jono == null ? "" : jono.trim();
         if (tjono.isEmpty()) {
             return "Input string cannot be empty";
         }
         //StringBuffer sb = new StringBuffer(tjono);
         switch (k) {
             case 0:
                 //setElokuvaId(Mjonot.erota(sb, '§', getElokuvaId()));
                 return null;
             case 1:
                 int luku = Integer.parseInt(tjono);
                 arvosana = luku;
                 return null;
             case 2:
                 kommentit = tjono;
                 return null;
             default:
                 return "Invalid input";
         }
     }
    
    
    @Override
    public String toString() {
        return "" + 
                getTunnusNro() + "|" +
                arvostelijanId + "|" +
                elokuvanId     + "|" +
                arvosana       + "|" +
                kommentit      ;  
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
        arv.taytaArvostelu(2, 2);
        arv.tulosta(System.out, "testiNimi");
    }

}
