package Repositoty;

import java.util.List;

public class AcademyGroup implements IRepository<AcademyGroup>{
    @Override
    public List<AcademyGroup> GetAll() {
        return List.of();
    }

    @Override
    public AcademyGroup GetById(int id) {
        return null;
    }

    @Override
    public void Update(AcademyGroup updatedGroup) {

    }

    @Override
    public void Remove(int id) {

    }

    @Override
    public void Add(AcademyGroup newGroup) {

    }
}
