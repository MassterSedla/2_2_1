package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserFromCar(Car car) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("select user from User user," +
              "Car car where car.model = :model and car.series = :series and car.id = user.car.id");
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      return query.getSingleResult();

   }

}
