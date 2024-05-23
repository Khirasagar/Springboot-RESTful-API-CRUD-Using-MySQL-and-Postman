package com.user.service;

import com.user.dto.PropertyUserDto;
import com.user.entity.PropertyUserEntity;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.PropertyUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PropertyUserImpl implements PropertyUser{

    private final PropertyUserRepository propertyUserRepository;

    public PropertyUserImpl(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }

    @Override
    public PropertyUserDto addPropertyUser(PropertyUserDto dto) {
        PropertyUserEntity entity = dtoToEntity(dto);

        //save the entity
        PropertyUserEntity savePropertyUser = propertyUserRepository.save(entity);

        PropertyUserDto puo = entityToDto(savePropertyUser);
        return puo;
    }

    PropertyUserEntity dtoToEntity(PropertyUserDto dto){
        PropertyUserEntity entity = new PropertyUserEntity();
        entity.setName(dto.getName());
        entity.setMobile(dto.getMobile());
        entity.setEmailId(dto.getEmailId());

        return entity;
    }

    PropertyUserDto entityToDto(PropertyUserEntity en){
        PropertyUserDto puo = new PropertyUserDto();
        puo.setId(en.getId());
        puo.setName(en.getName());
        puo.setEmailId(en.getEmailId());
        puo.setMobile(en.getMobile());
        puo.setDate(new Date());

        return puo;
    }

    @Override
    public void deletePropertyUser(long propertyUserId) {
        propertyUserRepository.deleteById(propertyUserId);
    }


    @Override
    public PropertyUserEntity updatePropertyUser(
            long propertyUserId,
            PropertyUserDto dto
    ) {
        PropertyUserEntity propertyUserEntity = null;
        Optional<PropertyUserEntity> byId = propertyUserRepository.findById(propertyUserId);
        if(byId.isPresent()){
            propertyUserEntity = byId.get();
        }else{
            throw  new ResourceNotFoundException("User not found with id: "+propertyUserId);
        }

        propertyUserEntity.setName(dto.getName());
        propertyUserEntity.setMobile(dto.getMobile());
        propertyUserEntity.setEmailId(dto.getEmailId());
        return propertyUserRepository.save(propertyUserEntity);

    }

    @Override
    public List<PropertyUserDto> getPropertyUsers(int pageSize,int pageNo,String sortBy,String sortDir) {
        Pageable pageable =null;
        if (sortDir.equalsIgnoreCase("asc")){
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }else if(sortDir.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        }

        Page<PropertyUserEntity> page = propertyUserRepository.findAll(pageable);
        List<PropertyUserEntity> pue = page.getContent();
        List<PropertyUserDto> pud = pue.stream()
                .map(p -> entityToDto(p))
                .collect(Collectors.toList());
        return pud;
    }

    @Override
    public PropertyUserEntity getPropertyUserById(Long propertyUserId) {
        PropertyUserEntity propertyUserEntity =
                propertyUserRepository.findById(propertyUserId).orElseThrow(
                        ()->new ResourceNotFoundException("User not found with requested id :"+ propertyUserId+ "!")
                );
        return propertyUserEntity;
    }
}
