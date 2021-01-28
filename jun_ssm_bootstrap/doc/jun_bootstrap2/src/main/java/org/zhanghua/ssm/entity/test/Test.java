package org.zhanghua.ssm.entity.test;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 测试实体类
 * 
 * @author Wujun
 * 
 */
public class Test extends DataEntity<Test> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "名称不能为空")
	@Length(max = 50, message = "名称长度不超过50个字符")
	private String name;

	@Length(min = 0, max = 50, message = "最大可以输入50个字符")
	private String remark;

	public Test() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
