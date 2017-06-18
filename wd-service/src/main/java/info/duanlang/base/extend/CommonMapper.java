package info.duanlang.base.extend;

import tk.mybatis.mapper.common.Mapper;

/**
 * 扩展通用Mapper
 * 
 * @author lzf
 * @param <T> 泛型
 */
public interface CommonMapper<T> extends Mapper<T>, BatchInsertMapper<T>, BatchUpdateMapper<T> {

}
