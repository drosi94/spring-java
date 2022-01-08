package com.project.smdb.converters;

import com.project.smdb.domain.MovieType;
import org.springframework.core.convert.converter.Converter;

public class MovieTypeConverter implements Converter<String, MovieType> {
    @Override
    public MovieType convert(String source) {
        try {
            return MovieType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
