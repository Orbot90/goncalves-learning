package org.agoncal.book.javaee7.chapter02;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by orbot on 09.04.16.
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Override
    @Loggable
    public String generateNumber() {
        String issn = "8-" + Math.abs(new Random().nextInt());
        logger.info("Сгенерирован ISSN: " + issn);
        return issn;
    }
}
