package com.company.di;

public interface Config {
    <T> Class<? extends T>  getImplClass(Class<T> type, String nameImplType);
}
