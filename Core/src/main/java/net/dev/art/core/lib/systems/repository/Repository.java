package net.dev.art.core.lib.systems.repository;

import net.dev.art.core.lib.systems.repository.providers.Information;
import net.dev.art.core.lib.systems.repository.providers.Provider;

import java.util.HashMap;
import java.util.Map;

public abstract class Repository<T extends Storable> implements IRepository<T> {

    private Provider provider;
    private RepositoryType type;
    private Map<Integer, T> map = new HashMap<>();

    public Repository(RepositoryType type, Information information) {
        this.type = type;
        try {
            this.provider = type.getProviderClass().newInstance();
            this.provider.provideInformation(information);
            this.provider.init();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(T t) {
        RepositoryItem item = serialize(t);
        //item.getMap().forEach(item::set);
        provider.set(item.getId()+"", item);
    }

    @Override
    public void delete(T t) {
        RepositoryItem item = serialize(t);
        provider.set(item.getId()+"", null);
    }

    public void add(T t) {

    }

    public int getNextId() {
        int i = 0;
        for (T t : map.values()) {
            if (i > 0 && t.getId() == i)
                return i;
            if (i < t.getId())
                i = t.getId();
        }
        return i + 1;
    }

}
