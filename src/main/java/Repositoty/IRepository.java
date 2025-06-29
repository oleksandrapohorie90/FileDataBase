package Repositoty;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRepository<T> {
    Map<Integer, T> GetAll();

    T GetById(int id);

    void Update(T group);

    void Remove(int id);

    void Add(T newGroup);

    //void LoadFromFile();
}
