package com.redgear.cog.impl.resultmapper;

import com.redgear.cog.CogResultMapper;
import com.redgear.cog.CogResultMapperFactory;
import com.redgear.cog.exception.CogQueryException;

import java.util.Optional;

public class OptionalResultMapperFactory<T> implements CogResultMapperFactory<T, Optional> {

    @Override
    public Class<Optional> type() {
        return Optional.class;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public CogResultMapper<T, Optional> builder() {
        return new CogResultMapper<T, Optional>() {

            Optional<T> value;

            @Override
            public void add(T next) {
                if (value != null) {
                    throw new CogQueryException("Query expected single result but got more than one.");
                } else {
                    value = Optional.of(next);
                }
            }

            @Override
            public void complete() {
                if (value == null) {
                    value = Optional.empty();
                }
            }

            @Override
            public Optional build() {
                return value;
            }
        };
    }

}