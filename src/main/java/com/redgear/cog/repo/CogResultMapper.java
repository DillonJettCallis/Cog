package com.redgear.cog.repo;

public interface CogResultMapper<T, C> {

    void add(T next);

    default void error(Throwable t) {

    }

    void complete();

    C build();

}
