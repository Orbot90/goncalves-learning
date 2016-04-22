package org.agoncal.book.javaee7.chapter08;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by orbot on 22.04.16.
 */
public class DatabaseProducer {
    @Produces
    @PersistenceContext(unitName = "chapter08PU")
    private EntityManager em;
}
