package paneles;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import futbol.Datos;
import futbol.DatosCompare1;
import futbol.DatosCompare2;
import futbol.DatosCompare3;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class Panel3 extends JPanel
{
	private static final long serialVersionUID = 3L;
	private JTextField textField;
	private JTextField textField_1;
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private static Datos E;
	//para el calendario
	private ArrayList<String> Lista1;
	private ArrayList<String> Lista2;
	//para los partidos
	private ArrayList<Datos> pCabeza;
	private ArrayList<Datos> pLista1;
	private ArrayList<Datos> pLista2;
	private int iL1, iL2, iL3, f;
	private JTable table;
	/**
	 * En este panel se puede ver el calendario e ir ingresando
	 * cada uno de los partidos con sus anotaciones
	 */
	public Panel3(final ArrayList<String> Cabeza, final boolean band)
	{
		Lista1= new ArrayList<String>();
		Lista2= new ArrayList<String>();
		
		pCabeza= new ArrayList<Datos>();
		pLista1= new ArrayList<Datos>();
		pLista2= new ArrayList<Datos>();
		iL1= 0;
		iL2= 0;
		iL3= 0;
		f= 1;
		
		crearListasDatos(Cabeza, pLista1, pLista2, Cabeza.size());
		crearListas(Cabeza, Lista1, Lista2, Cabeza.size());
		
		setLayout(null);
		
		JLabel lblEncuentros = new JLabel("Partidos");
		lblEncuentros.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEncuentros.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncuentros.setBounds(12, 23, 100, 15);
		add(lblEncuentros);
		
		final JLabel label = new JLabel("Equipo 1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 86, 106, 15);
		add(label);
		
		JLabel lblVs = new JLabel("vs");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(49, 107, 34, 15);
		add(lblVs);
		
		final JLabel lblNewLabel = new JLabel("Equipo 2");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 134, 106, 15);
		add(lblNewLabel);
		
		final JButton btnCampen = new JButton("Campeón");
		final JButton btnPartido = new JButton("Sig Partido");
		final JButton btnResultado = new JButton("Resultado");
		btnResultado.setEnabled(false);
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp1,temp2,fijo;
				try
				{
					if( (!pLista1.get(iL2).getNombre().equals("Descanso"))&&(!pLista2.get(iL2).getNombre().equals("Descanso")) )
					{
						if( iL1 < pCabeza.size()-1 )
						{
							//Esta serie de condiciones es para solucionar el caso de que
							//se inserte un numero negativo, se convierte a positivo
							if ( Integer.parseInt(textField.getText())<0 )
							{
								pLista1.get(iL2).setG(-1*Integer.parseInt(textField.getText()));
							}
							else
							{
								pLista1.get(iL2).setG(Integer.parseInt(textField.getText()));
							}
							if (Integer.parseInt(textField_1.getText())<0)
							{
								pLista2.get(iL2).setG(-1*Integer.parseInt(textField_1.getText()));
							}
							else
							{
								pLista2.get(iL2).setG(Integer.parseInt(textField_1.getText()));
							}
							tabla(pLista1.get(iL2), pLista2.get(iL2));
						}
						else
						{
							//Esta serie de condiciones es para solucionar el caso de que
							//se inserte un numero negativo, se convierte a positivo
							if (Integer.parseInt(textField.getText())<0 )
							{
								pLista2.get(iL2).setG(-1*Integer.parseInt(textField.getText()));
							}
							else
							{
								pLista2.get(iL2).setG(Integer.parseInt(textField.getText()));
							}
							if (Integer.parseInt(textField_1.getText())<0)
							{
								pLista1.get(iL2).setG(-1*Integer.parseInt(textField_1.getText()));
							}
							else
							{
								pLista1.get(iL2).setG(Integer.parseInt(textField_1.getText()));
							}
							tabla(pLista2.get(iL2), pLista1.get(iL2));
						}
					}
					Collections.sort(pCabeza, new DatosCompare1());
					Collections.sort(pCabeza, new DatosCompare2());
					Collections.sort(pCabeza, new DatosCompare3());
					insertarData(table, band);
					
					textField.setEnabled(false);
					textField_1.setEnabled(false);
					btnResultado.setEnabled(false);
					btnPartido.setEnabled(true);
					textField.setText("");
					textField_1.setText("");
					iL2++;
					iL3++;
					
					if( iL3==pCabeza.size()/2 )
					{
						fijo= pLista1.remove(0).getNombre();
						temp1= pLista1.remove(pLista1.size()-1).getNombre();
						temp2= pLista2.remove(0).getNombre();

						insertarAlInicio(pLista1, fijo, temp2);
						E= new Datos();
						E.setNombre(temp1);
						pLista2.add(E);

						f++;
						iL3= 0;
						iL1++;
					}
					int t= pCabeza.size()-1;
					if(iL1 == t*2)
					{
						btnResultado.setEnabled(false);
						btnPartido.setEnabled(false);
						btnCampen.setEnabled(true);
					}
				} catch (Exception e2)
				{
						/**
						 * para capturar el error de cuando no se ingresa goles
						 * en el caso de que el equipo Descansa debido al numero
						 * impar de equipos
						*/
				}
			}
		});
		btnResultado.setBounds(22, 161, 106, 25);
		add(btnResultado);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(136, 84, 34, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(136, 132, 34, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		final JLabel lblFecha = new JLabel("Fecha #");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(12, 59, 106, 15);
		add(lblFecha);
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(414, 59, 234, 179);
		add(textArea);
		
		scroll = new JScrollPane(textArea);
		scroll.setBounds(new Rectangle(414, 59, 234, 179));
		add(scroll);
		
		JLabel lblTablaDePosiciones = new JLabel("Tabla de Posiciones");
		lblTablaDePosiciones.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTablaDePosiciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaDePosiciones.setBounds(22, 230, 169, 15);
		add(lblTablaDePosiciones);
		
		JButton btnCalendario = new JButton("Calendario");
		btnCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(" "+"\n");
				textArea.append("*************"+"\n");
				textArea.append("*****Ida*****"+"\n");
				textArea.append("*************"+"\n");
				calculaIda(textArea, Lista1, Lista2, Cabeza.size());
				textArea.append("\n");
				textArea.append("**************"+"\n");
				textArea.append("****Vuelta***"+"\n");
				textArea.append("**************"+"\n");
				calculaVuelta(textArea, Lista1, Lista2, Cabeza.size());
			}
		});
		btnCalendario.setBounds(472, 18, 117, 25);
		add(btnCalendario);
		
		//se declara en la linea 97
		btnPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (iL2 == pLista1.size())
				{
					iL2=0;
				}
				if( (!pLista1.get(iL2).getNombre().equals("Descanso"))&&(!pLista2.get(iL2).getNombre().equals("Descanso")) )
				{
					textField.setEnabled(true);
					textField_1.setEnabled(true);
				}
				lblFecha.setText("Fecha #"+f);
				if( iL1 < pCabeza.size()-1)
				{
					label.setText(pLista1.get(iL2).getNombre());
					lblNewLabel.setText(pLista2.get(iL2).getNombre());
				}
				else
				{
					label.setText(pLista2.get(iL2).getNombre());
					lblNewLabel.setText(pLista1.get(iL2).getNombre());
				}
				btnResultado.setEnabled(true);
				btnPartido.setEnabled(false);
			}
		});
		btnPartido.setBounds(195, 102, 117, 25);
		add(btnPartido);
		
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"Pos", "Equipo", "Pts", "PJ", "PG", "PE", "PP", "GA", "GE", "Dif"
		}
	) {
		/**
		 * crea la tabla de posiciones de la liga
		 */
		private static final long serialVersionUID = 2L;
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false, false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
		table = new JTable(modelo);
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		table.setBackground(Color.WHITE);
		table.setBounds(32, 250, 638, 138);
		for (int i = 0; i < 10; i++)
		{
			if(i==1)
			{
				table.getColumnModel().getColumn(i).setPreferredWidth(200);
			}
			else
			{
				table.getColumnModel().getColumn(i).setPreferredWidth(50);
			}
			table.getColumnModel().getColumn(i).setResizable(false);
		}
		table.getTableHeader().setReorderingAllowed(false);
		Object[] fila = {"", 
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",};
		for (int i = 0; i < pCabeza.size(); i++)
		{
			if( !pCabeza.get(i).getNombre().equals("Descanso") )
				modelo.addRow(fila);
		}
		add(table);
		
		scroll2 = new JScrollPane(table);
		scroll2.setBounds(new Rectangle(32, 250, 638, 138));
		add(scroll2);
		
		//se declara en la linea 96
		btnCampen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String equi;
				if(pCabeza.get(pCabeza.size()-1).getNombre().equals("Descanso"))
				{
					equi= pCabeza.get(pCabeza.size()-2).getNombre();
				}
				else
				{
					equi= pCabeza.get(pCabeza.size()-1).getNombre();
				}
				JOptionPane.showMessageDialog(null, pCabeza.get(0).getNombre()+" es el ganador de la Liga de las Estrellas\n\n"+
			equi+" es el ultimo de la tabla");
				btnCampen.setEnabled(false);
			}
		});
		btnCampen.setEnabled(false);
		btnCampen.setBounds(195, 18, 117, 25);
		add(btnCampen);
		
	}
	
	//algoritmo para combinar las listas de alguna forma
	public void crearListas(ArrayList<String> Cabeza, ArrayList<String> Lista1, ArrayList<String> Lista2, int n)
	{
		for(int i=0; i<n; i++)
		{
			if(i<n/2)
			{
				Lista1.add(Cabeza.get(i));
			}
			else
			{
				Lista2.add(Cabeza.get(i));
			}
		}
	}
	//algoritmo para combinar las listas<Datos> de alguna forma
	public void crearListasDatos(ArrayList<String> Cabeza, ArrayList<Datos> Lista1, ArrayList<Datos> Lista2, int n)
	{
		for(int i=0; i<n; i++)
		{
			E= new Datos();
			if(i<n/2)
			{
				E.setNombre(Cabeza.get(i));
				Lista1.add(E);
				pCabeza.add(E);
			}
			else
			{
				E.setNombre(Cabeza.get(i));
				Lista2.add(E);
				pCabeza.add(E);
			}
		}
	}
	
	public static void insertarAlInicioCalendario(ArrayList<String> Lista1, String fijo, String temp2)
	{
		ArrayList<String> Aux= new ArrayList<String>();
		Aux.add(fijo);
		Aux.add(temp2);
		for (int i = 0; i < Lista1.size(); i++)
		{
			Aux.add(Lista1.get(i));
		}
		Lista1.clear();
		for (int i = 0; i < Aux.size(); i++)
		{
			Lista1.add(Aux.get(i));
		}
	}
	
	public static void insertarAlInicio(ArrayList<Datos> Lista1, String fijo, String temp2)
	{
		ArrayList<Datos> Aux= new ArrayList<Datos>();
		Datos E;
		E= new Datos();
		E.setNombre(fijo);
		Aux.add(E);
		E= new Datos();
		E.setNombre(temp2);
		Aux.add(E);
		for (int i = 0; i < Lista1.size(); i++)
		{
			Aux.add(Lista1.get(i));
		}
		Lista1.clear();
		for (int i = 0; i < Aux.size(); i++)
		{
			Lista1.add(Aux.get(i));
		}
	}
	//solo para el calendario
	public void calculaIda(JTextArea text, ArrayList<String> Lista1, ArrayList<String> Lista2, int n)
	{
		String temp1, temp2, fijo;
		int f=1;
		for(int i=0; i<n-1; i++)
		{
			text.append("   Fecha #"+f+"\n");
			for(int j=0; j<n/2; j++)
			{
				text.append("   "+Lista1.get(j)+" vs "+Lista2.get(j)+"\n");
			}
			text.append("\n");
			
			fijo= Lista1.remove(0);
			temp1= Lista1.remove(Lista1.size()-1);
			temp2= Lista2.remove(0);
			
			insertarAlInicioCalendario(Lista1, fijo, temp2);
			Lista2.add(temp1);
			f++;
		}
	}
	public void calculaVuelta(JTextArea text, ArrayList<String> Lista1, ArrayList<String> Lista2, int n)
	{
		String temp1, temp2, fijo;
		int f= n;
		for(int i=0; i<n-1; i++)
		{
			text.append("   Fecha #"+f+"\n");
			for(int j=0; j<n/2; j++)
			{
				text.append("   "+Lista2.get(j)+" vs "+Lista1.get(j)+"\n");
			}
			text.append("\n");
			
			fijo= Lista1.remove(0);
			temp1= Lista1.remove(Lista1.size()-1);
			temp2= Lista2.remove(0);
			
			insertarAlInicioCalendario(Lista1, fijo, temp2);
			Lista2.add(temp1);
			f++;
		}
	}
	public void tabla(Datos nodo, Datos nodo2)
	{
		int num;
		//1ro gana 2do pierde
		if( nodo.getG()>nodo2.getG() )
		{
			for(int i=0; i<pCabeza.size(); i++)
			{
				if( nodo.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getPts()+3;
					pCabeza.get(i).setPts(num);
				
					num= pCabeza.get(i).getGA()+nodo.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo2.getG();
					pCabeza.get(i).setGE(num);
				
					num= pCabeza.get(i).getDif()+(nodo.getG()-nodo2.getG());
					pCabeza.get(i).setDif(num);
				
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
				
					num= pCabeza.get(i).getPG()+1;
					pCabeza.get(i).setPG(num);
				}
				if( nodo2.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getGA()+nodo2.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo.getG();
					pCabeza.get(i).setGE(num);
				
					num= pCabeza.get(i).getDif()+(nodo2.getG()-nodo.getG());
					pCabeza.get(i).setDif(num);
				
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
				
					num= pCabeza.get(i).getPP()+1;
					pCabeza.get(i).setPP(num);
				}
			}
		}
		//Empate
		if ( nodo.getG() == nodo2.getG())
		{
			for(int i=0; i<pCabeza.size(); i++)
			{
				if (nodo.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getPts()+1;
					pCabeza.get(i).setPts(num);
				
					num= pCabeza.get(i).getGA()+nodo.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo2.getG();
					pCabeza.get(i).setGE(num);
					
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
					
					num= pCabeza.get(i).getPE()+1;
					pCabeza.get(i).setPE(num);
				}
				if ( nodo2.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getPts()+1;
					pCabeza.get(i).setPts(num);
					
					num= pCabeza.get(i).getGA()+nodo2.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo.getG();
					pCabeza.get(i).setGE(num);
					
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
					
					num= pCabeza.get(i).getPE()+1;
					pCabeza.get(i).setPE(num);
				}
			}
		}
		//1ro pierde 2do gana
		if ( nodo.getG()<nodo2.getG() )
		{
			for(int i=0; i<pCabeza.size(); i++)
			{
				if ( nodo2.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getPts()+3;
					pCabeza.get(i).setPts(num);
				
					num= pCabeza.get(i).getGA()+nodo2.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo.getG();
					pCabeza.get(i).setGE(num);
					
					num= pCabeza.get(i).getDif()+(nodo2.getG()-nodo.getG());
					pCabeza.get(i).setDif(num);
					
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
				
					num= pCabeza.get(i).getPG()+1;
					pCabeza.get(i).setPG(num);
				}
				if ( nodo.getNombre().equals(pCabeza.get(i).getNombre()) )
				{
					num= pCabeza.get(i).getGA()+nodo.getG();
					pCabeza.get(i).setGA(num);
				
					num= pCabeza.get(i).getGE()+nodo2.getG();
					pCabeza.get(i).setGE(num);
				
					num= pCabeza.get(i).getDif()+(nodo.getG()-nodo2.getG());
					pCabeza.get(i).setDif(num);
				
					num= pCabeza.get(i).getPJ()+1;
					pCabeza.get(i).setPJ(num);
				
					num= pCabeza.get(i).getPP()+1;
					pCabeza.get(i).setPP(num);
				}
			}
		}
	}
	public void insertarData(JTable T, boolean band2)
	{
		int c= 0;
		ArrayList<Datos> Temp= new ArrayList<Datos>();
		Temp.addAll(pCabeza);
		if(band2)
		{
			for (int i = 0; i < Temp.size(); i++)
			{
				if( Temp.get(i).getNombre().equals("Descanso") )
				{
					c= i;
				}
			}
			Temp.remove(c);
		}
		DefaultTableModel modelo= (DefaultTableModel) T.getModel();
		for (int j = 0; j < Temp.size(); j++)
		{
			modelo.setValueAt((j+1), j, 0);
			modelo.setValueAt(Temp.get(j).getNombre(), j, 1);
			modelo.setValueAt(Temp.get(j).getPts(), j, 2);
			modelo.setValueAt(Temp.get(j).getPJ(), j, 3);
			modelo.setValueAt(Temp.get(j).getPG(), j, 4);
			modelo.setValueAt(Temp.get(j).getPE(), j, 5);
			modelo.setValueAt(Temp.get(j).getPP(), j, 6);
			modelo.setValueAt(Temp.get(j).getGA(), j, 7);
			modelo.setValueAt(Temp.get(j).getGE(), j, 8);
			modelo.setValueAt(Temp.get(j).getDif(), j, 9);
		}
	}
}
