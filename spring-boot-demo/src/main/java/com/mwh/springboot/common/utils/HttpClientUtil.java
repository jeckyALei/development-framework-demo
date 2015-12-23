package com.mwh.springboot.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

/**
 * 基于HttpClient 4.3.x版本的客户端
 * 
 * @author shawn
 * 
 */
public class HttpClientUtil {
	private static PoolingHttpClientConnectionManager cm;
	private static RequestConfig requestConfig;

	/**
	 * 发送 post请求访问远程服务
	 */
	public static String doPost(String url, String jsonData) throws Exception {
		// 创建httpClient实例.
		CloseableHttpClient httpclient = getConnection();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		try {
			StringEntity string = new StringEntity(jsonData, "utf-8");
			httppost.setEntity(string);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					if (entity != null) {
						return EntityUtils.toString(entity, "UTF-8");
					}
				} else {

					throw new Exception(response.getStatusLine()
							.getReasonPhrase());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw e;
		} finally {
			// 关闭连接,释放资源
			// try {
			// httpclient.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		return null;
	}

	/**
	 * 发送 post请求访问远程服务
	 */
	public static byte[] doPost(String url, byte[] byteData) throws Exception {
		// 创建httpClient实例.
		CloseableHttpClient httpclient = getConnection();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		try {
			ByteArrayEntity byteEntity = new ByteArrayEntity(byteData);
			BasicHeader header = new BasicHeader("Content-Type",
					"application/octet-stream");
			byteEntity.setContentType(header);
			httppost.setEntity(byteEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					if (entity != null) {
						return EntityUtils.toByteArray(entity);
					}
				} else {
					throw new Exception(response.getStatusLine()
							.getReasonPhrase());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw e;
		} finally {
		}
		return null;
	}

	private byte[] doHandle(HttpEntity entity)
			throws UnsupportedOperationException, IOException {
		final InputStream instream = entity.getContent();
		if (instream == null) {
			return null;
		}
		try {
			Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
					"HTTP entity too large to be buffered in memory");
			int i = (int) entity.getContentLength();
			if (i < 0) {
				i = 4096;
			}
			final ByteArrayBuffer buffer = new ByteArrayBuffer(i);
			final byte[] tmp = new byte[4096];
			int l;
			while ((l = instream.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return buffer.toByteArray();
		} finally {
			instream.close();
		}
	}

	/**
	 * 发送 get请求
	 */
	public static String doGet(String url) throws Exception {
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpClient httpclient = getConnection();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					if (entity != null) {
						return EntityUtils.toString(entity);
					}
				} else {
					throw new Exception(response.getStatusLine()
							.getReasonPhrase());
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭连接,释放资源
			// try {
			// httpclient.close();
			// } catch (IOException e) {
			// logger.error(e.getMessage());
			// }
		}
		return null;
	}

	private static CloseableHttpClient getConnection() {
		return HttpClients.custom()
				.setConnectionManager(getConnectionManager())
				.setDefaultRequestConfig(getRequestConfig()).build();
	}

	private static synchronized PoolingHttpClientConnectionManager getConnectionManager() {
		if (null == cm) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(PropertiesUtil
					.getIntegerConstValue("httpclient.maxTotal"));
			cm.setDefaultMaxPerRoute(PropertiesUtil
					.getIntegerConstValue("httpclient.maxPerRoute"));
			new IdleConnectionMonitor(cm).start();
		}
		return cm;
	}

	private static synchronized RequestConfig getRequestConfig() {
		if (null == requestConfig) {
			requestConfig = RequestConfig
					.custom()
					.setConnectionRequestTimeout(
							PropertiesUtil
									.getIntegerConstValue("httpclient.requestTimeout"))
					.setSocketTimeout(
							PropertiesUtil
									.getIntegerConstValue("httpclient.socketTimeout"))
					.setConnectTimeout(
							PropertiesUtil
									.getIntegerConstValue("httpclient.connectTimeout"))
					.build();
		}
		return requestConfig;
	}

	/**
	 * 按字节上传文件
	 * 
	 * @throws Exception
	 */
	public static String doUpload(String url, String filePath) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);

			FileBody bin = new FileBody(new java.io.File(filePath));
			StringBody comment = new StringBody("A binary file",
					ContentType.DEFAULT_BINARY);
			StringBody name = new StringBody("name", ContentType.DEFAULT_TEXT);
			// ByteArrayBody bab = new ByteArrayBody(bytes, fileName);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("name", name).addPart("file", bin)
					.addPart("comment", comment).build();

			httppost.setEntity(reqEntity);

			System.out
					.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					if (entity != null) {
						return EntityUtils.toString(entity);
					}
				} else {
					throw new Exception(response.getStatusLine()
							.getReasonPhrase());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// try {
			// httpclient.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		return null;
	}

}

class IdleConnectionMonitor extends Thread {
	PoolingHttpClientConnectionManager cm;
	private volatile boolean shutdown;

	public IdleConnectionMonitor(PoolingHttpClientConnectionManager cm) {
		super();
		this.cm = cm;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000);
					// Close expired connections
					cm.closeExpiredConnections();
					// Optionally, close connections
					// that have been idle longer than 30 sec
					cm.closeIdleConnections(30, TimeUnit.SECONDS);
				}
			}
		} catch (InterruptedException ex) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}

}