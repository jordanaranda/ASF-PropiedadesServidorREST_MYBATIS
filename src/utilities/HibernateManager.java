package utilities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.Actividad;
import dao.Cliente;

public class HibernateManager {

	private static HibernateManager	instance;

	private SessionFactory			factory;
	private Transaction				tx;
	private Session					s;

	private HibernateManager() {
		factory = new Configuration().configure().buildSessionFactory();
		s = factory.openSession();
	}

	public void closeSession() {
		s.close();
	}

	// ACTIVIDADES
	@SuppressWarnings("unchecked")
	public List<Actividad> getAllActividades() {
		System.out.println("Se recuperan todas las actividades" + "\n");
		List<Actividad> actividades = new ArrayList<Actividad>();
		try {
			actividades = s.createQuery("FROM actividad").list();
		} catch (Exception ex) {
			System.out.println("Error en Query" + ex.getMessage());
		}
		return actividades;
	}

	@SuppressWarnings("unchecked")
	public List<Actividad> getActividadesPropiedad(String idPropiedad) {
		System.out.println("Se recuperan todas las actividades de la propiedad" + "\n");
		List<Actividad> actividades = new ArrayList<Actividad>();
		try {
			actividades = s
					.createQuery(
							"FROM actividad AS A INNER JOIN propiedad_actividad AS PA ON A.idActividad=PA.idActividad where PA.idPropiedad = :idPropiedad")
					.setString("idPropiedad", idPropiedad).list();
		} catch (Exception ex) {
			System.out.println("Error en Query" + ex.getMessage());
		}
		return actividades;
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

	@SuppressWarnings("unchecked")
	public List<Cliente> getAllCliente() {
		System.out.println("Se recuperan todos los clientes" + "\n");
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = s.createQuery("FROM cliente").list();
		} catch (Exception ex) {
			System.out.println("Error en Query" + ex.getMessage());
		}
		return clientes;
	}

	public Cliente getClienteByDNI(String dniCliente) {
		System.out.println("Recuperacion de Cliente por DNI" + "\n");
		Cliente cliente = null;
		try {
			cliente = (Cliente) s.load(Cliente.class, dniCliente);
		} catch (Exception ex) {
			System.out.println("Error recuperando cliente" + ex);
		}
		return cliente;
	}

	// PROPIEDAD

	// ALQUILER

	public static HibernateManager getInstance() {
		if (instance == null) {
			instance = new HibernateManager();
		}
		return instance;
	}
}