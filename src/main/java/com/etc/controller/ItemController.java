package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.Item;
import com.etc.service.ItemService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController extends BasicController {

    @Autowired
    @Qualifier("itemService")
    private ItemService itemService;

    @ModelAttribute
    @RequestMapping("/getItemsList")
    public void getItemsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Item> itemsList = itemService.getAllItem();
        json.put("count", itemsList.size());

        int listSum = itemsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Item> itemsListsPage = new ArrayList<>();
        for(int i = start; i < end; i++) {
            itemsListsPage.add(itemsList.get(i));
        }
        json.put("data", itemsListsPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getItem")
    public void getItem(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Item item = itemService.getItemById(id);

        json.put("item", item);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addItem")
    public void addItem(HttpServletRequest request, HttpServletResponse response, Item item) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        itemService.addItem(item);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modItem")
    public void modItem(HttpServletRequest request, HttpServletResponse response, Item item) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        itemService.modItemById(item);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delItem")
    public void delItem(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        itemService.delItemById(id);

        this.writeJson(json.toString(), response);
    }

}
