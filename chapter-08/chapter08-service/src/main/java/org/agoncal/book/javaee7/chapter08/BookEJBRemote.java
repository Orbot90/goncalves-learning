package org.agoncal.book.javaee7.chapter08;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by orbot on 22.04.16.
 */
@Remote
public interface BookEJBRemote {
    List<Book> findBooks();

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Book book);
}
