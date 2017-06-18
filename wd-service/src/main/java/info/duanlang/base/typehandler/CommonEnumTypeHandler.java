package info.duanlang.base.typehandler;

import info.duanlang.base.enumeration.IEnum;
import info.duanlang.base.utils.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通用枚举映射转换 在@MappedTypes追加枚举即可自动数据库映射转换
 *
 * @param <E> 需要转换的枚举
 * @author lzf
 */
@MappedTypes({})
public class CommonEnumTypeHandler<E extends IEnum> extends BaseTypeHandler<E> {

    private Class<E> type;

    public CommonEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull())
            return null;
        else
            return EnumUtil.convert(type, i);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull())
            return null;
        else
            return EnumUtil.convert(type, i);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull())
            return null;
        else
            return EnumUtil.convert(type, i);
    }
}
