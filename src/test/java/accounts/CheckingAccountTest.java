package accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Расчетный может выполнять все три операции, но не может уходить в минус.
 */

class CheckingAccountTest {
    double balance = 2;
    double payment = 2;
    double bigPayment = 4;
    double negativePayment = -2;
    CheckingAccount checkingAccount;
    SavingsAccount savingsAccount;

    @BeforeEach
    public void initEach() {
        checkingAccount = new CheckingAccount(balance);
        savingsAccount = new SavingsAccount(balance);
    }

    @Test
    void classCheckingAccountTest() {
        assertThat(checkingAccount, isA(Account.class));
        assertThat(savingsAccount, isA(Account.class));
        assertThat(checkingAccount, notNullValue());
        assertThat(savingsAccount, notNullValue());
        assertThat(checkingAccount.getBalance(), allOf(
                greaterThanOrEqualTo(BigDecimal.valueOf(0)),
                equalTo(BigDecimal.valueOf(balance))
        ));
    }

    @Test
    void addMoney() {
        checkingAccount.addMoney(payment);
        assertThat(BigDecimal.valueOf(balance + payment), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void addMoneyForNegativePayment() {
        checkingAccount.addMoney(negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void pay() {
        checkingAccount.pay(payment);
        assertThat(BigDecimal.valueOf(balance - payment), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void payOnInsufficientFounds() {
        checkingAccount.pay(bigPayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void payForNegativePayment() {
        checkingAccount.pay(negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void transfer() {
        checkingAccount.transfer(savingsAccount, payment);
        assertThat(BigDecimal.valueOf(balance - payment), equalTo(checkingAccount.getBalance()));
        assertThat(BigDecimal.valueOf(balance + balance), equalTo(savingsAccount.getBalance()));
    }

    @Test
    void transferOnInsufficientFounds() {
        checkingAccount.transfer(savingsAccount, bigPayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
        assertThat(BigDecimal.valueOf(balance), equalTo(savingsAccount.getBalance()));
    }

    @Test
    void transferForNegativePayment() {
        checkingAccount.transfer(savingsAccount, negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
    }

}