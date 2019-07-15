
public class ${entity + "Query"} extends BaseQuery {
    @SQLGenerateField(column = {"memberNo", "nickName", "phoneNo"})
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}