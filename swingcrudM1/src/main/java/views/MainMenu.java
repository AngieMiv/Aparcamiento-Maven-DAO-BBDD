package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

	
	final int txtSize1 = 12;
	final int txtSize2 = 14;
	final String defaultFont = "Tahoma";
	
	public MainMenu() {
		
        setTitle("Mantenimientos");
        setSize(300, 364);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usamos null layout
        
    	JLabel lblNewLabel = new JLabel("Mantenimientos");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font(defaultFont, Font.BOLD, txtSize2));
		lblNewLabel.setBounds(88, 11, 259, 60);
        add(lblNewLabel);
        String[] opc = {
        		"Usuarios",
        		"Puestos Trabajo",
        		"Perifericos",
        		"Asignaciones"
        		
        };
        // Crear botones con posiciones manuales
        for (int i = 1; i <= opc.length; i++) {
            JButton button = new JButton(opc[i-1] );
            button.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
            button.setBounds(40, 30 + (i * 40), 200, 30);
            int finalI = i;
            button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (finalI==1) {
						openWindow(finalI);
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Opción no implementada");
					}
				}
			});
//            button.addActionListener(e -> openWindow(finalI));
            add(button);
        }
        
        // Botón para salir
        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(140, 236, 100, 30);
        exitButton.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
        

	}
	
	
	  private void openWindow(int number) {
		  
		  // Ocultar la ventana principal
	        this.setVisible(false);
	        UsersView window = new UsersView();
		    
	        // Restaurar la ventana principal al cerrar la secundaria
	        window.frmCurdSwing.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                setVisible(true);
	            }
	        });

	        window.frmCurdSwing.setVisible(true);
	    }
	
}
