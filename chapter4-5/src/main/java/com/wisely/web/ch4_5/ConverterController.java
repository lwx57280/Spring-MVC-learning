package com.wisely.web.ch4_5;


import com.wisely.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 */
@Controller
public class ConverterController {

    /**
     * 指定返回的媒体类型为自定义的媒体类application/x-wisely
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "/convert", produces = {"application/x-wisely"})
    public @ResponseBody DemoObj convert(@RequestBody DemoObj obj) {
        System.out.println("obj===========" + obj.getId() + "|" + obj.getName());
        return obj;
    }

}
