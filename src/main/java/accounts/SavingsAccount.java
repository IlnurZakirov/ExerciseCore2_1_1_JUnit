package accounts;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void transfer(Account account, double amount) {
        if ((getBalance().subtract(BigDecimal.valueOf(amount))).signum() >= 0) {
            if (account.addMoney(amount)) {
                setBalance(getBalance().subtract(BigDecimal.valueOf(amount)));
            }
        } else {
            System.out.println("Лимит исчерпан, пополните баланс.");
        }
    }
}
