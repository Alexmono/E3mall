package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;


public interface ItemService {

	TbItem getItemById(long itemId);

	EasyUIResult getItemList(int page, int rows);

	E3Result addItem(TbItem item, String desc);

}
