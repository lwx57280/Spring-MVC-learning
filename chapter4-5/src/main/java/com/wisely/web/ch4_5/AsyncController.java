package com.wisely.web.ch4_5;

import com.wisely.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AsyncController {

    // 1、定时任务，定时更新DeferredResult
    @Autowired
    PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredResult(){
        //  2、返回给客户端DeferredResult
        return pushService.getAsyncUpdate();
    }
}
