package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Actividad;
import utilities.Database;

@Path("/actividades")
public class ActividadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividades() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from actividad");
			while (rs.next()) {
				actividades.add(new Actividad(rs.getInt("idActividad"), rs.getString("nombre")));
			}
			Database.getInstance().disconnect();
			return actividades;
		} catch (SQLException e) {
			e.printStackTrace();
			return actividades;
		}
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividadesPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		List<Actividad> actividades = new ArrayList<Actividad>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult(
					"select A.idActividad, A.nombre from actividad AS A INNER JOIN propiedad_actividad AS PA ON A.idActividad=PA.idActividad where PA.idPropiedad = "
							+ Integer.parseInt(idPropiedad));
			while (rs.next()) {
				actividades.add(new Actividad(rs.getInt("idActividad"), rs.getString("nombre")));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actividades;
	}
}