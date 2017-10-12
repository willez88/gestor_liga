package futbol;

/*
 * Clase que se usa para guardar los equipos y cada uno de sus datos
 */
public class Datos
{
	private String nombre;
	private int G;
	private int Pts;
	private int PJ;
	private int PG;
	private int PE;
	private int PP;
	private int GA;
	private int GE;
	private int Dif;
	
	public Datos()
	{
		nombre= "";
		G=0;
		Pts=0;
		PG=0;
		PE=0;
		PP=0;
		GA=0;
		GE=0;
		Dif=0;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public int getG()
	{
		return G;
	}
	public void setG(int g)
	{
		G = g;
	}
	public int getPts()
	{
		return Pts;
	}
	public void setPts(int pts)
	{
		Pts = pts;
	}
	public int getPJ()
	{
		return PJ;
	}
	public void setPJ(int pJ)
	{
		PJ = pJ;
	}
	public int getPG()
	{
		return PG;
	}
	public void setPG(int pG)
	{
		PG = pG;
	}
	public int getPE()
	{
		return PE;
	}
	public void setPE(int pE)
	{
		PE = pE;
	}
	public int getPP()
	{
		return PP;
	}
	public void setPP(int pP)
	{
		PP = pP;
	}
	public int getGA()
	{
		return GA;
	}
	public void setGA(int gA)
	{
		GA = gA;
	}
	public int getGE()
	{
		return GE;
	}
	public void setGE(int gE)
	{
		GE = gE;
	}
	public int getDif()
	{
		return Dif;
	}
	public void setDif(int dif)
	{
		Dif = dif;
	}
}