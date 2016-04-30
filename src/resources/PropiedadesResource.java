package resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.MyBatisManager;
import vo.CustomPropiedad;

@Path("/propiedades")
public class PropiedadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomPropiedad> getPropiedades() {
		return MyBatisManager.getInstance().getAllPropiedades();
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomPropiedad getPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		return MyBatisManager.getInstance().getPropiedadById(Integer.parseInt(idPropiedad));
	}
}