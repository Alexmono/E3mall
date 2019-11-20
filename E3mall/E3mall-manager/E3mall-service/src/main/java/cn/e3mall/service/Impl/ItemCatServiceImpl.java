package cn.e3mall.service.Impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {
        //1.根据pid查询节点
        TbItemCatExample example = new TbItemCatExample();
        //设置查询节点
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //2.转换EasyUITreeNode列表
        List<EasyUITreeNode> resultlist = new ArrayList<>();
        for(TbItemCat tbItemCat:list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultlist.add(node);
        }
        return resultlist;
    }
}
