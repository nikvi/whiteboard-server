package dst.four.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dst.four.person.UserPofile;

public class MyProfileWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblSetNickName;
	private JLabel lblSetGender;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProfileWindow frame = new MyProfileWindow();
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
	public MyProfileWindow() {
		setTitle("New WhiteBoard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWhiteboardName = new JLabel("Nickname");
		lblWhiteboardName.setForeground(new Color(255, 255, 255));
		lblWhiteboardName.setBounds(20, 20, 150, 38);
		contentPane.add(lblWhiteboardName);
		lblWhiteboardName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));

		textField = new JTextField();
		textField.setBounds(172, 20, 184, 32);
		contentPane.add(textField);
		textField.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		textField.setColumns(10);

		JLabel lblMaxUsers = new JLabel("Gender");
		lblMaxUsers.setForeground(new Color(255, 255, 255));
		lblMaxUsers.setBounds(20, 72, 150, 38);
		contentPane.add(lblMaxUsers);
		lblMaxUsers.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(new Color(255, 255, 255));
		rdbtnFemale.setBackground(new Color(128, 128, 128));
		rdbtnFemale.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		rdbtnFemale.setBounds(172, 83, 121, 23);
		contentPane.add(rdbtnFemale);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		rdbtnMale.setBackground(Color.GRAY);
		rdbtnMale.setBounds(172, 120, 121, 23);
		contentPane.add(rdbtnMale);

		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(rdbtnMale);
		groupGender.add(rdbtnFemale);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		textPane.setBounds(20, 200, 339, 104);
		contentPane.add(textPane);

		final UserPofile up = new UserPofile();
		if (up.loadProfile()) {
			textField.setText(up.getNickname());
			if (up.getGender().equals("Female"))
				rdbtnFemale.setSelected(true);
			else if (up.getGender().equals("Male"))
				rdbtnMale.setSelected(true);
			textPane.setText(up.getDiscription());
		}
		JButton btnStart = new JButton("Save");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().equals("")) {
					lblSetNickName.setVisible(true);
				} else
					up.setNickname(textField.getText());
				if (!rdbtnFemale.isSelected() && !rdbtnMale.isSelected()) {
					lblSetGender.setVisible(true);
				} else if (rdbtnFemale.isSelected()) {
					up.setGender("Female");
				} else
					up.setGender("Male");
				up.setDiscription(textPane.getText());
				if (!textField.getText().equals("")
						&& (rdbtnFemale.isSelected() || rdbtnMale.isSelected())) {
					up.saveProfile();
					dispose();
				}
			}
		});
		btnStart.setBounds(220, 320, 140, 40);
		contentPane.add(btnStart);
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnStart.setBackground(new Color(255, 215, 0));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(220, 380, 140, 40);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		btnCancel.setBackground(new Color(255, 215, 0));

		JLabel lblDiscriptionToMyself = new JLabel("Discription of Myself");
		lblDiscriptionToMyself.setForeground(Color.WHITE);
		lblDiscriptionToMyself.setFont(new Font("Segoe UI Light", Font.PLAIN,
				18));
		lblDiscriptionToMyself.setBounds(21, 158, 165, 38);
		contentPane.add(lblDiscriptionToMyself);

		lblSetNickName = new JLabel("Please Set a Nickname");
		lblSetNickName.setForeground(Color.ORANGE);
		lblSetNickName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSetNickName.setBounds(20, 55, 169, 15);
		lblSetNickName.setVisible(false);
		contentPane.add(lblSetNickName);

		lblSetGender = new JLabel("Please choose your gender");
		lblSetGender.setForeground(Color.ORANGE);
		lblSetGender.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSetGender.setBounds(21, 108, 169, 15);
		lblSetGender.setVisible(false);
		contentPane.add(lblSetGender);
	}
}