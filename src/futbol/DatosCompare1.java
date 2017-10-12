package futbol;

import java.util.Comparator;

/*
 * Criterio de comparación que se usa para comparar dos objetos
 * de tipo Datos. Compara si GA del equipo1 es menor que GA del equipo2
 * GA= goles a favor de un equipo
 */
public class DatosCompare1 implements Comparator<Datos>
{
	public int compare(Datos d1, Datos d2)
	{
		Integer item1= d1.getGA();
		Integer item2= d2.getGA();
		return item2.compareTo(item1);
	}
}
