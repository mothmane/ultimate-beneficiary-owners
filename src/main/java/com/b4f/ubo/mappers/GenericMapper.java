package com.b4f.ubo.mappers;


import com.google.common.collect.ImmutableList;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<T, U> {


    public T fromDTO(U u) ;
    public U toDTO(T t) ;

    default public  List<U> toDTOs(List<T> list){
          return   list.stream().map(e -> toDTO(e)).collect(Collectors.toList());
    }
    default public  List<T> fromDTOs(List<U> list){
        return   list.stream().map(e -> fromDTO(e)).collect(Collectors.toList());
    }
    default public  List<U> toDTOs(Iterable<T> list){
        return  this.toDTOs(ImmutableList.copyOf(list));
    }
    default public  List<T> fromDTOs(Iterable<U> list){
        return   this.fromDTOs(ImmutableList.copyOf(list));
    }
}
