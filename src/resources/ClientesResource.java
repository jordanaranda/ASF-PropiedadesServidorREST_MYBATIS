package resources;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dao.Cliente;
import utilities.Database;

@Path("/clientes")
public class ClientesResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getClients() {
		List<Cliente> clients = new ArrayList<Cliente>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"),
						rs.getString("direccion"), rs.getInt("cp"), rs.getInt("telefono"));
				clients.add(cliente);
			}
			Database.getInstance().disconnect();
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
			return clients;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newClient(Cliente client) {
		Response res = null;
		Database.getInstance().createConnection();
		if (Database.getInstance().count("cliente", "dni = " + client.getDni()) > 0) {
			res = Response.status(409).entity("Post: Client with " + client.getDni() + " already exists").build();
		} else {
			URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(client.getDni())).build();
			res = Response.created(uri).entity(client).build();
			Database.getInstance()
					.update("insert into cliente values (" + client.getDni() + ", '" + client.getNombre() + "', '" + client.getApellido() + "', '"
							+ client.getEmail() + "', '" + client.getDireccion() + "', " + client.getCodigoPostal() + ", " + client.getTelefono()
							+ ")");
		}
		Database.getInstance().disconnect();
		return res;
	}

	@Path("{dni}")
	public ClienteResource getCliente(@PathParam("dni") String dni) {
		return new ClienteResource(dni);
	}
}