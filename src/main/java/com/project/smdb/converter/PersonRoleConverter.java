package com.project.smdb.converter;

import com.project.smdb.domain.type.PersonRole;
import org.springframework.core.convert.converter.Converter;

public class PersonRoleConverter implements Converter<String, PersonRole> {
    @Override
    public PersonRole convert(String source) {
        try {
            return PersonRole.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
