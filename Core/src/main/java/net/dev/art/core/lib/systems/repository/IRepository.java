package net.dev.art.core.lib.systems.repository;

public interface IRepository<T> {

    RepositoryItem serialize(T t);

    T deserialize(RepositoryItem item);

    void save(T t);
    void delete(T t);

}
