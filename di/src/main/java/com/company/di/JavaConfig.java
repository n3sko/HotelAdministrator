package com.company.di;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;

    public JavaConfig(Reflections scanner) {
        this.scanner = scanner;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> type, String nameImplType) throws IllegalArgumentException {
        Set<Class<? extends T>> set = scanner.getSubTypesOf(type);
        if (set.size() > 1 && nameImplType != null) {
            List<Class<? extends T>> list = new ArrayList<>(set);
            for (Class<? extends T> impl : list) {
                if (impl.getSimpleName().equalsIgnoreCase(nameImplType)) {
                    return impl;
                }
            }
        }
        if (set.size() == 0) {
            throw new IllegalArgumentException(type + " has 0 impl");
        }
        return set.iterator().next();
    }
}





