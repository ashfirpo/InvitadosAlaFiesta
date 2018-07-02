package invitadosAlaFiesta;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Casamiento c = new Casamiento("casamiento_sinEnemigos.in");
		c.resolver();
	}

}
