package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import controller.RedesController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Network Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px][204px]", "[23px][23px][23px][]"));
		
		JLabel lblNewLabel = new JLabel("System: \n" + System.getProperty("os.name"));
		contentPane.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 0,alignx left,aligny top");
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnNewButton = new JButton("Config IP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RedesController controller = new RedesController();
				controller.ipConfig();
			}
		});
		contentPane.add(btnNewButton, "cell 0 1 2 1,growx,aligny top");
		
		JButton btnNewButton_1 = new JButton("Ping Google");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RedesController controller = new RedesController();
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				controller.ping();
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}
		});
		contentPane.add(btnNewButton_1, "cell 0 2 2 1,growx,aligny top");
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		contentPane.add(btnNewButton_2, "cell 0 3 2 1,growx");
	}

}
