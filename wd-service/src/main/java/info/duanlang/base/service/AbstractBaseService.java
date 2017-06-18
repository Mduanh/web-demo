package info.duanlang.base.service;

import com.github.pagehelper.PageHelper;
import info.duanlang.base.bean.PageInfo;
import info.duanlang.base.bean.PageParam;
import info.duanlang.base.extend.CommonMapper;
import info.duanlang.base.utils.BeanUtil;
import info.duanlang.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通用Service
 * 
 * @author lzf
 * @param <T> Entry对象
 * @param <B> 业务对象
 */
@Service
public abstract class AbstractBaseService<T, B> implements BaseService<B> {

    @SuppressWarnings("unchecked")
    protected Class<T> entryClass = (Class<T>) TypeUtil.getTypeArguments(AbstractBaseService.class, getClass()).get(0);

    @SuppressWarnings("unchecked")
    protected Class<B> businessObjClass = (Class<B>) TypeUtil.getTypeArguments(AbstractBaseService.class, getClass()).get(1);

    @Autowired
    protected CommonMapper<T> commonMapper;

    /**
     * 获取Entry对象
     * 
     * @return T
     */
    protected T getEntry() {
        try {
            return entryClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取业务对象
     * 
     * @return B
     */
    protected B getBusinessObject() {
        try {
            return businessObjClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int commonSave(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return 0;
        return commonMapper.insert(t);
    }

    @Override
    public int commonBatchSave(List<B> businessObjs) {
        List<T> t = BeanUtil.copyListProperties(businessObjs, new ArrayList<T>(), entryClass);
        if (t == null)
            return 0;
        return commonMapper.batchInsert(t);
    }

    @Override
    public int commonDeleteByPrimaryKey(Object key) {
        return commonMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int commonDeleteByExample(Object example) {
        return commonMapper.deleteByExample(example);
    }

    @Override
    public int commonUpdateByPrimaryKeySelective(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return 0;
        return commonMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int commonBatchUpdateByPrimaryKeySelective(List<B> businessObjs) {
        List<T> t = BeanUtil.copyListProperties(businessObjs, new ArrayList<T>(), entryClass);
        if (t == null)
            return 0;
        return commonMapper.batchUpdateByPrimaryKeySelective(t);
    }

    @Override
    public int commonUpdateByExampleSelective(B businessObj, Object example) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return 0;
        return commonMapper.updateByExampleSelective(t, example);
    }

    @Override
    public List<B> commonSelectAll() {
        List<T> list = commonMapper.selectAll();
        return BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass);
    }

    @Override
    public B commonSelectByPrimaryKey(Object key) {
        T t = commonMapper.selectByPrimaryKey(key);
        return BeanUtil.copyProperties(t, getBusinessObject());
    }

    @Override
    public List<B> commonSelect(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return null;
        List<T> list = commonMapper.select(t);
        return BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass);
    }
    
    @Override
    public B commonSelectFirst(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return null;
        List<T> list = commonMapper.select(t);

        if(list==null||list.isEmpty())
            return null;
        else{
            return BeanUtil.copyProperties(list.get(0), getBusinessObject());
        }
    }

    @Override
    public List<B> commonSelectByExample(Object example) {
        List<T> list = commonMapper.selectByExample(example);
        return BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass);
    }

    @Override
    public B commonSelectOne(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return null;
        T returnT = commonMapper.selectOne(t);
        return BeanUtil.copyProperties(returnT, getBusinessObject());
    }

    @Override
    public PageInfo<B> commonSelectAllByPage(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<T> list = commonMapper.selectAll();
        return new PageInfo<B>(list, BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass));
    }

    @Override
    public PageInfo<B> commonSelectByPage(B businessObj, PageParam pageParam) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return null;
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<T> list = commonMapper.select(t);
        return new PageInfo<B>(list, BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass));
    }

    @Override
    public PageInfo<B> commonSelectPageByExample(Object example, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<T> list = commonMapper.selectByExample(example);
        return new PageInfo<B>(list, BeanUtil.copyListProperties(list, new ArrayList<B>(), businessObjClass));
    }
    
    @Override
    public int selectCount(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return 0;
        return commonMapper.selectCount(t);
    }

    @Override
    public int selectCountByExample(Object example) {
        return commonMapper.selectCountByExample(example);
    }
    
    @Override
    public int commonUpdateByPrimaryKey(B businessObj) {
        T t = BeanUtil.copyProperties(businessObj, getEntry());
        if (t == null)
            return 0;
        return commonMapper.updateByPrimaryKey(t);
    }
}
