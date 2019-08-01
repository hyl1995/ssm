import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entity + "ServiceImpl"} implements ${entity + "Service"} {
    @Autowired
    private ${entity + "Mapper"} ${entityName + "Mapper"};

    public boolean add(${entity} ${entityName}) {
        if (${entityName + "Mapper"}.add(${entityName}) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(${entity} ${entityName}) {
        if (${entityName + "Mapper"}.update(${entityName}) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (${entityName + "Mapper"}.delete(id) == 1) {
            return true;
        } else {
            return false;
        }
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
