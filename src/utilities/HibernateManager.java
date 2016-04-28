package utilities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import dao.Actividad;
import dao.Alquiler;
import dao.Cliente;
import dao.Propiedad;

public class HibernateManager {

	private static HibernateManager	instance;

	private SessionFactory			factory;
	private Transaction				tx;
	private Session					s;

	private HibernateManager() {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		s = factory.openSession();
	}

	public void closeSession() {
		s.close();
	}

	// ACTIVIDADES
	@SuppressWarnings("unchecked")
	public List<Actividad> getAllActividades() {
		System.out.println("Se recuperan todas las actividades" + "\n");
		try {
			return s.createCriteria(Actividad.class).list();
		} catch (Exception ex) {
			System.out.println("Error en Query" + ex.getMessage());
			return null;
		}
	}

	// CLIENTE
	public void addCliente(Cliente cliente) {
		tx = null;
		System.out.println("Insercion de cliente" + "\n");
		try {
			tx = s.beginTransaction();
			s.save(cliente);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error insertando cliente" + ex);
		}
	}

	public void editCliente(Cliente cliente) {
		tx = null;
		System.out.println("Modificacion de cliente" + "\n");
		try {
			tx = s.beginTransaction();
			s.update(cliente);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error editando cliente" + ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getAllClientes() {
		System.out.println("Se recuperan todos los clientes" + "\n");
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = s.createCriteria(Cliente.class).list();
		} catch (Exception ex) {
			System.out.println("Error en Query" + ex.getMessage());
		}
		return clientes;
	}

	public Cliente getClienteByDNI(int dniCliente) {
		System.out.println("Recuperacion de Cliente por DNI" + "\n");
		Cliente cliente = null;
		try {
			cliente = (Cliente) s.load(Cliente.class, dniCliente);
		} catch (Exception ex) {
			System.out.println("Error recuperando cliente " + ex);
		}
		return cliente;
	}

	public void deleteCliente(Cliente cliente) {
		tx = null;
		System.out.println("Borrado de cliente" + "\n");
		try {
			tx = s.beginTransaction();
			s.delete(cliente);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error eliminando cliente " + ex);
		}
	}

	// PROPIEDAD
	@SuppressWarnings("unchecked")
	public List<Propiedad> getAllPropiedades() {
		System.out.println("Se recuperan todas las propiedades" + "\n");
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			propiedades = s.createQuery("FROM propiedad").list();
		} catch (Exception ex) {
			System.out.println("Error en Query " + ex.getMessage());
		}
		return propiedades;
	}

	public Propiedad getPropiedadByID(int idPropiedad) {
		System.out.println("Recuperacion de propiedad por ID" + "\n");
		Propiedad propiedad = null;
		try {
			propiedad = (Propiedad) s.load(Propiedad.class, idPropiedad);
		} catch (Exception ex) {
			System.out.println("Error recuperando propiedad " + ex);
		}
		return propiedad;
	}

	// ALQUILER

	public Alquiler getAlquilerByID(int idAlquiler) {
		System.out.println("Recuperacion de Alquiler por ID" + "\n");
		Alquiler alquiler = null;
		try {
			alquiler = (Alquiler) s.load(Cliente.class, idAlquiler);
		} catch (Exception ex) {
			System.out.println("Error recuperando alquiler " + ex);
		}
		return alquiler;
	}

	public void addAlquiler(Alquiler alquiler) {
		tx = null;
		System.out.println("Insercion de alquiler " + "\n");
		try {
			tx = s.beginTransaction();
			s.save(alquiler);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error insertando alquiler " + ex);
		}
	}

	public void editAlquiler(Alquiler alquiler) {
		tx = null;
		System.out.println("Modificacion de alquiler" + "\n");
		try {
			tx = s.beginTransaction();
			s.update(alquiler);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error editando alquiler" + ex);
		}
	}

	public void deleteAlquiler(Alquiler alquiler) {
		tx = null;
		System.out.println("Borrado de alquiler " + "\n");
		try {
			tx = s.beginTransaction();
			s.delete(alquiler);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error eliminando alquiler" + ex);
		}
	}

	public static HibernateManager getInstance() {
		if (instance == null) {
			instance = new HibernateManager();
		}
		return instance;
	}
}