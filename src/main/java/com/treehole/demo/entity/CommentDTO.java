package com.treehole.demo.entity;

import lombok.Data;

@Data
public class CommentDTO {

    private Integer id;
    private Integer parentId;
    private Integer type;
    private String commentator;
    private Long gmtCreate;
    private Integer likeCount;
    private String content;
    private Long gmtModified;
    private Integer commentCount;

    private User user;

}
