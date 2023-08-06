package com.xiyuan.orange.Utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WeChatUtils {
    @Value("${appid}")
    String appid;
    @Value("${app-secret}")
    String app_secret;
    private static String APPID;
    private static String APP_SECRET;
    @PostConstruct
    public void init(){
        APPID = appid;
        APP_SECRET = app_secret;
    }
    public static String getUserOpenid(String code) {
        Map<String,Object> data = new HashMap<>();
        data.put("appid",APPID);
        data.put("secret",APP_SECRET);
        data.put("js_code",code);
        data.put("grant_type","authorization_code");
        String body = HttpUtil.createGet("https://api.weixin.qq.com/sns/jscode2session").form(data).execute().body();
        JSONObject result = JSON.parseObject(body);
        return result.getString("openid");
    }

    public static void pushMessage(String openid,JSONObject params) {

    }
    public static String getAccessToken() {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APP_SECRET;
        String result = HttpUtil.createGet(accessTokenUrl).execute().body();
        JSONObject res = JSON.parseObject(result);
        return res.getString("access_token");
    }

    public static String getSHA1(String... values) throws Exception {

        try {
            String[] array = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                array[i] = values[i];
            }

            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < values.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("SHA1加密失败");
        }
    }
}
