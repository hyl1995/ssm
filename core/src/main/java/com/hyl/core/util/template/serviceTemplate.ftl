import java.util.List;

public interface ${entity + "Service"} {
    int add(${entity} ${entityName});

    int update(${entity} ${entityName});

    void delete(String id);

    List<${entity}> selectByList(${entity + "Query"} query);

    ${entity} selectBySingle(${entity + "Query"} query);
}
