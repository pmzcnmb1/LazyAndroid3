package com.gh.lazy.debug.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ParameterizedTypeListIml implements ParameterizedType {

    Class clazz;

    public ParameterizedTypeListIml(Class clz) {
        clazz = clz;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{clazz};//返回实际类型组成的数据
    }

    @Override
    public Type getRawType() {
        return List.class;//返回原生类型
    }

    @Override
    public Type getOwnerType() {
        return null;//返回 Type 对象，表示此类型是其成员之一的类型;如果此类型为 O<T>.I<S>，则返回 O<T> 的表示形式。 如果此类型为顶层类型，则返回 null。这里就直接返回null就行了。
    }
}
