package cc.mrbird.febs.system.dao;

import cc.mrbird.febs.common.annotation.DataFilter;
import cc.mrbird.febs.system.domain.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
@DataFilter(filterMethods={"selectPage"})
public interface CityMapper extends BaseMapper<City> {
}
