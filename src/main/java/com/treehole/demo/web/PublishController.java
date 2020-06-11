package com.treehole.demo.web;

import com.treehole.demo.cache.TagCache;
import com.treehole.demo.entity.Question;
import com.treehole.demo.entity.QuestionVo;
import com.treehole.demo.entity.User;
import com.treehole.demo.mapper.QuestionMapper;
import com.treehole.demo.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PublishController {


    @Autowired
    private QuestionService questionService;


    //编辑
    @RequestMapping("/publish/{id}")
    public String edit(
            @PathVariable(name = "id") Integer id,
            Model model
    ){
        QuestionVo question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @RequestMapping("/pub")
    public String publish( Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @RequestMapping("/publish")
    public String doPublish(
        @RequestParam(value = "title",required = false) String title,
        @RequestParam(value = "description",required = false) String description,
        @RequestParam(value = "tag",required = false) String tag,
        @RequestParam(value = "id",required = false) Integer id,
        HttpSession session,
        Model model
    ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        //判断标签是否合法
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入非法标签！ ------》’" + invalid + "'");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        //System.out.println(question);
        questionService.createOrUpdate(question);
        return "redirect:/index";

    }

}
