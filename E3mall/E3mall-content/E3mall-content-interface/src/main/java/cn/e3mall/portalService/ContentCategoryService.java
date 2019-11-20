package cn.e3mall.portalService;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(long parentId);

    E3Result addContentCategory(long parenId, String name);

    E3Result upContentCategory(Long id, String name);

    E3Result deleteContentCategory(Long parentId, Long id);
}
