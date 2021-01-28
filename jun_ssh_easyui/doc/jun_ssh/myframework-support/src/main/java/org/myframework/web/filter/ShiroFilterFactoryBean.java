package org.myframework.web.filter;

import org.apache.shiro.config.Ini;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.config.WebIniSecurityManagerFactory;

public class ShiroFilterFactoryBean
		extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {

    /**
     * 默认直接从classpath:shiro.ini加载URL和拦截器直接的配置关系
     */
    public ShiroFilterFactoryBean() {
        super();
        //
        Ini ini = WebIniSecurityManagerFactory.loadDefaultClassPathIni();
        if (ini!=null) {
    	   Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
           if (CollectionUtils.isEmpty(section)) {
               //no urls section.  Since this _is_ a urls chain definition property, just assume the
               //default section contains only the definitions:
               section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
           }
           setFilterChainDefinitionMap(section);
		}
    }
    
    /**
     * 从指定路径加载shiro url的配置
     */
	public void setConfigLocation(String configLocation) {
		Ini ini =  Ini.fromResourcePath(ResourceUtils.CLASSPATH_PREFIX + configLocation) ;
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
	}
	
}
