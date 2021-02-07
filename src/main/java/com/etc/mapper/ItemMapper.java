package com.etc.mapper;

import com.etc.domain.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

    Item getItemById(@Param("id") int id);

    List<Item> getAllItem();

    void addItem(Item item);

    void modItemById(Item item);

    void delItemById(@Param("id") int id);
    
}
