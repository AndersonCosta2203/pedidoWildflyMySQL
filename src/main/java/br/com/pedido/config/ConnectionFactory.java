package br.com.pedido.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("MySqlDS");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}