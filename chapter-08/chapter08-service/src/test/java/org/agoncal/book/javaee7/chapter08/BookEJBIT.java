package org.agoncal.book.javaee7.chapter08;

import org.junit.Assert;
import org.junit.Test;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by orbot on 22.04.16.
 */
public class BookEJBIT {

    @Test
    public void shouldCreateABook() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
            Context ctx = ec.getContext();
            Assert.assertNotNull(ctx.lookup("java:global/jdbc/chapter08DS"));;
            Assert.assertNotNull(
                    ctx.lookup("java:global/classes/BookEJB!org.agoncal.book.javaee7.chapter08.BookEJB")
            );

            BookEJB bookEJB =
                    (BookEJB)ctx.lookup("java:global/classes/BookEJB!org.agoncal.book.javaee7.chapter08.BookEJB");

            Assert.assertEquals(2, bookEJB.findBooks().size());

            Book book = new Book("H2G2", 12.5F, "Научная фантастика", "1-24561-799-0", 354, false);

            book = bookEJB.createBook(book);
            Assert.assertNotNull("Id не может быть пустым", book.getId());

            Assert.assertEquals(3, bookEJB.findBooks().size());

            bookEJB.deleteBook(book);

            Assert.assertEquals(2, bookEJB.findBooks().size());
        }
    }
}
