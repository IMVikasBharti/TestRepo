package bankingProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class BankingSignUP extends JFrame implements ActionListener
{
	JTextField homeHeader;
	JLabel fNameLB;
	JLabel lastNameLB;
	JTextField fNameTF;
	JTextField lastNameTF;
	JLabel emailLB;
	JTextField emailTF;
	JLabel pWordLB;
	JPasswordField pWordTF;
	JLabel conf_pWordLB;
	JPasswordField conf_pWordTF;
	JLabel phoneLB;
	JTextField phoneTF;
	JComboBox salutation;
	JLabel salutationLB;
	JTextField signUPDisplayTF;
	JButton reset;
	JButton submit;
	JButton homeAtSignUPBT;
	Font italic;
	Font bold;
	Font shortItalic;
	ImageIcon resetButton;
	ImageIcon submitButton;
	ImageIcon homeFromSignUP;
	JTextField resetTF;
	JTextField submitTF;

		
	void showSignUP()
	{
		super.setTitle("Banking SignUP Page");
		super.setBounds(250, 30, 800, 600);
		
		bold = new Font("serif",Font.BOLD,28);
		italic = new Font("serif",Font.ITALIC,30);
		shortItalic = new Font("serif",Font.ITALIC,20);
		resetButton = new ImageIcon("C:\\Users\\V.Bharti\\Desktop\\Java, FullStack\\imageUtilize\\resetImageS.jpg");
		submitButton = new ImageIcon("C:\\Users\\V.Bharti\\Desktop\\Java, FullStack\\imageUtilize\\submitButton.jpg");
		homeFromSignUP = new ImageIcon("C:\\Users\\V.Bharti\\Desktop\\Java, FullStack\\imageUtilize\\homeIcon.jpg");
		
		homeHeader = new JTextField("Sign Up To Happy Banking !!");
		homeHeader.setBounds(100, 10, 600, 50);
		homeHeader.setEditable(false);
		homeHeader.setFont(italic);
		homeHeader.setHorizontalAlignment(SwingConstants.CENTER);
		homeHeader.setBackground(Color.LIGHT_GRAY);
		super.add(homeHeader);
		
		fNameLB = new JLabel("First Name :");
		fNameLB.setBounds(142, 80, 200, 30);
		fNameLB.setFont(bold);
		super.add(fNameLB);
		
		fNameTF = new JTextField();
		fNameTF.setBounds(305, 80, 300, 30);
		fNameTF.setFont(shortItalic);
		super.add(fNameTF);
		
		lastNameLB = new JLabel("Last Name :");
		lastNameLB.setBounds(145, 115, 200, 30);
		lastNameLB.setFont(bold);
		super.add(lastNameLB);
		
		lastNameTF = new JTextField();
		lastNameTF.setBounds(305, 115, 300, 30);
		lastNameTF.setFont(shortItalic);
		super.add(lastNameTF);
		
		emailLB = new JLabel("eMail :");
		emailLB.setBounds(205, 150, 200, 30);
		emailLB.setFont(bold);
		super.add(emailLB);
		
		emailTF = new JTextField();
		emailTF.setBounds(305, 150, 300, 30);
		emailTF.setFont(shortItalic);
		super.add(emailTF);
		
		pWordLB = new JLabel("passWord :");
		pWordLB.setBounds(150, 188, 200, 30);
		pWordLB.setFont(bold);
		super.add(pWordLB);
		
		pWordTF = new JPasswordField();
		pWordTF.setBounds(305, 188, 300, 30);
		pWordTF.setFont(shortItalic);
		super.add(pWordTF);
		
		conf_pWordLB = new JLabel(" Confirm passWord :");
		conf_pWordLB.setBounds(35, 223, 280, 30);
		conf_pWordLB.setFont(bold);
		super.add(conf_pWordLB);
		
		conf_pWordTF = new JPasswordField();
		conf_pWordTF.setBounds(305, 223, 300, 30);
		conf_pWordTF.setFont(shortItalic);
		super.add(conf_pWordTF);
		
		phoneLB = new JLabel(" Mobile Number :");
		phoneLB.setBounds(77, 258, 280, 30);
		phoneLB.setFont(bold);
		super.add(phoneLB);
		
		phoneTF = new JTextField();
		phoneTF.setBounds(305, 258, 300, 30);
		phoneTF.setFont(shortItalic);
		super.add(phoneTF);

		salutationLB = new JLabel("Gender :");
		salutationLB.setBounds(182, 285, 150, 47);
		salutationLB.setFont(bold);
		super.add(salutationLB);
		
		String[] s = {"Select Option","Male","Female"};
		salutation = new JComboBox(s);
		salutation.setBounds(305, 295, 120, 30);
		super.add(salutation);
		salutation.addActionListener(this);
		
		signUPDisplayTF = new JTextField("Message Display");
		signUPDisplayTF.setForeground(Color.WHITE);
		signUPDisplayTF.setBounds(150, 350, 500, 40);
		signUPDisplayTF.setEditable(false);
		signUPDisplayTF.setFont(italic);
		signUPDisplayTF.setBackground(Color.LIGHT_GRAY);
		signUPDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(signUPDisplayTF);

			
		resetTF = new JTextField("RESET");
		resetTF.setBounds(200, 430, 78, 21);
		resetTF.setEditable(false);
		resetTF.setFont(shortItalic);
		resetTF.setBackground(Color.WHITE);
		resetTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(resetTF);
		
		reset = new JButton();
		reset.setBounds(200, 450, 78, 47);
		reset.setIcon(resetButton);
		super.add(reset);
		reset.addActionListener(this);
		
		submitTF = new JTextField("SUBMIT");
		submitTF.setBounds(500, 430, 78, 21);
		submitTF.setEditable(false);
		submitTF.setFont(shortItalic);
		submitTF.setBackground(Color.WHITE);
		submitTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(submitTF);
		
		submit = new JButton();
		submit.setBounds(500, 450, 78, 47);
		submit.setIcon(submitButton);
		super.add(submit);
		submit.addActionListener(this);
		
		homeAtSignUPBT = new JButton();
		homeAtSignUPBT.setBounds(350, 431, 78, 67);
		homeAtSignUPBT.setIcon(homeFromSignUP);
		super.add(homeAtSignUPBT);
		homeAtSignUPBT.addActionListener(this);
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == reset)
		{
			System.out.println("Reset Actioned");
			fNameTF.setText("");
			lastNameTF.setText("");
			emailTF.setText("");
			pWordTF.setText("");
			conf_pWordTF.setText("");
			phoneTF.setText("");
			signUPDisplayTF.setForeground(Color.BLUE);
			signUPDisplayTF.setText("Reset Served");
			salutation.setSelectedIndex(0);

		}
		else if(e.getSource() == submit)
		{
			System.out.println("Submit Actioned");
			
			String passWordPT = null;
			char[] instantP = pWordTF.getPassword();
			char[] instantCP = conf_pWordTF.getPassword();
			
			String P = String.valueOf(instantP);
			String CP = String.valueOf(instantCP);
			
//			long phoneLong_db = Long.parseLong(phoneTF.getText());
			
//			System.out.println(sha1('P'));
			
			if(fNameTF.getText().isEmpty() || emailTF.getText().isEmpty() || P.isEmpty() || CP.isEmpty() || phoneTF.getText().isEmpty() || salutation.getSelectedIndex() == 0) 
			{
				signUPDisplayTF.setForeground(Color.RED);
				signUPDisplayTF.setText("Empty Entry Not Allowed");	
				JOptionPane.showMessageDialog(this, "Only Last Name Is Allowed To Remain Empty. Please Fill Rest Other Column");		
			}
			else if(!P.equals(CP))
			{
				signUPDisplayTF.setForeground(Color.RED);
				signUPDisplayTF.setText("Message Display");
				JOptionPane.showMessageDialog(this, "passWord & conf_passWord Not Matching");
			}
			else
			{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection cPT = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				
				Statement sPT = cPT.createStatement();
				
				sPT.executeUpdate("Create table if not exists Password_Tally(passWord varchar(100) unique not null)");
				PreparedStatement psPT = cPT.prepareStatement("insert into password_tally values(sha1(?))");
				psPT.setString(1, P);
				
				psPT.executeUpdate();
				
				ResultSet rPT = sPT.executeQuery("select * from password_tally");
				
				while(rPT.next())
				{
					passWordPT = rPT.getString("password");
				}
//				System.out.println(passWordPT);
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				Statement s = c.createStatement();
				s.executeUpdate("CREATE TABLE if not exists Happy_Banking(first_name varchar(100),last_name varchar(100),e_Mail varchar(100) primary key,passWord varchar(100) unique not null, mobile_number varchar(20),gender varchar(20))");
				ResultSet r = s.executeQuery("select * from happy_banking");
					
				boolean pwFlag = false;
				boolean emFlag = false;
				
				while(r.next())
				{
					if(emailTF.getText().equalsIgnoreCase(r.getString("e_Mail")))
					{
						emFlag = true;
					}		
					else if(passWordPT.equals(r.getString("password")))
					{
						pwFlag = true;
					}
				}
				
				if(emFlag)
				{
					signUPDisplayTF.setForeground(Color.RED);
					signUPDisplayTF.setText("eMail Exists");
					JOptionPane.showMessageDialog(this, "eMail Already Available In Record. Please Login at Home Page");
				}
				else if(pwFlag)
				{
					signUPDisplayTF.setForeground(Color.RED);
					signUPDisplayTF.setText("Restructure Password");
					JOptionPane.showMessageDialog(this, "Password Strength Not Met. Please Choose Another Password");
				}
				else
				{
					sPT.executeUpdate("delete from password_tally");
					
					PreparedStatement ps = c.prepareStatement("insert into happy_banking values(?,?,?,sha1(?),?,?)");
					
					ps.setString(1, fNameTF.getText());
					ps.setString(2, lastNameTF.getText());
					ps.setString(3, emailTF.getText());
					ps.setString(4, P);
					ps.setString(5, phoneTF.getText());
					ps.setString(6,(String) salutation.getSelectedItem());
					
					ps.executeUpdate();
					
					
					JOptionPane.showMessageDialog(this, "Credential Added, Please Login Using eMail & Password");
				}
				
				if(!pwFlag)
				{
					BankingHomePage bp = new BankingHomePage();
					bp.showHome();
								
					super.dispose();
				}
				

				c.close();
				s.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
			}
		}
		else if(e.getSource() == homeAtSignUPBT)
		{
			System.out.println("atHome Actioned");
			
			BankingHomePage bp = new BankingHomePage();
			bp.showHome();
	
			
			super.dispose();
		}
	}
	
}
