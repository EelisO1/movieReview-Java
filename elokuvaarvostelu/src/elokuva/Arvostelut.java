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
 * @version 7.3.2024
 *
 */
public class Arvostelut {

    // alkiot siis kai arvostelut
    private Collection<Arvostelu> alkiot = new ArrayList<Arvostelu>();
    
    
    /**
     * TODO: MUUTA GETELOKUVANRO
     * Palautetaan mille elokuvalle arvostelu kuuluu
     * @return jäsenen id
     * Ei vissiin tarvi
    public int getTunnusnro() {
        return Nimimerkki.getTunnusnro();
    }
     */     
    
    
    /**
     * Poistaa kaikki id:n alla olevat arvostelut
     * @param elokuvaId Elokuvan id jonka arvostelut poistetaan
     * @example
     * <pre name="test">
     *   Arvostelut arvostelut = new Arvostelut();
     *   Arvostelu arv1 = new Arvostelu(); arv1.taytaArvostelu(1, 1); arvostelut.lisaa(arv1);
     *   Arvostelu arv2 = new Arvostelu(); arv2.taytaArvostelu(1, 2); arvostelut.lisaa(arv2);
     *   Arvostelu arv3 = new Arvostelu(); arv3.taytaArvostelu(2, 1); arvostelut.lisaa(arv3);
     *   arvostelut.annaArvostelut(1).size() === 2;
     *   arvostelut.annaArvostelut(2).size() === 1;
     *   arvostelut.poistaElokuvanArvostelut(1); // Poistetaan ID 1 arvostelut
     *   arvostelut.annaArvostelut(1).size() === 0;
     *   arvostelut.annaArvostelut(2).size() === 1;
     *   arvostelut.poistaElokuvanArvostelut(2); // Poistetaan ID 2 arvostelut
     *   arvostelut.annaArvostelut(2).size() === 0;
     *   arvostelut.poistaElokuvanArvostelut(999); // Ei olemassa
     *   arvostelut.annaArvostelut(1).size() === 0; // Ei muutosta
     * </pre>
     */
    public void poistaElokuvanArvostelut(int elokuvaId) {
        alkiot.removeIf(arvostelu -> arvostelu.getElokuvaNro() == elokuvaId);
    }
    
    
    /**
     * @param arv lisättävä arvostelu
     */
    public void lisaa(Arvostelu arv) {
        alkiot.add(arv);
    }
    
    /**
     * @param elokuvanId Minkä elokuvan arvostelut
     * @return Pyydetyn arvostelut
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Arvostelut arvostelut = new Arvostelut(); 
     *  
     *  Arvostelu arvostelu1  = new Arvostelu(1); arvostelut.lisaa(arvostelu1);
     *  Arvostelu arvostelu2  = new Arvostelu(3); arvostelut.lisaa(arvostelu2);
     *  Arvostelu arvostelu13 = new Arvostelu(1); arvostelut.lisaa(arvostelu13);
     *  Arvostelu arvostelu14 = new Arvostelu(1); arvostelut.lisaa(arvostelu14);
     *  Arvostelu arvostelu15 = new Arvostelu(2); arvostelut.lisaa(arvostelu15);
     *  Arvostelu arvostelu16 = new Arvostelu(2); arvostelut.lisaa(arvostelu16);
     *   
     *  List<Arvostelu> loytyneet;
     *  loytyneet = arvostelut.annaArvostelut(4);
     *  loytyneet.size() === 0;
     *  loytyneet = arvostelut.annaArvostelut(3);
     *  loytyneet.size() === 1;
     *  loytyneet = arvostelut.annaArvostelut(1);
     *  loytyneet.size() === 3;
     *  loytyneet.get(0) == arvostelu1 === true;
     *  loytyneet.get(1) == arvostelu13 === true;
     *  loytyneet = arvostelut.annaArvostelut(2);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == arvostelu15 === true;
     *  loytyneet.get(0) == arvostelu1 === false;
     * </pre>
     */
    public List<Arvostelu> annaArvostelut(int elokuvanId) {
        List<Arvostelu> loydetyt = new ArrayList<Arvostelu>();
        for (Arvostelu arv : alkiot) //Iteraattori
            if (arv.getElokuvaNro() == elokuvanId) loydetyt.add(arv);
        return loydetyt;
    } 
    
    
    /**
     * @param hakemisto nimi
     * @throws SailoException virhe
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/arvostelut.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext() ) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0) == ';') continue;
                Arvostelu arvostelu = new Arvostelu();
                arvostelu.parse(s);
                lisaa(arvostelu);
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
        File tiedosto = new File(nimi + "/arvostelut.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
            for (var arv: alkiot) {
                fo.println(arv.toString());
            }
        }
            catch (FileNotFoundException ex) {
                throw new SailoException("Tiedosto " + tiedosto.getAbsolutePath() + " ei aukia");
                
            }   
    }
    
    
    /** Testiohjelma Arvosteluille
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Arvostelut arvostelut = new Arvostelut();
        
        try {
            arvostelut.lueTiedostosta("data");
        } catch (SailoException e) {
            System.err.println("ei toimi " + e.getMessage());
        }
        
        
        Arvostelu arvostelu1 = new Arvostelu();
        arvostelu1.tekija(1);
        arvostelu1.rekisteroi();
        arvostelu1.taytaArvostelu(2, 2);
        Arvostelu arvostelu2 = new Arvostelu();
        arvostelu2.tekija(1);
        arvostelu2.rekisteroi();
        arvostelu2.taytaArvostelu(1, 2);
        Arvostelu arvostelu3 = new Arvostelu();
        arvostelu3.tekija(3);
        arvostelu3.rekisteroi();
        arvostelu3.taytaArvostelu(2, 2);
        Arvostelu arvostelu4 = new Arvostelu();
        arvostelu4.tekija(2);
        arvostelu4.rekisteroi();
        arvostelu4.taytaArvostelu(2, 2);
        
        arvostelut.lisaa(arvostelu1);
        arvostelut.lisaa(arvostelu2);
        arvostelut.lisaa(arvostelu3);
        arvostelut.lisaa(arvostelu4);
        
        System.out.println("===================== ARVOSTELUT TESTAUS ==========================");

        
        List<Arvostelu> arvostelut1 = arvostelut.annaArvostelut(1);
        
        for (Arvostelu arv : arvostelut1) {
            System.out.println("Tämän arvostelun jätti: " + arv.getArvostelijanId());
            System.out.println(arv.getElokuvaNro() + " elokuvan arvostelu:");
            arv.tulosta(System.out, "Aku");
        }
        
        List<Arvostelu> arvostelut2 = arvostelut.annaArvostelut(2);
        
        for (Arvostelu arv : arvostelut2) {
            System.out.println("Tämän arvostelun jätti: " + arv.getArvostelijanId());
            System.out.println(arv.getElokuvaNro() + " elokuvan arvostelu:");
            arv.tulosta(System.out, "Aku");
        }
        
        try {
            arvostelut.tallenna("data");
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
    }
}
