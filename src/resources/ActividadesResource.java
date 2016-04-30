package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.MyBatisManager;
import vo.CustomActividad;
import vo.CustomPropiedad;

@Path("/actividades")
public class ActividadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomActividad> getActividades() {
		List<CustomActividad> actividades = new ArrayList<CustomActividad>();
		actividades = MyBatisManager.getInstance().getAllActividades();
		return actividades;
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomActividad> getActividadesPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		List<CustomActividad> actividades = new ArrayList<CustomActividad>();
		CustomPropiedad propiedad = MyBatisManager.getInstance().getPropiedadById(Integer.parseInt(idPropiedad));
		if (propiedad != null) {
			actividades.addAll(propiedad.getActividades());
		}
		return actividades;
	}
}