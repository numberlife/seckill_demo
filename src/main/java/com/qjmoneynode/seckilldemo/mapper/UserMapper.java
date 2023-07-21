package com.qjmoneynode.seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qjmoneynode.seckilldemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
