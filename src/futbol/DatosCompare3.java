package futbol;

import java.util.Comparator;

/*
 * Criterio de comparación que se usa para comparar dos objetos
 * de tipo Datos. Compara si Pts del equipo1 es menor que Pts del equipo2
 * Pts= puntos de un equipo
 */
public class DatosCompare3 implements Comparator<Datos>
{
	public int compare(Datos d1, Datos d2)
	{
		Integer item1= d1.getPts();
		Integer item2= d2.getPts();
		return item2.compareTo(item1);
	}
}
