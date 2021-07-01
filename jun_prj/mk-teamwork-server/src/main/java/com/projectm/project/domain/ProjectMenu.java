package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.framework.common.utils.StringUtils;
import com.projectm.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.thymeleaf.util.ListUtils;

import java.io.Serializable;
import java.util.List;

@TableName("team_project_menu")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMenu  extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    private String title;
    private String icon;
    private String url;
    private String file_path;
    private String params;
    private String node;
    private Integer sort;
    private Integer status;
    private Integer create_by;
    private String create_at;

    @TableField("is_inner")
    private Integer isinner;
    @TableField(exist = false)
    private boolean is_inner;

    private String _values;
    @TableField(exist = false)
    private String values;

    @TableField("show_slider")
    private Integer showslider;
    @TableField(exist = false)
    private boolean show_slider;

    @TableField(exist = false)
    private String statusText;
    @TableField(exist = false)
    private String innerText;
    @TableField(exist = false)
    private String fullUrl;

    @TableField(exist = false)
    private List<ProjectMenu> children;

    public List<ProjectMenu> getChildren(){
        if(ListUtils.isEmpty(children)){
            return null;
        }
        return  children;
    }

    public boolean getIs_inner(){
        if(isinner == 0){
            return false;
        }else{
            return true;
        }
    }

    public String getStatusText(){
        if(1 == status)return "使用中";
        else if(0 == status) return "禁用";
        else return "";
    }
    public String getInnerText(){
        if(1 == isinner)return "内页";
        else if(0 == isinner) return "导航";
        else return "";
    }
    public String getFullUrl(){
        //if((null != params && null != values) || !"".equals(values)){
        if(StringUtils.isNotEmpty(params) && StringUtils.isNotEmpty(values)){
            return url+"/"+values;
        }
        return url;
    }
}
