package com.pierceecom.entities;

import com.pierceecom.general.abstracts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlType;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@XmlType(name="")
@Entity
public class PostEntity extends AbstractEntity {

    private String title;

    @Lob
    private String content;

    public PostEntity() {
    }

    public PostEntity(String content) {
        this.content = content;
    }

    //<editor-fold desc="getters and setters">
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //</editor-fold>
}
