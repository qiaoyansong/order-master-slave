package com.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 4:08 下午
 * description：
 */
@Configuration
public class HttpConfig {

    /**
     * 连接池的最大连接数
     */
    @Value("${http.maxTotalConnect}")
    private int maxTotalConnect;

    /**
     * 单个主机的最大连接数,前两个参数的具体含义可以参考 https://blog.csdn.net/u013905744/article/details/94714696
     */
    @Value("${http.maxConnectPerRoute}")
    private int maxConnectPerRoute;

    /**
     * 连接超时时间，即客服端发送请求到与目标url建立起连接的最大时间,单位ms
     */
    @Value("${http.connectTimeout}")
    private int connectTimeout;

    /**
     * 读取超时时间,连接上一个url后，获取response的返回等待时间,单位ms
     */
    @Value("${http.readTimeout}")
    private int readTimeout;

    /**
     * 从连接池获取http客户端的最长等待时间，
     * HttpClient要用连接时尝试从连接池中获取，若是在等待了一定的时间后还没有获取到可用连接（比如连接池中没有空闲连接了）则会抛出获取连接超时异常。
     * 0 代表无限超时，默认值是-1
     * 单位ms
     */
    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;


    /**
     * 创建HTTP客户端工厂
     *
     * @return
     */
    private ClientHttpRequestFactory createFactory() {
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect).setMaxConnPerRoute(this.maxConnectPerRoute).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectionRequestTimeout(this.connectionRequestTimeout);
        factory.setConnectTimeout(this.connectTimeout);
        factory.setReadTimeout(this.readTimeout);
        return factory;
    }

    /**
     * 初始化RestTemplate,并加入spring的Bean工厂，由spring统一管理
     *
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.createFactory());
        // 添加内容转换器
        List<HttpMessageConverter<?>> converterList = new ArrayList<>();
        converterList.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        converterList.add(new FormHttpMessageConverter());
//        converterList.add(new MappingJackson2XmlHttpMessageConverter());
//        converterList.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converterList);
        return restTemplate;
    }

}
