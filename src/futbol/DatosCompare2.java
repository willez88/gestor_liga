package futbol;

import java.util.Comparator;

/*
 * Criterio de comparación que se usa para comparar dos objetos
 * de tipo Datos. Compara si Dif del equipo1 es menor que Dif del equipo2
 * Dif= diferencia de goles de un equipo
 */
public class DatosCompare2 implements Comparator<Datos>
{
	public int compare(Datos d1, Datos d2)
	{
		Integer item1= d1.getDif();
		Integer item2= d2.getDif();
		return item2.compareTo(item1);
	}
}
