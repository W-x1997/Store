import com.weixin.db.core.JdbcTemplate;
import com.weixin.db.core.PreparedStatementCreator;
import com.weixin.db.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static JdbcTemplate jdbcTemplate=new JdbcTemplate();


    public static void main(String[] args) throws ClassNotFoundException{



        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                return null;

            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

            }
        });

    }
}
