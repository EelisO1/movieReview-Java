package elokuva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author eelis
 * @version 7.3.2024
 *
 */
public class Arvostelut {

    private Collection<Arvostelu> alkiot = new ArrayList<Arvostelu>();
    
    
    /**
     * TODO: MUUTA GETELOKUVANRO
     * Palautetaan mille elokuvalle arvostelu kuuluu
     * @return jäsenen id
     
    public int getTunnusnro() {
        return Nimimerkki.getTunnusnro;
    }
    */
    
    
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
    
    
    /** Testiohjelma Arvosteluille
     * @param args eikäyt.
     */
    public static void main(String[] args) {
        Arvostelut arvostelut = new Arvostelut();
        Arvostelu arvostelu1 = new Arvostelu();
        arvostelu1.tekija(1);
        arvostelu1.rekisteroi();
        arvostelu1.taytaArvostelu(2);
        Arvostelu arvostelu2 = new Arvostelu();
        arvostelu2.tekija(1);
        arvostelu2.rekisteroi();
        arvostelu2.taytaArvostelu(1);
        Arvostelu arvostelu3 = new Arvostelu();
        arvostelu3.tekija(3);
        arvostelu3.rekisteroi();
        arvostelu3.taytaArvostelu(2);
        Arvostelu arvostelu4 = new Arvostelu();
        arvostelu4.tekija(2);
        arvostelu4.rekisteroi();
        arvostelu4.taytaArvostelu(2);
        
        arvostelut.lisaa(arvostelu1);
        arvostelut.lisaa(arvostelu2);
        arvostelut.lisaa(arvostelu3);
        arvostelut.lisaa(arvostelu4);
        
        System.out.println("===================== ARVOSTELUT TESTAUS ==========================");

        
        List<Arvostelu> arvostelut1 = arvostelut.annaArvostelut(1);
        
        for (Arvostelu arv : arvostelut1) {
            System.out.println("Tämän arvostelun jätti: " + arv.getArvostelijanId());
            System.out.println(arv.getElokuvaNro() + " elokuvan arvostelu:");
            arv.tulosta(System.out);
        }
        
        List<Arvostelu> arvostelut2 = arvostelut.annaArvostelut(2);
        
        for (Arvostelu arv : arvostelut2) {
            System.out.println("Tämän arvostelun jätti: " + arv.getArvostelijanId());
            System.out.println(arv.getElokuvaNro() + " elokuvan arvostelu:");
            arv.tulosta(System.out);
        }
    }
}
