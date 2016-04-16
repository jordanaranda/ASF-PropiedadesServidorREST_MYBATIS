package resources;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.NotFoundException;

import dao.Cliente;
import utilities.Database;

public class ClienteResource {

	private int dni;

	public ClienteResource(String dni) {
		this.dni = Integer.parseInt(dni);
		Database.getInstance().createConnection();
		if (Database.getInstance().count("cliente", "dni = " + dni) == 0)
			throw new NotFoundException("Get: Client with " + dni + " not found");
		Database.getInstance().disconnect();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getClient() {
		Cliente client = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("Select * from cliente where dni = " + dni);
			if (rs.next()) {
				client = new Cliente(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"),
						rs.getString("direccion"), rs.getInt("cp"), rs.getInt("telefono"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putClient(@Context UriInfo uriInfo, Cliente client) {
		Response res;
		Database.getInstance().createConnection();
		if (dni != client.getDni()) {
			res = Response.status(409).entity("Put: Client with " + client.getDni() + " does not match with current client").build();
		} else {
			res = Response.noContent().build();
			Database.getInstance()
					.update("update cliente set nombre = '" + client.getNombre() + "', apellido = '" + client.getApellido() + "', email = '"
							+ client.getEmail() + "', direccion = '" + client.getDireccion() + "', cp = " + client.getCodigoPostal() + ", telefono = "
							+ client.getTelefono() + " where dni = " + client.getDni());
		}
		Database.getInstance().disconnect();
		return res;
	}

	@DELETE
	public void deleteClient() {
		Database.getInstance().createConnection();
		Database.getInstance().update("delete from cliente where dni = " + dni);
		Database.getInstance().disconnect();
	}
}