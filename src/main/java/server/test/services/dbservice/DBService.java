package server.test.services.dbservice;

import server.test.services.accountservice.model.UserProfile;

public interface DBService {
    public void addUser(UserProfile user) throws DBException;

    public UserProfile getUserByLogin(String login) throws DBException;
}
