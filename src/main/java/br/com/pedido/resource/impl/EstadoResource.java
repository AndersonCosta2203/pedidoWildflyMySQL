package br.com.pedido.resource.impl;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.pedido.entities.Estado;
import br.com.pedido.resource.IResource;
import br.com.pedido.util.data.EstadoData;

@Path("/estados")
public class EstadoResource implements IResource<Estado> {

    @Inject
    private EstadoData estadoData;

    @Override
    public Response findAll() {
        List<Estado> estados = estadoData.findAll();
        return Response.ok().entity(estados).build();
    }

    @Override
    public Response insert(Estado obj, UriInfo uriInfo) {
        final Estado newEstado = estadoData.insert(obj);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newEstado.getCodigo().toString()).build();
        return Response.created(uri).entity(newEstado).build();
    }

    @Override
    public Response findById(Integer id) {
        Estado estado = estadoData.findById(id);
        return Response.ok().entity(estado).build();
    }

    @Override
    public Response update(Integer id, Estado obj) {
        obj.setCodigo(id);
        estadoData.update(obj);
        return  Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        estadoData.delete(id);
        return  Response.noContent().build();
    }

    @GET
    @Produces({ "application/json" })
    @Path("/buscar-por-nome/{nome}")
    public Response getNome(@PathParam("nome") String nome) {
        return Response.ok().entity(estadoData.findByNome(nome)).build();
    }
}
