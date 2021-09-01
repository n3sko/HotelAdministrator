package com.company.di;

import com.company.di.annotations.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(ApplicationContext.class);

    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Set<Class<? extends ObjectConfigurator>> classes = context.getScanner().getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                logger.error("Error! Object " + aClass.getSimpleName() + " not created.");
            } catch (IllegalAccessException e) {
                logger.error("Error! No access to constructor " + aClass.getSimpleName() + ".");
            } catch (InvocationTargetException e) {
                logger.error("Error! Method called by reflection threw an exception." + e.getCause().getMessage());
            } catch (NoSuchMethodException e) {
                logger.error("Error! Method does not exist when using reflection.");
            }
        }
    }

    public <T> T createObject(Class<T> type) throws Exception {
        T t = type.getDeclaredConstructor().newInstance();
        configure(t);
        invokeInit(type, t);
        return t;
    }

    private <T> void invokeInit(Class<T> type, T t) {
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(t);
                } catch (IllegalAccessException e) {
                    logger.error("Error! No access to method " + method.getName() + ".");
                } catch (InvocationTargetException e) {
                    logger.error("Error! Method called by reflection threw an exception." + e.getCause().getMessage());
                }
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(configurator -> configurator.configure(t, context));
    }
}
