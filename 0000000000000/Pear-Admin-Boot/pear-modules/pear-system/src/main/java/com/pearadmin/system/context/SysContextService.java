package com.pearadmin.system.context;

import com.pearadmin.system.domain.SysLog;
import com.pearadmin.system.service.ISysLogService;
import com.pearadmin.common.plugin.system.domain.*;
import com.pearadmin.common.plugin.system.service.SysContext;
import com.pearadmin.system.domain.*;
import com.pearadmin.system.mapper.*;
import com.pearadmin.system.service.ISysDictDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 对外开放的公用服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SysContextService implements SysContext {

    @Resource
    private ISysLogService sysLogService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysPowerMapper sysPowerMapper;
    @Resource
    private SysConfigMapper sysConfigMapper;
    @Resource
    private ISysDictDataService iSysDictDataService;
    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public SysBaseUser getUserByName(String username) {
        SysUser sysUser= sysUserMapper.selectByUsername(username);
        SysBaseUser sysUserModel=null;
        if(sysUser!=null) {
            sysUserModel = new SysBaseUser();
            BeanUtils.copyProperties(sysUser, sysUserModel);
            List<SysPower> powerList = sysPowerMapper.selectByUsername(username);
            if(powerList!=null&&powerList.size()>0){
                List<SysBasePower> sysPowerModelList=new ArrayList<>();
                for(SysPower sysPower:powerList){
                    try{
                        SysBasePower sysPowerModel = new SysBasePower();
                        BeanUtils.copyProperties(sysPower, sysPowerModel);
                        sysPowerModelList.add(sysPowerModel);
                    }catch (Exception ignored){

                    }
                }
                sysUserModel.setPowerList(sysPowerModelList);
            }
        }
        return sysUserModel;
    }

    @Override
    public SysBaseUser getUserById(String id) {
        SysUser sysUser= sysUserMapper.selectById(id);
        SysBaseUser sysUserModel=null;
        if(sysUser!=null) {
            sysUserModel = new SysBaseUser();
            BeanUtils.copyProperties(sysUser, sysUserModel);
        }
        return sysUserModel;
    }

    @Override
    public List<SysBaseRole> getRolesByUsername(String username) {
        List<SysRole>  roles=  sysRoleMapper.selectByUsername(username);
        List<SysBaseRole> sysRoleModelList=new ArrayList<>();
        if(roles!=null&&roles.size()>0){
            for(SysRole sysRole:roles){
                try{
                    SysBaseRole sysRoleModel = new SysBaseRole();
                    BeanUtils.copyProperties(sysRole, sysRoleModel);
                    sysRoleModelList.add(sysRoleModel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return sysRoleModelList;
    }

    @Override
    public List<SysBaseDict> selectDictByCode(String typeCode) {
        List<SysDictData>  sysDictDataList= iSysDictDataService.selectByCode(typeCode);
        return buildSysDictDataModel(sysDictDataList);
    }

    @Override
    public List<SysBaseDict> queryTableDictItemsByCode(String table, String text, String code) {
        return buildSysDictDataModel(sysDictDataMapper.queryTableDictItemsByCode(table,text,code));
    }

    @Override
    public List<SysBaseDict> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql) {
        return buildSysDictDataModel(sysDictDataMapper.queryTableDictItemsByCodeAndFilter(table,text,code,filterSql));
    }

    @Override
    public List<SysBaseDict>  queryTableDictByKeys(String table, String text, String code, String[] keyArray) {
        return buildSysDictDataModel(sysDictDataMapper.queryTableDictByKeys(table,text,code,keyArray));
    }

    private  List<SysBaseDict> buildSysDictDataModel(List<SysDictData>  sysDictDataList){
        List<SysBaseDict> sysDictDataModelList=new ArrayList<>();
        if(sysDictDataList!=null&&sysDictDataList.size()>0){
            for(SysDictData sysDictData:sysDictDataList){
                try{
                    SysBaseDict sysDictDataModel = new SysBaseDict();
                    BeanUtils.copyProperties(sysDictData, sysDictDataModel);
                    sysDictDataModelList.add(sysDictDataModel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return sysDictDataModelList;
    }

    @Override
    public String getConfig(String code) {
        SysConfig config = sysConfigMapper.selectByCode(code);
        return config!=null?config.getConfigValue():"";
    }

    @Override
    public Boolean saveLog(SysBaseLog baseLog) {
        SysLog log = new SysLog();
        BeanUtils.copyProperties(baseLog,log);
        return sysLogService.save(log);
    }
}
