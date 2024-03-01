package elokuva;

/**
 * Poikkeusluokka tietorakenteen poikkeuksia varten.
 * @author eelis
 * @version 1.3.2024
 *
 */
public class SailoException extends Exception{
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja mikäli tarvitaan poikkeuksia
     * TODO: myöhemmin pois kai ainakin elokuvat luokasta
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
