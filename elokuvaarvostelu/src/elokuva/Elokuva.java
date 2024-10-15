package elokuva;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Tarkistukset;

/**
 * @author eelis
 * @version 1.3.2024
 *
 */
public class Elokuva {
    private int     elokuvaId  = 0; 
    private String  nimi       = "";
    private int     vuosi      = 0;
    private double  pituus     = 0;
    private String  genre      = "";
    private int     ikaraja    = 0;
    private String  kuvaus     = "";
    
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
     * Asettaa nimen ja palauttaa virheen
     * @param s uusi nimi
     * @return null jos ok
     */
    public String setNimi(String s) {
        this.nimi = s;
        return null;
    }  
    
    
    /**
     * @param id id
     */
    public void setElokuvaId(int id) {
        elokuvaId = id;
        if (elokuvaId >= seuraavaId) seuraavaId = elokuvaId + 1;
    }
    
    
    /**
     * @return Palauttaa "this." tämän elokuvan nimen
     */
    public String getElokuvaNimi() {
        return nimi;
    }
    
    /**
     * @return Palauttaa elokuvan kuvauksen
     */
    public String getKuvaus() {
        return kuvaus;
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
        kuvaus     = "Elokuva kertoo tarinan" + Tarkistukset.arvoLuku(1,18);
    }
    
    /**
     *  Täyttää elokuvan tiedot annetuilla arvoilla
     * @param vuosiluku vuosi
     * @param elokuvanPituus pituus
     * @param elokuvanNimi nimi
     * @param elokuvanGenre genre
     * @param elokuvanIkaraja ikaraja
     * @param elokuvanKuvaus Kuvaus
     */
    public void taytaLeffa(int vuosiluku, double elokuvanPituus, String elokuvanNimi, String elokuvanGenre, int elokuvanIkaraja, String elokuvanKuvaus) {
        vuosi      = vuosiluku; 
        pituus     = elokuvanPituus; 
        nimi       = elokuvanNimi; 
        genre      = elokuvanGenre;
        ikaraja    = elokuvanIkaraja;
        kuvaus     = elokuvanKuvaus;
    }
    
    
    /*
     * 
     */
    @Override
    public String toString() {
        return "" + 
                    getElokuvaId() + "|" +
                    nimi           + "|" +
                    vuosi          + "|" +
                    pituus         + "|" +
                    genre          + "|" +
                    ikaraja        + "|" +
                    kuvaus         ;          
    }
    
    
    /**
     * Selvittää elokuvien tiedot '|' erotelluista
     * Pitää myös huolen että seuraava elokuvan id on suurempi kuin tuleva id 
     * @param rivi luettu rivi
     * @example
     * <pre name="test">
     *  Elokuva elokuva = new Elokuva();
     *  elokuva.parse("      3 | Aku Ankka | 23");
     *  elokuva.getElokuvaId() === 3;
     *  elokuva.toString().startsWith("3|Aku Ankka|23") === true;
     *  
     *  elokuva.taytaValmisLeffa();
     *  int n = elokuva.getElokuvaId();
     *  elokuva.parse(""+(n+20)); //otetaan vain id
     *  elokuva.taytaValmisLeffa();
     *  elokuva.getElokuvaId() === n+20;
     * </pre>
     */
    public void parse(String rivi)  {
        StringBuffer sb = new StringBuffer(rivi);
        setElokuvaId(Mjonot.erota(sb, '|', getElokuvaId()));
        nimi = Mjonot.erota(sb, '|', nimi);
        vuosi = Mjonot.erota(sb, '|', vuosi);
        pituus = Mjonot.erota(sb, '|', pituus);
        genre = Mjonot.erota(sb, '|', genre);
        ikaraja = Mjonot.erota(sb, '|', ikaraja);
        kuvaus = Mjonot.erota(sb, '|', kuvaus);
    }
    
    
    /**
     * @return Palauttaa ensimmäisen mielekkään kentän indeksin
     */
    public int ekaKentta() {
        return 1;
    }
    
    
    /**
     * @return palauttaa kenttien määrän
     */
    public int getKenttia() {
        return 7;
    }  
    
    
    /**     
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     * @example
     * <pre name="test">
     *   Jasen jasen = new Jasen();
     *   jasen.aseta(1,"Ankka Aku") === null;
     *   jasen.aseta(2,"kissa") =R= "Hetu liian lyhyt"
     *   jasen.aseta(2,"030201-1111") === "Tarkistusmerkin kuuluisi olla C"; 
     *   jasen.aseta(2,"030201-111C") === null; 
     *   jasen.aseta(9,"kissa") === "Liittymisvuosi väärin jono = \"kissa\"";
     *   jasen.aseta(9,"1940") === null;
     * </pre>
     */
     public String aseta(int k, String jono) {
         String tjono = jono == null ? "" : jono.trim();
         if (tjono.isEmpty()) {
             return "Input string cannot be empty";
         }
         StringBuffer sb = new StringBuffer(tjono);
         switch (k) {
             case 0:
                 setElokuvaId(Mjonot.erota(sb, '§', getElokuvaId()));
                 return null;
             case 1:
                 nimi = tjono;
                 return null;
             case 2:
                 vuosi = Mjonot.erota(sb, '§', vuosi);
                 return null;
             case 3:
                 pituus = Mjonot.erota(sb, '§', pituus);
                 return null;
             case 4:
                 ikaraja = Mjonot.erota(sb, '§', ikaraja);
                 return null;
             case 5:
                 genre = tjono;
                 return genre;
             case 6:
                 kuvaus = tjono;
                 return null;
             default:
                 return "Invalid input";
         }
     }

    
    /**
     * Palauttaa k:tta elokuvan kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Elokuvan ID:";
        case 1: return "Elokuvan nimi:";
        case 2: return "Vuosi:";
        case 3: return "Kesto:";
        case 4: return "Ikäraja:";
        case 5: return "Genre:";
        case 6: return "Kuvaus:";
        default: return "väärin meni:";
        }
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
