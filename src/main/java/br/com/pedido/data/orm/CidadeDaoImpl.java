package br.com.pedido.data.orm;

import org.apache.log4j.Logger;

import br.com.pedido.data.orm.util.GenericDaoImpl;
import br.com.pedido.entities.Cidade;
import br.com.pedido.util.data.CidadeData;

public class CidadeDaoImpl extends GenericDaoImpl<Cidade> implements CidadeData{
	private final Logger logger = Logger.getLogger(getEntityClass());
	
	@Override
	protected Class<Cidade> getEntityClass() {
		return Cidade.class;
	}
	
    public Cidade findByNome(String nome) {
    	logger.info("processando findByNome");
        try {
        	return (Cidade) this.executeQuerySingleResult("Select e from Cidade e where e.nome like :NOME", nome);
        } catch (Exception e) {
        	logger.error("Erro ao buscar por nome", e);
            return null;
        }
    }

}
