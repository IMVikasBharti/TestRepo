package bankingProject;

public class BankingCallPage
{
	public static void main(String[] args)
	{
		BankingSignUP bsUP = new BankingSignUP();
//		bsUP.showSignUP();
		
		BankingHomePage bp = new BankingHomePage();
		bp.showHome();
		
		EasyBankingOptions eBO = new EasyBankingOptions();
//		eBO.bankingOptions();
		
		OpenBankingAccount oBA = new OpenBankingAccount();
//		oBA.openAccount();
		
		DepositMoney dm = new DepositMoney();
//		dm.depositM();
		
		WithdrawMoney wm = new WithdrawMoney();
//		wm.withdrawM();
		
		FundTransfer ft =new FundTransfer();
//		ft.fundT();
	}
	
}
