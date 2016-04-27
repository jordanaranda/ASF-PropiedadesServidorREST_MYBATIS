package resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Propiedad;
import utilities.HibernateManager;

@Path("/propiedades")
public class PropiedadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Propiedad> getPropiedades() {
		return HibernateManager.getInstance().getAllPropiedades();
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Propiedad getPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		return HibernateManager.getInstance().getPropiedadByID(Integer.parseInt(idPropiedad));
	}
}