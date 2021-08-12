package com.sanri.tools.controller;

import com.sanri.tools.modules.core.exception.BusinessException;
import com.sanri.tools.service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@Slf4j
public class VersionController {
   @Autowired
   VersionService versionService;

   @GetMapping("/current")
   public String current(){
       return versionService.currentVersion();
   }

}
