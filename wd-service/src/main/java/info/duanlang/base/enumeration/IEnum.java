package info.duanlang.base.enumeration;

/**
 * 业务枚举都需要遵守的接口
 * @author lzf
 */
public interface IEnum {

    /**
     * 获取枚举值
     * @return 枚举值
     */
    public int getId();
    
    /**
     * 获取枚举文本信息
     * @return 枚举文本信息
     */
    public String getText();
}
