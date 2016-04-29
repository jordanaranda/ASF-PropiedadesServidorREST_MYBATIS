package test;

import java.util.List;

import dao.MyBatisManager;
import vo.CustomActividad;
import vo.CustomPropiedad;

public class TestActividadesPropiedades {
	
	public static void main(String[] args) {
		
		MyBatisManager myBatisManager = new MyBatisManager();
		
		List<CustomActividad> actividades = myBatisManager.getAllActividades();
		for (CustomActividad a : actividades) {
			System.out.println(a.getNombre());
		}
		
		List<CustomPropiedad> propiedades = myBatisManager.getAllPropiedades();
		System.out.println(propiedades.size());
		for (CustomPropiedad p : propiedades) {
			System.out.println(p.getNombre());
			for (CustomActividad ca : p.getActividades()) {
				System.out.println("\t - " + ca.getNombre());
			}
		}
	}
}
