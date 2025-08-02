package bankingProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class EasyBankingOptions extends JFrame implements ActionListener
{
	JButton openAcc;
	JButton checkBal;
	JButton depositM;
	JButton withdrawM;
	JButton fundTransfer;
	JButton accountDeactive;
	JButton accountReactive;
	JButton logoutToHomeP;
	JTextField easyBankingHeader;
	JTextField ebDisplayTF;
	Font italic;
	Font shortItalic;
	int accStatus;
	
	void bankingOptions()
	{
		super.setTitle("Easy Banking Options");
		super.setBounds(250, 30, 800, 600);
		
		italic = new Font("serif",Font.ITALIC,30);
		shortItalic = new Font("serif",Font.ITALIC,20);
		
		easyBankingHeader = new JTextField("Welcome "+BankingHomePage.loginName+" , Choose The Banking Options!!");
		easyBankingHeader.setBounds(100, 10, 600, 50);
		easyBankingHeader.setEditable(false);
		easyBankingHeader.setFont(italic);
		easyBankingHeader.setHorizontalAlignment(SwingConstants.CENTER);
		easyBankingHeader.setBackground(Color.LIGHT_GRAY);
		super.add(easyBankingHeader);
		
		ebDisplayTF = new JTextField("Message Display");
		ebDisplayTF.setForeground(Color.WHITE);
		ebDisplayTF.setBounds(150, 85, 500, 40);
		ebDisplayTF.setEditable(false);
		ebDisplayTF.setFont(italic);
		ebDisplayTF.setBackground(Color.LIGHT_GRAY);
		ebDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(ebDisplayTF);
		
		
		openAcc = new JButton("Open Account");
		openAcc.setBounds(300, 150, 200, 40);
		super.add(openAcc);
		openAcc.addActionListener(this);
		
		checkBal = new JButton("Check A/c Balance");
		checkBal.setBounds(300, 190, 200, 40);
		super.add(checkBal);
		checkBal.addActionListener(this);

		depositM = new JButton("Deposit Money");
		depositM.setBounds(300, 230, 200, 40);
		super.add(depositM);
		depositM.addActionListener(this);
		
		withdrawM = new JButton("Withrawal Money");
		withdrawM.setBounds(300, 270, 200, 40);
		super.add(withdrawM);
		withdrawM.addActionListener(this);
		
		fundTransfer = new JButton("Fund Transfer");
		fundTransfer.setBounds(300, 310, 200, 40);
		super.add(fundTransfer);
		fundTransfer.addActionListener(this);

		accountDeactive = new JButton("Account Deactivate");
		accountDeactive.setBounds(300, 350, 200, 40);
		super.add(accountDeactive);
		accountDeactive.addActionListener(this);

		accountReactive = new JButton("Account Reactivate");
		accountReactive.setBounds(300, 390, 200, 40);
		super.add(accountReactive);
		accountReactive.addActionListener(this);

		logoutToHomeP = new JButton("Logout");
		logoutToHomeP.setBackground(Color.WHITE);
		logoutToHomeP.setFont(shortItalic);
		logoutToHomeP.setForeground(Color.BLUE);
		logoutToHomeP.setBounds(650, 500, 100, 40);
		super.add(logoutToHomeP);
		logoutToHomeP.addActionListener(this);
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == openAcc)
		{
			try
			{
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
					OpenBankingAccount oBA = new OpenBankingAccount();
					oBA.openAccount();
					
					super.dispose();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
		}
		else if(e.getSource() == checkBal)
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
					accStatus = r.getInt("acc_active_status");
				}
				if(accStatus == 0)
				{
					ebDisplayTF.setFont(shortItalic);
					ebDisplayTF.setForeground(Color.RED);
					ebDisplayTF.setText("Account Deactivated. Reactivate to Operate an Account.");
					JOptionPane.showMessageDialog(this, "Use Reactivate Button to Change the Status of Your Account.");
				}
				else 
				{
				ebDisplayTF.setForeground(Color.BLUE);
				ebDisplayTF.setFont(shortItalic);
				ebDisplayTF.setText("Amount Available : INR " + amountInDB);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
			
		}
		else if(e.getSource() == depositM)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				Statement s = c.createStatement();
				ResultSet r = s.executeQuery("select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
				
				while(r.next())
				{
					accStatus = r.getInt("acc_active_status");
				}
				if(accStatus == 0)
				{
					ebDisplayTF.setFont(shortItalic);
					ebDisplayTF.setForeground(Color.RED);
					ebDisplayTF.setText("Account Deactivated. Reactivate to Operate an Account.");
					JOptionPane.showMessageDialog(this, "Use Reactivate Button to Change the Status of Your Account.");
				}
				else
				{
					DepositMoney dm = new DepositMoney();
					dm.depositM();
					
					super.dispose();
				}
		
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
		}
		else if(e.getSource() == withdrawM)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				Statement s = c.createStatement();
				ResultSet r = s.executeQuery("select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
				
				while(r.next())
				{
					accStatus = r.getInt("acc_active_status");
				}
				if(accStatus == 0)
				{
					ebDisplayTF.setFont(shortItalic);
					ebDisplayTF.setForeground(Color.RED);
					ebDisplayTF.setText("Account Deactivated. Reactivate to Operate an Account.");
					JOptionPane.showMessageDialog(this, "Use Reactivate Button to Change the Status of Your Account.");
				}
				else
				{
					WithdrawMoney wm = new WithdrawMoney();
					wm.withdrawM();
					
					super.dispose();
				}
			}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
		}
		else if(e.getSource() == fundTransfer)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				Statement s = c.createStatement();
				ResultSet r = s.executeQuery("select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
				
				while(r.next())
				{
					accStatus = r.getInt("acc_active_status");
				}
				if(accStatus == 0)
				{
					ebDisplayTF.setFont(shortItalic);
					ebDisplayTF.setForeground(Color.RED);
					ebDisplayTF.setText("Account Deactivated. Reactivate to Operate an Account.");
					JOptionPane.showMessageDialog(this, "Use Reactivate Button to Change the Status of Your Account.");
				}
				else
				{
					FundTransfer ft =new FundTransfer();
					ft.fundT();
					
					super.dispose();
				}
				
				
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
		}

		else if(e.getSource() == accountDeactive)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				
				Statement s = c.createStatement();
				s.executeUpdate("update happy_banking_accounting set acc_active_status = '0' where email_id = '"+BankingHomePage.loginID+"'");
				ResultSet r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
				
				while(r.next())
				{
					accStatus = r.getInt("Acc_Active_Status");
				}
				ebDisplayTF.setForeground(Color.RED);
				ebDisplayTF.setFont(shortItalic);
				ebDisplayTF.setText("Account Deactivated. System Code: "+accStatus);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
		}
		else if(e.getSource() == accountReactive)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
				
				Statement s = c.createStatement();
				s.executeUpdate("update happy_banking_accounting set acc_active_status = '1' where email_id = '"+BankingHomePage.loginID+"'");
				ResultSet r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
				
				while(r.next())
				{
					accStatus = r.getInt("Acc_Active_Status");
				}
				ebDisplayTF.setForeground(Color.BLUE);
				ebDisplayTF.setFont(shortItalic);
				ebDisplayTF.setText("Account Reactivated. System Code: "+accStatus);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {}
		}
		else if(e.getSource() == logoutToHomeP)
		{
			BankingHomePage bp = new BankingHomePage();
			bp.showHome();
			
			super.dispose();
		}
		
	}
	
}
