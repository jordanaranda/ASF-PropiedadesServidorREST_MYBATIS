package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Actividad;
import dao.Propiedad;
import utilities.HibernateManager;

@Path("/actividades")
public class ActividadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividades() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades = HibernateManager.getInstance().getAllActividades();
		return actividades;
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividadesPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		List<Actividad> actividades = new ArrayList<Actividad>();
		Propiedad propiedad = HibernateManager.getInstance().getPropiedadByID(Integer.parseInt(idPropiedad));
		if (propiedad != null) {
			actividades.addAll(propiedad.getActividades());
		}
		return actividades;
	}
}