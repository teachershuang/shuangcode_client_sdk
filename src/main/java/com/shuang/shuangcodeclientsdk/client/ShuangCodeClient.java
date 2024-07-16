package com.shuang.shuangcodeclientsdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.shuang.shuangcodeclientsdk.model.User;
import com.shuang.shuangcodeclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口客户端
 *
 * @author Shuang
 */
public class ShuangCodeClient {
    private String accessKey;
    private String secretKey;

    public ShuangCodeClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameUsingGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        System.out.printf(result);
        return result;
    }


    public String getNameUsingPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        System.out.printf(result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        //一定不能直接发送给后端
        //hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(5));
        hashMap.put("body", body);
        hashMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        return hashMap;
    }


    public String getUserNameUsingPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String body = httpResponse.body();
        System.out.println(body);
        return body;
    }
}
