/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lu.web.core.aop;

import com.lu.core.utils.ToolUtil;
import com.lu.web.core.annotion.LuAccessAuth;
import com.lu.web.core.exception.LuGentrateException;
import com.lu.web.core.utils.HttpContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @program 戒毒执法平台
 * @description:  权限检查的aop
 * @author: zhanglu
 * @create: 2019-11-21 08:53:00
 */
@Aspect
@Component
@Order(200)
public class LuAccessAop {


    @Pointcut(value = "@annotation(com.lu.web.core.annotion.LuAccessAuth)")
    private void cutPermission() {

    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        LuAccessAuth accessAuth = method.getAnnotation(LuAccessAuth.class);
        Object name = accessAuth.name();
        Object[] auth = accessAuth.auth();
        Map<String, String> parameters = HttpContextUtil.getRequestParameters();
        for (Object o : auth) {
            if (ToolUtil.isEmpty(parameters.get(o))) {
                throw new LuGentrateException(401, "接口："+name+"，参数：" + o + "为空");
            }
        }
        return point.proceed();
    }

}
