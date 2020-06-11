package com.treehole.demo.cache;

import com.treehole.demo.entity.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {

    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO type1 = new TagDTO();
        type1.setCategoryName("学习交流");
        type1.setTags(Arrays.asList("问题询问","学习笔记","学习打卡","踩坑记录"));
        tagDTOS.add(type1);

        TagDTO type2 = new TagDTO();
        type2.setCategoryName("日常吐槽");
        type2.setTags(Arrays.asList("日常吐槽","生活琐事","心情分享"));
        tagDTOS.add(type2);

        TagDTO type3 = new TagDTO();
        type3.setCategoryName("信息发布");
        type3.setTags(Arrays.asList("信息发布","广告信息"));
        tagDTOS.add(type3);

        TagDTO type4 = new TagDTO();
        type4.setCategoryName("经验分享");
        type4.setTags(Arrays.asList("经验分享","好物推荐"));
        tagDTOS.add(type4);

        return tagDTOS;
    }

    //判断标签是否在库中
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",|，");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
