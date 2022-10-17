package com.anthem.blcs.partition.batch.config;

import javax.validation.constraints.NotNull;

/**
 * The Class DatasourceConfiguration.
 */
public abstract class DatasourceConfiguration {

	/** The username. */
	@NotNull
	private String username;

	/** The password. */
	@NotNull
	private String password;

	/** The url. */
	@NotNull
	private String url;

	/** The driver class name. */
	@NotNull
	private String driverClassName;

	/** The pool size. */
	private int poolSize;

	/** The connection timeout. */
	private int connectionTimeout;

	/** The test query. */
	private String testQuery;

	private int minimumIdle;

	private long idleTimeout;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * Gets the driver class name.
	 *
	 * @return the driver class name
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * Sets the driver class name.
	 *
	 * @param driverClassName the new driver class name
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * Gets the pool size.
	 *
	 * @return the pool size
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * Sets the pool size.
	 *
	 * @param poolSize the new pool size
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * Gets the connection timeout.
	 *
	 * @return the connection timeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * Sets the connection timeout.
	 *
	 * @param connectionTimeout the new connection timeout
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * Gets the test query.
	 *
	 * @return the test query
	 */
	public String getTestQuery() {
		return testQuery;
	}

	/**
	 * Sets the test query.
	 *
	 * @param testQuery the new test query
	 */
	public void setTestQuery(String testQuery) {
		this.testQuery = testQuery;
	}

	public int getMinimumIdle() {
		return minimumIdle;
	}

	public void setMinimumIdle(int minimumIdle) {
		this.minimumIdle = minimumIdle;
	}

	public long getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(long idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

}
