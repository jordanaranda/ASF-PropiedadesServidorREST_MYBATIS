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

import dao.Propiedad;
import utilities.Database;

@Path("/propiedades")
public class PropiedadesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Propiedad> getPropiedades() {
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from propiedad");
			while (rs.next()) {
				propiedades.add(new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre")));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return propiedades;
	}

	@GET
	@Path("{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Propiedad getPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		Propiedad propiedad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from propiedad where idPropiedad = " + idPropiedad);
			if (rs.next()) {
				propiedad = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return propiedad;
	}
}