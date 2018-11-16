**Spring MVC注解**

参数列表添加@RequestParam注解，可以对参数进行相关设置

 *  @RequestParam
 *  value = "id": 将HTTP请求中名为id的参数与形参进行映射。
 *  required = false: id参数非必填，可省略。
 *  defaultValue = "1" :若HTTP请求中没有id参数，则默认值为1。
 
 
 ![RequestParam](chapter8/img-folder/requestparam.jpg)