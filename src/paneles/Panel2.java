package paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel2 extends JPanel
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private ArrayList<String> nombre;
	private JScrollPane scroll;
	private Panel3 panel3;
	private int i=0;

	/**
	 * En este panel se piden los nombres de los equipos
	 * y se pasa: n y el booleano band para saber si n es par o impar,
	 * al siguiente panel
	 */
	public Panel2(final int n, final boolean band)
	{	
		setLayout(null);
		JLabel lblHola = new JLabel("Equipos");
		lblHola.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHola.setHorizontalAlignment(SwingConstants.CENTER);
		lblHola.setBounds(243, 49, 75, 29);
		add(lblHola);
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(243, 93, 179, 164);
		add(textArea);
		
		scroll = new JScrollPane(textArea);
		scroll.setBounds(new Rectangle(243, 93, 179, 164));
		add(scroll);
		
		final JButton btnNewButton = new JButton("Barajar");
		final JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel3= new Panel3(nombre, band);
				remove(getRootPane());
				getRootPane().setBorder(new EmptyBorder(5, 5, 5, 5));
				getRootPane().setContentPane(panel3);
				panel3.setLayout(null);
				panel3.setSize(700, 450);
			}
		});
		nombre= new ArrayList<String>();
		final int tnum;
		if(band)
		{
			nombre.add("Descanso");
			tnum= n-1;
		}
		else
		{
			tnum= n;
		}
		final JButton btnEquipo = new JButton("Ingresar Equipo");
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nombre.add(textField.getText());
				textArea.append("  "+" "+textField.getText()+"\n");
				i++;
				if(i==tnum)
				{
					btnEquipo.setEnabled(false);
					btnNewButton.setEnabled(true);
				}
			}
		});
		btnEquipo.setBounds(12, 116, 160, 25);
		add(btnEquipo);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(12, 153, 160, 29);
		add(textField);
		textField.setColumns(10);
		
		//se declara en la linea 56
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.shuffle(nombre);
				textArea.setText("");
				for(int i=0;i<nombre.size();i++)
				{
					if(nombre.get(i)!="Descanso")
					{
						textArea.append("  "+" "+nombre.get(i)+"\n");
					}
				}
				btnSiguiente.setEnabled(true);
			}
		});
		btnNewButton.setBounds(324, 51, 98, 25);
		add(btnNewButton);
		
		JLabel lblNombresDeLos = new JLabel("Nombres de Los Equipos");
		lblNombresDeLos.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNombresDeLos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombresDeLos.setBounds(12, 22, 207, 25);
		add(lblNombresDeLos);
		
		//se declara en la linea 57
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(12, 282, 117, 25);
		add(btnSiguiente);
		
		JLabel lblSiElNum = new JLabel("<html><body>Funciona para numero de Equipos pares<br>y numero de Equipos impares</body></html>");
		lblSiElNum.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSiElNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiElNum.setBounds(12, 194, 207, 67);
		add(lblSiElNum);

	}
}
