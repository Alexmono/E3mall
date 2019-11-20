package cn.e3mall.portalController;


import cn.e3mall.pojo.TbContent;
import cn.e3mall.portalService.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {

    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @Autowired
    private ContentManagerService contentManagerService;

    @RequestMapping("/showIndex")
    public String showIndex(Model model){
        List<TbContent> list = contentManagerService.getContentListByCid(CONTENT_LUNBO_ID);
        model.addAttribute(list);
        return "index";
    }
}
