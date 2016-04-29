package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ActividadDAO;
import dao.AlquilerDAO;
import dao.ClienteDAO;
import dao.PropiedadDAO;
import vo.Actividad;
import vo.Alquiler;
import vo.Cliente;
import vo.Propiedad;

public class Test {

	public static void main(String[] args) throws Exception {

		ActividadDAO actividadDAO = new ActividadDAO();
		AlquilerDAO alquilerDAO = new AlquilerDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		PropiedadDAO propiedadDAO = new PropiedadDAO();
		
		// CLIENTES

		// INSERTAR CLIENTES Y EN CASO DE QUE EXISTAN LOS BORRAMOS
		System.out.println("INSERTAMOS DOS CLIENTES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		Cliente cl1 = new Cliente(78952922, "Endika", "Salgueiro", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl2 = new Cliente(12457845, "Jordan", "Aranda", "email@email.com", "Calle falsa 1234", 48012, 123456789);

		if (clienteDAO.getClienteByDNI(cl1.getDni()) != null) {
			clienteDAO.deleteCliente(cl1);
		}

		if (clienteDAO.getClienteByDNI(cl2.getDni()) != null) {
			clienteDAO.deleteCliente(cl2);
		}

		clienteDAO.addCliente(cl1);
		clienteDAO.addCliente(cl2);

		// OBTENEMOS TODOS LOS CLIENTES DE LA BASE DE DATOS Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES DE LA BASE DE DATOS");
		List<Cliente> clientes = clienteDAO.getAllClientes();
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA' y 'JORDAN'");
		cl1.setNombre("Endika Editado");
		cl2.setNombre("Jordan Editado");
		clienteDAO.editCliente(cl1);
		clienteDAO.editCliente(cl2);

		// OBTENEMOS EL CLIENTE EDITADO Y LO MOSTRAMOS
		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'ENDIKA' TRAS LA EDICION");
		cl1 = clienteDAO.getClienteByDNI(cl1.getDni());
		System.out.println(cl1.toString());

		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'JORDAN' TRAS LA EDICION");
		cl2 = clienteDAO.getClienteByDNI(cl2.getDni());
		System.out.println(cl2.toString());

		// ACTIVIDADES

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS");
		List<Actividad> actividades = actividadDAO.getAllActividades();
		for (Actividad a : actividades) {
			System.out.println(a.toString());
		}
		/*
		// PROPIEDADES

		// OBTENEMOS LA PROPIEDAD CON ID 6 Y SUS ACTIVIDADES
		System.out.println("\nVISUALIZAMOS LA PROPIEDAD CON ID 1");
		Propiedad p = propiedadDAO.getPropiedadById(1);
		System.out.println(p.toString());

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1");
		List<Actividad> actividadesPropiedad = new ArrayList<Actividad>();
		actividadesPropiedad.addAll(p.getActividades());
		for (Actividad a : actividadesPropiedad) {
			System.out.println(a.toString());
		}

		// ALQUILERES
		System.out.println("\nINSERTAMOS DOS ALQUILERES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		Alquiler alq1 = new Alquiler(cl1, actividadesPropiedad.get(0), p, new Date(), new Date(), 100);
		Alquiler alq2 = new Alquiler(cl2, actividadesPropiedad.get(1), p, new Date(), new Date(), 200);

		cl1.getAlquileres().add(alq1);
		clienteDAO.editCliente(cl1);

		cl2.getAlquileres().add(alq2);
		clienteDAO.editCliente(cl2);

		// HibernateManager.getInstance().addAlquiler(alq1);
		// HibernateManager.getInstance().addAlquiler(alq2);

		cl1 = clienteDAO.getClienteByDNI(cl1.getDni());
		cl2 = clienteDAO.getClienteByDNI(cl2.getDni());
		System.out.println("Alquileres de cl1: " + cl1.getAlquileres().size());
		System.out.println("Alquileres de cl2: " + cl2.getAlquileres().size());
		*/
	}
}
