package com.example.sparkjava.config;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
public class DbConnection {

    private static final Logger LOG = LogManager.getLogger(DbConnection.class);
    Config config = new Config();
    private static interface Singleton {

        final DbConnection INSTANCE = new DbConnection();
    }

    private final BasicDataSource dataSource;

    /**
     * This connection factory implements dbcp2 pooling. It creates instance of
     * BasicDataSource to access the DBCP pool. Closing a connection will simply
     * return it to its pool.
     */
    private DbConnection() {
        String dbName = config.getProperty("db_name");
        String user = config.getProperty("db_user");
        String password = config.getProperty("db_password");
        String url = config.getProperty("db_url");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url+dbName);
        ds.setUsername(user);
        ds.setPassword(password);

        this.dataSource = ds;
    }

    public static synchronized Connection getCon() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
