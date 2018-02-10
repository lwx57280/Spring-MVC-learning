package com.wisely.web.ch4_5;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * 控制器
 */
@Controller
public class SseController {

    /**
     * 这里使用输出的媒体类型为text/event-stream，这是服务器端
     * SSE的支持，本例演示每5秒向浏览器推送随机消息。
     * @return
     */
    @RequestMapping(value = "/push",produces = {"text/event-stream"})
    public @ResponseBody String push(){
        Random random = new Random();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+random.nextInt()+"\n\n";
    }
}
