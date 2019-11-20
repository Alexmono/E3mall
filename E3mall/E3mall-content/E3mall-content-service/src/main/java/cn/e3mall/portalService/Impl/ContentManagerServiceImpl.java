package cn.e3mall.portalService.Impl;

import cn.e3mall.common.pojo.EasyUIResult;

import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.portalService.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContentManagerServiceImpl implements ContentManagerService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public List<EasyUIResult> getContentList(Long categoryId) {
        return null;
    }

    @Override
    public List<TbContent> getContentListByCid(Long content_lunbo_id) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(content_lunbo_id);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }
}
