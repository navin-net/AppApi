package org.example.appapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
public class BaseEntity {

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

}
