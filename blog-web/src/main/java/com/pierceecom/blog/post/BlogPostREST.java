package com.pierceecom.blog.post;

import com.pierceecom.entities.PostEntity;
import com.pierceecom.general.abstracts.AbstractRESTResource;
import com.pierceecom.general.contracts.PostService;
import com.pierceecom.general.exceptions.PierceNoResultException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogPostREST extends AbstractRESTResource<PostEntity> {

    @EJB
    private PostService postService;

    @GET
    public Response list() {
        return listResult(new GenericEntity<List<PostEntity>>(postService.list()){});
    }

    @GET
    @Path("/{postId}")
    public Response single(@PathParam("postId") Long postId) throws PierceNoResultException {
        return singleResult(new GenericEntity<PostEntity>(postService.single(postId)){});
    }

    @POST
    public Response create(@Context UriInfo uriInfo, PostEntity newPost) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(postService.create(newPost).getId()));

        return createdResult(builder.build());
    }

    @DELETE
    @Path("/{postId}")
    public Response delete(@PathParam("postId") Long postId) throws PierceNoResultException {
        return singleResult(new GenericEntity<PostEntity>(postService.delete(postId)){});
    }

}
