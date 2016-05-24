package com.lucilu.rxdynamicsearch.provider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Generic {@link ParameterizedType} for lists of Json generic objects of type T.
 */
public class ListOfJsonType<T> implements ParameterizedType {

    private Class<?> wrapped;

    public ListOfJsonType(Class<T> wrapper) {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
