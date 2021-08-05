package com.library.manage.model.dto;

import com.library.manage.model.enums.Sex;
import com.library.manage.model.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class StudentDTO {

    private String name;

    private String stuno;

    private Sex sex;

    private UserStatus status;

    private String email;
}
