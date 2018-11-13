**Spring MVC注解**

* @RequestMapping

    Spring MVC通过@RequestMapping注解将URL请求与业务方法进行映射。在控制器的类定义处以及方法定义处都可以添加
    @RequestMapping,在类定义处添加@RequestMapping注解，相当于多了一层访问路径。
    
   
* 参数

    1、value：指定URL请求的实际对，是@RequestMapping的默认值等价于: @RequestMapping(value ="hello")
    
    2、method:指定请求的method类型，GET、POST、PUT、DELETE等。
    
![method](chapter7/img-folder/method.jpg)

 表示只有POST请求可以访问该方法，若使用GET请求方法，直接报错。 
    
    
    3、params: 指定request中必须包含某些参数值,否则无法调用方法
      URL 请求中必须包含name和id 两个参数，并且id的值必须为10，才能调用paramsTest方法
    
    http://localhost:8089/helloHandler/paramsTest?name=zhangsan&id=10
    
    
参数绑定:
    
    params是对URL请求的参数进行限制,不满足条件的URL无法到达业务方法，这个特性并不是我们开发中
    常用的，我们需要用到的是在业务方法中获取URL的参数，实现这一步骤很简单。
    
    (1)、在业务方法定义时声明参数列表。
    (2)、给参数列表添加@RequestParam注解。
    
![RequestParam注解](chapter7/img-folder/requestParam.jpg)


将URL请求的参数name和id分别赋给形参name和id,同时进行了数据类型转换，URL参数都是String类型的，根据形参
的数据类型，将id转换为Integer类型，所以可以看到打印的num值为20,完成了数学运算。

http://localhost:8089/helloHandler/paramsBind?name=zhangsan&id=10


Spring MVC同时也支持RESTFUL分隔的URL

![RESTFUL 风格的URL](chapter7/img-folder/RESTFUL.jpg)

将参数列表的注解改为@PathVariable("name")即可,非常简单


http://localhost:8089/helloHandler/rest/zhangsan

映射 Cookie

    Spring MVC通过映射可以直接在业务方法中获取Cookie的值
    
![映射 Cookie](chapter7/img-folder/cookie.jpg)

Spring MVC解决中文乱码很简单,在web.xml中添加过滤器即可

![encoding中文乱码过滤器](chapter7/img-folder/encoding.jpg)

redirect重定向:
    地址栏发生改变,需要注意的是业务方法中，设置重定向不能写逻辑视图，必须写明目标资源的物理路径
    
forward转发:
    地址栏不会发生改变，request对象数据不会丢失
    
备注:
    如果是单纯的springmvc，可以不需要applicationContext，如果是框架整合，需要Spring容器来管理的，
比如ssm，需要将其他框架在applicationContext中配置,ContextLoaderListener监听器的作用就是启动Web容器时，
自动装配ApplicationContext的配置信息