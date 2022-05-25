package server.test.services.accountservice;

public class AccountServiceController implements AccountServiceControllerMBean {
    private AccountService accountService;

    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public int getUsersLimit() {
        return accountService.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int limit) {
        accountService.setUsersLimit(limit);

    }
}
