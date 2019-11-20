package cn.e3mall.portalService.Impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.portalService.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        // 1、取查询参数id，parentId
        // 2、根据parentId查询tb_content_category，查询子节点列表。
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        //3、得到List<TbContentCategory>
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        // 4、把列表转换成List<EasyUITreeNode>
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getName());
            node.setText(tbContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public E3Result addContentCategory(long parenId, String name) {
        // 1、接收两个参数：parentId、name
        // 2、向tb_content_category表中插入数据。
        // a)创建一个TbContentCategory对象
        TbContentCategory contentCategory = new TbContentCategory();
        // b)补全TbContentCategory对象的属性
        contentCategory.setParentId(parenId);
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        contentCategory.setSortOrder(1);
        //状态。可选值:1(正常),2(删除)
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        // c)向tb_content_category表中插入数据
        contentCategoryMapper.insert(contentCategory);
        // 3、判断父节点的isparent是否为true，不是true需要改为true。
        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parenId);
        if(!parentNode.getIsParent()){
            parentNode.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        // 4、需要主键返回。
        // 5、返回E3Result，其中包装TbContentCategory对象
        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result upContentCategory(Long id, String name) {
        //1.查询TbContentCategory
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //2.修改name
        contentCategory.setName(name);
        //3.执行更新
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        // 5、返回E3Result，其中包装TbContentCategory对象
        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result deleteContentCategory(Long parentId, Long id) {
        //0.根据id获取TbContentCategory
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //1.根据id删除
        contentCategoryMapper.deleteByPrimaryKey(id);
        //2.判断父节点下是否还有子节点,如果没有需要把父节点的isparent改为false
        //a.设置查询条件
        TbContentCategoryExample example1 = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andParentIdEqualTo(parentId);
        //b.查询
        List<TbContentCategory> list1 = contentCategoryMapper.selectByExample(example1);
        if(list1.size() == 0){
            //c.查询父节点
            TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
            //d.设置isParent
            parentNode.setIsParent(false);
            //e.更新
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        //3.判断是否是父节点，是则删除其下的子节点
        if(contentCategory.getIsParent()){
            //设置查询条件
            TbContentCategoryExample example2 = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andParentIdEqualTo(id);
            //查询
            List<TbContentCategory> list2 = contentCategoryMapper.selectByExample(example2);
            for(TbContentCategory tbContentCategory : list2){
                //根据查询结果删除子节点
                contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
            }
        }
        return E3Result.ok(contentCategory);
    }
}
