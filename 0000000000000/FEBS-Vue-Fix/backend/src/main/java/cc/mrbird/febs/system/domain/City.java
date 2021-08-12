package cc.mrbird.febs.system.domain;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("city")
@Excel("城市信息表")
public class City implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @TableId(value = "CITY_ID", type = IdType.AUTO)
    private Long  cityId;
    /**
     *  城市名称
     */
    @ExcelField(value = "城市名称", required = true, maxLength = 20,
            comment = "提示：必填，长度不能超过20个字符")
    @NotBlank(message = "{required}")
    private String cityName;
    /**
     *  城市简介
     */
    @ExcelField(value = "城市简介",maxLength = 255,
            comment = "提示：长度不能超过255个字符")
    private String introduce;
    /**
     *  经度
     */
    @ExcelField(value = "经度",maxLength = 11, regularExp = "[0-9]+.*[0-9]*",
            regularExpMessage = "必须是数字", comment = "提示: 只能填写数字，并且长度不能超过11位")
    private String longitude;
    /**
     *  纬度
     */
    @ExcelField(value = "纬度",maxLength = 11, regularExp = "[0-9]+.*[0-9]*",
            regularExpMessage = "必须是数字", comment = "提示: 只能填写数字，并且长度不能超过11位")
    private String latitude;


    private Date createTime;

    private Long creator;
}
