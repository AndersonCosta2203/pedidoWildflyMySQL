package br.com.pedido.repository;

import br.com.pedido.config.ConnectionFactory;
import br.com.pedido.domain.Estado;

import javax.persistence.*;
import java.util.List;

public class EstadoDAO extends DAOGenerico<Estado> {

    public Estado findByNome(String nome) {
        try {
            Query query = entityManager.createQuery("Select e from Estado e where e.nome like :NOME");
            query.setParameter("NOME", "%" + nome + "%");
            return (Estado) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
