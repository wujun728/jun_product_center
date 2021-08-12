package cc.mrbird.febs.web.controller;

import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.system.dao.CityMapper;
import cc.mrbird.febs.system.domain.City;
import cc.mrbird.febs.system.domain.Test;
import cc.mrbird.febs.system.service.CityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service("cityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Value("${febs.max.batch.insert.num}")
    private int batchInsertMaxNum;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public IPage<City> findCitys(QueryRequest request, City city) {
        try {
            LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper<>();


            if (StringUtils.isNotBlank(city.getCityName())) {
                queryWrapper.like(City::getCityName, city.getCityName());
            }
            if (StringUtils.isNotBlank(city.getIntroduce())) {
                queryWrapper.like(City::getIntroduce, city.getIntroduce());
            }

            Page<City> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取城市信息失败", e);
            return null;
        }
    }

    @Override
    public List<City> findCityList(QueryRequest request, City city) {
        LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper<>();


        if (StringUtils.isNotBlank(city.getCityName())) {
            queryWrapper.like(City::getCityName, city.getCityName());
        }
        if (StringUtils.isNotBlank(city.getIntroduce())) {
            queryWrapper.like(City::getIntroduce, city.getIntroduce());
        }
        List<City> cityList=cityMapper.selectList(queryWrapper);
        return cityList;
    }

    @Override
    public List<City> selectListByIds(List idsList) {
        return cityMapper.selectBatchIds(idsList);
    }

    @Override
    @Transactional
    public void createCity(City city) {
        city.setCreator(FebsUtil.getCurrentUser().getUserId());
        this.save(city);
    }

    @Override
    @Transactional
    public void updateCity(City city) {
        this.baseMapper.updateById(city);
    }

    @Override
    @Transactional
    public void deleteCitys(String[] cityIds) {
        List<String> list = Arrays.asList(cityIds);
        this.baseMapper.deleteBatchIds(list);
    }

    @Override
    @Transactional
    public void batchInsert(List<City> list) {
        int total = list.size();
        int max = batchInsertMaxNum;
        int count = total / max;
        int left = total % max;
        int length;
        if (left == 0) length = count;
        else length = count + 1;
        for (int i = 0; i < length; i++) {
            int start = max * i;
            int end = max * (i + 1);
            if (i != count) {
                log.info("正在插入第" + (start + 1) + " ~ " + end + "条记录 ······");
                saveBatch(list, end);
            } else {
                end = total;
                log.info("正在插入第" + (start + 1) + " ~ " + end + "条记录 ······");
                saveBatch(list, end);
            }
        }
    }
}
