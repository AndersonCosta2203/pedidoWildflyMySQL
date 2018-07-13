package br.com.pedido.util.data;

import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.entities.Cliente;

public interface ClienteData extends GenericData<Cliente>{

	public Cliente fromClienteNewDTO(ClienteNewDTO obj);
}
