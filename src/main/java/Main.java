import accounts.CheckingAccount;
import accounts.CreditAccount;
import accounts.SavingsAccount;

public class Main {

    public static void main(String[] args) {
        CreditAccount creditAccount = new CreditAccount(100_000);
        SavingsAccount savingsAccount = new SavingsAccount(200_000);
        CheckingAccount checkingAccount = new CheckingAccount(300_000);

        creditAccount.transfer(savingsAccount, 50_000);

        checkingAccount.addMoney(10_000);

        creditAccount.addMoney(1_000_000);
        savingsAccount.addMoney(1_000_000);

        checkingAccount.pay(30_000);
        checkingAccount.pay(280_000);
        checkingAccount.pay(30_000);
        checkingAccount.pay(30_000);
        creditAccount.pay(100_000);
        savingsAccount.transfer(creditAccount, 43_438.36);
        savingsAccount.transfer(creditAccount, 43_438.36);
        savingsAccount.transfer(creditAccount, 6_561.64);
        System.out.println(creditAccount.getBalance());
        System.out.println(savingsAccount.getBalance());
        System.out.println(checkingAccount.getBalance());
    }
}
