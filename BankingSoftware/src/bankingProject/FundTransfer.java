package bankingProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class FundTransfer extends JFrame implements ActionListener
{
	Font bold;
	Font italic;
	Font shortItalic;
	JTextField fundTransferHeader;
	JTextField mDisplayTF;
	JLabel eMailFundTransferToLB;
	JTextField eMailFundTransferToTF;
	JLabel amountValLB;
	JTextField amountValTF;
	JButton backBT;
	JButton submitBT;
	
	void fundT()
	{
		super.setTitle("Open Account & Options");
		super.setBounds(250, 30, 800, 600);
		
		bold = new Font("serif",Font.BOLD,28);
		italic = new Font("serif",Font.ITALIC,30);
		shortItalic = new Font("serif",Font.ITALIC,20);
		
		fundTransferHeader = new JTextField("Welcome "+BankingHomePage.loginName+" , Access Your Fund!!");
		fundTransferHeader.setBounds(100, 10, 600, 50);
		fundTransferHeader.setEditable(false);
		fundTransferHeader.setFont(italic);
		fundTransferHeader.setHorizontalAlignment(SwingConstants.CENTER);
		fundTransferHeader.setBackground(Color.LIGHT_GRAY);
		super.add(fundTransferHeader);
		
		mDisplayTF = new JTextField("Message Display");
		mDisplayTF.setForeground(Color.WHITE);
		mDisplayTF.setBounds(150, 90, 500, 40);
		mDisplayTF.setEditable(false);
		mDisplayTF.setFont(italic);
		mDisplayTF.setBackground(Color.LIGHT_GRAY);
		mDisplayTF.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(mDisplayTF);
		
		eMailFundTransferToLB = new JLabel("Transfer To [eMail_ID] :");
		eMailFundTransferToLB.setBounds(80, 200, 350, 40);
		eMailFundTransferToLB.setFont(italic);
		eMailFundTransferToLB.setForeground(Color.BLUE);
		super.add(eMailFundTransferToLB);
	
		eMailFundTransferToTF = new JTextField();
		eMailFundTransferToTF.setBounds(405, 200, 250, 40);
		eMailFundTransferToTF.setFont(shortItalic);
		super.add(eMailFundTransferToTF);
		
		amountValLB = new JLabel("Amount Value :");
		amountValLB.setBounds(200, 250, 250, 40);
		amountValLB.setFont(italic);
		super.add(amountValLB);
		
		amountValTF = new JTextField();
		amountValTF.setBounds(405, 255, 250, 35);
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
		if(e.getSource() == submitBT)
		{
			double inputVal = 0;
			
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
				if(inputVal < 100)
				{
					mDisplayTF.setFont(shortItalic);
					mDisplayTF.setForeground(Color.RED);
					mDisplayTF.setText("Min. Requirement to Transfer is INR 100/-");	
				}
				else
				{
				
				try
				{	
					double amountInDonor = 0;
					double amountInDBReceiver = 0;
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register?user=root&password=root");
					
					Statement s = c.createStatement();
					ResultSet r = s.executeQuery("Select * from happy_banking_accounting where email_id = '"+BankingHomePage.loginID+"'");
					
					while(r.next())
					{
						amountInDonor = r.getInt("Acc_Balance");
					
					if(amountInDonor >= inputVal)
					{
						
						amountInDonor = amountInDonor - inputVal;
						
						
						Statement sReceiver = c.createStatement();
						ResultSet receiver = sReceiver.executeQuery("Select * from happy_banking_accounting");// where email_id = '"+eMailFundTransferToTF.getText()+"'");
						while(receiver.next())
						{
							if(eMailFundTransferToTF.getText().equals(receiver.getString("eMail_ID")))
							{
								amountInDBReceiver = receiver.getInt("Acc_Balance");
								amountInDBReceiver = amountInDBReceiver + inputVal;
								
								PreparedStatement psReceiver = c.prepareStatement("update happy_banking_accounting set acc_balance = ?, last_transaction = ? where email_id = '"+receiver.getString("eMail_ID")+"'");
								psReceiver.setDouble(1, amountInDBReceiver);
								psReceiver.setDouble(2, inputVal);
								
								psReceiver.executeUpdate();
								
								mDisplayTF.setForeground(Color.BLUE);
								mDisplayTF.setFont(shortItalic);
								mDisplayTF.setText("Amount Available : INR " + amountInDBReceiver);
								JOptionPane.showMessageDialog(this, "Modification Done. A/c Updated.");
								
								PreparedStatement ps = c.prepareStatement("update happy_banking_accounting set acc_balance = ?, last_transaction = -(?) where email_id = '"+BankingHomePage.loginID+"'");
								ps.setDouble(1, amountInDonor);
								ps.setDouble(2, inputVal);
								
								ps.executeUpdate();
							
								break;
							}
							else
							{
								mDisplayTF.setForeground(Color.RED);
								mDisplayTF.setFont(shortItalic);
								mDisplayTF.setText("'Transfer To' Account Is Not In Record.");
							}
						}
//						double setDepositReceiver = amountInDBReceiver + inputVal;
						
					
					}
					else
					{
						mDisplayTF.setForeground(Color.RED);
						mDisplayTF.setFont(shortItalic);
						mDisplayTF.setText("Amount Not Sufficient");
						JOptionPane.showMessageDialog(this, "Amount Not Sufficient");
					}
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


