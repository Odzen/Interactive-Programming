package craps;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICraps extends JFrame {
	
	private JLabel dado1, dado2, textTiro, textPunto;
	private JButton lanzar;
	private JTextField tiro, punto;
	private Container contenedor;
	private ControlCraps control;
	private EscuchaEventos escucha;
	
	private ImageIcon imagen;
	
	public GUICraps() {
		
		super("Craps");
		
		initGUI();
		
		this.setSize(420,200);
		this.setVisible(true);
		this.setResizable(true);
		this.setLocationRelativeTo(null);	
	}
	
	private void initGUI() {
		contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		control = new ControlCraps();
		escucha = new EscuchaEventos();
		
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado1 = new JLabel(imagen);
		contenedor.add(dado1);
		
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado2 = new JLabel(imagen);
		contenedor.add(dado2);
		
		lanzar = new JButton("lanzar");
		lanzar.addActionListener(escucha);
		contenedor.add(lanzar);
		
		textTiro = new JLabel("Tiro: ");
		textTiro.setVisible(false);
		contenedor.add(textTiro);
		
		tiro = new JTextField(3);
		tiro.setVisible(false);
		contenedor.add(tiro);
		
		textPunto = new JLabel("Punto: ");
		textPunto.setVisible(false);
		contenedor.add(textPunto);
		
		punto = new JTextField(3);
		punto.setVisible(false);
		contenedor.add(punto);
		
	}
	
	private class EscuchaEventos implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evento) {
			// TODO Auto-generated method stub
			if(evento.getSource()==lanzar) {
				visualizarCara();
				control.determinarJuego();
				Icon icono;
				switch(control.getEstado()) {
				case 1:
					   icono = new ImageIcon("src/imagenes/ganaste.png");
					   JOptionPane.showMessageDialog(lanzar, "Ganaste!!", "Resultado", JOptionPane.DEFAULT_OPTION, icono);
					   break;
				case 2: icono = new ImageIcon("src/imagenes/perdiste.png");
				        JOptionPane.showMessageDialog(lanzar, "Perdiste!!", "Resultado", JOptionPane.DEFAULT_OPTION, icono);
					    break;
				case 3: icono = new ImageIcon("src/imagenes/punto.png");
				        textPunto.setVisible(true);
				        punto.setText(String.valueOf(control.getPunto()));
				        punto.setVisible(true);
		                JOptionPane.showMessageDialog(lanzar, "Estas en ronda punto!!", "Resultado", JOptionPane.DEFAULT_OPTION, icono);
					    break;
				}
			}
			
		}
		
	}
	
	private void visualizarCara() {
		control.calcularTiro();
		
		imagen = new ImageIcon("src/imagenes/"+String.valueOf(control.getCaraDado1())+".png");
		dado1.setIcon(imagen);
		
		imagen = new ImageIcon("src/imagenes/"+String.valueOf(control.getCaraDado2())+".png");
		dado2.setIcon(imagen);
		
		textTiro.setVisible(true);
		tiro.setText(String.valueOf(control.getTiro()));
		tiro.setVisible(true);
	}
	

}
