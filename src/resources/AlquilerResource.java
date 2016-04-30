package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import dao.MyBatisManager;
import vo.CustomAlquiler;

public class AlquilerResource {

	private int id;

	public AlquilerResource(String id) {
		this.id = Integer.parseInt(id);
		CustomAlquiler alquiler = MyBatisManager.getInstance().getAlquilerByID(this.id);
		if (alquiler == null) {
			throw new NotFoundException("Get: Alquiler with " + id + " not found");
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putAlquiler(@Context UriInfo uriInfo, CustomAlquiler alquiler) {
		Response res;
		alquiler.toString();
		if (id != alquiler.getIdAlquiler()) {
			res = Response.status(409).entity("Put: Alquiler with " + alquiler.getIdAlquiler() + " does not match with current client").build();
		} else {
			res = Response.noContent().build();
			MyBatisManager.getInstance().editAlquiler(alquiler);
		}
		return res;
	}

	@DELETE
	public void deleteAlquiler() {
		CustomAlquiler alquiler = MyBatisManager.getInstance().getAlquilerByID(id);
		if (alquiler != null) {
			MyBatisManager.getInstance().deleteAlquiler(alquiler);
		}
	}
}