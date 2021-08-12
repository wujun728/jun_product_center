package cc.mrbird.febs.common.options;

import com.wuwenze.poi.config.Options;

/**
 * @author : wx
 * @version : 1
 * @date : 2019/9/27 15:51
 */
public class DataScopeOptions implements Options {
    @Override
    public String[] get() {
        return new String[]{"个人数据","部门数据","全部数据"};
    }
}
