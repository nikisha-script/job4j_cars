package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public interface Crud {

    default void run(Consumer<Session> command, SessionFactory sessionFactory) {
        wrapRequest(session -> {
                    command.accept(session);
                    return null;
                }, sessionFactory
        );
    }

    default void run(String query, Map<String, Object> args, SessionFactory sessionFactory) {
        Consumer<Session> command = session -> {
            var sq = session
                    .createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            sq.executeUpdate();
        };
        run(command, sessionFactory);
    }

    default <T> List<T> findAllWithoutParams(String query, Class<T> cl, SessionFactory sessionFactory) {
        Function<Session, List<T>> command = session -> session
                .createQuery(query, cl)
                .list();
        return wrapRequest(command, sessionFactory);
    }

    default <T> Optional<T> findOne(String query, Class<T> cl, Map<String, Object> args, SessionFactory sessionFactory) {
        Function<Session, Optional<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return Optional.ofNullable(sq.getSingleResult());
        };
        return wrapRequest(command, sessionFactory);
    }

    default <T> List<T> findAllWithParams(String query, Class<T> cl, Map<String, Object> args, SessionFactory sessionFactory) {
        Function<Session, List<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.list();
        };
        return wrapRequest(command, sessionFactory);
    }

    default <T> T wrapRequest(final Function<Session, T> command, SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


}
