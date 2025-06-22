package Repositoty;

import Entities.AcademyGroup;

import java.util.List;

public interface IRepository<T> {
    List<T> GetAll();
    T GetById(int id);
    void Update(T updatedGroup);
    void Remove(int id);
    void Add(T newGroup);
}
