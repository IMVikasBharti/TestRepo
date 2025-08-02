package bankingProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class OpenBankingAccount extends JFrame implements ActionListener
{
	Font bold;
	Font italic;
	Font shortItalic;
	JTextField OpenBankingHeader;
	JLabel accTypeLB;
	JComboBox accTypeCB;
	JLabel amountValLB;
	JTextField amountValTF;
	JTextField mDisplayTF;
	JButton submitBT;
	JButton backBT;
	
	void openAccount()
	{
		super.setTitle("Open Account & Options");
		super.setBounds(250, 30, 800, 600);
		
		bold = new Font("serif",Font.BOLD,28);
		italic = new Font("serif",Font.ITALIC,30);
		shortItalic = new Font("serif",Font.ITALIC,20);
		
		OpenBankingHeader = new JTextField("Welcome "+BankingHomePage.loginName+" , Choose The Account Type!!");
		OpenBankingHeader.setBounds(100, 10, 600, 50);
		OpenBankingHeader.setEditable(false);
		OpenBankingHeader.setFont(italic);
		OpenBankingHeader.setHorizontalAlignment(SwingConstants.CENTER);
		OpenBankingHeader.setBackground(Color.LIGHT_GRAY);
		super.add(OpenBankingHeader);
		
		mDisplayTF = new JTextField("Message Display");
		mDisplayTF.setForeground(Color.WHITE);
		mDisplayTF.setBounds(150, 90, 500, 40);
		mDisplayTF.setEditable(false);
		mDisplayTF.setFont(italic);
		mDisplayTF.setBackground(Color.LIGHT_GRAY);
		mDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(mDisplayTF);
		
		accTypeLB = new JLabel("Account Type :");
		accTypeLB.setBounds(200, 200, 250, 40);
		accTypeLB.setFont(italic);
		accTypeLB.setForeground(Color.BLUE);
		super.add(accTypeLB);
		
		String[] s = {"Select Option","Saving A/c","Current A/c"};
		accTypeCB = new JComboBox<String>(s);
		accTypeCB.setBounds(405, 200, 150, 40);
		super.add(accTypeCB);
		accTypeCB.addActionListener(this);
		
		amountValLB = new JLabel("Amount Value :");
		amountValLB.setBounds(200, 250, 250, 40);
		amountValLB.setFont(italic);
		super.add(amountValLB);
		
		amountValTF = new JTextField();
		amountValTF.setBounds(405, 255, 150, 35);
		amountValTF.setFont(shortItalic);
		super.add(amountValTF);
		
		backBT = new JButton("Back");
		backBT.setBounds(400, 450, 150, 40);
		backBT.setFont(italic);
		backBT.setForeground(Color.BLUE);
		super.add(backBT);
		backBT.addActionListener(this);
		
		submitBT = new JButton("Submit");
		submitBT.setBounds(555, 450, 150, 40);
		submitBT.setFont(italic);
		submitBT.setForeground(Color.BLUE);
		super.add(submitBT);
		submitBT.addActionListener(this);
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		double inputVal = 0;
		
		if(e.getSource() == submitBT)
		{
//			String accType = (String) accTypeCB.getSelectedItem();
			char[] inputValidate = amountValTF.getText().toCharArray();
			
			for (int i = 0; i< inputValidate.length; i++)
			{
				if(inputValidate[i] >= 48 &&  inputValidate[i] <= 57 )
				{
					inputVal = Double.parseDouble(amountValTF.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid Input. Character or Decimal Places Not Accepted");
					break;
				}
			}
			if(accTypeCB.getSelectedIndex() == 0)
			{
				mDisplayTF.setForeground(Color.RED);
				mDisplayTF.setText("Please Choose Account Type");		
			}
			else if(inputVal < 1000)
			{
				mDisplayTF.setFont(shortItalic);
				mDisplayTF.setForeground(Color.RED);
				mDisplayTF.setText("Min. INR 1000/- Require to open An Account");	
			}
			else
			{
			
			try
			{	
				int amountInDB = 0;
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				
				Statement s = c.createStatement();
				s.executeUpdate("Create table if not exists Happy_Banking_Accounting(eMail_ID varchar(50) primary key, Acc_Type_SA_CA varchar(50) not null, Acc_Balance int(100), Last_Transaction int(100), Fund_Transfer_TO varchar(100), Fund_Transfer_Amount int(100), Acc_Active_Status int(2))");
				
				ResultSet r = s.executeQuery("Select * from Happy_Banking_Accounting");

				boolean available = false;
				
				while(r.next())
				{
					if(BankingHomePage.loginID.equalsIgnoreCase(r.getString("eMail_ID")))
					{
						available = true;
					}
				}
				if(available)
				{
					JOptionPane.showMessageDialog(this, "Arleady Have An Account. Please Choose Other Available Actions !!");
				}
				else
				{
				
				
					r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
					
					while(r.next())
					{
						amountInDB = r.getInt("Acc_Balance");
					}
					
					PreparedStatement ps = c.prepareStatement("insert into happy_banking_accounting(email_id, acc_type_sa_ca, acc_balance, last_transaction, acc_active_status) values(?,?,?,+(?),?)");
					ps.setString(1, BankingHomePage.loginID);
					ps.setString(2, (String) accTypeCB.getSelectedItem());
					ps.setDouble(3, inputVal+amountInDB);
					ps.setDouble(4, inputVal);
					ps.setInt(5, 1);
					
					ps.executeUpdate();
					
					mDisplayTF.setFont(shortItalic);
					mDisplayTF.setForeground(Color.BLUE);
					mDisplayTF.setText("Account Created. Happy Banking !!");	
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
			}
		}
		else if(e.getSource() == backBT)
		{
			System.out.println("Back Button Pressed");
			
			EasyBankingOptions eBO = new EasyBankingOptions();
			eBO.bankingOptions();
			
			super.dispose();
		}
	}
	
}
