package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ActividadDAO;
import dao.PropiedadDAO;
import vo.Actividad;
import vo.Propiedad;

@Path("/actividades")
public class ActividadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividades() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades = new ActividadDAO().getAllActividades();
		return actividades;
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividadesPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		List<Actividad> actividades = new ArrayList<Actividad>();
		Propiedad propiedad = new PropiedadDAO().getPropiedadById(Integer.parseInt(idPropiedad));
		if (propiedad != null) {
			actividades.addAll(propiedad.getActividades());
		}
		return actividades;
	}
}