package test;

import java.util.List;

import dao.MyBatisManager;
import vo.CustomActividad;
import vo.CustomPropiedad;

public class TestActividadesPropiedades {

	public static void main(String[] args) {

		System.out.println("MOSTRAMOS TODAS LAS ACTIVIDADES");
		List<CustomActividad> actividades = MyBatisManager.getInstance().getAllActividades();
		for (CustomActividad a : actividades) {
			System.out.println(a.toString());
		}

		System.out.println("\nMOSTRAMOS TODAS LAS PROPIEDADES JUNTO A SUS ACTIVIDADES");
		List<CustomPropiedad> propiedades = MyBatisManager.getInstance().getAllPropiedades();
		for (CustomPropiedad p : propiedades) {
			System.out.println(p.toString());
			for (CustomActividad ca : p.getActividades()) {
				System.out.println("\t - " + ca.toString());
			}
		}
	}
}
