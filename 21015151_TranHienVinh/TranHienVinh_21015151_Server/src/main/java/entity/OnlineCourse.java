/*
 * @ {#} OnlineCourse.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
public class OnlineCourse extends Course implements Serializable {
    @Column(name = "URL")
    private String url;
    public OnlineCourse() {
    }

    public OnlineCourse(String title, int credits, Department department, String url) {
        super(title, credits, department);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OnlineCourse{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}
