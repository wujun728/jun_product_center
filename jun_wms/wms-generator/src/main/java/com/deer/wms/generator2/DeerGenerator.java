package com.deer.wms.generator2;


import com.deer.wms.generator2.configurer.DatasourceConfigurer;
import com.deer.wms.generator2.configurer.Table;

/**
 * Created by Floki on 2017/9/29.
 */
public class DeerGenerator {
    public static void main(String[] args) {
        DatasourceConfigurer configurer = new DatasourceConfigurer();
        //设置数据库连接信息
//        configurer.setUrl("jdbc:mysql://sh-cdb-60jldfhk.sql.tencentcdb.com:62802/wuxi_wms?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        configurer.setUrl("jdbc:mysql://localhost:3306/wxwms?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        configurer.setPassword("1234");
        configurer.setUserName("root");
//        configurer.setPassword("12!@qwQW");
        configurer.setDiverClassName("com.mysql.jdbc.Driver");

        //设置需要生成代码的基础路径，生成的代码都会在该路径下面
        configurer.setBasePackage("com.deer.wms.base.system");
        configurer.setAuthor("");

        //设置子模块名称(如果要生成子模块的代码，一定要设置该值，否则代码将会生成到父项目中了)
        configurer.setModuleName("/wms-base-system");

        //设置需要生成的表
       // configurer.getTables().add(new Table("bill_master", null, "id", "Integer"));
        //configurer.getTables().add(new Table("bill_detail",null,"id","Integer"));
        //configurer.getTables().add(new Table("Shelf_info", null, "ShelfIdInt", "Integer"));
        //configurer.getTables().add(new Table("help_content", null, "content_id", "Integer"));
        //configurer.getTables().add(new Table("validate_record", null, "record_id", "Long"));

        configurer.getTables().add(new Table("transfer_reason",null,"transfer_reason_id","Integer"));

        //生成代码
        Generator.genCode(configurer);
    }
}
