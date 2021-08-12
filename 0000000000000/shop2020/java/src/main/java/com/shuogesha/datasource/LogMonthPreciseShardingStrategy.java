package com.shuogesha.datasource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import com.google.common.collect.Range;

/**
 * 按照日期分表
 * 
 * @author zhaohaiyuan
 *
 */
 
public class LogMonthPreciseShardingStrategy
		implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

	DateTimeFormatter formFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("yyyyMM");

	@Override
	public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
		return preciseShardingValue.getLogicTableName() + "_"
				+ preciseShardingValue.getValue().toString().substring(0, 7).replaceAll("-", "");
	}

	@Override
	public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
		List<String> list = new ArrayList<>();
		Range<String> ranges = rangeShardingValue.getValueRange();
		LocalDateTime startTime = LocalDateTime.parse(ranges.lowerEndpoint(), formFormatter);
		LocalDateTime endTime = LocalDateTime.parse(ranges.upperEndpoint(), formFormatter);
		while (startTime.isBefore(endTime)) {
			list.add(rangeShardingValue.getLogicTableName() + "_" + startTime.format(toFormatter));
			startTime = startTime.plusMonths(1);
		}
		return list;
	}
}
