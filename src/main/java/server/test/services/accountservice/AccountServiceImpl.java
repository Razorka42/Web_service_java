package server.test.services.accountservice;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.test.services.accountservice.model.UserProfile;

public class AccountServiceImpl implements AccountService {
    private int usersLimit = 10;
    private Session session;

    public AccountServiceImpl(Session session) {
        this.session = session;
    }

    public AccountServiceImpl() {
    }

    @Override
    public void addNewUser(UserProfile userProfile) throws HibernateException {
        session.save(userProfile);
    }

    @Override
    public UserProfile getUserByLogin(String login) throws HibernateException {
        Criteria loginCriteria = session.createCriteria(UserProfile.class);
        return (UserProfile) loginCriteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int limit) {
        usersLimit = limit;
    }
}
