import java.util.List;

public interface ${entity + "Service"} {
    boolean add(${entity} ${entityName});

    boolean update(${entity} ${entityName});

    boolean delete(Long id);

    List<${entity}> selectByList(${entity + "Query"} query);

    ${entity} selectBySingle(${entity + "Query"} query);
}
