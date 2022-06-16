package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 4:26 下午
 * description：http工具类
 * https://blog.csdn.net/qq_34417433/article/details/105098915
 */
@Component
public class HttpUtil {

    /**
     * 从配置文件中获取
     * @see com.config.HttpConfig
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public String get(String url, Map<String, Object> params) {
        return get(url, params, null);
    }

    /**
     * get请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public String get(String url, Map<String, Object> params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.GET, MediaType.APPLICATION_FORM_URLENCODED);
    }

    /**
     * post请求
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public String post(String url, String params) {
        return post(url, params, null);
    }

    /**
     * post请求
     *
     * @param url
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public String post(String url, String params, MultiValueMap<String, String> headers) {
        return request(url, params, headers, HttpMethod.POST, MediaType.APPLICATION_JSON);
    }

    /**
     * http请求
     *
     * @param url
     * @param params    请求参数
     * @param headers   请求头
     * @param method    请求方式
     * @param mediaType 参数类型
     * @return
     */
    public String request(String url, Object params, MultiValueMap<String, String> headers, HttpMethod method, MediaType mediaType) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        // header
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            httpHeaders.addAll(headers);
        }
        // 提交方式：表单、json
        httpHeaders.setContentType(mediaType);
        HttpEntity httpEntity = new HttpEntity(params, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, method, httpEntity, String.class);
        return response.getBody();
    }

}
