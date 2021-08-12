package cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.log;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.adminserver.modules.infra.enums.InfDictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 定时任务 Excel VO
 *
 * @author 芋艿
 */
@Data
public class InfJobLogExcelVO {

    @ExcelProperty("日志编号")
    private Long id;

    @ExcelProperty("任务编号")
    private Long jobId;

    @ExcelProperty("处理器的名字")
    private String handlerName;

    @ExcelProperty("处理器的参数")
    private String handlerParam;

    @ExcelProperty("第几次执行")
    private Integer executeIndex;

    @ExcelProperty("开始执行时间")
    private Date beginTime;

    @ExcelProperty("结束执行时间")
    private Date endTime;

    @ExcelProperty("执行时长")
    private Integer duration;

    @ExcelProperty(value = "任务状态", converter = DictConvert.class)
    @DictFormat(InfDictTypeConstants.JOB_STATUS)
    private Integer status;

    @ExcelProperty("结果数据")
    private String result;

    @ExcelProperty("创建时间")
    private Date createTime;

}
