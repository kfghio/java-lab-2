package reps;

import java.util.Collection;
import java.util.Optional;

public interface DataAccessObject<TClass, TId> {
    void delete(TClass tClass);
    void insert(TClass tClass);
    Optional<TClass> getById(TId id);
    TClass update(TClass tClass);
    Collection<TClass> getAll();


}
