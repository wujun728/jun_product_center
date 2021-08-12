package cc.mrbird.febs.server.generator.controller;

import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.system.GeneratorConfig;
import cc.mrbird.febs.common.core.exception.FebsException;
import cc.mrbird.febs.server.generator.service.IGeneratorConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("config")
public class GeneratorConfigController {

    private final IGeneratorConfigService generatorConfigService;

    @GetMapping
    @PreAuthorize("hasAuthority('gen:config')")
    public FebsResponse getGeneratorConfig() {
        return new FebsResponse().data(generatorConfigService.findGeneratorConfig());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('gen:config:update')")
    public void updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) throws FebsException {
        if (StringUtils.isBlank(generatorConfig.getId())) {
            throw new FebsException("配置id不能为空");
        }
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
    }
}
