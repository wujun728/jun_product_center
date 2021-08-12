package cc.mrbird.febs.gateway.enhance.service.impl;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.constant.StringConstant;
import cc.mrbird.febs.common.core.utils.DateUtil;
import cc.mrbird.febs.gateway.enhance.entity.RouteLog;
import cc.mrbird.febs.gateway.enhance.mapper.RouteLogMapper;
import cc.mrbird.febs.gateway.enhance.service.RouteLogService;
import cc.mrbird.febs.gateway.enhance.utils.AddressUtil;
import cc.mrbird.febs.gateway.enhance.utils.PageableExecutionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author MrBird
 */
@Service
public class RouteLogServiceImpl implements RouteLogService {

    private RouteLogMapper routeLogMapper;
    private ReactiveMongoTemplate template;

    @Autowired(required = false)
    public void setRouteLogMapper(RouteLogMapper routeLogMapper) {
        this.routeLogMapper = routeLogMapper;
    }

    @Autowired(required = false)
    public void setTemplate(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<RouteLog> findAll() {
        return routeLogMapper.findAll();
    }

    @Override
    public Mono<RouteLog> create(RouteLog routeLog) {
        routeLog.setCreateTime(DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        routeLog.setLocation(AddressUtil.getCityInfo(routeLog.getIp()));
        return routeLogMapper.insert(routeLog);
    }

    @Override
    public Flux<RouteLog> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, StringConstant.COMMA);
        return routeLogMapper.deleteByIdIn(Arrays.asList(idArray));
    }

    @Override
    public Flux<RouteLog> findPages(QueryRequest request, RouteLog routeLog) {
        Query query = getQuery(routeLog);
        return PageableExecutionUtil.getPages(query, request, RouteLog.class, template);
    }

    @Override
    public Mono<Long> findCount(RouteLog routeLog) {
        Query query = getQuery(routeLog);
        return template.count(query, RouteLog.class);
    }

    private Query getQuery(RouteLog routeLog) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(routeLog.getIp())) {
            criteria.and("ip").is(routeLog.getIp());
        }
        if (StringUtils.isNotBlank(routeLog.getTargetServer())) {
            criteria.and("targetServer").is(routeLog.getTargetServer());
        }
        if (StringUtils.isNotBlank(routeLog.getRequestMethod())) {
            criteria.and("requestMethod").is(routeLog.getRequestMethod().toUpperCase());
        }
        if (StringUtils.isNotBlank(routeLog.getCreateTimeFrom())
                && StringUtils.isNotBlank(routeLog.getCreateTimeTo())) {
            criteria.andOperator(
                    Criteria.where("createTime").gt(routeLog.getCreateTimeFrom()),
                    Criteria.where("createTime").lt(routeLog.getCreateTimeTo())
            );
        }
        query.addCriteria(criteria);
        return query;
    }
}
