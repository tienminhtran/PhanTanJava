package entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@Embeddable
public class Approval implements Serializable {


    private LocalDate approvalDate;

    @Enumerated(EnumType.STRING)
    private Status status;


}
