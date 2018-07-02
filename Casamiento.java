package invitadosAlaFiesta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Casamiento {
	
	private int cantInvitados;
	private int cantParejas;
	private boolean[][] matriz;
	private ArrayList<Arista> enemigos;
	
	
	public Casamiento(String path) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(path));
		
		this.cantInvitados = sc.nextInt();
		this.cantParejas = sc.nextInt();
		this.enemigos = new ArrayList<>();
		this.matriz = new boolean[this.cantInvitados][this.cantInvitados];
		for(int i=0;i<this.cantInvitados;i++)
			Arrays.fill(this.matriz[i], true);
		
		if(this.cantParejas!=0)
		{
			sc.nextLine();

			for(int i=0;i<this.cantParejas;i++)
			{
				int origen = sc.nextInt() -1;
				int destino = sc.nextInt() -1;
				Arista a = new Arista(origen, destino);
				if(!this.enemigos.contains(a))
					this.enemigos.add(a);
				this.matriz[origen][destino] = this.matriz[destino][origen] = false; //Seteo en falso a los enemigos
			}
		}
		sc.close();
	}
	
	
	public void resolver()
	{
		System.out.println(coloreo());
	}
	
	public int coloreo()//(int nodoOrigen)
	{
		int cantColores=0;
		int[] colores = new int[this.cantInvitados];
		boolean[] puedoPintar = new boolean[this.cantInvitados]; //Este array me dice si puedo pintar el color i: como máximo, tengo que pintar todos los nodos de distinto color
		
		for(int i=0;i<this.cantInvitados;i++)
		{
			colores[i]=-1; //Inicializo los arrays: tengo todos los colores y puedo usar todos
			puedoPintar[i] = true;
		}
		
		int nodo = this.enemigos.size()>=0?0:-1;
		if(nodo == -1)
			return 0;
		
		colores[nodo]=0; //Coloreo el primer nodo
		cantColores++;
		for(int i=1; i<this.cantInvitados;i++) //Recorro el resto de los nodos
		{	
			for(Arista a : getAristas(i))
			{
				if(colores[a.getDestino()] != -1) //Si ya está pintado, le digo que no puedo
					puedoPintar[colores[a.getDestino()]] = false;				
			}
			
			int j=0;
			while(!puedoPintar[j]) //Busco el próximo color que puedo usar
				j++;
			
			colores[i]=j;
			cantColores = (j+1)>cantColores?(j+1):cantColores;
			Arrays.fill(puedoPintar, true);
		}
		
		return cantColores;
	}
	
	public LinkedList<Arista> getAristas(int nodo)
	{
		if(nodo >=this.cantInvitados)
			try {
				throw new Exception("Nodo inválido");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		LinkedList<Arista> aristas = new LinkedList<>();
		for(int i=0;i<this.cantInvitados;i++)
		{
			if(this.matriz[nodo][i] == true)
				aristas.add(new Arista(nodo, i));
			else if(this.matriz[i][nodo] == true)
				aristas.add(new Arista(i, nodo));
		}
		return aristas;
	}
}
