*java实现迷你版Spring MVC*


* 工程结构

[工程结构]（Spring-MVC/img-folder/SpringMVC.jpg）
    
    第一，在annotation包下，我将提供自定义的注解，为了方便理解，就和Spring MVC保持一致。
    第二，为了模拟Spring MVC的方法调用链，我这里提供Controller/Service/Dao层进行测试
    第三，提供自己的DispatcherServlet完成核心处理逻辑
    
    
* 关于自定义注解：

    
    JDK提供了几个元注解，比如：
    @Documented ： JavaDoc文档
    @Target：标志此注解可以修饰在哪些地方，类，成员变量，方法...
    @Retention：Annotation的生命周期，一般情况下，我们自定义注解的话，显然需要在运行期获取注解的一些信息。
    
### 以下是自定义注解


[自定义注解]（Spring-MVC/img-folder/SpringMVC.jpg）


* 模拟Spring MVC的@Controller注解
 
 
[@Controller注解]（Spring-MVC/img-folder/SpringMVC.jpg）

 
 
 
* @Qualifier提供依赖注入

[提供依赖注入]（Spring-MVC/img-folder/SpringMVC.jpg）


 
 
* @RequestMapping提供URL地址处理映射

[提供URL地址处理映射]（Spring-MVC/img-folder/SpringMVC.jpg）


* Dao层注解

[Dao层注解]（Spring-MVC/img-folder/SpringMVC.jpg）

* Service层注解

[Service层注解]（Spring-MVC/img-folder/SpringMVC.jpg）


### 编写核心控制器：DispatcherServlet


    在Spring MVC中，DispatcherServlet是核心，下面我们来实现它。首先来说，Spring MVC中的DispatcherServlet说到底，还是HttpServlet的子类，因此我这边自己的DispatcherSerlvet需要extends HttpServlet。
    

* pom.xml：

提供servlet依赖

* 看DispatcherServlet的定义：

[DispatcherServlet的定义]（Spring-MVC/img-folder/SpringMVC.jpg）

* URL映射处理:

[URL映射处理]（Spring-MVC/img-folder/SpringMVC.jpg）

    URL，我们需要提取出来，映射到Controller的Method上。



* init初始化处理：


[init初始化处理]（Spring-MVC/img-folder/SpringMVC.jpg）


* 扫描基包：

[扫描基包]（Spring-MVC/img-folder/SpringMVC.jpg）


* 实例化：

        

[实例化]（Spring-MVC/img-folder/SpringMVC.jpg）


    从这里你可以看出，我们完成了被注解标注的类的实例化，以及和注解名称的映射。
    
* 依赖注入：

[依赖注入]（Spring-MVC/img-folder/SpringMVC.jpg）

    以前，我们总是说Spring IOC，上面不就是在做这个事情么？
    
* URL映射处理：




* doGet/doPost

[doGet/doPost]（Spring-MVC/img-folder/SpringMVC.jpg）

    在doPost方法中，非常简单，我们只需要提取出URL，通过URL映射到Method上，然后通过反射的方式进行调用即可。
    
    
* Make it run！# Controller层：

[Controller层：]（Spring-MVC/img-folder/SpringMVC.jpg）


* Service层：

[Service层：]（Spring-MVC/img-folder/SpringMVC.jpg）

* dao层：

[dao层：]（Spring-MVC/img-folder/SpringMVC.jpg）

* 运行结果：

[运行结果：]（Spring-MVC/img-folder/SpringMVC.jpg）




