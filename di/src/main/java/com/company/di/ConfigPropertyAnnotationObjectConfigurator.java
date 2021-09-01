package com.company.di;

import com.company.di.annotations.ConfigProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;

public class ConfigPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> map;

    private static final Logger logger = LogManager.getLogger(ConfigPropertyAnnotationObjectConfigurator.class);

    public ConfigPropertyAnnotationObjectConfigurator() {
        final String PATH = "di/src/main/resources/application.properties";
        Stream<String> lines = null;
        try {
            lines = new BufferedReader(new FileReader(PATH)).lines();
        } catch (FileNotFoundException e) {
            logger.error("Error! File not found on path: " + PATH);
        }
        map = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    public void configure(Object t, ApplicationContext context) {
        Class<?> type = t.getClass();
        for (Field field : type.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                String typeFiled = field.getType().getSimpleName();
                String propertyName = annotation.value();
                if (typeFiled.equalsIgnoreCase("String")) {
                    String propertyValue = map.get(propertyName);
                    field.setAccessible(true);
                    try {
                        field.set(t, propertyValue);
                    } catch (IllegalAccessException e) {
                        logger.error("Error! No access to the field " + field.getName() + ".");
                    }
                } else if (typeFiled.equalsIgnoreCase("boolean")) {
                    Boolean propertyValue = Boolean.parseBoolean(map.get(propertyName));
                    field.setAccessible(true);
                    try {
                        field.set(t, propertyValue);
                    } catch (IllegalAccessException e) {
                        logger.error("Error! No access to the field " + field.getName() + ".");
                    }
                } else if (typeFiled.equalsIgnoreCase(("int"))) {
                    int propertyValue = Integer.parseInt(map.get(propertyName));
                    field.setAccessible(true);
                    try {
                        field.set(t, propertyValue);
                    } catch (IllegalAccessException e) {
                        logger.error("Error! No access to the field " + field.getName() + ".");
                    }
                }
            }
        }
    }
}
