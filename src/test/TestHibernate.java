package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.Actividad;
import dao.Alquiler;
import dao.Cliente;
import dao.Propiedad;
import utilities.HibernateManager;

public class TestHibernate {

	public static void main(String[] args) {

		// CLIENTES

		// INSERTAR CLIENTES Y EN CASO DE QUE EXISTAN LOS BORRAMOS
		System.out.println("INSERTAMOS DOS CLIENTES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		Cliente cl1 = new Cliente(78952922, "Endika", "Salgueiro", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl2 = new Cliente(12457845, "Jordan", "Aranda", "email@email.com", "Calle falsa 1234", 48012, 123456789);

		if (HibernateManager.getInstance().getClienteByDNI(cl1.getDni()) != null) {
			HibernateManager.getInstance().deleteCliente(cl1);
		}

		if (HibernateManager.getInstance().getClienteByDNI(cl2.getDni()) != null) {
			HibernateManager.getInstance().deleteCliente(cl2);
		}

		HibernateManager.getInstance().addCliente(cl1);
		HibernateManager.getInstance().addCliente(cl2);

		// OBTENEMOS TODOS LOS CLIENTES DE LA BASE DE DATOS Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES DE LA BASE DE DATOS");
		List<Cliente> clientes = HibernateManager.getInstance().getAllClientes();
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA'");
		cl1.setNombre("Endika Editado");
		HibernateManager.getInstance().editCliente(cl1);

		// OBTENEMOS EL CLIENTE EDITADO Y LO MOSTRAMOS
		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'ENDIKA' TRAS LA EDICION");
		cl1 = HibernateManager.getInstance().getClienteByDNI(cl1.getDni());
		System.out.println(cl1.toString());

		// ACTIVIDADES

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS");
		List<Actividad> actividades = HibernateManager.getInstance().getAllActividades();
		for (Actividad a : actividades) {
			System.out.println(a.toString());
		}

		// PROPIEDADES

		// OBTENEMOS LA PROPIEDAD CON ID 6 Y SUS ACTIVIDADES
		System.out.println("\nVISUALIZAMOS LA PROPIEDAD CON ID 6");
		Propiedad p = HibernateManager.getInstance().getPropiedadByID(6);
		System.out.println(p.toString());

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 6");
		List<Actividad> actividadesPropiedad = new ArrayList<Actividad>();
		actividadesPropiedad.addAll(p.getActividades());
		for (Actividad a : actividadesPropiedad) {
			System.out.println(a.toString());
		}

		// ALQUILERES
		System.out.println("\nINSERTAMOS DOS ALQUILERES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		Alquiler alq1 = new Alquiler(cl1, actividadesPropiedad.get(0), p, new Date(), new Date(), 100);
		Alquiler alq2 = new Alquiler(cl2, actividadesPropiedad.get(1), p, new Date(), new Date(), 200);

		HibernateManager.getInstance().addAlquiler(alq1);
		HibernateManager.getInstance().addAlquiler(alq2);

		cl1 = HibernateManager.getInstance().getClienteByDNI(cl1.getDni());
		System.out.println("Alquileres de cl1: " + cl1.getAlquileres().size());
		System.out.println("Alquileres de cl2: " + cl2.getAlquileres().size());

		for (Alquiler a : cl1.getAlquileres()) {
			System.out.println(a.toString());
		}

		// CERRAMOS LA SESION
		HibernateManager.getInstance().closeSession();
	}
}