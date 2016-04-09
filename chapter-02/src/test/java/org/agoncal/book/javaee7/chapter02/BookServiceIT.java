package org.agoncal.book.javaee7.chapter02;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by orbot on 09.04.16.
 */
public class BookServiceIT {

    @Test
    public void shourdCheckNumberIsMOCK() {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        BookService bookService = container.instance().select(BookService.class).get();
        Book book = bookService.createBook("H2G2", 12.5f, "Интересная книга на тему высоких" +
                "технологий");
        assertTrue(book.getNumber().startsWith("MOCK"));
    }
}
