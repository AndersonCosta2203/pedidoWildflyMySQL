package br.com.pedido.resource.impl;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.entities.Cliente;
import br.com.pedido.resource.IResource;
import br.com.pedido.util.data.ClienteData;

@Path("/clientes")
public class ClienteResource implements IResource<Cliente> {

    @Inject
    private ClienteData clienteData;

    @Override
    public Response findAll() {
        return Response.ok().entity(clienteData.findAll()).build();
    }

    @Override
    public Response insert(Cliente obj, UriInfo uriInfo) {
        return null;
    }

    @POST
    @Path("/cliente-newdto")
    @Produces({ "application/json" })
    public Response insert(ClienteNewDTO obj, @Context UriInfo uriInfo) {
        Cliente cliente = clienteData.fromClienteNewDTO(obj);

        final Cliente newCliente = clienteData.insert(cliente);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCliente.getId().toString()).build();
        return Response.created(uri).entity(newCliente).build();
    }

    @Override
    public Response findById(Integer id) {
        return Response.ok().entity(clienteData.findById(id)).build();
    }

    @Override
    public Response update(Integer id, Cliente obj) {
        obj.setId(id);
        clienteData.update(obj);
        return Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        clienteData.delete(id);
        return Response.noContent().build();
    }
}
