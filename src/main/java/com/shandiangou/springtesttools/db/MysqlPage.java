package com.shandiangou.springtesttools.db;

import com.alibaba.fastjson.JSONObject;
import com.tools.api.common.dao.config.JDBCConfigDao;
import com.tools.api.common.utils.CommonUtils;
import com.tools.api.common.utils.MysqlConnectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MysqlPage
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 18:00
 * @Description:
 */
public class MysqlPage {

    public static MysqlConnectionUtils o2o(String dbName) {
        return new MysqlConnectionUtils(JDBCConfigDao.REMOTE_HOST_O2O, dbName);
    }

    public static MysqlConnectionUtils poi(String dbName) {
        return new MysqlConnectionUtils(JDBCConfigDao.REMOTE_HOST_POI, dbName);
    }

    public static MysqlConnectionUtils history(String dbName) {
        return new MysqlConnectionUtils(JDBCConfigDao.REMOTE_HOST_HISTORY, dbName);
    }

    public static MysqlConnectionUtils centralbank(String dbName) {
        return new MysqlConnectionUtils(JDBCConfigDao.REMOTE_HOST_CENTRALBANK, dbName);
    }

    public static MysqlConnectionUtils midware(String dbName) {
        return new MysqlConnectionUtils(JDBCConfigDao.REMOTE_HOST_MIDWARE, dbName);
    }




    /**
     * 拼接SQL
     *
     * @param dbFrom ： 表名
     * @param fields ： 展示字段
     */
    public static String spliceSql(String dbFrom, Map<String, Object> filterMap, Object... fields) {
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        if (fields.length > 0) {
            String dispalyField = Arrays.toString(fields)
                    .replace("[", "")
                    .replace("]", "");

            sql.append(dispalyField);
        } else {
            sql.append("*");
        }

        sql.append(" from ");
        sql.append(dbFrom);


        if (filterMap.size() > 0) {

            int num = 1;
            sql.append(" where ");
            for (Object filterKey : filterMap.keySet()) {
                Object value = filterMap.get(filterKey);

                sql.append(filterKey).append(" = ");

                if (value instanceof String) {
                    sql.append("'").append(value).append("'");
                } else {
                    sql.append(value);
                }
                if (num < filterMap.size()) {
                    sql.append(" and ");
                }
                num++;
            }

        }


        return sql.toString();
    }


    /**
     * 巡检 SQL 查询结果
     */
    public static boolean cycleSql(MysqlConnectionUtils con, String sql, int cycle) {
        try {

            for (int i = 0; i < cycle; i++) {

                List<JSONObject> queryRes = con.query(sql);
                if (queryRes.size() > 0) {
                    return true;
                }
                CommonUtils.sleep(1000);
            }
        } finally {
            con.close();
        }
        return false;
    }


    public static JSONObject firstQuery(MysqlConnectionUtils con,String dbForm,Map filterMap,Object... fields){
        String sql = spliceSql(dbForm, filterMap,fields);
        JSONObject queryRes = con.firstQuery(sql);
        con.close();
        return queryRes;
    }


    public static List<JSONObject> query(MysqlConnectionUtils con,String dbForm,Map filterMap,Object... fields){
        String sql = spliceSql(dbForm, filterMap,fields);
        List<JSONObject> queryRes = con.query(sql);
        con.close();
        return queryRes;
    }


}
