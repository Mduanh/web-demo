package info.duanlang.base.extend;

import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 批量保存
 * @author lzf
 * @param <T> 对象
 */
public interface BatchInsertMapper<T> {

    /**
     * 批量保存实体，null的属性也会保存，不会使用数据库默认值
     *
     * @return
     */
    @InsertProvider(type = BatchInsertProvider.class, method = "dynamicSQL")
    int batchInsert(List<T> list);
}
