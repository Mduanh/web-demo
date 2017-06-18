package info.duanlang.base.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import info.duanlang.base.utils.JSONUtil;

import java.io.Serializable;

@JsonFilter("DefaultValuePropertyFilter") //该注解只生效于APP接口
public class BasicBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSONUtil.toJson(this);
    }
}
