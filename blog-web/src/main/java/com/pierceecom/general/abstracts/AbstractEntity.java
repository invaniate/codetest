package com.pierceecom.general.abstracts;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@XmlRootElement
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    protected Long id;

    //<editor-fold desc="getters and setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //</editor-fold>
}
