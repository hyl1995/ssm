import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entity + "ServiceImpl"} implements ${entity + "Service"} {
    @Autowired
    private ${entity + "Mapper"} ${entityName + "Mapper"};

    public int add(${entity} ${entityName}) {
        return ${entityName + "Mapper"}.add(${entityName});
    }

    public int update(${entity} ${entityName}) {
        return ${entityName + "Mapper"}.update(${entityName});
    }

    @Override
    public void delete(String id) {
        ${entityName + "Mapper"}.delete(id);
    }

    @Override
    public List<${entity}> selectByList(${entity + "Query"} query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return ${entityName + "Mapper"}.selectByList(query.getFilter());
    }

    @Override
    public ${entity} selectBySingle(${entity + "Query"} query) {
        return ${entityName + "Mapper"}.selectBySingle(query.getFilter());
    }
}
