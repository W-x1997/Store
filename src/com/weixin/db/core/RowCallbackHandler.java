package com.weixin.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 *  处理结果集对象
 **/

public interface RowCallbackHandler {
    void processRow(ResultSet resultSet) throws SQLException;
}
