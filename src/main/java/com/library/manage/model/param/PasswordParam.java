package com.library.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordParam {

    @NotBlank(message = "不能为空")
    @JsonProperty("oldpsw")
    private String oldPwd;

    @NotBlank(message = "不能为空")
    @Size(min = 6, max = 20, message = "密码长度为{min}-{max}")
    @JsonProperty("newpsw")
    private String newPwd;

    @NotBlank
    private String id;
}
