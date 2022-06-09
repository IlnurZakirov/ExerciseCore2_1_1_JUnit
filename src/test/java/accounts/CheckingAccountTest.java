package accounts;

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
    CheckingAccount checkingAccount = new CheckingAccount(balance);
    SavingsAccount savingsAccount = new SavingsAccount(balance);

    @Test
    void classTest() {
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
        checkingAccount.addMoney(negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));

        checkingAccount.addMoney(payment);
        assertThat(BigDecimal.valueOf(balance + payment), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void pay() {
        checkingAccount.pay(bigPayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));

        checkingAccount.pay(negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));

        checkingAccount.pay(payment);
        assertThat(BigDecimal.valueOf(balance - payment), equalTo(checkingAccount.getBalance()));
    }

    @Test
    void transfer() {
        checkingAccount.transfer(savingsAccount, bigPayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));
        assertThat(BigDecimal.valueOf(balance), equalTo(savingsAccount.getBalance()));

        checkingAccount.transfer(savingsAccount, negativePayment);
        assertThat(BigDecimal.valueOf(balance), equalTo(checkingAccount.getBalance()));

        checkingAccount.transfer(savingsAccount, payment);
        assertThat(BigDecimal.valueOf(balance - payment), equalTo(checkingAccount.getBalance()));
        assertThat(BigDecimal.valueOf(balance + balance), equalTo(savingsAccount.getBalance()));
    }
}