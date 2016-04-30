package test;

import java.util.List;

import dao.MyBatisManager;
import vo.CustomAlquiler;
import vo.CustomCliente;

public class TestClientesAlquileres {

	public static void main(String[] args) {

		List<CustomCliente> clientes = MyBatisManager.getInstance().getAllClientes();
		for (CustomCliente c : clientes) {
			System.out.println(c.toString());
			for (CustomAlquiler ca : c.getAlquileres()) {
				System.out.println("\t - " + ca.toString());
			}
		}
	}

}
