package dk.marc.currencyconverter.data;

import java.util.List;
import java.util.function.Function;

public interface IRepository<Entity extends Class>  {
    void add();
    void remove();
    Entity get(int id);
    List<Entity> find(Function<Entity, Boolean> predicate);
}
