package com.jd.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lk
 * @Date 2020/3/15 0:28
 * @Version 1.0
 */
public class MastNode {

    public static void main(String[] args) {
        MastNode mastNode = new MastNode();
        ProxyFactory proxyFactory = mastNode.new ProxyFactory(new BirdServiceImpl(), BirdService.class);
        BirdService proxy = (BirdService) proxyFactory.getProxy();
        System.out.println(proxy.say("叽叽喳喳", 6));
        System.out.println(proxy.walk(5));

    }

    class ProxyFactory {
        private Object obj;
        private Class<? extends Object> cla;

        public ProxyFactory(Object obj, Class<? extends Object> cla) {
            this.obj = obj;
            this.cla = cla;
        }

        public Object getProxy() {
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{cla}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    RpcRequest rpcRequest = new RpcRequest();
                    rpcRequest.setServiceBean(obj);
                    rpcRequest.setMethodName(method.getName());
                    rpcRequest.setParameterTypes(method.getParameterTypes());
                    rpcRequest.setParameters(args);

                    WorkNode workNode = new WorkNode();
                    return workNode.executeRpcMethod(rpcRequest);
                }
            });
        }
    }
}


class WorkNode {
    public Object executeRpcMethod(RpcRequest request) throws InvocationTargetException {
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        Object serviceBean = request.getServiceBean();
//        FastClass serviceFastClass = FastClass.create(serviceBean.getClass());
//        FastMethod serviceFastClassMethod = serviceFastClass.getMethod(methodName, parameterTypes);
//        Object result = serviceFastClassMethod.invoke(serviceBean, parameters);
//        return "远程执行结果:{" + result + "}";
        return "";
    }
}

class RpcRequest {
    private Object serviceBean;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    public void setServiceBean(Object serviceBean) {
        this.serviceBean = serviceBean;
    }

    public Object getServiceBean() {
        return serviceBean;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}

class BirdServiceImpl implements BirdService {
    @Override
    public String say(String content, Integer times) {

        for (int i = 0; i < times; i++) {
            System.out.println(content + " ");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("该小鸟鸣叫内容:").append(content).append(",鸣叫次数:").append(times);
        return sb.toString();
    }

    @Override
    public String walk(Integer steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("行走一步");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("该小鸟行走了").append(steps).append("步");
        return sb.toString();
    }
}

interface BirdService {
    /**
     * @param content
     * @param times
     * @return
     */
    String say(String content, Integer times);

    /**
     * @param steps
     * @return
     */
    String walk(Integer steps);

}
