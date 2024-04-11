/**
 * @ (#) Address.java      3/30/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/30/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String street;
    @Column(columnDefinition = "nvarchar(50)")
    private String city;
    @Column(columnDefinition = "nvarchar(10)")
    private String state;
    @Column(columnDefinition = "nvarchar(5)", name = "zip_code")
    private String zipCode;
}
