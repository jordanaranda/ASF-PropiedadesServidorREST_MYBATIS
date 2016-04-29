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
import vo.CustomPropiedad;
import vo.Propiedad;

public class MyBatisManager {
	
	private SqlSessionFactory sqlSessionFactory;
	
	private SqlSessionFactory getSqlSessionFactory()
	{
		if (sqlSessionFactory == null){
			try {				
				String resource = "conf/mybatis-config.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
	
	// ACTIVIDADES
	public List<CustomActividad> getAllActividades() {
		List<CustomActividad> customActividades = new ArrayList<CustomActividad>();
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			List<Actividad> actividades = session.selectList("getAllActividades");
			for(Actividad a : actividades) {
				customActividades.add(a.toCustomActividad());
			}
		}finally{
			session.close();
		}
		return customActividades;
	}
	
	// PROPIEDADES
	public List<CustomPropiedad> getAllPropiedades()
	{
		List<CustomPropiedad> customPropiedades = new ArrayList<CustomPropiedad>();
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			List<Propiedad> propiedades = session.selectList("getAllPropiedades");
			for(Propiedad p : propiedades){
				CustomPropiedad cp = p.toCustomPropiedad();
				List<Actividad> actividades = session.selectList("getAllActividadesByIdPropiedad", p.getIdPropiedad());
				for (Actividad a : actividades) {
					cp.getActividades().add(a.toCustomActividad());
				}
				customPropiedades.add(cp);
			}
		}finally{
			session.close();
		}
		return customPropiedades;
	}
	
	public CustomPropiedad getPropiedadById(int idPropiedad) {
		CustomPropiedad customPropiedad = null;
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			Propiedad propiedad = session.selectOne("getPropiedadById", idPropiedad);
			customPropiedad = propiedad.toCustomPropiedad();
			List<Actividad> actividades = session.selectList("getAllActividadesByIdPropiedad", propiedad.getIdPropiedad());
			for (Actividad a : actividades) {
				customPropiedad.getActividades().add(a.toCustomActividad());
			}
		}finally{
			session.close();
		}
		return customPropiedad;
	}
	
	// CLIENTES
	public void addCliente(Cliente cliente) 
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.insert("addCliente", cliente);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
	public void editCliente(Cliente cliente)
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.update("editCliente", cliente);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
	public List<Cliente> getAllClientes() throws Exception
	{
		List<Cliente> clientes = new ArrayList<Cliente>();
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			clientes = session.selectList("getAllClientes");
		}finally{
			session.close();
		}
		return clientes;
	}
	
	public Cliente getClienteByDNI(int dni) {
		Cliente cliente = null;
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			cliente = session.selectOne("getClienteByDNI", dni);
		}finally{
			session.close();
		}
		return cliente;
	}

	public void deleteCliente(Cliente cliente)
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.delete("deleteCliente", cliente);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
	// ALQUILERES
	public Alquiler getAlquilerByID(int idAlquiler) {
		Alquiler alq = null;
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			alq = session.selectOne("getAlquilerByID", idAlquiler);
		}finally{
			session.close();
		}
		return alq;
	}
	
	public void addAlquiler(Alquiler alq) 
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.insert("addAlquiler", alq);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
	public void editAlquiler(Alquiler alq)
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.update("editAlquiler", alq);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	public void deleteAlquiler(int idAlquiler)
	{
		SqlSession session = getSqlSessionFactory().openSession();
		try{
			session.delete("deleteAlquiler", idAlquiler);
			session.commit();
		}catch(SqlSessionException e){
			session.rollback();
			throw e;
		}finally{
			session.close();
		}
	}
}
