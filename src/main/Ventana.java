package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import paneles.Panel2;

import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class Ventana extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField textField;
	private int num;
	private boolean band;
	private JButton btnSiguiente;
	private JLabel lblIngreseElNumero;
	private Panel2 panel2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana()
	{
		setTitle("Gestor de Ligas de Futbol Inteligente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmPrueba = new JMenuItem("Sistema de Liga de Futbol");
		mntmPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Este programa calcula las estadísticas más\n " +
						"importantes de una liga de futbol\n"+
						"usando un algoritmo de emparejamiento");
			}
		});
		mnArchivo.add(mntmPrueba);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem validar = new JMenuItem("Datos Validos");
		validar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Respetar los respectivos campos.\n"+
			"En caso de ingresar un número negativo por error, en los resultados de los partidos, se convertirá a positivo.");
			}
		});
		mnAyuda.add(validar);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca del Programa");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Programa creado por: Edu031, Edu037 del Grupo21 de Sistemas Inteligentes\n"+
			"Gestor de Ligas de Futbol Inteligente");
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn = new JButton("Num de Equipos");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				num= Integer.parseInt(textField.getText());
				if(num>=3)
				{
					if(num%2!=0)
					{
						num++;
						band = true;
					}
					else
					{
						band = false;
					}
					JOptionPane.showMessageDialog(null, "Se ha insertado correctamente");
					btnSiguiente.setEnabled(true);
				}
			}
		});
		btn.setBounds(33, 86, 148, 25);
		contentPane.add(btn);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(193, 86, 37, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setEnabled(false);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2= new Panel2(num, band);
				remove(contentPane);
				panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(panel2);
				panel2.setLayout(null);
				panel2.setSize(700, 450);
			}
		});
		btnSiguiente.setBounds(113, 182, 117, 25);
		contentPane.add(btnSiguiente);
		
		lblIngreseElNumero = new JLabel("Ingrese el Número de Equipos");
		lblIngreseElNumero.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIngreseElNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseElNumero.setBounds(97, 22, 242, 25);
		contentPane.add(lblIngreseElNumero);
	}
}
