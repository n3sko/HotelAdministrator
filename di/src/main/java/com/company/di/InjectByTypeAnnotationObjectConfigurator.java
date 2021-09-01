package com.company.di;

import com.company.di.annotations.InjectByType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    private static final Logger logger = LogManager.getLogger(InjectByTypeAnnotationObjectConfigurator.class);

    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                Object object = context.getObject(field.getType(), null);
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
