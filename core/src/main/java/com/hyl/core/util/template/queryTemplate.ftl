import com.hyl.core.model.BaseQuery;

public class ${entity + "Query"} extends BaseQuery {
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}