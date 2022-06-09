package accounts;

import operations.*;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements Pay {

    public CheckingAccount(double balance) {
        super(balance);
    }

    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Недопустимое значение платежа");
            return;
        }
        if ((getBalance().subtract(BigDecimal.valueOf(amount))).signum() >= 0) {
            balance = getBalance().subtract(BigDecimal.valueOf(amount));
            System.out.printf("Платеж на сумму %s выполнен \nОстаток на счете %s\n",
                    amount,
                    getBalance()
            );
        } else {
            System.out.println("Лимит исчерпан, пополните баланс.");
        }
    }

    @Override
    public void transfer(Account account, double amount) {
        if ((getBalance().subtract(BigDecimal.valueOf(amount))).signum() < 0) {
            System.out.println("Лимит исчерпан, пополните баланс.");
            return;
        }
        if (account.addMoney(amount)) {
            this.pay(amount);
        }
    }
}
