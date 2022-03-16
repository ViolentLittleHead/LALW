package com.landian.mapper;

import com.landian.domain.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin login(@Param("adminname")String adminname, @Param("password")String password);
}
