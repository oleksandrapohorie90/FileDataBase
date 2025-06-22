package Repositoty;

import java.util.List;

public interface IRepository<T> {
    List<T> GetAll();
    T GetById(int id);
    void Update(T group);
    void Remove(int id);
    void Add(T newGroup);
    void SaveChanges(List<T> entities);
    //void LoadFromFile();
}
