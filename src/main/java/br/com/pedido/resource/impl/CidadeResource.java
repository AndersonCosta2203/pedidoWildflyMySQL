package br.com.pedido.resource.impl;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.pedido.entities.Cidade;
import br.com.pedido.resource.IResource;
import br.com.pedido.util.data.CidadeData;

@Path("cidades")
public class CidadeResource implements IResource<Cidade> {

    @Inject
    private CidadeData cidadeData;

    @Override
    public Response findAll() {
        return Response.ok().entity(cidadeData.findAll()).build();
    }

    @Override
    public Response insert(Cidade obj, UriInfo uriInfo) {
        obj.setCodigo(null);
        final Cidade newCidade = cidadeData.insert(obj);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCidade.getCodigo().toString()).build();
        return Response.created(uri).entity(newCidade).build();
    }

    @Override
    public Response findById(Integer id) {
        return Response.ok().entity(cidadeData.findById(id)).build();
    }

    @Override
    public Response update(Integer id, Cidade obj) {
        obj.setCodigo(id);
        cidadeData.update(obj);
        return Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        cidadeData.delete(id);
        return Response.noContent().build();
    }
}
