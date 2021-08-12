package cc.mrbird.febs.auth.controller;

import cc.mrbird.febs.auth.entity.OauthClientDetails;
import cc.mrbird.febs.auth.service.OauthClientDetailsService;
import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.exception.FebsException;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Yuuki
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class OauthClientDetailsController {

    private final OauthClientDetailsService oauthClientDetailsService;

    @GetMapping("check/{clientId}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OauthClientDetails client = this.oauthClientDetailsService.findById(clientId);
        return client == null;
    }

    @GetMapping("secret/{clientId}")
    @PreAuthorize("hasAuthority('client:decrypt')")
    public FebsResponse getOriginClientSecret(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OauthClientDetails client = this.oauthClientDetailsService.findById(clientId);
        String origin = client != null ? client.getOriginSecret() : StringUtils.EMPTY;
        return new FebsResponse().data(origin);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('client:view')")
    public FebsResponse oauthCliendetailsList(QueryRequest request, OauthClientDetails oAuthClientDetails) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.oauthClientDetailsService.findOauthClientDetails(request, oAuthClientDetails));
        return new FebsResponse().data(dataTable);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('client:add')")
    public void addOauthCliendetails(@Valid OauthClientDetails oAuthClientDetails) throws FebsException {
        try {
            this.oauthClientDetailsService.createOauthClientDetails(oAuthClientDetails);
        } catch (Exception e) {
            String message = "新增客户端失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('client:delete')")
    public void deleteOauthCliendetails(@NotBlank(message = "{required}") String clientIds) throws FebsException {
        try {
            this.oauthClientDetailsService.deleteOauthClientDetails(clientIds);
        } catch (Exception e) {
            String message = "删除客户端失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('client:update')")
    public void updateOauthCliendetails(@Valid OauthClientDetails oAuthClientDetails) throws FebsException {
        try {
            this.oauthClientDetailsService.updateOauthClientDetails(oAuthClientDetails);
        } catch (Exception e) {
            String message = "修改客户端失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
