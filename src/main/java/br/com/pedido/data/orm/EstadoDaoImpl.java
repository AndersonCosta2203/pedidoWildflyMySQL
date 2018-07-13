package br.com.pedido.data.orm;

import org.apache.log4j.Logger;

import br.com.pedido.data.orm.util.GenericDaoImpl;
import br.com.pedido.entities.Estado;
import br.com.pedido.util.data.EstadoData;

public class EstadoDaoImpl extends GenericDaoImpl<Estado> implements EstadoData {
	private final Logger logger = Logger.getLogger(getEntityClass());
	
	@Override
	protected Class<Estado> getEntityClass() {
		return Estado.class;
	}

	public Estado findByNome(String nome) {
		logger.info("processando findByNome");
        try {
            return (Estado) this.executeQuerySingleResult("Select e from Estado e where e.nome like :NOME", "%" + nome + "%");
        } catch (Exception ex) {
        	logger.error("Erro ao pesquisar pelo nome", ex);
            return null;
        }
    }	

}
