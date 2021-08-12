package com.shuogesha.es;

import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClientBuilder {
	private int connectTimeoutMillis = 1000;
	private int socketTimeoutMillis = 30000;
	private int connectionRequestTimeoutMillis = 500;
	private int maxConnectPerRoute = 10;
	private int maxConnectTotal = 30;

	private final List<HttpHost> httpHosts;

	private EsClientBuilder(List<HttpHost> httpHosts) {
		this.httpHosts = httpHosts;
	}

	public EsClientBuilder setConnectTimeoutMillis(int connectTimeoutMillis) {
		this.connectTimeoutMillis = connectTimeoutMillis;
		return this;
	}

	public EsClientBuilder setSocketTimeoutMillis(int socketTimeoutMillis) {
		this.socketTimeoutMillis = socketTimeoutMillis;
		return this;
	}

	public EsClientBuilder setConnectionRequestTimeoutMillis(int connectionRequestTimeoutMillis) {
		this.connectionRequestTimeoutMillis = connectionRequestTimeoutMillis;
		return this;
	}

	public EsClientBuilder setMaxConnectPerRoute(int maxConnectPerRoute) {
		this.maxConnectPerRoute = maxConnectPerRoute;
		return this;
	}

	public EsClientBuilder setMaxConnectTotal(int maxConnectTotal) {
		this.maxConnectTotal = maxConnectTotal;
		return this;
	}

	public static EsClientBuilder build(List<HttpHost> httpHosts) {
		return new EsClientBuilder(httpHosts);
	}

	public RestHighLevelClient create() {

		HttpHost[] httpHostArr = httpHosts.toArray(new HttpHost[0]);
		RestClientBuilder builder = RestClient.builder(httpHostArr);
		
		/*1.设置请求头，避免每个请求都必须指定*/
//		Header[] defaultHeaders = new Header[]{
//				new BasicHeader("header", "value")
//		};
//		builder.setDefaultHeaders(defaultHeaders); 
		//设置密码
//		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "password")); 
//		builder.setDefaultCredentialsProvider(credentialsProvider);
			
		builder.setRequestConfigCallback(requestConfigBuilder -> {
			requestConfigBuilder.setConnectTimeout(connectTimeoutMillis);
			requestConfigBuilder.setSocketTimeout(socketTimeoutMillis);
			requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeoutMillis);
			return requestConfigBuilder;
		});

		builder.setHttpClientConfigCallback(httpClientBuilder -> {
			httpClientBuilder.setMaxConnTotal(maxConnectTotal);
			httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
			return httpClientBuilder;
		});

		return new RestHighLevelClient(builder);
	}

}
