/**
 * @Date 2016年11月16日
 *
 * @author 郭  璞
 *
 */
package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * @author 郭  璞
 *
 */
public class Login extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	public Login() {
		setSize(new Dimension(417, 332));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		setForeground(new Color(224, 255, 255));
		setAlwaysOnTop(true);
		setTitle("登陆");
		getContentPane().setLayout(null);
		getContentPane().setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Logo");
		//  /com/sun/javafx/scene/control/skin/caspian/fxvk-capslock-button.png
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("beach.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 419, 147);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("icon48.png")));
//		lblNewLabel_1.setToolTipText("头像");
		lblNewLabel_1.setBounds(49, 172, 86, 82);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(173, 216, 230));
		textField.setToolTipText("用户名");
		textField.setBounds(152, 175, 175, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(173, 216, 230));
		passwordField.setToolTipText("密码");
		passwordField.setBounds(152, 211, 175, 27);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("登    陆");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setForeground(new Color(147, 112, 219));
		btnNewButton.setBounds(152, 246, 175, 23);
		getContentPane().add(btnNewButton);
	}
	
	
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
	
}
