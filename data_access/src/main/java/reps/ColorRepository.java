package reps;

import models.Color;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class ColorRepository implements DataAccessObject<Color, UUID>{
    private final SessionFactory sessionFactory;
    public ColorRepository() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public void delete(Color color) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(color);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insert(Color color) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(color);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<Color> getById(UUID uuid) {
        Session session = sessionFactory.openSession();
        Optional<Color> result = Optional.ofNullable(session.get(Color.class, uuid));
        session.close();
        return result;
    }

    @Override
    public Color update(Color color) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(color);
        Color result = session.get(Color.class, color.getId());
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Collection<Color> getAll() {
        Session session = sessionFactory.openSession();
        Collection<Color> result = session.createQuery("from Color", Color.class).list();
        session.close();
        return result;
    }
}
