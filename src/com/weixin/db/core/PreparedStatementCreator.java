package com.weixin.db.core;


import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

public interface PreparedStatementCreator {

      PreparedStatement createPreparedStatement(Connection connection) throws SQLException;

}
