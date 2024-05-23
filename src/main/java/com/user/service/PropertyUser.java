package com.user.service;

import com.user.dto.PropertyUserDto;
import com.user.entity.PropertyUserEntity;

import java.util.List;

public interface PropertyUser {
    public PropertyUserDto addPropertyUser(PropertyUserDto dto);

    void deletePropertyUser(long propertyUserId);

    PropertyUserEntity updatePropertyUser(long propertyUserId, PropertyUserDto dto);

    List<PropertyUserDto> getPropertyUsers(int pageSize, int pageNo, String sortBy, String sortDir);

    PropertyUserEntity getPropertyUserById(Long propertyUserId);
}
