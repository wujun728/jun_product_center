package cn.iocoder.yudao.adminserver.modules.system.service.dict;

import cn.iocoder.yudao.adminserver.BaseDbUnitTest;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictDataDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictTypeDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.dict.SysDictDataMapper;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.dict.SysDictDataProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.dict.impl.SysDictDataServiceImpl;
import cn.iocoder.yudao.framework.common.util.collection.ArrayUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import com.google.common.collect.ImmutableTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static cn.hutool.core.bean.BeanUtil.getFieldValue;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
* {@link SysDictDataServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(SysDictDataServiceImpl.class)
public class SysDictDataServiceTest extends BaseDbUnitTest {

    @Resource
    private SysDictDataServiceImpl dictDataService;

    @Resource
    private SysDictDataMapper dictDataMapper;
    @MockBean
    private SysDictTypeService dictTypeService;
    @MockBean
    private SysDictDataProducer dictDataProducer;

    /**
     * 测试加载到新的字典数据的情况
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testInitLocalCache() {
        // mock 数据
        SysDictDataDO dictData01 = randomDictDataDO();
        dictDataMapper.insert(dictData01);
        SysDictDataDO dictData02 = randomDictDataDO();
        dictDataMapper.insert(dictData02);

        // 调用
        dictDataService.initLocalCache();
        // 断言 labelDictDataCache 缓存
        ImmutableTable<String, String, SysDictDataDO> labelDictDataCache =
                (ImmutableTable<String, String, SysDictDataDO>) getFieldValue(dictDataService, "labelDictDataCache");
        assertEquals(2, labelDictDataCache.size());
        assertPojoEquals(dictData01, labelDictDataCache.get(dictData01.getDictType(), dictData01.getLabel()));
        assertPojoEquals(dictData02, labelDictDataCache.get(dictData02.getDictType(), dictData02.getLabel()));
        // 断言 valueDictDataCache 缓存
        ImmutableTable<String, String, SysDictDataDO> valueDictDataCache =
                (ImmutableTable<String, String, SysDictDataDO>) getFieldValue(dictDataService, "valueDictDataCache");
        assertEquals(2, valueDictDataCache.size());
        assertPojoEquals(dictData01, valueDictDataCache.get(dictData01.getDictType(), dictData01.getValue()));
        assertPojoEquals(dictData02, valueDictDataCache.get(dictData02.getDictType(), dictData02.getValue()));
        // 断言 maxUpdateTime 缓存
        Date maxUpdateTime = (Date) getFieldValue(dictDataService, "maxUpdateTime");
        assertEquals(ObjectUtils.max(dictData01.getUpdateTime(), dictData02.getUpdateTime()), maxUpdateTime);
    }

    @Test
    public void testGetDictDataPage() {
        // mock 数据
        SysDictDataDO dbDictData = randomPojo(SysDictDataDO.class, o -> { // 等会查询到
            o.setLabel("芋艿");
            o.setDictType("yunai");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        dictDataMapper.insert(dbDictData);
        // 测试 label 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setLabel("艿")));
        // 测试 dictType 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setDictType("nai")));
        // 测试 status 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 准备参数
        SysDictDataPageReqVO reqVO = new SysDictDataPageReqVO();
        reqVO.setLabel("芋");
        reqVO.setDictType("yu");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        PageResult<SysDictDataDO> pageResult = dictDataService.getDictDataPage(reqVO);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbDictData, pageResult.getList().get(0));
    }

    @Test
    public void testGetDictDataList() {
        // mock 数据
        SysDictDataDO dbDictData = randomPojo(SysDictDataDO.class, o -> { // 等会查询到
            o.setLabel("芋艿");
            o.setDictType("yunai");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        dictDataMapper.insert(dbDictData);
        // 测试 label 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setLabel("艿")));
        // 测试 dictType 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setDictType("nai")));
        // 测试 status 不匹配
        dictDataMapper.insert(ObjectUtils.clone(dbDictData, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 准备参数
        SysDictDataExportReqVO reqVO = new SysDictDataExportReqVO();
        reqVO.setLabel("芋");
        reqVO.setDictType("yu");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        List<SysDictDataDO> list = dictDataService.getDictDatas(reqVO);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbDictData, list.get(0));
    }

    @Test
    public void testCreateDictData_success() {
        // 准备参数
        SysDictDataCreateReqVO reqVO = randomPojo(SysDictDataCreateReqVO.class,
                o -> o.setStatus(randomCommonStatus()));
        // mock 方法
        when(dictTypeService.getDictType(eq(reqVO.getDictType()))).thenReturn(randomDictTypeDO(reqVO.getDictType()));

        // 调用
        Long dictDataId = dictDataService.createDictData(reqVO);
        // 断言
        assertNotNull(dictDataId);
        // 校验记录的属性是否正确
        SysDictDataDO dictData = dictDataMapper.selectById(dictDataId);
        assertPojoEquals(reqVO, dictData);
        // 校验调用
        verify(dictDataProducer, times(1)).sendDictDataRefreshMessage();
    }

    @Test
    public void testUpdateDictData_success() {
        // mock 数据
        SysDictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SysDictDataUpdateReqVO reqVO = randomPojo(SysDictDataUpdateReqVO.class, o -> {
            o.setId(dbDictData.getId()); // 设置更新的 ID
            o.setStatus(randomCommonStatus());
        });
        // mock 方法，字典类型
        when(dictTypeService.getDictType(eq(reqVO.getDictType()))).thenReturn(randomDictTypeDO(reqVO.getDictType()));

        // 调用
        dictDataService.updateDictData(reqVO);
        // 校验是否更新正确
        SysDictDataDO dictData = dictDataMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dictData);
        // 校验调用
        verify(dictDataProducer, times(1)).sendDictDataRefreshMessage();
    }

    @Test
    public void testDeleteDictData_success() {
        // mock 数据
        SysDictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDictData.getId();

        // 调用
        dictDataService.deleteDictData(id);
        // 校验数据不存在了
        assertNull(dictDataMapper.selectById(id));
        // 校验调用
        verify(dictDataProducer, times(1)).sendDictDataRefreshMessage();
    }

    @Test
    public void testCheckDictDataExists_success() {
        // mock 数据
        SysDictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据

        // 调用成功
        dictDataService.checkDictDataExists(dbDictData.getId());
    }

    @Test
    public void testCheckDictDataExists_notExists() {
        assertServiceException(() -> dictDataService.checkDictDataExists(randomLongId()), DICT_DATA_NOT_EXISTS);
    }

    @Test
    public void testCheckDictTypeValid_success() {
        // mock 方法，数据类型被禁用
        String type = randomString();
        when(dictTypeService.getDictType(eq(type))).thenReturn(randomDictTypeDO(type));

        // 调用, 成功
        dictDataService.checkDictTypeValid(type);
    }

    @Test
    public void testCheckDictTypeValid_notExists() {
        assertServiceException(() -> dictDataService.checkDictTypeValid(randomString()), DICT_TYPE_NOT_EXISTS);
    }

    @Test
    public void testCheckDictTypeValid_notEnable() {
        // mock 方法，数据类型被禁用
        String dictType = randomString();
        when(dictTypeService.getDictType(eq(dictType))).thenReturn(
                randomPojo(SysDictTypeDO.class, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));

        // 调用, 并断言异常
        assertServiceException(() -> dictDataService.checkDictTypeValid(dictType), DICT_TYPE_NOT_ENABLE);
    }

    @Test
    public void testCheckDictDataValueUnique_success() {
        // 调用，成功
        dictDataService.checkDictDataValueUnique(randomLongId(), randomString(), randomString());
    }

    @Test
    public void testCheckDictDataValueUnique_valueDuplicateForCreate() {
        // 准备参数
        String dictType = randomString();
        String value = randomString();
        // mock 数据
        dictDataMapper.insert(randomDictDataDO(o -> {
            o.setDictType(dictType);
            o.setValue(value);
        }));

        // 调用，校验异常
        assertServiceException(() -> dictDataService.checkDictDataValueUnique(null, dictType, value),
                DICT_DATA_VALUE_DUPLICATE);
    }

    @Test
    public void testCheckDictDataValueUnique_valueDuplicateForUpdate() {
        // 准备参数
        Long id = randomLongId();
        String dictType = randomString();
        String value = randomString();
        // mock 数据
        dictDataMapper.insert(randomDictDataDO(o -> {
            o.setDictType(dictType);
            o.setValue(value);
        }));

        // 调用，校验异常
        assertServiceException(() -> dictDataService.checkDictDataValueUnique(id, dictType, value),
                DICT_DATA_VALUE_DUPLICATE);
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static SysDictDataDO randomDictDataDO(Consumer<SysDictDataDO>... consumers) {
        Consumer<SysDictDataDO> consumer = (o) -> {
            o.setStatus(randomCommonStatus()); // 保证 status 的范围
        };
        return randomPojo(SysDictDataDO.class, ArrayUtils.append(consumer, consumers));
    }

    /**
     * 生成一个有效的字典类型
     *
     * @param type 字典类型
     * @return SysDictTypeDO 对象
     */
    private static SysDictTypeDO randomDictTypeDO(String type) {
        return randomPojo(SysDictTypeDO.class, o -> {
            o.setType(type);
            o.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 保证 status 是开启
        });
    }

}
