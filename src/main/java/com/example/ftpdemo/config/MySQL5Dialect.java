package com.example.ftpdemo.config;

import org.hibernate.dialect.MySQL55Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * hibernate自动建表的编码格式
 *
 * @author hongen.zhang
 * time: 2019/11/20 16:57
 * email: hongen.zhang@things-matrix.com
 */
public class MySQL5Dialect extends MySQL55Dialect {

    public MySQL5Dialect() {
        super();
        this.registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
    }

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci";
    }
}
