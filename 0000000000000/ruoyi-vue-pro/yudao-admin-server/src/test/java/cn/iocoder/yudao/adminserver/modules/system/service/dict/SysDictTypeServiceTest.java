package cn.iocoder.yudao.adminserver.modules.system.service.dict;

import cn.iocoder.yudao.adminserver.BaseDbUnitTest;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type.SysDictTypeCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type.SysDictTypeExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type.SysDictTypePageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type.SysDictTypeUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictTypeDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.dict.SysDictTypeMapper;
import cn.iocoder.yudao.adminserver.modules.system.service.dict.impl.SysDictTypeServiceImpl;
import cn.iocoder.yudao.framework.common.util.collection.ArrayUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.buildTime;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
* {@link SysDictTypeServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(SysDictTypeServiceImpl.class)
public class SysDictTypeServiceTest extends BaseDbUnitTest {

    @Resource
    private SysDictTypeServiceImpl dictTypeService;

    @Resource
    private SysDictTypeMapper dictTypeMapper;
    @MockBean
    private SysDictDataService dictDataService;

    @Test
    public void testGetDictTypePage() {
       // mock 数据
       SysDictTypeDO dbDictType = randomPojo(SysDictTypeDO.class, o -> { // 等会查询到
           o.setName("yunai");
           o.setType("芋艿");
           o.setStatus(CommonStatusEnum.ENABLE.getStatus());
           o.setCreateTime(buildTime(2021, 1, 15));
       });
       dictTypeMapper.insert(dbDictType);
       // 测试 name 不匹配
       dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setName("tudou")));
       // 测试 type 不匹配
       dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setType("土豆")));
       // 测试 status 不匹配
       dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
       // 测试 createTime 不匹配
       dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setCreateTime(buildTime(2021, 1, 1))));
       // 准备参数
       SysDictTypePageReqVO reqVO = new SysDictTypePageReqVO();
       reqVO.setName("nai");
       reqVO.setType("艿");
       reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
       reqVO.setBeginCreateTime(buildTime(2021, 1, 10));
       reqVO.setEndCreateTime(buildTime(2021, 1, 20));

       // 调用
       PageResult<SysDictTypeDO> pageResult = dictTypeService.getDictTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDictType, pageResult.getList().get(0));
    }

    @Test
    public void testGetDictTypeList() {
        // mock 数据
        SysDictTypeDO dbDictType = randomPojo(SysDictTypeDO.class, o -> { // 等会查询到
            o.setName("yunai");
            o.setType("芋艿");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
            o.setCreateTime(buildTime(2021, 1, 15));
        });
        dictTypeMapper.insert(dbDictType);
        // 测试 name 不匹配
        dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setName("tudou")));
        // 测试 type 不匹配
        dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setType("土豆")));
        // 测试 status 不匹配
        dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 测试 createTime 不匹配
        dictTypeMapper.insert(ObjectUtils.clone(dbDictType, o -> o.setCreateTime(buildTime(2021, 1, 1))));
        // 准备参数
        SysDictTypeExportReqVO reqVO = new SysDictTypeExportReqVO();
        reqVO.setName("nai");
        reqVO.setType("艿");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        reqVO.setBeginCreateTime(buildTime(2021, 1, 10));
        reqVO.setEndCreateTime(buildTime(2021, 1, 20));

        // 调用
        List<SysDictTypeDO> list = dictTypeService.getDictTypeList(reqVO);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbDictType, list.get(0));
    }

    @Test
    public void testGetDictType() {
        // mock 数据
        SysDictTypeDO dbDictType = randomDictTypeDO();
        dictTypeMapper.insert(dbDictType);
        // 准备参数
        String type = dbDictType.getType();

        // 调用
        SysDictTypeDO dictType = dictTypeService.getDictType(type);
        // 断言
        assertNotNull(dictType);
        assertPojoEquals(dbDictType, dictType);
    }

    @Test
    public void testCreateDictType_success() {
        // 准备参数
        SysDictTypeCreateReqVO reqVO = randomPojo(SysDictTypeCreateReqVO.class,
                o -> o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()));

        // 调用
        Long dictTypeId = dictTypeService.createDictType(reqVO);
        // 断言
        assertNotNull(dictTypeId);
        // 校验记录的属性是否正确
        SysDictTypeDO dictType = dictTypeMapper.selectById(dictTypeId);
        assertPojoEquals(reqVO, dictType);
    }

    @Test
    public void testUpdateDictType_success() {
        // mock 数据
        SysDictTypeDO dbDictType = randomDictTypeDO();
        dictTypeMapper.insert(dbDictType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SysDictTypeUpdateReqVO reqVO = randomPojo(SysDictTypeUpdateReqVO.class, o -> {
            o.setId(dbDictType.getId()); // 设置更新的 ID
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus());
        });

        // 调用
        dictTypeService.updateDictType(reqVO);
        // 校验是否更新正确
        SysDictTypeDO dictType = dictTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dictType);
    }

    @Test
    public void testDeleteDictType_success() {
        // mock 数据
        SysDictTypeDO dbDictType = randomDictTypeDO();
        dictTypeMapper.insert(dbDictType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDictType.getId();

        // 调用
        dictTypeService.deleteDictType(id);
        // 校验数据不存在了
        assertNull(dictTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteDictType_hasChildren() {
        // mock 数据
        SysDictTypeDO dbDictType = randomDictTypeDO();
        dictTypeMapper.insert(dbDictType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDictType.getId();
        // mock 方法
        when(dictDataService.countByDictType(eq(dbDictType.getType()))).thenReturn(1);

        // 调用, 并断言异常
        assertServiceException(() -> dictTypeService.deleteDictType(id), DICT_TYPE_HAS_CHILDREN);
    }

    @Test
    public void testCheckDictDataExists_success() {
        // mock 数据
        SysDictTypeDO dbDictType = randomDictTypeDO();
        dictTypeMapper.insert(dbDictType);// @Sql: 先插入出一条存在的数据

        // 调用成功
        dictTypeService.checkDictTypeExists(dbDictType.getId());
    }

    @Test
    public void testCheckDictDataExists_notExists() {
        assertServiceException(() -> dictTypeService.checkDictTypeExists(randomLongId()), DICT_TYPE_NOT_EXISTS);
    }

    @Test
    public void testCheckDictTypeUnique_success() {
        // 调用，成功
        dictTypeService.checkDictTypeUnique(randomLongId(), randomString());
    }

    @Test
    public void testCheckDictTypeUnique_valueDuplicateForCreate() {
        // 准备参数
        String type = randomString();
        // mock 数据
        dictTypeMapper.insert(randomDictTypeDO(o -> o.setType(type)));

        // 调用，校验异常
        assertServiceException(() -> dictTypeService.checkDictTypeUnique(null, type),
                DICT_TYPE_TYPE_DUPLICATE);
    }

    @Test
    public void testCheckDictTypeUnique_valueDuplicateForUpdate() {
        // 准备参数
        Long id = randomLongId();
        String type = randomString();
        // mock 数据
        dictTypeMapper.insert(randomDictTypeDO(o -> o.setType(type)));

        // 调用，校验异常
        assertServiceException(() -> dictTypeService.checkDictTypeUnique(id, type),
                DICT_TYPE_TYPE_DUPLICATE);
    }

    @Test
    public void testCheckDictTypNameUnique_success() {
        // 调用，成功
        dictTypeService.checkDictTypeNameUnique(randomLongId(), randomString());
    }

    @Test
    public void testCheckDictTypeNameUnique_nameDuplicateForCreate() {
        // 准备参数
        String name = randomString();
        // mock 数据
        dictTypeMapper.insert(randomDictTypeDO(o -> o.setName(name)));

        // 调用，校验异常
        assertServiceException(() -> dictTypeService.checkDictTypeNameUnique(null, name),
                DICT_TYPE_NAME_DUPLICATE);
    }

    @Test
    public void testCheckDictTypeNameUnique_nameDuplicateForUpdate() {
        // 准备参数
        Long id = randomLongId();
        String name = randomString();
        // mock 数据
        dictTypeMapper.insert(randomDictTypeDO(o -> o.setName(name)));

        // 调用，校验异常
        assertServiceException(() -> dictTypeService.checkDictTypeNameUnique(id, name),
                DICT_TYPE_NAME_DUPLICATE);
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static SysDictTypeDO randomDictTypeDO(Consumer<SysDictTypeDO>... consumers) {
        Consumer<SysDictTypeDO> consumer = (o) -> {
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
        };
        return randomPojo(SysDictTypeDO.class, ArrayUtils.append(consumer, consumers));
    }

}
