/*
 * @author Ryan Friedrich, Saif Jouda, Omar Elsayed
 *
 * Implements the Login interface and allows access to the MainUI
 *
 */
package statsVisualiser.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener{

	private static JTextField userText;
	private static JTextField passText;
	private static JFrame frame;

	/**
	 * The Main
	 * launches the LoginUI
	 */
	public static void main(String [] args) {
		frame = new JFrame("Login");
		JPanel panel = new JPanel();
		frame.setSize(400,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		placeComponents(panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
	}

	/**
	 * The Login Interface
	 * @param panel the panel
	 */
	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);

		// username text label
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(20, 10, 80, 25);
		panel.add(userLabel);

		// username text field
		userText = new JTextField(20);
		userText.setBounds(100, 10, 250, 25);
		panel.add(userText);

		// password text label
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(20, 45, 80, 25);
		panel.add(passLabel);

		// passoword text field
		passText = new JTextField(20);
		passText.setBounds(100, 45, 250, 25);
		panel.add(passText);

		// login button
		JButton button = new JButton("Login");
		button.setBounds(160, 80, 80 , 25);
		button.addActionListener(new Login());
		panel.add(button);
	}


	/**
	 * Verifies the Login entered by the user
	 * @param username the username
	 * @param password the password
	 */
	private void loginVerify(String username, String password) {
		boolean login = false;
		// login info 1st is username and 2nd is password
		String loginInfo [][] = {{"ryan", "password123"},
								 {"admin", "apples"},
								 {"user", "idk"},{"a","a"}};
		// checking if the text fields match with a username and password set in the array
		for (int i = 0; i < loginInfo.length; i++) {
			if (username.equals(loginInfo[i][0]) && password.equals(loginInfo[i][1])) {
				login = true;
				//LoginInitiator();
				loginClose();
				loginInitiator();
			}
		}
		if (login == false) {
			displayError();
			System.exit(0);
		}


	}

	/**
	 * Initiates the Main UI
	 */
	private void loginInitiator(){
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Closes the Login Interface
	 */
	private void loginClose(){
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Displays an error message
	 */
	private void displayError() {
		JOptionPane.showMessageDialog(null, "Incorrect Login!");
	}

	/**
	 * Listener
	 */
	public void actionPerformed(ActionEvent e) {
		String username = userText.getText();
		String pasword = passText.getText();
		loginVerify(username,pasword);
	}
}
