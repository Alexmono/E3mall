package cn.e3mall.portalController;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.portalService.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(
            @RequestParam(value = "id", defaultValue = "0") long parentId) {
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public E3Result createCategory(Long parentId, String name){
        E3Result result = contentCategoryService.addContentCategory(parentId,name);
        return result;
    }

    @RequestMapping("/updata")
    @ResponseBody
    public E3Result updataCategory(Long id, String name){
        E3Result result = contentCategoryService.upContentCategory(id, name);
        return  result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public E3Result deleteCategory(Long parentId,Long id){
        E3Result result = contentCategoryService.deleteContentCategory(parentId, id);
        return result;
    }

}
