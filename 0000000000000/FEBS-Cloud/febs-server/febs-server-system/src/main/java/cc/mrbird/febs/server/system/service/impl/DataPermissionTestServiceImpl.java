package cc.mrbird.febs.server.system.service.impl;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.system.DataPermissionTest;
import cc.mrbird.febs.server.system.mapper.DataPermissionTestMapper;
import cc.mrbird.febs.server.system.service.IDataPermissionTestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MrBird
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataPermissionTestServiceImpl extends ServiceImpl<DataPermissionTestMapper, DataPermissionTest> implements IDataPermissionTestService {

    @Override
    public IPage<DataPermissionTest> findDataPermissionTests(QueryRequest request, DataPermissionTest dataPermissionTest) {
        LambdaQueryWrapper<DataPermissionTest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(DataPermissionTest::getCreateTime);
        Page<DataPermissionTest> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }
}
