package bankingProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class BankingHomePage extends JFrame implements ActionListener
{
	JTextField mDisplayTF;
	JTextField homeHeader;
	JLabel eMailJL;
	JLabel pWordJL;
	JTextField eMailTF;
	JPasswordField pWordTF;
	JButton signIN;
	JButton signUP;
	JButton resetPass;
	Font italic;
	Font bold;
	ImageIcon sIN;
	ImageIcon sUP;
	static String loginID;
	static String loginName;
		
	void showHome()
	{
		super.setTitle("Banking Home Page");
		super.setBounds(250, 30, 800, 600);
		
		bold = new Font("serif",Font.BOLD,28);
		italic = new Font("serif",Font.ITALIC,30);
		Font shortItalic = new Font("serif",Font.ITALIC,20);
		sIN = new ImageIcon("C:\\Users\\V.Bharti\\Desktop\\Java, FullStack\\imageUtilize\\logIN.jpg");
		sUP = new ImageIcon("C:\\Users\\V.Bharti\\Desktop\\Java, FullStack\\imageUtilize\\signUP.jpg");
		
		mDisplayTF = new JTextField("Message Display");
		mDisplayTF.setForeground(Color.WHITE);
		mDisplayTF.setBounds(150, 90, 500, 40);
		mDisplayTF.setEditable(false);
		mDisplayTF.setFont(italic);
		mDisplayTF.setBackground(Color.LIGHT_GRAY);
		mDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(mDisplayTF);
		
		homeHeader = new JTextField("Welcome To Happy Banking !!");
		homeHeader.setBounds(100, 10, 600, 50);
		homeHeader.setEditable(false);
		homeHeader.setFont(italic);
		homeHeader.setHorizontalAlignment(SwingConstants.CENTER);
		homeHeader.setBackground(Color.LIGHT_GRAY);
		super.add(homeHeader);
		
		eMailJL = new JLabel("eMail :");
		eMailJL.setBounds(200, 150, 100, 30);
		eMailJL.setFont(bold);
		super.add(eMailJL);
		
		eMailTF = new JTextField();
		eMailTF.setBounds(305, 150, 300, 30);
		eMailTF.setFont(shortItalic);
		super.add(eMailTF);
		
		pWordJL = new JLabel("passWord :");
		pWordJL.setBounds(145, 200, 200, 30);
		pWordJL.setFont(bold);
		super.add(pWordJL);
		
		pWordTF = new JPasswordField();
		pWordTF.setBounds(305, 200, 300, 30);
		pWordTF.setFont(shortItalic);
		super.add(pWordTF);
		
		signIN = new JButton();
		signIN.setBounds(500, 300, 82, 76);
		signIN.setIcon(sIN);
		super.add(signIN);
		signIN.addActionListener(this);
		
		resetPass = new JButton("Forget Password");
		resetPass.setBounds(318, 340, 150, 30);
		super.add(resetPass);
		resetPass.addActionListener(this);
		
		signUP = new JButton();
		signUP.setBounds(200, 300, 82, 76);
		signUP.setIcon(sUP);
		super.add(signUP);
		signUP.addActionListener(this);
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == signIN)
		{
			System.out.println("Sign IN Actioned");
			
			try
			{
				String passWordPT = null;
				
				char[] instantP = pWordTF.getPassword();				
				String P = String.valueOf(instantP);
				
				if(eMailTF.getText().isEmpty() || P.isEmpty()) 
				{
					mDisplayTF.setForeground(Color.RED);
					mDisplayTF.setText("Empty Entry Not Allowed");	
					JOptionPane.showMessageDialog(this, "eMail & passWord, both need to be filled");		
				}
				else
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
//					System.out.println(passWordPT);
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
					
					Statement s = c.createStatement();
					ResultSet r = s.executeQuery("select * from happy_banking");
					
//					boolean pwFlag = false;
					boolean emFlag = false;
					
					while(r.next())
					{
						if(eMailTF.getText().equalsIgnoreCase(r.getString("e_Mail")) && passWordPT.equals(r.getString("password")))
						{
							loginID = r.getString("e_Mail");
							loginName =r.getString("First_Name");
							emFlag = true;
						}		
			/*			else if(passWordPT.equals(r.getString("password")))
						{
							pwFlag = true;
						}
			*/			sPT.executeUpdate("delete from password_tally");
					}
					if(emFlag)
					{
						mDisplayTF.setForeground(Color.BLUE);
						mDisplayTF.setText("Access Granted");
						JOptionPane.showMessageDialog(this, "Login Accessed, Diverting to Monitor Page");
//						System.out.println(loginID);
						
						EasyBankingOptions eBO = new EasyBankingOptions();
						eBO.bankingOptions();
						
						super.dispose();
					}
					
					else
					{
						mDisplayTF.setForeground(Color.RED);
						mDisplayTF.setText("Check Credential");
						JOptionPane.showMessageDialog(this, "Login Denied, Check Credential");
					}
	
				}
			}
			catch(Exception ex)
			{
				
			}
			finally{}
			
			
		}
		else if(e.getSource() == signUP)
		{
			System.out.println("Sign UP Actioned");
			
			BankingSignUP bsUP = new BankingSignUP();
			bsUP.showSignUP();
			super.dispose();
		}
		else if(e.getSource() == resetPass)
		{
			if(eMailTF.getText().isEmpty()) 
			{
				mDisplayTF.setForeground(Color.RED);
				mDisplayTF.setText("Please Provide eMail ID");
			}
			else 
			{
				mDisplayTF.setForeground(Color.BLUE);
				mDisplayTF.setText("passWord has been sent to eMail ID");
			}
		}
	}	
	
}
