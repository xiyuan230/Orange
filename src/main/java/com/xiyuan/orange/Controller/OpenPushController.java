package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Utils.WeChatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/open")
public class OpenPushController {
    @GetMapping("/verify")
    public String verifyUrl(@RequestParam Map<String,String> params) throws Exception {
        String signature = params.get("signature");
        // 随机字符串
        String echostr = params.get("echostr");
        // 时间戳
        String timestamp = params.get("timestamp");
        // 随机数
        String nonce = params.get("nonce");

        // 消息推送配置中的 Token(令牌)
        String token = "xiyuan";

        // 验证
        String msgSignature = WeChatUtils.getSHA1(token, timestamp, nonce);

        // 验证失败
        if (!signature.equals(msgSignature)) {
            return "false";
        }
        // 验证成功 将 echostr 原格式返回 ，即可完成验证
        return echostr;
    }
}
