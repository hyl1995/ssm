public interface ${entity + "Service"} {

    /**
     * 统计记录行数
     * @param sql
     * @return
     * @throws BizException
     */
    int count(String sql) throws BizException;

    /**
     * 分页查询
     * @param query
     * @return
     * @throws BizException
     */
    Page<${entity + "Model"}> selectByPage(${entity + "Query"} query) throws BizException;

    /**
     * list查询
     * @param query
     * @return
     * @throws BizException
    */
    List<${entity + "Model"}> selectByList(${entity + "Query"} query) throws BizException;

    /**
     * 对象查询
     * @param query
     * @return
     * @throws BizException
    */
    ${entity + "Model"} selectBySingle(${entity + "Query"} query) throws BizException;

    /**
     * 新增对象
     * @param model
     * @return
     * @throws BizException
    */
    int save${entity}(${entity+"Model"} model) throws BizException;

    /**
     * 修改对象
     * @param model
     * @return
     * @throws BizException
    */
    int update${entity}(${entity+"Model"} model) throws BizException;

}
