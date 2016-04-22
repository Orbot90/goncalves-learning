package org.agoncal.book.javaee7.chapter08;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by orbot on 22.04.16.
 */
@Singleton
@Startup
@DataSourceDefinition(
        className = "org.apache.derby.jdbc.EmbeddedDataSource",
        name = "java:global/jdbc/chapter08DS",
        user = "app",
        password = "app",
        databaseName = "chapter08DB",
        properties = {"connectionAttributes=;create=true"}
)
public class DatabasePopulator {

    @Inject
    private BookEJB bookEJB;
    private Book h2g2;
    private Book lord;

    @PostConstruct
    private void populateDB() {
        h2g2 = new Book("Ищучаем Java EE 7", 35F, "Великая книга", "1-8763-9125-7", 605, true);
        lord = new Book("Властелин колец", 50.4f, "Фэнтези", "1-84023-742-2", 1216, true);
        bookEJB.createBook(h2g2);
        bookEJB.createBook(lord);
    }

    @PreDestroy
    private void clearDB() {
        bookEJB.deleteBook(h2g2);
        bookEJB.deleteBook(lord);
    }
}
