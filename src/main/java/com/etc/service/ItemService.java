package com.etc.service;

import com.etc.domain.Item;

import java.util.List;

public interface ItemService {

    Item getItemById(int id);

    List<Item> getAllItem();

    void addItem(Item item);

    void modItemById(Item item);

    void delItemById(int id);

}
