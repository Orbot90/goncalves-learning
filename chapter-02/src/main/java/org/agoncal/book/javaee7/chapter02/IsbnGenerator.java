package org.agoncal.book.javaee7.chapter02;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by orbot on 09.04.16.
 */
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String isbn = "13-84356-" + Math.abs(new Random().nextInt());
        logger.info("Сгенерирован ISBN: " + isbn);
        return isbn;
    }
}
