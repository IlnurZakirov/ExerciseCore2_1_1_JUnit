package accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Со сберегательного счета нельзя платить, только переводить и пополнять.
 */

class SavingsAccountTest {
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
    void classSavingAccountTest() {
        Assertions.assertNotNull(savingsAccount);
        Assertions.assertTrue(savingsAccount.getBalance().subtract(BigDecimal.valueOf(balance)).signum() >= 0);
        Assertions.assertEquals(0, savingsAccount.getBalance().compareTo(BigDecimal.valueOf(balance)));
    }

    @Test
    void addMoney() {
        savingsAccount.addMoney(payment);
        Assertions.assertEquals(0, BigDecimal.valueOf(balance + payment).compareTo(savingsAccount.getBalance()));
    }

    @Test
    void transfer() {
        savingsAccount.transfer(checkingAccount, payment);
        Assertions.assertEquals(0, BigDecimal.valueOf(balance - payment).compareTo(savingsAccount.getBalance()));
        Assertions.assertEquals(0, BigDecimal.valueOf(balance + payment).compareTo(checkingAccount.getBalance()));
    }

    @Test
    void transferForNegativePayment() {
        savingsAccount.transfer(checkingAccount, negativePayment);
        Assertions.assertEquals(0, BigDecimal.valueOf(balance).compareTo(savingsAccount.getBalance()));
        Assertions.assertEquals(0, BigDecimal.valueOf(balance).compareTo(checkingAccount.getBalance()));
    }

    @Test
    void transferOnInsufficientFounds() {
        savingsAccount.transfer(checkingAccount, bigPayment);
        Assertions.assertEquals(0, BigDecimal.valueOf(balance).compareTo(savingsAccount.getBalance()));
        Assertions.assertEquals(0, BigDecimal.valueOf(balance).compareTo(checkingAccount.getBalance()));
    }
}