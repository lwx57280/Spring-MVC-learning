**Spring MVC常用注解**
Spring MVC常用以下几个注解：

（1）@Controller

    @Controller注解在类上，表明这个类是Spring MVC里的Controller,将其声明为Spring的一个Bean，Dispatchar Servlet会自动扫描
 注解了此注解的类，并将We请求映射到注解了@RequestMapping的方法上。在声明普通Bean的时候,使用@Component、@Service、@Repository
 和@Controller是等同的,因为@Service、@Repository、@Controller都组合了@Compoment元注解；但在Spring MVC声明控制器Bean的时候
 ，只能使用@Controller。
 
（2）@RequestMapping
    @RequestMapping注解是用来映射Web请求（访问路径和参数）、处理类和方法的。@RequestMapping可注解在类或方法上。注解在方法上
 的@RequestMapping路径会继承注解在类上的路径，@RequestMapping支持Servlet的request和response作为参数，也支持request和response
 的媒体类型进行配置。
 
（3）@ResponseBody
    @ResponseBody支持将返回值放在response体内，而不是返回一个页面。基于Ajax的程序时候，可以以此注解返回数据而不是页面；此
 注解可放置在返回值前或者方法上。
 
（4）@RequestBody
    @RequestBody允许request的参数在request体中，而不是在直接链接在地址后面。此注解放置在参数前。
    
（5）@PathVariable
    @PathVariable用来接收路径参数，如/news/001，可接收001作为参数，注解放置在参数前。
    
（6）@RestController
    @RestController是一个组合注解，组合了@Controller和@ResponseBody，这就意味着当只开发一个和页面交互数据控制的时候，需要
 此注解。若没有此注解，要想实现上述功能，则需要自己在代码中加@Controller和@ResponseBody两个注解。
    
**Spring MVC logbock日志配置**

新建一个logback.xml，放到resources路径下。其中“com.springapp.mvc”需要换成你自己的java文件所在的包路径。

后面的level是日志显示等级，DEBUG等级最低，可以显示所有level的日志，WARN等级居中，可以显示WARN及其以上level的日志，ERROR等级最高，只能显示ERROR这个level的日志。所以一般用DEBUG就行。

　　level等级顺序：error>warn>info>debug。

![日志文件] https://github.com/lwx57280/Spring-MVC-leraning/blob/master/chapter4-5/img-folder/logback.jpg
=============================================================================
**静态资源映射**
程序的静态文件（js、css、图片）等需要直接访问这时可以配置重写addResourceHandlers方法来实现。

**`Spring MVC 拦截器配置`**
    拦截器（Interceptor）实现每一个请求处理后进行相关的业务处理，类似于Servlet的Filter。
    
    可以让普通的Bean实现HanlderInterceptor接口或者继承HanlderInterceptorAdapter类来实现自定义拦截器。
    通过重写WebMvcConfigurerAdapter的addInterceptors方法来注册自定义的拦截器。
    
    
**@ControllerAdvice**
    通过@ControllerAdvice，将对于控制器的全局配置放置在同一个位置，注解了@Controller的类的方法可以使用
    @ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上，这对所有注解了@RequestMapping的控制器
    内的方法有效。
    
    @ExceptionHandler:用于全局处理控制器里的异常。
    
    @InitBinder:用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中。
    
    @ModelAttribue:@ModelAttribue本来的作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能获得
    在此设置的键值对。
 
========================================================
**Spring MVC的高级配置**

Spring控制器中，通过MultipartFile file来接收文件，通过MultipartFile[] file接收多个文件上传。

**自定义HttpMessageConverter**

HttpMessageConverter是用来处理request和response里的数据的。Spring为我们内置了大量的HttpMessageConverter
例如：MappingJackjson2HttpMessageConverter、StringHttpMessageConverter等。

    配置自定义HttpMessageConverter的Bean，在Spring MVC里注册HttpMessageConverter有两个方法：
    ●configureMessageConverters：重载会覆盖掉Spring MVC默认注册的多个HttpMessageConverter。
    

    ●extendMessageConverters:仅添加一个自定义的HttpMessageConverter，不覆盖默注册的HttpMessageConverter。

    ●extendMessageConverters:仅添加一个自定义的HttpMessageConverter，不覆盖默注册的HttpMessageConverter。

**服务端消息推送技术**

服务推送方案都是基于：当客户端向服务端发送请求，服务端会抓住这个请求不放，等有数据库更新的时候才返回给客户端，当客户端
接收到消息后，再向服务端发送请求，周而复始。这种方式的好处是减少了服务器的请求数量，大大减少了服务器的压力。

@EnableScheduling 注解对应的内容如下：

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SchedulingConfiguration.class})
@Documented
public @interface EnableScheduling {
}
由上可以看到实际上是SchedulingConfiguration.class 类实现了Spring 的任务调度框架级功能。
该配置类仅仅是定义了ScheduledAnnotationBeanPostProcessor 的实例。Spring 的调度功能由该实例进行配置。

ScheduledAnnotationBeanPostProcessor
该类实现的接口如下所示：




**Spring MVC的测试**
1、@WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。它的
属性指定的是Web资源位置,默认为src/main/webapp

