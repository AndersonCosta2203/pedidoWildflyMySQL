package br.com.pedido.util.data;

import br.com.pedido.entities.Estado;

public interface EstadoData extends GenericData<Estado>{
	
	public Estado findByNome(String nome);
}
