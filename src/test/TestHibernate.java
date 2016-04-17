package test;

import java.util.ArrayList;
import java.util.List;

import dao.Actividad;
import dao.Cliente;
import utilities.HibernateManager;

public class TestHibernate {

	public static void main(String[] args) {

		// CLIENTES

		// INSERTAR CLIENTES Y EN CASO DE QUE EXISTAN LOS BORRAMOS
		System.out.println("INSERTAMOS TRES CLIENTES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
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

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1");
		ArrayList<Actividad> actividadesPropiedad = (ArrayList<Actividad>) HibernateManager.getInstance().getActividadesPropiedad(6);
		for (Actividad a : actividadesPropiedad) {
			System.out.println(a.toString());
		}

		// CERRAMOS LA SESIÓN
		HibernateManager.getInstance().closeSession();
	}
}