package cn.e3mall.portalService;

import cn.e3mall.common.pojo.EasyUIResult;
import cn.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentManagerService {

    List<EasyUIResult> getContentList(Long categoryId);

    List<TbContent> getContentListByCid(Long content_lunbo_id);
}
