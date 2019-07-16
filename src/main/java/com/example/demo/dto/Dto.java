package com.example.demo.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Dto<T> implements Serializable {

    private static final PersistenceUtil PU = Persistence.getPersistenceUtil();

    public Dto() { }

    public Dto(T entity, String... excludeProperties) {
        if (entity != null) {
            // BeanUtils.copyProperties(entity, this, Arrays.strexcludeProperties + getNullPropertyNames(entity));
        }
    }

    public Dto(T entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this, getNullPropertyNames(entity));
        }
    }

    protected static boolean isLoaded(Object entity) {
        return PU.isLoaded(entity) && entity != null;
    }

    private String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set emptyNames = new HashSet();
        for(java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }

}