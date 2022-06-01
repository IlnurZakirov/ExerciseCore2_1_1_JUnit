package operations;

import accounts.Account;

public interface Transfer {

    void transfer(Account account, double amount);
}
