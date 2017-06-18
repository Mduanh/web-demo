package info.duanlang.base.bean;


import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页对象
 * 调用过PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
 * 才可以使用该对象
 * @author lzf
 * @param <B> 业务对象
 */
public class PageInfo<B> extends BasicBean {

    private static final long serialVersionUID = -8340505498216961384L;

    /**
     * 页码，从1开始
     */
    private int currentPage;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 总数
     */
    private long totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 数据集合
     */
    private List<B> resultList;
    
    public PageInfo() {
        
    }
    
    /**
     * 复制对象
     * @param pageInfo
     */
    public PageInfo(PageInfo<?> pageInfo, List<B> resultList) {
        this.currentPage = pageInfo.getCurrentPage();
        this.pageSize = pageInfo.getPageSize();
        this.totalPage = pageInfo.getTotalPage();
        this.totalCount = pageInfo.getTotalCount();
        this.resultList = resultList;
    }

    /**
     * 如果只是调用这个  确定Page<?>的?为B类型
     * @param page (Page<?>)list
     */
    public PageInfo(List<?> page) {
        init((Page<?>) page, true);
    }

    /**
     * 当Page<?> 的 ?不是B类型时 调用这个
     * @param page list
     * @param resultList list > BeanUtil > List<B> 
     */
    public PageInfo(List<?> page, List<B> resultList) {
        this.resultList = resultList;
        init((Page<?>) page, false);
    }

    
    @SuppressWarnings("unchecked")
    public void init(Page<?> page, boolean usePage) {
        this.currentPage = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalPage = page.getPages();
        this.totalCount = page.getTotal();
        
        if(usePage) 
            this.resultList = (List<B>) page;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<B> getResultList() {
        return resultList;
    }

    public void setResultList(List<B> resultList) {
        this.resultList = resultList;
    }
}
