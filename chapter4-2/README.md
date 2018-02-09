**Spring MVC概述**
    MVC:Model + View + Controller(数据模型+视图+控制器)
    
    三层架构:Presentation tier + Application tier + Data tier(展现层+应用层+数据访问层)。
    
    MVC的M就是数据访问层、V就是展现层、C就是应用层。
    
    
    但是实际上MVC只存在三层架构的展现层、M实际上是数据模型，是包含数据的对象。在Spring MVC
里，有一个专门的类叫Model,用来和V之间的数据交互、传值；V指的是视图页面，包含JSP、freeMarker、
Velocity、Thymeleaf、Title等；C当然就是控制器（Spring MVC的注解@Controller的类）。

