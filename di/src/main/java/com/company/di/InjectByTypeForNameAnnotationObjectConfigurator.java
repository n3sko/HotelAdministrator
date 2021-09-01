package com.company.di;

import com.company.di.annotations.InjectByTypeForName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;

public class InjectByTypeForNameAnnotationObjectConfigurator implements ObjectConfigurator {

    private static final Logger logger = LogManager.getLogger(InjectByTypeForNameAnnotationObjectConfigurator.class);

    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            InjectByTypeForName annotation = field.getAnnotation(InjectByTypeForName.class);
            if (annotation != null) {
                String nameImplType = annotation.value();
                Object object = context.getObject(field.getType(), nameImplType);
                field.setAccessible(true);
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    logger.error("Error! No access to the field " + field.getName() + ".");
                }
            }
        }
    }
}
