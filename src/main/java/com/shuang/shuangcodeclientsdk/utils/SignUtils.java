package com.shuang.shuangcodeclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 * @author Shuang
 */
public class SignUtils {

    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey) {
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String content = body + '.' + secretKey;
        return digester.digestHex(content);
    }

}
