package com.hyl.core.util;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DatabaseUtil {

    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static Map<String, String> javaTypeMap = new HashMap<>();
    private static Map<String, String> jdbcTypeMap = new HashMap<>();

    static {
        try {
            Class.forName(CreateCodeUtils.DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("can not load jdbc driver", e);
        }
        //java实体类用
        javaTypeMap.put("BIGINT", "Long");
        javaTypeMap.put("VARCHAR", "String");
        javaTypeMap.put("INT", "Integer");
        javaTypeMap.put("DECIMAL", "BigDecimal");
        javaTypeMap.put("TINYINT UNSIGNED", "Integer");
        javaTypeMap.put("INT UNSIGNED", "Integer");
        javaTypeMap.put("BIGINT UNSIGNED", "Long");
        javaTypeMap.put("TINYINT", "Integer");
        javaTypeMap.put("BIT", "Boolean");
        javaTypeMap.put("DATETIME", "Date");
        javaTypeMap.put("TIME", "Date");
        javaTypeMap.put("TIMESTAMP", "Date");
        javaTypeMap.put("JSON", "String");

        //xxxMapper.xml用
        jdbcTypeMap.put("TINYINT UNSIGNED", "TINYINT");
        jdbcTypeMap.put("INT UNSIGNED", "INTEGER");
        jdbcTypeMap.put("BIGINT UNSIGNED", "BIGINT");
        jdbcTypeMap.put("DATETIME", "TIMESTAMP");
        jdbcTypeMap.put("TIME", "TIMESTAMP");
        jdbcTypeMap.put("INT", "INTEGER");
        jdbcTypeMap.put("BIT", "BOOLEAN");
        jdbcTypeMap.put("TIMESTAMP", "TIMESTAMP");
        jdbcTypeMap.put("JSON", "VARCHAR");
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CreateCodeUtils.URL, CreateCodeUtils.USERNAME, CreateCodeUtils.PASSWORD);
        } catch (SQLException e) {
            log.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取表数据
     * @param tableName
     * @return
     */
    public static ResultSetMetaData resultSetMetaData(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            return pStemt.getMetaData();
        } catch (SQLException e) {
            log.error("getResultSetMetaData failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return null;
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        try {
            //结果集元数据
            ResultSetMetaData rsmd = resultSetMetaData(tableName);
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        try {
            //结果集元数据
            ResultSetMetaData rsmd = resultSetMetaData(tableName);
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnTypes failure", e);
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {

        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }

    /**
     * 获取表的所有字段数据
     * @param tableName
     * @return
     */
    public static List<Column> columns(String tableName) {
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<Column> columns = new ArrayList<>();
        ResultSetMetaData rsmd = null;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            rsmd =  pStemt.getMetaData();
            ResultSet rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }

            for (int i = 2; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnTypeName(i));
                Column column = new Column();
                column.setColumnName(rsmd.getColumnName(i));
                column.setColumnType(jdbcTypeMap.get(rsmd.getColumnTypeName(i)) != null ? jdbcTypeMap.get(rsmd.getColumnTypeName(i)) : rsmd.getColumnTypeName(i));
                column.setJavaName(StrUtil.toCamelCase(rsmd.getColumnName(i)));
                column.setJavaType(javaTypeMap.get(rsmd.getColumnTypeName(i)));
                column.setComment(columnComments.get(i-1));
                columns.add(column);
            }
        } catch (SQLException e) {
            log.error("getResultSetMetaData failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columns;
    }

    @Data
    public static class Column {
        private String columnName;

        private String javaName;

        private String columnType;

        private String javaType;

        private String comment;
    }

    public static void main(String[] args) {
//        List<String> tableNames = getTableNames();
//        System.out.println("tableNames:" + tableNames);
//        for (String tableName : tableNames) {
//            System.out.println("ColumnNames:" + getColumnNames(tableName));
//            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
//            System.out.println("ColumnComments:" + getColumnComments(tableName));
//        }
        String tableName = "t_gift_giving_record";
        System.out.println("ColumnNames:" + getColumnNames(tableName));
        System.out.println("ColumnTypes:" + getColumnTypes(tableName));
        System.out.println("ColumnComments:" + getColumnComments(tableName));
    }
}
