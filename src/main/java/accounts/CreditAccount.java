package accounts;

import operations.*;

import java.math.BigDecimal;

public class CreditAccount extends Account implements Pay {

    private BigDecimal creditLimit;
    private BigDecimal creditBalance = BigDecimal.valueOf(0);

    public CreditAccount(double balance) {
        super(balance);
        setCreditLimit((BigDecimal.valueOf(0)).subtract(new BigDecimal(balance)));
    }

    @Override
    public BigDecimal getBalance() {
        return creditBalance;
    }

    @Override
    protected void setBalance(BigDecimal amount) {
        creditBalance = amount;
    }

    private BigDecimal setCreditLimit(BigDecimal amount) {
        return creditLimit = amount;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    @Override
    public boolean addMoney(double amount) {
        if (amount < 0) {
            System.out.println("Нельзя пополнить счет отрицательной суммой");
            return false;
        } else {
            if (getBalance().add(BigDecimal.valueOf(amount)).signum() <= 0) {
                setBalance(getBalance().add(BigDecimal.valueOf(amount)));
                System.out.printf("Кредитный счет на сумму %s пополнен \nОстаток на счете %s\n",
                        amount,
                        getBalance()
                );
                return true;
            } else {
                System.out.printf("Слишком большая сумма. Для пополнения достаточно %s\n",
                        BigDecimal.valueOf(0).subtract(getBalance())
                );
                return false;
            }
        }
    }

    @Override
    public void pay(double amount) {
        if (getCreditLimit().compareTo(getBalance().subtract(BigDecimal.valueOf(amount))) <= 0) {
            setBalance(getBalance().subtract(BigDecimal.valueOf(amount)));
            System.out.printf("Платеж на сумму %s выполнен \nОстаток на счете %s\n",
                    amount,
                    getBalance()
            );
        } else {
            System.out.println("Кредитный лимит исчерпан, пополните баланс.");
        }
    }

    @Override
    public void transfer(Account account, double amount) {
        if (account.addMoney(amount)) {
            this.pay(amount);
        }
    }
}
