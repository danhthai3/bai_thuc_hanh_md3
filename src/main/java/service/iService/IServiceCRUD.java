package service.iService;

import java.util.List;

public interface IServiceCRUD<E> {
    void add(E e);

    void edit(int id, E e);

    void delete(int id);

    List<E> getAll();
}
