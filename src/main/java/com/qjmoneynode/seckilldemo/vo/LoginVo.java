package com.qjmoneynode.seckilldemo.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author qjmoneynode
 * @version 1.0
 * @createTime 2023-07-11  13:21
 * @description TODO
 */
@Data
public class LoginVo {

    @NotNull
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
