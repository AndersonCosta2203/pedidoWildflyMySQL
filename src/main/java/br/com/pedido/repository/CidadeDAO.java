package br.com.pedido.repository;

import br.com.pedido.domain.Cidade;

import javax.persistence.Query;

public class CidadeDAO extends DAOGenerico<Cidade> {

    public Cidade findByNome(String nome) {
        try {
            Query query = entityManager.createQuery("Select e from Cidade e where e.nome like :NOME");
            query.setParameter("NOME", "%" + nome + "%");
            return (Cidade) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
