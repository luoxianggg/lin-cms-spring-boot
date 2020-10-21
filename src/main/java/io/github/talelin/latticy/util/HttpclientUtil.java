package io.github.talelin.latticy.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by frankliu on 15/7/6.
 */
public class HttpclientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpclientUtil.class);

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        cm.setDefaultMaxPerRoute(20);
        cm.setMaxTotal(200);

       // hc = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static <T> T postJSON(String url, Map<String, String> params, Object o, Class clazz) throws IOException {

        //

        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();


        HttpPost post=new HttpPost(url);

        for(Map.Entry<String, String> param : params.entrySet()) {
            post.setHeader(param.getKey(), param.getValue());
        }
        post.setEntity(new StringEntity(JSON.toJSONString(o), "utf-8"));

        CloseableHttpResponse resp = null;
        try {
            resp = httpClient.execute(post);
            String buf = StringUtil.InputStreamTOString(resp.getEntity().getContent(), "utf-8");
            return StringUtil.isValid(buf) ? (T) JSON.parseObject(buf, clazz) : null;
        } catch(Exception e2){
            logger.error(e2.getMessage());
            throw new RuntimeException(e2.getMessage());
        } finally{
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    public static <T> T getJSON(String url, Map<String, String> params,  Class clazz) throws IOException {

        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        HttpGet get = new HttpGet(url);
        for(Map.Entry<String, String> param : params.entrySet()) {
            get.setHeader(param.getKey(), param.getValue());
        }

        CloseableHttpResponse resp = null;
        try {
            resp = httpClient.execute(get);
            String buf = StringUtil.InputStreamTOString(resp.getEntity().getContent(), "utf-8");
            return StringUtil.isValid(buf) ? (T) JSON.parseObject(buf, clazz) : null;
        }catch(Exception e2){
            logger.error(e2.getMessage());
            return null;
        } finally {
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    public static <T> T getJSONP(String url, Map<String, String> params,  Class clazz) throws IOException {

        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        HttpGet get = new HttpGet(url);
        for(Map.Entry<String, String> param : params.entrySet()) {
            get.setHeader(param.getKey(), param.getValue());
        }

        CloseableHttpResponse resp = null;
        try {
            resp = httpClient.execute(get);
            String buf = StringUtil.InputStreamTOString(resp.getEntity().getContent(), "utf-8");
            String trans = buf;
            if(trans.contains("(")){
                int start = trans.indexOf("(");
                int end = trans.indexOf(")");
                trans = trans.substring(start+1,end);
            }
            return StringUtil.isValid(buf) ? (T) JSON.parseObject(trans, clazz) : null;
        }catch(Exception e2){
            logger.error(e2.getMessage());
            return null;
        } finally {
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    private static TrustManager manager =new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    private static void enableSSL(){
        try {
            SSLContext context=SSLContext.getInstance("TLS");
            context.init(null,new TrustManager[]{manager},null);
            socketFactory =new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static SSLConnectionSocketFactory socketFactory;

    public static CloseableHttpResponse doHttpsGet(String url,String cookie,String refer)throws IOException{
        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        HttpGet get=new HttpGet(url);
        if(cookie!=null){
            get.setHeader("Cookie",cookie);
        }
        if(refer!=null){
            get.setHeader("Refer",refer);
        }
        CloseableHttpResponse response=httpClient.execute(get);
        return response;
    }

    public static CloseableHttpResponse doHttpsPost(String url, List<NameValuePair> values, String cookie, String refer)throws IOException{
        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        HttpPost post=new HttpPost(url);
        if(cookie!=null){
            post.setHeader("Cookie",cookie);
        }
        if(refer!=null){
            post.setHeader("Refer",refer);
        }
        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(values, Consts.UTF_8);
        post.setEntity(entity);
        CloseableHttpResponse response=httpClient.execute(post);
        return response;
    }

    public static String postJSON(String url, Map<String, String> params, Object o) throws IOException {

        enableSSL();
        RequestConfig defaultRequestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();


        HttpPost post=new HttpPost(url);

        for(Map.Entry<String, String> param : params.entrySet()) {
            post.setHeader(param.getKey(), param.getValue());
        }
        post.setEntity(new StringEntity(JSON.toJSONString(o), "utf-8"));

        CloseableHttpResponse resp = null;
        try {
            resp = httpClient.execute(post);

            String buf = StringUtil.InputStreamTOString(resp.getEntity().getContent(), "utf-8");
            return buf;
        } catch(Exception e2){
            logger.error(e2.getMessage());
            return null;
        }finally {
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

   /* public static void main(String[] args){
        Map<String,String> para = new HashMap<>();
        Map<String,String> request = new HashMap<>();
        para.put("Content-Type","application/json");
        request.put("token","bc6c05b9-be10-4468-8f10-c634f1f3b082");
        Map<String,String> response = null;
        try {
            response = HttpclientUtil.getJSON("https://10.192.31.13/sso/check?" +
                    "token=bc6c05b9-be10-4468-8f10-c634f1f3b082&" +
                    "accesskey=2f13a2a5-b9bf-43b4-a58d-5473079a17b1", para, Map.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(response != null && response.containsKey("code")) {
            if(String.valueOf(response.get("code")).equals("0")){
                System.out.println(String.valueOf(response.get("code")));
                System.out.println(response.get("account"));
                System.out.println(response.get("msg"));
            }
        }

    }*/

}