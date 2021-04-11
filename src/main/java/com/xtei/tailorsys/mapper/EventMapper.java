package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Event record);

    Event selectByPrimaryKey(Integer id);

    List<Event> selectAll();

    int updateByPrimaryKey(Event record);
}