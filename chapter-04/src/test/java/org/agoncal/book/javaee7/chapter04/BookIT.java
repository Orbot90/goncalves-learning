package org.agoncal.book.javaee7.chapter04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by orbot on 12.04.16.
 */
public class BookIT {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04TestPU");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (em != null) em.close();
    }

    @Test
    public void shouldFindjavaee7Book() throws Exception {
        Book book = em.find(Book.class, 1001L);
        assertEquals("Изучаем Java EE 7", book.getTitle());
    }

    @Test
    public void shouldCreateH2G2Book() throws Exception {
        Book book = new Book("H2G2", "Автостопом по Галактике", 12.5F, "1-84023-742-2", 354, false);

        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID не может быть пустым", book.getId());

        book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        assertEquals("Автостопом по Галактике", book.getDescription());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationExceptionCauseNullTitle() {
        Book book = new Book(null, "Пустое название, ошибка", 12.5F, "1-84023-742-2", 354, false);
        em.persist(book);
    }
}
