package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.system.DataPermissionTest;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.server.system.service.IDataPermissionTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller
 *
 * @author MrBird
 * @date 2020-04-14 15:25:33
 */
@Slf4j
@RestController
@RequestMapping("dataPermissionTest")
@RequiredArgsConstructor
public class DataPermissionTestController {

    private final IDataPermissionTestService dataPermissionTestService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('others:datapermission')")
    public FebsResponse dataPermissionTestList(QueryRequest request, DataPermissionTest dataPermissionTest) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.dataPermissionTestService.findDataPermissionTests(request, dataPermissionTest));
        return new FebsResponse().data(dataTable);
    }
}
