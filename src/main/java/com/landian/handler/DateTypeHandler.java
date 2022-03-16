package com.landian.handler;

import com.landian.domain.Question;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateTypeHandler extends BaseTypeHandler<Question> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Question question, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Question getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Question getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Question getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
