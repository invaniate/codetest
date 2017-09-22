package com.pierceecom.blog.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@XmlType(name="")
@XmlRootElement
public class PostEntity {

    protected Long id;
    private String title;
    private String content;

    public PostEntity() {
    }

    public PostEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //<editor-fold desc="getters and setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //</editor-fold>
}
