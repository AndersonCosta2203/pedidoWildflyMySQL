package br.com.pedido.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface IResource<T> {

    @GET
    public Response findAll();

    @POST
    public Response insert(T obj, @Context UriInfo uriInfo);

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id);

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, T obj);

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id);
}
