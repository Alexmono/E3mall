package cn.e3mall.controller;


import cn.e3mall.common.pojo.EasyUIResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getItemList(Integer page, Integer rows){
        EasyUIResult result = itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public E3Result saveItem(TbItem item, String desc){
        E3Result result = itemService.addItem(item, desc);
        return result;
    }

}
