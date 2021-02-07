package com.etc.service.impl;

import com.etc.domain.Item;
import com.etc.mapper.ItemMapper;
import com.etc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public Item getItemById(int id) {
        return itemMapper.getItemById(id);
    }

    @Override
    public List<Item> getAllItem() {
        return itemMapper.getAllItem();
    }

    @Override
    public void addItem(Item item) {
        itemMapper.addItem(item);
    }

    @Override
    public void modItemById(Item item) {
        itemMapper.modItemById(item);
    }

    @Override
    public void delItemById(int id) {
        itemMapper.delItemById(id);
    }
}
