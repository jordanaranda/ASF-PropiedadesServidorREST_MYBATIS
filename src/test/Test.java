package test;

import java.util.Date;
import java.util.List;

import dao.MyBatisManager;
import vo.CustomActividad;
import vo.CustomAlquiler;
import vo.CustomCliente;
import vo.CustomPropiedad;

public class Test {

	public static void main(String[] args) throws Exception {

		// CLIENTES

		// INSERTAR CLIENTES Y EN CASO DE QUE EXISTAN LOS BORRAMOS
		System.out.println("INSERTAMOS DOS CLIENTES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		CustomCliente cl1 = new CustomCliente(78952922, "Endika", "Salgueiro", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		CustomCliente cl2 = new CustomCliente(12457845, "Jordan", "Aranda", "email@email.com", "Calle falsa 1234", 48012, 123456789);

		if (MyBatisManager.getInstance().getClienteByDNI(cl1.getDni(), false) != null) {
			MyBatisManager.getInstance().deleteCliente(cl1);
		}

		if (MyBatisManager.getInstance().getClienteByDNI(cl2.getDni(), false) != null) {
			MyBatisManager.getInstance().deleteCliente(cl2);
		}

		MyBatisManager.getInstance().addCliente(cl1);
		MyBatisManager.getInstance().addCliente(cl2);

		// OBTENEMOS TODOS LOS CLIENTES DE LA BASE DE DATOS Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES DE LA BASE DE DATOS");
		List<CustomCliente> clientes = MyBatisManager.getInstance().getAllClientes();
		for (CustomCliente c : clientes) {
			System.out.println(c.toString());
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA' y 'JORDAN'");
		cl1.setNombre("Endika Editado");
		cl2.setNombre("Jordan Editado");
		MyBatisManager.getInstance().editCliente(cl1);
		MyBatisManager.getInstance().editCliente(cl2);

		// OBTENEMOS EL CLIENTE EDITADO Y LO MOSTRAMOS
		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'ENDIKA' TRAS LA EDICION");
		CustomCliente customCliente1 = MyBatisManager.getInstance().getClienteByDNI(cl1.getDni(), false);
		System.out.println(customCliente1.toString());

		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'JORDAN' TRAS LA EDICION");
		CustomCliente customCliente2 = MyBatisManager.getInstance().getClienteByDNI(cl2.getDni(), false);
		System.out.println(customCliente2.toString());

		// ACTIVIDADES

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS");
		List<CustomActividad> actividades = MyBatisManager.getInstance().getAllActividades();
		for (CustomActividad a : actividades) {
			System.out.println(a.toString());
		}

		// PROPIEDADES

		// OBTENEMOS LA PROPIEDAD CON ID 6 Y SUS ACTIVIDADES
		System.out.println("\nVISUALIZAMOS LA PROPIEDAD CON ID 1");
		CustomPropiedad p = MyBatisManager.getInstance().getPropiedadById(1);
		System.out.println(p.toString());

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1");
		for (CustomActividad a : p.getActividades()) {
			System.out.println(a.toString());
		}

		// ALQUILERES
		System.out.println("\nINSERTAMOS DOS ALQUILERES DE PRUEBA PERO SI EXISTEN LOS BORRAMOS");
		CustomAlquiler alq1 = new CustomAlquiler(cl1, p.getActividades().get(0), p, new Date(), new Date(), 100);
		CustomAlquiler alq2 = new CustomAlquiler(cl2, p.getActividades().get(1), p, new Date(), new Date(), 200);

		MyBatisManager.getInstance().addAlquiler(alq1);
		MyBatisManager.getInstance().addAlquiler(alq2);

		cl1 = MyBatisManager.getInstance().getClienteByDNI(cl1.getDni(), true);
		alq1 = cl1.getAlquileres().get(0);
		alq1.setPrecio(200);
		MyBatisManager.getInstance().editAlquiler(alq1);

		cl2 = MyBatisManager.getInstance().getClienteByDNI(cl2.getDni(), true);
		alq2 = cl2.getAlquileres().get(0);
		alq2.setPrecio(100);
		MyBatisManager.getInstance().editAlquiler(alq2);

		cl1 = MyBatisManager.getInstance().getClienteByDNI(cl1.getDni(), true);
		System.out.println("Alquileres de cl1: " + cl1.getAlquileres().size());
		for (CustomAlquiler alquiler : cl1.getAlquileres()) {
			System.out.println(alquiler.toString());
		}

		cl2 = MyBatisManager.getInstance().getClienteByDNI(cl2.getDni(), true);
		System.out.println("Alquileres de cl2: " + cl2.getAlquileres().size());
		for (CustomAlquiler alquiler : cl2.getAlquileres()) {
			System.out.println(alquiler.toString());
		}
	}
}
