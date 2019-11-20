package cn.e3mall.portalController;

import cn.e3mall.common.pojo.EasyUIResult;
import cn.e3mall.portalService.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentManagerController {

    @Autowired
    private ContentManagerService contentManagerService;

    @RequestMapping("/query/list")
    @ResponseBody
    public List<EasyUIResult> getContenList(Long categoryId){
        List<EasyUIResult> results = contentManagerService.getContentList(categoryId);
        return  results;
    }

}
