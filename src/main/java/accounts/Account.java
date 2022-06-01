package accounts;

import operations.AddMoney;
import operations.Transfer;

import java.math.BigDecimal;

public abstract class Account implements AddMoney, Transfer {
    protected BigDecimal balance;

    public Account(double balance) {
        this.balance = BigDecimal.valueOf(balance);
    }

    @Override
    public boolean addMoney(double amount) {
        if (amount < 0) {
            System.out.println("Нельзя пополнить счет отрицательной суммой");
            return false;
        } else {
            setBalance(getBalance().add(BigDecimal.valueOf(amount)));
            System.out.printf("Баланс пополнен на %s\nДоступно %s\n",
                    amount,
                    getBalance()
            );
            return true;
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    protected void setBalance(BigDecimal amount) {
        balance = amount;
    }
}
