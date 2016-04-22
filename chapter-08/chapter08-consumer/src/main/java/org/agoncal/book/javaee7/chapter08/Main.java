package org.agoncal.book.javaee7.chapter08;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by orbot on 22.04.16.
 */
public class Main {
    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();
        BookEJBRemote bookEJB = (BookEJBRemote)ctx.lookup("java:global/" +
                "chapter08-service-1.0/BookEJB!org.agoncal.book.javaee7.chapter08.BookEJBRemote");

        List<Book> books = bookEJB.findBooks();
        for(Book aBook : books) {
            System.out.println(aBook);
        }

        Book book = new Book("H2G2", 12.5F, "Научная фантастика", "1-24561-799-0", 354, false);

        book = bookEJB.createBook(book);
        System.out.println("Создана книга : " + book);
        book.setTitle("H2G2");
        book = bookEJB.updateBook(book);
        System.out.println("Создана книга : " + book);
        bookEJB.deleteBook(book);
        System.out.println("Книга удалена");
    }
}
