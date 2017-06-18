package info.duanlang.base.bean;

/**
 * 排序参数
 * @author huyong
 *
 */
public class SortParam {

	private String field;
	private boolean ascending;
	
	
	public String getField() {
		return field;
	}
	
	/**
	 * 设置排序的字段名称
	 * @param sortField
	 */
	public void setField(String sortField) {
		this.field = sortField;
	}
	public boolean isAscending() {
		return ascending;
	}
	
	/**
	 * 是否升序
	 * @param isAscending
	 */
	public void setAscending(boolean isAscending) {
		this.ascending = isAscending;
	}
	
	public SortParam() {
        // TODO Auto-generated constructor stub
    }
	
	public SortParam(String sortField, boolean isAscending){
	    setField(sortField);
	    setAscending(isAscending);
	}
}
