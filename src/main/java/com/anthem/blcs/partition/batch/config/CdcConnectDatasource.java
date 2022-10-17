package com.anthem.blcs.partition.batch.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import oracle.jdbc.pool.OracleDataSource;

/**
 * The Class CdcConnectDatasource.
 */
@Lazy
@Configuration
@ConfigurationProperties("spring.oracle.datasource")
public class CdcConnectDatasource extends DatasourceConfiguration { 

    /**
     * CdcConnect data source.
     *
     * @return the data source
     */
    @Bean(name = "cdcDS")
    public DataSource entCompDatasource() throws SQLException{
        OracleDataSource  blcsDataSource = new OracleDataSource();
        blcsDataSource.setUser(getUsername());;
        blcsDataSource.setPassword(getPassword());
        blcsDataSource.setURL(getUrl());
        blcsDataSource.setPassword(getPassword());
        blcsDataSource.setImplicitCachingEnabled(true);
        blcsDataSource.setFastConnectionFailoverEnabled(true);
        
       
        return blcsDataSource;
    }
}
