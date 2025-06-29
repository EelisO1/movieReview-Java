package elokuva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

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
     * @return nimien määrä alkioissa.
     */
    public int getNimetPituus() {
        return alkiot.size();
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
     * @param tunnusnro millä haetaan
     * @return palauttaa käyttäjän olion
     */
    public Nimimerkki annaKayttajanNimiOlio(int tunnusnro) {
        Nimimerkki loydetty = new Nimimerkki();
        for (Nimimerkki nimi : alkiot) //Iteraattori
            if (nimi.getTunnusnro() == tunnusnro) loydetty = nimi;
        return loydetty;
    } 
    
    
    /**
     * @param hakemisto nimi
     * @throws SailoException virhe
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/nimimerkit.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext() ) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0) == ';') continue;
                Nimimerkki nimimerkki = new Nimimerkki();
                nimimerkki.parse(s);
                lisaa(nimimerkki);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("ei lue " + nimi);            
        }/** catch ( IOException e ) {
            throw new SailoException("Sisältö ei toimi " + nimi);       
        }**/ 
    }
    
    
    /**
     * Tallentaa arvostelut tiedostoon
     * @param nimi tiedoston nimi
     * @throws SailoException jos virhe
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void tallenna(String nimi) throws SailoException {
        File tiedosto = new File(nimi + "/nimimerkit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
            for (var nimet: alkiot) {
                fo.println(nimet.toString());
            }
        }
            catch (FileNotFoundException ex) {
                throw new SailoException("Tiedosto " + tiedosto.getAbsolutePath() + " ei aukia");
                
            }   
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
        
        try {
            nimet.lueTiedostosta("data");
        } catch (SailoException e) {
            System.err.println("ei toimi " + e.getMessage());
        }
        
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
        
        try {
            nimet.tallenna("data");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
