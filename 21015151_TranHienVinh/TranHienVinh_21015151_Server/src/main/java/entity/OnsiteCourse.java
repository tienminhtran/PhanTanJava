/*
 * @ {#} OnsiteCourse.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Days")
    private String days;
    @Column(name = "Location")
    private String location;
    @Column(name = "Time")
    private LocalDateTime time;

    public OnsiteCourse() {
    }

    public OnsiteCourse(String title, int credits, Department department, String days, String location, LocalDateTime time) {
        super(title, credits, department);
        this.days = days;
        this.location = location;
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OnsiteCourse{" +
                "days='" + days + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}
