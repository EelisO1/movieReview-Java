package elokuva.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Kaikki tarvittavat testit
 * @author eelis
 * @version 24.3.2024
 *
 */
@Suite
@SelectClasses({ ArvosteluTest.class, ArvostelutTest.class, ElokuvaTest.class,
        ElokuvatTest.class, KokoElokuvaTest.class })
public class AllTests {
    //
}
