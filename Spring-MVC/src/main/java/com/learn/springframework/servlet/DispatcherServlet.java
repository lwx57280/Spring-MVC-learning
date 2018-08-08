package com.learn.springframework.servlet;

import com.learn.springframework.annotation.*;
import com.learn.springframework.controller.UserController;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @WebServlet 是什么？
 * 其实，以前我们定义一个Servlet，需要在web.xml中去配置，不过在Servlet3.0后出现了基于注解的Servlet。
 * 仔细观察，你会发现，这个DispatcherServlet是自启动，而且传入了一个参数。
 * 要知道，在Spring MVC中，要想基于注解，需要在配置中指明扫描的包路径，就像这个样子：
 * 为了方便，我这里就通过初始化参数直接将需要扫描的基包路径传入。
 * DispatcherServlet的定义
 * @Author
 * @Date
 * @Version 1.0
 */
@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1,
        initParams = {@WebInitParam(name = "base-package", value = "com.learn.springframework")})
public class DispatcherServlet extends HttpServlet {

    // 扫描的基包
    private String basePackage = "";

    // 基包下面所有的带包路径权限定类名
    private List<String> packageNames = new ArrayList<String>();


    // 注解实例化，注解上的名称:实例化对象
    private Map<String, Object> instanceMap = new HashMap<String, Object>();

    // 带包路径的权限定名称:注解上的名称
    private Map<String, String> nameMap = new HashMap<String, String>();

    // URl地址和方法的映射关系 SpringMVC就是方法调用链
    private Map<String, Method> urlMethodMap = new HashMap<String, Method>();

    // Method和权限定类名映射关系，主要是为了通过Method找到该方法的对象利用反射执行
    private Map<Method, String> methodPackageMap = new HashMap<Method, String>();

    /**
     * 其实，在init中，我们主要是完成了什么呢？
     * 第一，我们应该去扫描基包下的类，得到信息A
     * 第二，对于@Controller/@Service/@Repository注解而言，我们需要拿到对应的名称，并初始化它们修饰的类，形成映射关系B
     * 第三，我们还得扫描类中的字段，如果发现有@Qualifier的话，我们需要完成注入
     * 第四，我们还需要扫描@RequestMapping，完成URL到某一个Controller的某一个方法上的映射关系C
     * 其实，Spring MVC的处理流程，就是类似这样的！
     * <p>
     * init初始化处理
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        basePackage = config.getInitParameter("base-package");

        try {
            // 1. 扫描基包得到全部的带包路径权限定名
            scanBasePackage(basePackage);
            // 2. 把带有@Controller/@Service/@Repository的类实例化放入Map中，KEY为注解上的名称
            instance(packageNames);
            // 3. Spring IOC注入
            springIOC();
            // 4. 完成URL地址的与方法的映射关系
            handlerUrlMethodMap();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * URL映射处理：URL，我们需要提取出来，映射到Controller的Method上。
     */
    private void handlerUrlMethodMap() throws ClassNotFoundException {
        if (packageNames.size() < 1) {
            return;
        }

        for (String string : packageNames) {

            Class c = Class.forName(string);
            if (c.isAnnotationPresent(Controller.class)) {

                Method[] methods = c.getMethods();
                StringBuffer baseUrl = new StringBuffer();
                if (c.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }

                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        baseUrl.append(requestMapping.value());

                        urlMethodMap.put(baseUrl.toString(), method);
                        methodPackageMap.put(method, string);
                    }
                }
            }
        }
    }

    /**
     * 依赖注入
     * <p>
     * 以前，我们总是说Spring IOC，上面不就是在做这个事情么？
     */
    private void springIOC() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {

            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), instanceMap.get(name));
                }
            }
        }
    }

    /**
     * 从这里你可以看出，我们完成了被注解标注的类的实例化，以及和注解名称的映射。
     * 实例化：
     *
     * @param packageNames 扫描包名
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String string : packageNames) {
            // 通过反射获取类的实例
            Class c = Class.forName(string);

            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();

                instanceMap.put(controllerName, c.newInstance());
                nameMap.put(string, controllerName);
                System.out.println("Controller :" + string + " , value :" + controller.value());

            } else if (c.isAnnotationPresent(Service.class)) {
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();

                instanceMap.put(serviceName, c.newInstance());
                nameMap.put(string, serviceName);
                System.out.println("Service :" + string + " , value :" + service.value());
            } else if (c.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository) c.getAnnotation(Repository.class);
                String repositoryName = repository.value();

                instanceMap.put(repositoryName, c.newInstance());
                nameMap.put(string, repositoryName);
                System.out.println("Repository :" + string + " , value :" + repository.value());
            }
        }
    }

    /**
     * 扫描基包
     *
     * @param basePackage
     */
    private void scanBasePackage(String basePackage) {
        // 注意为了得到基包下面的URl路径需要对basePackage做转换：将.转换为/
        // D:\Projects\Spring-MVC-learning\MySpringMVC\target\classes\com\learn\springframework\service\UserServiceImpl.class
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:" + basePackageFile);
        File[] childFiles = basePackageFile.listFiles();
        for (File file : childFiles) {
            // 如果是目录继续递归扫描
            if (file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                // 类似这种: com.learn.springframework 去掉class
                packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = url.replaceAll(contextPath, "");

        // 通过path找到method
        Method method = urlMethodMap.get(path);
        if (null != method) {

            // 通过Method拿到Controller对象准备反射执行
            String packageName = methodPackageMap.get(method);
            String controllerName = nameMap.get(packageName);

            // 拿到Controller对象
            UserController userController = (UserController) instanceMap.get(controllerName);
            try {
                method.setAccessible(true);
                method.invoke(userController);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
