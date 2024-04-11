/*
 * @ {#} OfficeAssignment.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
public class OfficeAssignment {
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    //Tạo  mối quan hệ 1-1 với Instructor
    @OneToOne
    @Id
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;

    public OfficeAssignment() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OfficeAssignment{" +
                "location='" + location + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
