package info.duanlang.base.service;


import info.duanlang.base.bean.PageInfo;
import info.duanlang.base.bean.PageParam;

import java.util.List;


/**
 * 通用Service
 * @author lzf
 * @param <B> 业务对象
 */
public interface BaseService<B> {

    /**
     * 通用保存
     * @param businessObj 业务对象
     * @return 大于0成功
     */
    public int commonSave(B businessObj);
    
    /**
     * 通用批量保存
     * @param businessObjs 业务对象集合
     * @return 大于0成功
     */
    public int commonBatchSave(List<B> businessObjs);
    
    /**
     * 通用按主键删除
     * @param key 主键
     * @return 大于0成功
     */
    public int commonDeleteByPrimaryKey(Object key);
    
    /**
     * 通用根据Example条件删除数据【只限于Service之间调用】
     * @param example Example条件，例如{@link tk.mybatis.mapper.entity.Example}
     * @return 大于0成功
     */
    public int commonDeleteByExample(Object example);
    
    /**
     * 通用根据主键更新属性不为null的值
     * @param businessObj 业务对象
     * @return 大于0成功
     */
    public int commonUpdateByPrimaryKeySelective(B businessObj);
    
    /**
     * 通用批量根据主键更新属性不为null的值
     * @param businessObjs 业务对象集合
     * @return 大于0成功，该返回值不准，因使用的多SQL执行
     */
    public int commonBatchUpdateByPrimaryKeySelective(List<B> businessObjs);

    /**
     * 通用根据Example条件更新实体`businessObj`包含的不是null的属性值【只限于Service之间调用】
     * @param businessObj 业务对象
     * @param example Example条件，例如{@link tk.mybatis.mapper.entity.Example}
     * @return 大于0成功
     */
    public int commonUpdateByExampleSelective(B businessObj, Object example);

    /**
     * 通用查询全部结果
     * @return List<B>
     */
    public List<B> commonSelectAll();

    
    /**
     * 通用根据主键字段进行查询
     * @param key 主键
     * @return B
     */
    public B commonSelectByPrimaryKey(Object key);

    /**
     * 通用根据业务对象中的属性值进行查询，查询条件使用等号
     * @param businessObj 业务对象
     * @return List<B>
     */
    public List<B> commonSelect(B businessObj);
    
    /**
     * 通用根据业务对象中的属性值进行查询，查询条件使用等号
     * @param businessObj 业务对象
     * @return 返回符合条件的第一条记录，如果不存在，则返回null
     */
    public B commonSelectFirst(B businessObj);

    /**
     * 通用根据Example条件进行查询【只限于Service之间调用】
     * @param example Example条件，例如{@link tk.mybatis.mapper.entity.Example}
     * @return List<B>
     */
    public List<B> commonSelectByExample(Object example);

    /**
     * 通用根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
     * @param businessObj 业务对象
     * @return B
     */
    public B commonSelectOne(B businessObj);

    /**
     * 通用分页查询全部结果
     * @param pageParam 分页参数
     * @return PageInfo<B>
     */
    public PageInfo<B> commonSelectAllByPage(PageParam pageParam);

    /**
     * 通用分页根据业务对象中的属性值进行查询，查询条件使用等号
     * @param businessObj 业务对象
     * @param pageParam 分页参数
     * @return PageInfo<B>
     */
    public PageInfo<B> commonSelectByPage(B businessObj, PageParam pageParam);

    /**
     * 通用分页根据Example条件进行查询【只限于Service之间调用】
     * @param example Example条件，例如{@link tk.mybatis.mapper.entity.Example}
     * @param pageParam 分页参数
     * @return PageInfo<B>
     */
    public PageInfo<B> commonSelectPageByExample(Object example, PageParam pageParam);

    /**
     * 通用根据业务对象中的属性值进行查询COUNT数，查询条件使用等号
     * @param businessObj 业务对象
     * @return 记录数
     */
    public int selectCount(B businessObj);

    /**
     * 通用根据Example条件进行查询Count数
     * @param example 条件
     * @return 记录数
     */
    public int selectCountByExample(Object example);

    /**
     * 通用根据主键更新属性, null值也会更新
     * @param businessObj 业务对象集合
     * @return 大于0成功
     */
    public int commonUpdateByPrimaryKey(B businessObj);
}
