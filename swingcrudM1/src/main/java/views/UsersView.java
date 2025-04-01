package views;

	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import java.awt.Font;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.border.TitledBorder;
	import javax.swing.table.TableModel;

import model.DaoJdbc;
import model.DaoUsersJdbc;
import model.DbUtils;
import model.User;

import javax.swing.border.LineBorder;
	import java.awt.Color;
	import javax.swing.border.EmptyBorder;
	import javax.swing.border.EtchedBorder;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import java.sql.*;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.JRadioButton;
	public class UsersView {

		public JFrame frmCurdSwing;
		private JTextField txtID;
		private JTextField txtNombre;
		private JTextField txtEdad;
		private JTextField txtCiudad;
		private JTable tabla;
		private DaoUsersJdbc du = new DaoUsersJdbc();
		
		//Database Connection
		Connection con = null;
		PreparedStatement pst;
		ResultSet rs;
		
		/**
		 * Create the application.
		 */
		public UsersView() {
			 initialize();
			 con = new DaoJdbc().getConn();
			 loadData();
		}
		
	
		// Limpiar Campos de la sección ficha
			public void clear() {
				txtID.setText("");
				txtNombre.setText("");
				txtEdad.setText("");
				txtCiudad.setText("");
				txtNombre.requestFocus();
			}
		
			// Rellenar la sección lista
			public void loadData() {
				try {
					// Rellenar de forma directa la lista
					pst = con.prepareStatement("Select * from users");
					rs = pst.executeQuery();
					tabla.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		private boolean validate(String s,String msg) {
			
			boolean result = true;
			
			if (s == null || s.isEmpty() || s.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, msg);
				result = false;
			}
			
			return result;
		}
		/**
		 * Inicializa la ventana.
		 */
		private void initialize() {
			
			final int txtSize1 = 12;
			final int txtSize2 = 14;
			final String defaultFont = "Tahoma";
			frmCurdSwing = new JFrame();
			frmCurdSwing.setTitle("CRUD Básico Swing MySQL");
			frmCurdSwing.getContentPane().setFont(new Font(defaultFont, Font.PLAIN, 14));
			frmCurdSwing.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("   Mantenimiento de Usuarios");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font(defaultFont, Font.BOLD, txtSize2));
			lblNewLabel.setBounds(10, 11, 259, 60);
			frmCurdSwing.getContentPane().add(lblNewLabel);
			
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(20, 71, 387, 284);
			frmCurdSwing.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Id");
			lblNewLabel_1.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			lblNewLabel_1.setBounds(21, 46, 46, 24);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Nombre");
			lblNewLabel_1_1.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			lblNewLabel_1_1.setBounds(21, 81, 46, 24);
			panel.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1_2 = new JLabel("Edad");
			lblNewLabel_1_2.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			lblNewLabel_1_2.setBounds(21, 116, 46, 24);
			panel.add(lblNewLabel_1_2);
			
			JLabel lblNewLabel_1_3 = new JLabel("Ciudad");
			lblNewLabel_1_3.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			lblNewLabel_1_3.setBounds(21, 154, 46, 24);
			panel.add(lblNewLabel_1_3);
			
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			txtID.setBounds(78, 46, 287, 24);
			panel.add(txtID);
			txtID.setColumns(10);
			
			txtNombre = new JTextField();
			txtNombre.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			txtNombre.setColumns(10);
			txtNombre.setBounds(78, 81, 287, 24);
			panel.add(txtNombre);
			
			txtEdad = new JTextField();
			txtEdad.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			txtEdad.setColumns(10);
			txtEdad.setBounds(78, 120, 287, 24);
			panel.add(txtEdad);
			
			txtCiudad = new JTextField();
			txtCiudad.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			txtCiudad.setColumns(10);
			txtCiudad.setBounds(78, 155, 287, 24);
			panel.add(txtCiudad);
			
			JButton btnSave = new JButton("Guardar");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = txtID.getText();
					String name = txtNombre.getText();
					String age = txtEdad.getText();
					String city = txtCiudad.getText();
					
					if (!validate(name,"Nombre Obligatorio")) {	txtNombre.requestFocus();	return;	}
					if (!validate(age,"Edad Obligatoria")) {	txtEdad.requestFocus();	    return;	}
					if (!validate(city,"Edad Obligatoria")) {	txtCiudad.requestFocus();   return;	}
					
					if (txtID.getText().isEmpty()) {
						
						if (du.insertOne(new User(0,name,Integer.parseInt(age),city))) {
							JOptionPane.showMessageDialog(null, "Registro Creado");
							clear();
							loadData();
						}
						else {
							
							JOptionPane.showMessageDialog(null, "Error al insertar registro");
						}
						
					}
					
					
				}
			});
			btnSave.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			btnSave.setBounds(78, 195, 89, 23);
			panel.add(btnSave);
			
			JButton btnUpdate = new JButton("Actualizar");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Update Details
					String id = txtID.getText();
					String name = txtNombre.getText();
					String age = txtEdad.getText();
					String city = txtCiudad.getText();

					if (!validate(name,"Nombre Obligatorio")) {	txtNombre.requestFocus();	return;	}
					if (!validate(age,"Edad Obligatoria")) {	txtEdad.requestFocus();	    return;	}
					if (!validate(city,"Edad Obligatoria")) {	txtCiudad.requestFocus();   return;	}

					if (!txtID.getText().isEmpty()) {
						
						if (du.updateOne(id,new User(Integer.parseInt(id),name,Integer.parseInt(age),city)))
						{
							JOptionPane.showMessageDialog(null, "Registro Actualizado");
							clear();
							loadData();
						}
						else {
							JOptionPane.showMessageDialog(null, "No se puedo actualizar el registro");
						}
					}
				}
			});
			btnUpdate.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			btnUpdate.setBounds(177, 195, 89, 23);
			panel.add(btnUpdate);
			
			JButton btnDelete = new JButton("Borrar");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Delete Details
					String id = txtID.getText();
					if (!txtID.getText().isEmpty()) {

						int result = JOptionPane.showConfirmDialog(null, "Confirma operación?", "Borrar",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (result == JOptionPane.YES_OPTION) {
															
							if (du.deleteOne(id)) {
								JOptionPane.showMessageDialog(null, "Registro Eliminado");
								clear();
								loadData();

							} else {
								JOptionPane.showMessageDialog(null, "Nos se pudo eliminar el registro");
							}
						}
					}

				}
			});
			btnDelete.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			btnDelete.setBounds(276, 195, 89, 23);
			panel.add(btnDelete);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(417, 71, 467, 284);
			frmCurdSwing.getContentPane().add(scrollPane);
			
			tabla = new JTable();
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int index = tabla.getSelectedRow();
					TableModel model = tabla.getModel();
					// ID NAME AGE CITY
					txtID.setText(model.getValueAt(index, 0).toString());
					txtNombre.setText(model.getValueAt(index, 1).toString());
					txtEdad.setText(model.getValueAt(index, 2).toString());
					txtCiudad.setText(model.getValueAt(index, 3).toString());
				}
			});
			tabla.setFont(new Font(defaultFont, Font.PLAIN, txtSize1));
			tabla.setRowHeight(30);
			scrollPane.setViewportView(tabla);
			frmCurdSwing.setBounds(100, 100, 910, 522);
			// Como es una ventana secundaria se destruye y punto.
			frmCurdSwing.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//frmCurdSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

