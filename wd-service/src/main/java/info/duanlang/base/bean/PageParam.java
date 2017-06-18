package info.duanlang.base.bean;

/**
 * 分页请求对象 可以被继承</br> 如果Mapper接口直接传递该对象 也会直接触发分页 </br>
 * Mapper请求参数只要包含pageNum和pageSize都会触发自动分页
 * 
 * @author lzf
 */
public class PageParam {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

    public PageParam() {
        // TODO Auto-generated constructor stub
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {

        if (pageNum <= 1)
            pageNum = 1;
        return pageNum;
    }

    /**
     * 为兼容页面请求， 不支持SET NULL值， SET NULL值使用 setPageNumNull方法
     * 
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        if (pageNum == null)
            return;
        this.pageNum = pageNum;
    }

    public void setPageNumNull() {
        this.pageNum = null;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 为兼容页面请求， 不支持SET NULL值， SET NULL值使用 setPageSizeNull方法
     * 
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null)
            return;
        this.pageSize = pageSize;
    }

    public void setPageSizeNull() {
        this.pageSize = null;
    }
}
