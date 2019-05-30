import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class ${entity + "ServiceImpl"} implements ${entity + "Service"} {
    private static final Logger logger = LoggerFactory.getLogger(${entity + "ServiceImpl"}.class);

    @Autowired
    private ${entity + "Mapper"} ${entityName + "Mapper"};

    /**
    * 统计记录行数
    * @param sql
    * @return
    * @throws BizException
    */
    @Override
    public int count(String sql) throws BizException {
        try{
            return ${entityName + "Mapper"}.count(sql);
        } catch (Exception e) {
            throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
        }
    }

    /**
     * 分页查询
     * @param query
     * @return
     * @throws BizException
     */
    @Override
    public Page<${entity + "Model"}> selectByPage(${entity + "Query"} query) throws BizException {
        try {
            if (query == null || !query.check())
                throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
            logger.info("selectByPage查询参数：" + JSON.toJSONString(query));
            String querySql = SQLGenerateUtils.whereSQLGenerate(query);
            logger.info("查询记录" + querySql);
            int rows = ${entityName + "Mapper"}.count(querySql);

            Page<${entity + "Model"}> page = new Page(rows, query.getPageNumber(), query.getPageSize(), null);
            int start = (query.getPageNumber() - 1) * query.getPageSize();
            List<${entity}> list = ${entityName + "Mapper"}.selectByPage(querySql, start, page.getPageSize());
            List<${entity + "Model"}> modelList = ObjectUtils.buildBatch(${entity + "Model"}.class, list);

            //设置枚举值
            page.setItems(modelList);
            return page;
        } catch (Exception e) {
            logger.error("分页查询异常", e);
            throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
        }
    }

    /**
     * list查询
     * @param query
     * @return
     * @throws BizException
    */
    @Override
    public List<${entity + "Model"}> selectByList(${entity + "Query"} query) throws BizException {
        try {
            if (query == null)
                throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
            logger.info("selectByList查询参数：" + JSON.toJSONString(query));
            String querySql = SQLGenerateUtils.whereSQLGenerate(query);
            logger.info("查询记录" + querySql);

            List<${entity}> list = ${entityName + "Mapper"}.selectByList(querySql);
            List<${entity + "Model"}> modelList = ObjectUtils.buildBatch(${entity + "Model"}.class, list);
            return modelList;
        } catch (Exception e) {
            logger.error("list查询异常", e);
            throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
        }
    }

    /**
     * 对象查询
     * @param query
     * @return
     * @throws BizException
    */
    @Override
    public ${entity + "Model"} selectBySingle(${entity + "Query"} query) throws BizException {
        try {
            if (query == null)
                throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
            logger.info("selectBySingle查询参数：" + JSON.toJSONString(query));
            String querySql = SQLGenerateUtils.whereSQLGenerate(query);
            logger.info("查询记录" + querySql);

            ${entity} obj = ${entityName + "Mapper"}.selectBySingle(querySql);
            ${entity + "Model"} model = ObjectUtils.build(${entity + "Model"}.class, obj);
            return model;
        } catch (Exception e) {
            logger.error("对象查询异常", e);
            throw new BizException(ErrorCode.DATABASE_QUERY_ERROR);
        }
    }

    /**
     * 新增对象
     * @param model
     * @return
     * @throws BizException
    */
    @Override
    public int save${entity}(${entity+"Model"} model) throws BizException{
        try {
            ${entity} ${entityName} = ObjectUtils.build(${entity }.class, model);
            ${entityName}.setGmtCreate(DateUtil.getNow());
            ${entityName}.setGmtUpdate(DateUtil.getNow());
            return ${entityName + "Mapper"}.insertSelective(${entityName});
        }catch (Exception e){
            throw new BizException(ErrorCode.SYSTEM_FAILED);
        }
    }

    /**
     * 修改对象
     * @param model
     * @return
     * @throws BizException
    */
    @Override
    public int update${entity}(${entity+"Model"} model) throws BizException{
        try {
            ${entity} ${entityName} = ObjectUtils.build(${entity }.class, model);
            ${entityName}.setGmtUpdate(DateUtil.getNow());
            return ${entityName + "Mapper"}.updateByPrimaryKeySelective(${entityName});
        }catch (Exception e){
            throw new BizException(ErrorCode.SYSTEM_FAILED);
        }
    }

}
