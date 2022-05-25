package server.test.services.accountservice;

import server.test.services.accountservice.model.UserProfile;

public interface AccountService {

    void addNewUser(UserProfile userProfile);

    UserProfile getUserByLogin(String login);

    int getUsersLimit();

    void setUsersLimit(int limit);
}
