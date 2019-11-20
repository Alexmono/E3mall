package cn.e3mall.service.Impl;

import cn.e3mall.common.pojo.EasyUIResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

import java.util.Date;
import java.util.List;


/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem getItemById(long itemId) {
		//根据主键查询
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return  tbItem;
		/*TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;*/
	}

	@Override
	public EasyUIResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//创建返回结果对象
		EasyUIResult result = new EasyUIResult();

		//打印查看pageInfo信息
		System.out.println(pageInfo.toString());

		result.setTotal((int) pageInfo.getTotal());
		result.setRows(list);

		return result;
	}

	@Override
	public E3Result addItem(TbItem item, String desc) {
		//1.生成商品id
		long id = IDUtils.genItemId();
		//补全TbItem属性
		item.setId(id);
		//商品状态(1-正常,2-下架,3-删除)
		item.setStatus((byte)1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//3.向商品表插入数据
		itemMapper.insert(item);
		//4.创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		//5.补全TbItemDesc属性
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//6.向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//7.E3Result.ok()
		return E3Result.ok();
	}

}
