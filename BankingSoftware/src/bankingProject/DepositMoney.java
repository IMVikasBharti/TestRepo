package bankingProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class DepositMoney extends JFrame implements ActionListener
{
	Font bold;
	Font italic;
	Font shortItalic;
	JTextField depositMoneyHeader;
	JTextField mDisplayTF;
	JLabel depositValLB;
	JTextField depositValTF;
	JButton backBT;
	JButton submitBT;
	
	void depositM()
	{
		super.setTitle("Open Account & Options");
		super.setBounds(250, 30, 800, 600);
		
		bold = new Font("serif",Font.BOLD,28);
		italic = new Font("serif",Font.ITALIC,30);
		shortItalic = new Font("serif",Font.ITALIC,20);
		
		depositMoneyHeader = new JTextField("Welcome "+BankingHomePage.loginName+" , Place The Deposit Value!!");
		depositMoneyHeader.setBounds(100, 10, 600, 50);
		depositMoneyHeader.setEditable(false);
		depositMoneyHeader.setFont(italic);
		depositMoneyHeader.setHorizontalAlignment(SwingConstants.CENTER);
		depositMoneyHeader.setBackground(Color.LIGHT_GRAY);
		super.add(depositMoneyHeader);
		
		mDisplayTF = new JTextField("Message Display");
		mDisplayTF.setForeground(Color.WHITE);
		mDisplayTF.setBounds(150, 90, 500, 40);
		mDisplayTF.setEditable(false);
		mDisplayTF.setFont(italic);
		mDisplayTF.setBackground(Color.LIGHT_GRAY);
		mDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(mDisplayTF);
		
		depositValLB = new JLabel("Deposit Value :");
		depositValLB.setBounds(200, 250, 250, 40);
		depositValLB.setFont(italic);
		super.add(depositValLB);
		
		depositValTF = new JTextField();
		depositValTF.setBounds(405, 255, 150, 35);
		depositValTF.setFont(shortItalic);
		super.add(depositValTF);
		
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
		if(e.getSource() == submitBT)
		{
			double inputVal = 0;
			
			if(e.getSource() == submitBT)
			{
				char[] inputValidate = depositValTF.getText().toCharArray();
				
				for (int i = 0; i< inputValidate.length; i++)
				{
					if(inputValidate[i] >= 48 &&  inputValidate[i] <= 57 )
					{
						inputVal = Double.parseDouble(depositValTF.getText());
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Invalid Input. Character or Decimal Places Not Accepted");
						break;
					}
				}
				if(inputVal < 100)
				{
					mDisplayTF.setFont(shortItalic);
					mDisplayTF.setForeground(Color.RED);
					mDisplayTF.setText("Min. Requirement to Deposit is INR 100/-");	
				}
				else
				{
				
				try
				{	
					int amountInDB = 0;
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
					
					Statement s = c.createStatement();
					ResultSet r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
					
					while(r.next())
					{
						amountInDB = r.getInt("Acc_Balance");
					}
					
					double setDeposit = amountInDB + inputVal;
					
					PreparedStatement ps = c.prepareStatement("update happy_banking_accounting set acc_balance = ?, last_transaction = ? where email_id = '"+BankingHomePage.loginID+"' " );
					ps.setDouble(1, setDeposit);
					ps.setDouble(2, inputVal);
					
					ps.executeUpdate();
					
					r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
					while(r.next())
					{
						amountInDB = r.getInt("Acc_Balance");
					}
					
					mDisplayTF.setForeground(Color.BLUE);
					mDisplayTF.setFont(shortItalic);
					mDisplayTF.setText("Amount Available : INR " + amountInDB);
					JOptionPane.showMessageDialog(this, "Modification Done. A/c Updated.");
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				finally {}
				}
				
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

