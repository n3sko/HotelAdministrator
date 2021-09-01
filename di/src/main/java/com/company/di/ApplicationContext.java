package com.company.di;

import com.company.di.annotations.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private Map<String, Object> cache2 = new ConcurrentHashMap<>();
    private Reflections scanner;
    private JavaConfig config;
    private ObjectFactory factory;

    private static final Logger logger = LogManager.getLogger(ApplicationContext.class);

    public ApplicationContext(String packageToScan) {
        scanner = new Reflections(packageToScan);
        config = new JavaConfig(scanner);
        factory = new ObjectFactory(this);
    }

    public <T> T getObject(Class<T> type, String nameImplType) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
        if (nameImplType != null) {
            if (cache2.containsKey(nameImplType.toLowerCase())) {
                return (T) cache2.get(nameImplType.toLowerCase());
            }
        }
        Class<T> implClass = resolveImpl(type, nameImplType);
        T t = null;
        try {
            t = factory.createObject(implClass);
        } catch (Exception e) {
            logger.error("Error! Object " + implClass.getSimpleName() + " not created!");
        }
        if (implClass.isAnnotationPresent(Singleton.class)) {
            if (implClass.getSimpleName().equalsIgnoreCase(nameImplType)) {
                cache2.put(implClass.getSimpleName().toLowerCase(), t);
            } else {
                cache.put(type, t);
            }
        }
        return t;
    }

    private <T> Class<T> resolveImpl (Class<T> type, String nameImplType) {
        if (type.isInterface()) {
            try {
                type = (Class<T>) config.getImplClass(type, nameImplType);
            } catch (IllegalArgumentException e) {
                logger.error("Error! Type " + type.getSimpleName() + " has no implementations.");
            }
        }
        return type;
    }

    public Reflections getScanner() {
        return scanner;
    }
}





