package elokuva;

import java.io.File;
import java.util.List;

import fi.jyu.mit.fxgui.Dialogs;

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
    private Arvostelut arvostelut = new Arvostelut();
    private Nimimerkit nimimerkit = new Nimimerkit();
    
    
    /**
     * Lisätään uusi elokuva
     * @param elokuva Lisättävä elokuva
     * @throws SailoException Mitä poikkeusta käytetään mikäli sellainen sattuu / lisääminen ei onnistu
     */
    public void lisaa(Elokuva elokuva) throws SailoException {
        elokuvat.lisaa(elokuva);
    }
    

    /**
     * @param nimi käyttäjän nimi
     */
    public void luoKayttaja(String nimi) {
        Nimimerkki uusi = new Nimimerkki();
        uusi.rekisteroi();
        uusi.setNimi(nimi);
        nimimerkit.lisaa(uusi);
    }
    
    
    /**
     * @return nimimerkit tietokannan pituus
     */
    public int getNimetPituus() {
        return nimimerkit.getNimetPituus();
    }
    
    
    /**
     * Lisätään uusi arvostelu
     * @param arv lisättävä arvostelu
     */
    public void lisaa(Arvostelu arv) {
        arvostelut.lisaa(arv);
    }
    
    
    /**
     * Lisätään uusi nimi
     * @param nimi Lisättävä nimi
     */
    public void lisaa(Nimimerkki nimi) {
        nimimerkit.lisaa(nimi);
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
     * Poistaa elokuvan ja siihen liittyvät arvostelut
     * @param elokuva Poistettava elokuva
     * @throws SailoException jos menee pieleen
     * <pre name="test">
     *   #THROWS SailoException
     *   KokoElokuva kokoelokuva = new KokoElokuva();
     *   Elokuva elokuva1 = new Elokuva(); elokuva1.lisaaElokuva(); // ID 1
     *   Elokuva elokuva2 = new Elokuva(); elokuva2.lisaaElokuva(); // ID 2
     *   Arvostelu arv1 = new Arvostelu(); arv1.taytaArvostelu(1, 1);
     *   Arvostelu arv2 = new Arvostelu(); arv2.taytaArvostelu(1, 2);
     *   kokoelokuva.lisaa(elokuva1);
     *   kokoelokuva.lisaa(elokuva2);
     *   kokoelokuva.lisaa(arv1);
     *   kokoelokuva.lisaa(arv2);
     *   kokoelokuva.getElokuvat() === 2;
     *   kokoelokuva.annaArvostelut(elokuva1).size() === 0; //TODO: tarkista
     *   kokoelokuva.poista(elokuva1);
     *   kokoelokuva.getElokuvat() === 1;
     *   kokoelokuva.annaElokuva(0) == elokuva2 === true;
     *   kokoelokuva.annaArvostelut(elokuva1).size() === 0;
     *   // Null lisäys
     *   kokoelokuva.poista(null);
     *   kokoelokuva.getElokuvat() === 1;
     *   // Elokuva ilman arvosteluja
     *   Elokuva elokuva3 = new Elokuva(); elokuva3.lisaaElokuva(); // ID 3
     *   kokoelokuva.lisaa(elokuva3);
     *   kokoelokuva.getElokuvat() === 2;
     *   kokoelokuva.poista(elokuva2); // Ei arvosteluja
     *   kokoelokuva.getElokuvat() === 1;
     *   kokoelokuva.annaElokuva(0) == elokuva3 === true;
     * </pre>
     */
    public void poista(Elokuva elokuva) throws SailoException {
        if (elokuva == null) return;
        elokuvat.poista(elokuva.getElokuvaId());
        arvostelut.poistaElokuvanArvostelut(elokuva.getElokuvaId());
    }
    
    
    /**
     * @param tunnusnro Minkä / wtf -> elokuvan arvostelut
     * @return Pyydetyn arvostelut
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Nimimerkit nimimerkit = new Nimimerkit(); 
     *  
     *  Nimimerkki nimi1 = new Nimimerkki(); nimi1.rekisteroi(); nimimerkit.lisaa(nimi1);
     *  Nimimerkki nimi2 = new Nimimerkki(); nimi2.rekisteroi(); nimimerkit.lisaa(nimi2);
     *  Nimimerkki nimi3 = new Nimimerkki(); nimi3.rekisteroi(); nimimerkit.lisaa(nimi3);
     *  Nimimerkki nimi4 = new Nimimerkki(); nimi4.rekisteroi(); nimimerkit.lisaa(nimi4);
     *  Nimimerkki nimi5 = new Nimimerkki(); nimi5.rekisteroi(); nimimerkit.lisaa(nimi5);
     *  Nimimerkki nimi6 = new Nimimerkki(); nimi6.rekisteroi(); nimimerkit.lisaa(nimi6);
     *   
     *  List<Nimimerkki> loytyneet;
     *  loytyneet = nimimerkit.annaNimet(1);
     *  loytyneet.size() === 1;
     *  loytyneet = nimimerkit.annaNimet(2);
     *  loytyneet.size() === 1;
     *  loytyneet = nimimerkit.annaNimet(3);
     *  loytyneet.size() === 1;
     *  loytyneet.get(0) == nimi3 === true;
     *  loytyneet.get(0) == nimi2 === false;
     *  loytyneet = nimimerkit.annaNimet(3);
     *  loytyneet.size() === 1;
     *  loytyneet.get(0) == nimi3 === true;
     *  loytyneet.get(0) == nimi1 === false;
     * </pre>
     */
    public List<Nimimerkki> annaNimet(int tunnusnro) {
        return nimimerkit.annaNimet(tunnusnro);
    } 
    
    
    /**
     * @param tunnusnro Haetaan kannasta viimeinen eli uusin joka on tämänhetkinen käyttäjä
     * @return käyttäjän olio
     */
    public Nimimerkki annaKayttajanNimiOlio(int tunnusnro) {
        return nimimerkit.annaKayttajanNimiOlio(tunnusnro);
    }
    
    
    /**
     * @param tunnusnro nro
     * @return nimi
     */
    public String annaNimi(int tunnusnro) {
        return nimimerkit.annaNimi(tunnusnro);
    } 
    
    /**
     * @throws SailoException jos tulee virhe
     */
    /*
    public void lueTiedostosta() throws SailoException {
        elokuvat = new Elokuvat();
        
    }*/
    
    /**
     *  Minkä elokuvan arvostelut halutaan etsiä
     * @param elokuva Minkä elokuvan harrastukset?
     * @return antaa numeron jolla voidaan hakea arvostelut tietystä elokuvasta
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *    KokoElokuva kokoelokuva = new KokoElokuva();
     *    Elokuva elokuva1 = new Elokuva(); Elokuva elokuva2 = new Elokuva(); Elokuva elokuva3 = new Elokuva();
     *    elokuva1.lisaaElokuva(); elokuva2.lisaaElokuva(); elokuva3.lisaaElokuva(); 
     *    int id1 = elokuva1.getElokuvaId();
     *    int id2 = elokuva2.getElokuvaId();
     *    Arvostelu arvostelu11 = new Arvostelu(id1); kokoelokuva.lisaa(arvostelu11);
     *    Arvostelu arvostelu12 = new Arvostelu(id1); kokoelokuva.lisaa(arvostelu12);
     *    Arvostelu arvostelu21 = new Arvostelu(id2); kokoelokuva.lisaa(arvostelu21);
     *    Arvostelu arvostelu22 = new Arvostelu(id2); kokoelokuva.lisaa(arvostelu22);
     *    Arvostelu arvostelu23 = new Arvostelu(id2); kokoelokuva.lisaa(arvostelu23);
     *    
     *    List<Arvostelu> loytyneet;
     *    loytyneet = kokoelokuva.annaArvostelut(elokuva3);
     *    loytyneet.size() === 0;
     *    loytyneet = kokoelokuva.annaArvostelut(elokuva1);
     *    loytyneet.size() === 2;
     *    loytyneet.get(0) == arvostelu11 === true;
     *    loytyneet.get(1) == arvostelu12 === true;
     *    loytyneet = kokoelokuva.annaArvostelut(elokuva2);
     *    loytyneet.size() === 3;
     *    loytyneet.get(0) == arvostelu21 === true;
     * </pre> 
     */
    public List<Arvostelu> annaArvostelut(Elokuva elokuva) {
        return arvostelut.annaArvostelut(elokuva.getElokuvaId());
    }
    
    
    /** Lukee ohjelman tiedot tiedostosta
     * @param hakemisto Tiedoston nimi
     * @throws SailoException jos tulee virheitä
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        File dir = new File(hakemisto);
        dir.mkdir();
        elokuvat = new Elokuvat();
        arvostelut = new Arvostelut();
        nimimerkit = new Nimimerkit();
        
        elokuvat.lueTiedostosta(hakemisto);
        arvostelut.lueTiedostosta(hakemisto);
        nimimerkit.lueTiedostosta(hakemisto);
    }
    
    
    /**
     * Tallentaa tiedot tiedostoon
     * @param hakemisto tiedoston nimi
     * @throws SailoException jos tulee virheitä
     */
    public void tallenna(String hakemisto) throws SailoException {
        String virhe = "";
        try {
            elokuvat.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        try {
            arvostelut.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        try {
            nimimerkit.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        if (!"".equals(virhe) ) throw new SailoException(virhe);
    }
    
    
    /**
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        KokoElokuva kokoelokuva = new KokoElokuva();
        
        try {
            kokoelokuva.lueTiedostosta("data");
        } catch (SailoException e) {
            System.err.println("ei toimi " + e.getMessage());
        }
        
        try {
            Elokuva elokuva1 = new Elokuva();
            Elokuva elokuva2 = new Elokuva();
            
            elokuva1.lisaaElokuva();
            elokuva1.taytaValmisLeffa();
            
            elokuva2.lisaaElokuva();
            elokuva2.taytaValmisLeffa();
            
            kokoelokuva.lisaa(elokuva1);
            kokoelokuva.lisaa(elokuva2);
            
            
            Nimimerkki nimimerkki1 = new Nimimerkki();
            nimimerkki1.rekisteroi();
            nimimerkki1.taytaNimi();

            
            
            Arvostelu arvostelu1 = new Arvostelu();
            arvostelu1.rekisteroi();
            arvostelu1.taytaArvostelu(elokuva2.getElokuvaId(), nimimerkki1.getTunnusnro());

            Arvostelu arvostelu6 = new Arvostelu();
            arvostelu6.rekisteroi();
            arvostelu6.taytaArvostelu(elokuva1.getElokuvaId(), nimimerkki1.getTunnusnro());

            
            Arvostelu arvostelu2 = new Arvostelu();
            arvostelu2.rekisteroi();
            arvostelu2.taytaArvostelu(elokuva1.getElokuvaId(), nimimerkki1.getTunnusnro());
            Arvostelu arvostelu3 = new Arvostelu();
            arvostelu3.rekisteroi();
            arvostelu3.taytaArvostelu(elokuva2.getElokuvaId(), nimimerkki1.getTunnusnro());
            Arvostelu arvostelu4 = new Arvostelu();
            arvostelu4.rekisteroi();
            arvostelu4.taytaArvostelu(elokuva2.getElokuvaId(), nimimerkki1.getTunnusnro());
            
            kokoelokuva.lisaa(arvostelu1);
            kokoelokuva.lisaa(arvostelu2);
            kokoelokuva.lisaa(arvostelu3);
            kokoelokuva.lisaa(arvostelu4);
            
            kokoelokuva.lisaa(nimimerkki1);
        
            System.out.println("===================== TESTAUS ==========================");
    
            for (int i = 0; i < kokoelokuva.getElokuvat(); i++) {
                Elokuva elokuva = kokoelokuva.annaElokuva(i);
                elokuva.tulosta(System.out);
                
                List<Arvostelu> arvostelutList = kokoelokuva.annaArvostelut(elokuva);
                
                for (Arvostelu arv : arvostelutList) {
                    System.out.println(arv.getElokuvaNro() + " elokuvan arvostelu:");
                    int arvostelija = arv.getArvostelijanId();  
                    String arvostelijaNimi = kokoelokuva.annaNimi(arvostelija);
                    arv.tulosta(System.out, arvostelijaNimi);
                }
            }
            
            try {
                kokoelokuva.tallenna("data");
            } catch (SailoException e) {
                e.printStackTrace();
            }
            
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
    }
}
