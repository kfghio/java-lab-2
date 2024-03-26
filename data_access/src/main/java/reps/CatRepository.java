package reps;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import models.Cat;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CatRepository implements DataAccessObject<Cat, UUID>{
    private final SessionFactory sessionFactory;
    public CatRepository() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void delete(Cat cat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(cat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insert(Cat cat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<Cat> getById(UUID uuid) {
        Session session = sessionFactory.openSession();
        Optional<Cat> result = Optional.ofNullable(session.get(Cat.class, uuid));
        session.close();
        return result;
    }

    @Override
    public Cat update(Cat cat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(cat);
        Cat result = session.get(Cat.class, cat.getId());
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Collection<Cat> getAll() {
        Session session = sessionFactory.openSession();
        Collection<Cat> result = session.createQuery("from Cat", Cat.class).list();
        session.close();
        return result;
    }
    public void addCatFriend(Cat cat, Cat friendCat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        cat.getFriends().add(friendCat);
        friendCat.getFriends().add(cat);
        session.merge(cat);
        session.merge(friendCat);
        session.getTransaction().commit();
        session.close();
    }
    public List<Cat> getFriends(Cat cat) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Cat> criteriaQuery = criteriaBuilder.createQuery(Cat.class);
        Root<Cat> rootCat = criteriaQuery.from(Cat.class);
        Join<Cat, Cat> friends = rootCat.join("friends");
        criteriaQuery.select(friends).where(criteriaBuilder.equal(rootCat, cat));
        List<Cat> friendsList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return friendsList;
    }
    public Owner getOwnerByCat(Cat cat) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Cat> rootCat = criteriaQuery.from(Cat.class);
        criteriaQuery.select(rootCat.get("owner")).where(criteriaBuilder.equal(rootCat, cat));
        Owner owner = session.createQuery(criteriaQuery).getSingleResult();
        session.close();
        return owner;
    }


}
