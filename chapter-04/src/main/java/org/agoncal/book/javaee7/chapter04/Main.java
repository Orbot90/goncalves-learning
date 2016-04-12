package org.agoncal.book.javaee7.chapter04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by orbot on 12.04.16.
 */
public class Main {
    public static void main(String[] args) {
        Book book = new Book("H2G2", "Автостопом по Галактике", 12.5f, "1-84023-742-2", 354, false);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();

        em.close();
        emf.close();
    }
}
