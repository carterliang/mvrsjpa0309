import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.*;

@Path("/suppliers")
public class SupplierRSService {
        SupplierDAO service=new SupplierDAO();
        
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getUsers() {            
	        List <Supplier> sups = service.getAll();

	        if (sups.size()>0) {
	            return Response.ok(sups).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	    }
	    
	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response createUser(Supplier s) {
	        List<Supplier> result = service.addSupplier(s);
	        if (result!=null) {
	            return Response.ok().entity(result).build();
	        } else {
	            return Response.notModified().build();
	        }
	    }
	    
	    @GET
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getUserById(@PathParam("id")int id) {

	        Supplier sup = service.searchById(id);

	        if (sup!=null) {
	            return Response.ok(sup).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	    }
	    
	    @PUT
	    @Path("/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response updateUser(@PathParam("id") int id, Supplier sup) {
	    	sup.setSupId(id);
	        boolean result = service.updateSupplier(id, sup);

	        if (result) {
	            return Response.ok().entity(sup).build();
	        } else {
	            return Response.notModified().build();
	        }
	    }
	    
	    @GET
	    @Path("/html")
	    @Produces("text/html")
	    public String getHtml() {
	        return "<html><body><h1>Hello, World!!</body></h1></html>";
	    }
}
