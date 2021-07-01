package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.ChallengeDetail;
import cn.lxsir.uniapp.entity.ChallengeResult;
import cn.lxsir.uniapp.mapper.ChallengeResultMapper;
import cn.lxsir.uniapp.service.ChallengeDetailService;
import cn.lxsir.uniapp.service.ChallengeResultService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 挑战结果+详情记录表 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
@Log4j2
public class ChallengeResultServiceImpl extends ServiceImpl<ChallengeResultMapper, ChallengeResult> implements ChallengeResultService {

    @Autowired
    ChallengeDetailService detailService;

    @Override
    @Async
    public Map<String, Object> challengeResults(Map<String, Object> map) {
        System.out.println(map);
        Integer score = (Integer) map.get("score");
        String userName = (String) map.get("userName");
        List<LinkedHashMap> list = (List<LinkedHashMap>) map.get("list");
        List<ChallengeDetail> detailArrayList = new ArrayList<>();
        for (LinkedHashMap linkedHashMap : list) {
            ChallengeDetail detail = ChallengeDetail.builder().questionId((Integer) linkedHashMap.get("questionId"))
                    .garbageName((String) linkedHashMap.get("garbageName"))
                    .garbageType((Integer) linkedHashMap.get("garbageType"))
                    .selectedType((Integer) linkedHashMap.get("selectedType")).build();
            detailArrayList.add(detail);
        }
        boolean save = this.save(ChallengeResult.builder().score(score).userName(userName).result(JSON.toJSONString(detailArrayList)).build());
        boolean saveBatch = detailService.saveBatch(detailArrayList);
        map.clear();
        map.put("handle", "ok");
        return map;
    }
}
