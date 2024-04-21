package elokuva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author eelis
 * @version 26.3.2024
 *
 */
public class Nimimerkit {
    
    private Collection<Nimimerkki> alkiot = new ArrayList<Nimimerkki>();
    
    
    /**
     * @param nimi lisättävä nimimerkki
     */
    public void lisaa(Nimimerkki nimi) {
        alkiot.add(nimi);
    }
    
    
    /**
     * @param tunnusnro Minkä elokuvan arvostelut
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
        List<Nimimerkki> loydetyt = new ArrayList<Nimimerkki>();
        for (Nimimerkki nimi : alkiot) //Iteraattori
            if (nimi.getTunnusnro() == tunnusnro) loydetyt.add(nimi);
        return loydetyt;
    } 
    
    
    /**
     * @param tunnusnro s
     * @return nimi
     */
    public String annaNimi(int tunnusnro) {
        for (Nimimerkki nimi : alkiot) //Iteraattori
            if (nimi.getTunnusnro() == tunnusnro) return nimi.getNimi();
        return "";
    } 
    
    
    /** Testiohjelma Arvosteluille
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Nimimerkit nimet = new Nimimerkit();
        Nimimerkki nimi1 = new Nimimerkki();
        nimi1.rekisteroi();
        nimi1.taytaNimi();
        Nimimerkki nimi2 = new Nimimerkki();
        nimi2.rekisteroi();
        nimi2.taytaNimi();
        Nimimerkki nimi3 = new Nimimerkki();
        nimi3.rekisteroi();
        nimi3.taytaNimi();
        Nimimerkki nimi4 = new Nimimerkki();
        nimi4.rekisteroi();
        nimi4.taytaNimi();
        
        nimet.lisaa(nimi1);
        nimet.lisaa(nimi2);
        nimet.lisaa(nimi3);
        nimet.lisaa(nimi4);
        
        System.out.println("===================== ARVOSTELUT TESTAUS ==========================");

        
        List<Nimimerkki> nimet1 = nimet.annaNimet(1);
        
        for (Nimimerkki nimi : nimet1) {
            System.out.println(nimi.getTunnusnro());
            nimi.tulosta(System.out);
        }
        
        List<Nimimerkki> nimet2 = nimet.annaNimet(2);
        
        for (Nimimerkki nimi : nimet2) {
            System.out.println(nimi.getTunnusnro());
            nimi.tulosta(System.out);
        }
    }
}
