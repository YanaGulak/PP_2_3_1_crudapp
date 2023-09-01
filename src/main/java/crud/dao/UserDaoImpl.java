package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public User getById(Long id) {
        return entityManager.find(User.class,id);
    }


    @Override
    public List <User> getAllUsers() {
     return entityManager.createQuery("from User",User.class).getResultList();

    }


    @Override
    public void update (User updateUser) {
        entityManager.merge(updateUser);
    }


    @Override
    public void removeUserById(long id) {
       User user = entityManager.find(User.class,id);
        entityManager.remove(user);

    }
}
