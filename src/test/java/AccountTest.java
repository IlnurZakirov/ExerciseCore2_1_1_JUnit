import accounts.SavingsAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class AccountTest {

    @Test
    @DisplayName("Проверяем пополнение счета методом addMoney")
    public void teatAddMoney() {
        double balance = 1;
        double amount = 1;
        double negativeAmount = -1;
        BigDecimal result = new BigDecimal(2);
        SavingsAccount savingsAccount = new SavingsAccount(balance);
        savingsAccount.addMoney(amount);
        Assertions.assertEquals(0, result.compareTo(savingsAccount.getBalance()));
        Assertions.assertFalse(savingsAccount.addMoney(negativeAmount));
    }
}
