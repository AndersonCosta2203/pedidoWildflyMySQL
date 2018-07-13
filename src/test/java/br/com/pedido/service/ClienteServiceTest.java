package br.com.pedido.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.entities.Cliente;
import br.com.pedido.service.builders.ClienteBuilder;
import br.com.pedido.util.data.ClienteData;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private ClienteData clienteData;

    @Mock
    private ClienteNewDTO clienteNewDTO;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void converterEmClienteAPartirDeUmDTO() {  
    	// Cenario
    	Mockito.when(clienteData.fromClienteNewDTO(clienteNewDTO)).thenReturn(ClienteBuilder.umCliente());
    	
    	// Acao
    	Cliente cliente = clienteData.fromClienteNewDTO(clienteNewDTO);
    	
    	// Verificacao
    	
        Assert.assertEquals(cliente.getId(), Integer.valueOf("1"));
    }
}