package br.com.pedido.data.orm;

import br.com.pedido.data.orm.util.GenericDaoImpl;
import br.com.pedido.entities.Endereco;
import br.com.pedido.util.data.EnderecoData;

public class EnderecoDaoImpl extends GenericDaoImpl<Endereco> implements EnderecoData {

	@Override
	protected Class<Endereco> getEntityClass() {
		return Endereco.class;
	}

}
