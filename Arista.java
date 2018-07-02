package invitadosAlaFiesta;

public class Arista {
	
	private int origen;
	private int destino;
	
	public Arista(int origen, int destino)
	{
		this.origen = origen;
		this.destino=destino;
	}
	
	public int getOrigen()
	{
		return this.origen;
	}
	
	public int getDestino()
	{
		return this.destino;
	}

}
