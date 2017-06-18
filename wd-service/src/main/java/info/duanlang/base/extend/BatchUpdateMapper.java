package info.duanlang.base.extend;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 批量修改
 * @author lzf
 * @param <T> 对象
 */
public interface BatchUpdateMapper<T> {

    /**
     * 批量修改实体，null的属性也会保存，不会使用数据库默认值, 每个实体必须有id
     *
     * @return
     */
    @UpdateProvider(type = BatchUpdateProvider.class, method = "dynamicSQL")
    int batchUpdateByPrimaryKeySelective(List<T> list);
}
