package dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.Actividad;
import vo.Alquiler;
import vo.Cliente;
import vo.CustomActividad;
import vo.CustomAlquiler;
import vo.CustomCliente;
import vo.CustomPropiedad;
import vo.Propiedad;

public class MyBatisManager {

	private SqlSession				session;
	private SqlSessionFactory		sqlSessionFactory;

	private static MyBatisManager	instance;

	private MyBatisManager() {
		try {
			String resource = "conf/mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ACTIVIDADES
	public List<CustomActividad> getAllActividades() {
		session = sqlSessionFactory.openSession();
		List<CustomActividad> customActividades = new ArrayList<CustomActividad>();
		try {
			List<Actividad> actividades = session.selectList("getAllActividades");
			for (Actividad a : actividades) {
				customActividades.add(a.toCustomActividad());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customActividades;
	}

	private CustomActividad getActividadById(final int idActividad) {
		CustomActividad customActividad = null;
		try {
			Actividad actividad = session.selectOne("getActividadById", idActividad);
			if (actividad != null) {
				customActividad = actividad.toCustomActividad();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customActividad;
	}

	private List<CustomActividad> getActividadesByIdPropiedad(final int idPropiedad) {
		List<CustomActividad> customActividades = new ArrayList<CustomActividad>();
		try {
			List<Actividad> actividades = session.selectList("getAllActividadesByIdPropiedad", idPropiedad);
			for (Actividad a : actividades) {
				customActividades.add(a.toCustomActividad());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customActividades;
	}

	// PROPIEDADES
	public List<CustomPropiedad> getAllPropiedades() {
		session = sqlSessionFactory.openSession();
		List<CustomPropiedad> customPropiedades = new ArrayList<CustomPropiedad>();
		try {
			List<Propiedad> propiedades = session.selectList("getAllPropiedades");
			for (Propiedad p : propiedades) {
				CustomPropiedad cp = p.toCustomPropiedad();
				cp.setActividades(getActividadesByIdPropiedad(p.getIdPropiedad()));
				customPropiedades.add(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customPropiedades;
	}

	public CustomPropiedad getPropiedadById(int idPropiedad) {
		session = sqlSessionFactory.openSession();
		CustomPropiedad customPropiedad = null;
		try {
			Propiedad propiedad = session.selectOne("getPropiedadById", idPropiedad);
			if (propiedad != null) {
				customPropiedad = propiedad.toCustomPropiedad();
				customPropiedad.setActividades(getActividadesByIdPropiedad(propiedad.getIdPropiedad()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customPropiedad;
	}

	private CustomPropiedad cargarPropiedadById(int idPropiedad) {
		CustomPropiedad customPropiedad = null;
		try {
			Propiedad propiedad = session.selectOne("getPropiedadById", idPropiedad);
			if (propiedad != null) {
				customPropiedad = propiedad.toCustomPropiedad();
				customPropiedad.setActividades(getActividadesByIdPropiedad(propiedad.getIdPropiedad()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customPropiedad;
	}

	// CLIENTES
	public void addCliente(CustomCliente cliente) {
		try {
			session = sqlSessionFactory.openSession();
			session.insert("addCliente", cliente.toCliente());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	public void editCliente(CustomCliente cliente) {
		try {
			session = sqlSessionFactory.openSession();
			session.update("editCliente", cliente.toCliente());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	public List<CustomCliente> getAllClientes() {
		session = sqlSessionFactory.openSession();
		List<CustomCliente> customClientes = new ArrayList<CustomCliente>();
		try {
			List<Cliente> clientes = session.selectList("getAllClientes");
			for (Cliente cliente : clientes) {
				CustomCliente customCliente = cliente.toCustomCliente();
				customCliente.setAlquileres(getAlquileresByClienteDNI(cliente.getDni()));
				customClientes.add(customCliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customClientes;
	}

	public CustomCliente getClienteByDNI(int dni, boolean cargarAlquileres) {
		session = sqlSessionFactory.openSession();
		CustomCliente customCliente = null;
		try {
			Cliente cliente = session.selectOne("getClienteByDNI", dni);
			if (cliente != null) {
				customCliente = cliente.toCustomCliente();
				if (cargarAlquileres) {
					customCliente.setAlquileres(getAlquileresByClienteDNI(cliente.getDni()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customCliente;
	}

	private CustomCliente cargarClientesByDNI(int dni, boolean cargarAlquileres) {
		CustomCliente customCliente = null;
		try {
			Cliente cliente = session.selectOne("getClienteByDNI", dni);
			if (cliente != null) {
				customCliente = cliente.toCustomCliente();
				if (cargarAlquileres) {
					customCliente.setAlquileres(getAlquileresByClienteDNI(cliente.getDni()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customCliente;
	}

	public void deleteCliente(CustomCliente cliente) {
		try {
			session = sqlSessionFactory.openSession();
			session.delete("deleteCliente", cliente.toCliente());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	// ALQUILERES
	public CustomAlquiler getAlquilerByID(int idAlquiler) {
		session = sqlSessionFactory.openSession();
		CustomAlquiler customAlquiler = null;
		try {
			Alquiler alquiler = session.selectOne("getAlquilerByID", idAlquiler);
			if (alquiler != null) {
				customAlquiler = alquiler.toCustomAlquiler();
				customAlquiler.setActividad(getActividadById(alquiler.getIdActividad()));
				customAlquiler.setPropiedad(cargarPropiedadById(alquiler.getIdPropiedad()));
				customAlquiler.setCliente(cargarClientesByDNI(alquiler.getDniCliente(), false));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customAlquiler;
	}

	public List<CustomAlquiler> getAlquileresByClienteDNI(final int dniCliente) {
		session = sqlSessionFactory.openSession();
		List<CustomAlquiler> customAlquileres = new ArrayList<CustomAlquiler>();
		try {
			List<Alquiler> alquileres = session.selectList("getAlquilerByClienteDNI", dniCliente);
			for (Alquiler alquiler : alquileres) {
				CustomAlquiler customAlquiler = alquiler.toCustomAlquiler();
				customAlquiler.setActividad(getActividadById(alquiler.getIdActividad()));
				customAlquiler.setPropiedad(cargarPropiedadById(alquiler.getIdPropiedad()));
				customAlquiler.setCliente(cargarClientesByDNI(alquiler.getDniCliente(), false));
				customAlquileres.add(customAlquiler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return customAlquileres;
	}

	public void addAlquiler(CustomAlquiler alquiler) {
		try {
			session = sqlSessionFactory.openSession();
			session.insert("addAlquiler", alquiler.toAlquiler());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	public void editAlquiler(CustomAlquiler alquiler) {
		try {
			session = sqlSessionFactory.openSession();
			session.update("editAlquiler", alquiler.toAlquiler());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	public void deleteAlquiler(CustomAlquiler alquiler) {
		try {
			session = sqlSessionFactory.openSession();
			session.delete("deleteAlquiler", alquiler.toAlquiler());
			session.commit();
		} catch (SqlSessionException e) {
			session.rollback();
			throw e;
		}
		session.close();
	}

	public static MyBatisManager getInstance() {
		if (instance == null) {
			instance = new MyBatisManager();
		}
		return instance;
	}
}