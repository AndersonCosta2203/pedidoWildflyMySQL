package br.com.pedido.data.orm;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.pedido.data.orm.util.GenericDaoImpl;
import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.entities.Cidade;
import br.com.pedido.entities.Cliente;
import br.com.pedido.entities.Endereco;
import br.com.pedido.util.data.CidadeData;
import br.com.pedido.util.data.ClienteData;

public class ClienteDaoImpl extends GenericDaoImpl<Cliente> implements ClienteData{	
	private final Logger logger = Logger.getLogger(getEntityClass());
	
	@Inject
	private CidadeData cidadeData;

	@Override
	protected Class<Cliente> getEntityClass() {
		return Cliente.class;
	}

    public Cliente fromClienteNewDTO(ClienteNewDTO obj) {
    	logger.info("processando fromClienteNewDTO");
    	
        Cliente cliente = new Cliente(obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), obj.getTipo());
        Cidade cidade = cidadeData.findById(obj.getEndereco().getCidade());
        Endereco endereco = new Endereco(obj.getEndereco().getLogradouro(), obj.getEndereco().getNumero(), obj.getEndereco().getComplemento(),
                obj.getEndereco().getBairro(), obj.getEndereco().getCep(), cidade, cliente);

        cliente.getEnderecos().add(endereco);
        return cliente;
    }

}
