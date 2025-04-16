package com.waypost.waypost.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointTypeHandler extends BaseTypeHandler<Geometry> {
    private final WKTReader reader = new WKTReader();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Geometry parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.toText());
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Geometry getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String pointText = rs.getString(columnName);
        if (pointText != null) {
            try {
                return reader.read(pointText);
            } catch (ParseException e) {
                throw new SQLException("Failed to parse POINT from: " + pointText, e);
            }
        }
        return null;
    }

    @Override
    public Geometry getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String pointText = rs.getString(columnIndex);
        if (pointText != null) {
            try {
                return reader.read(pointText);
            } catch (ParseException e) {
                throw new SQLException("Failed to parse POINT from: " + pointText, e);
            }
        }
        return null;
    }

    @Override
    public Geometry getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String pointText = cs.getString(columnIndex);
        if (pointText != null) {
            try {
                return reader.read(pointText);
            } catch (ParseException e) {
                throw new SQLException("Failed to parse POINT from: " + pointText, e);
            }
        }
        return null;
    }
}
