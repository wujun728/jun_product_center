package com.sanri.tools.modules.database.dtos.meta;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Setter
public class ActualTableName {
    private String catalog;
    private String schema;
    @NotNull
    private String tableName;
    private String fullName;

    public ActualTableName() {
    }

    public ActualTableName(String catalog, String schema, String tableName) {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;

        this.fullName = StringUtils.join(Arrays.asList(catalog, schema,tableName),'.');
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ActualTableName)){
            return false;
        }
        ActualTableName other = (ActualTableName) obj;
        if (getFullName() == null && other.getFullName() == null )return true;

        return getFullName().equals(other.getFullName());
    }

    @Override
    public int hashCode() {
        if (getFullName() == null){
            return 0 ;
        }
        return getFullName().hashCode();
    }

    public String getCatalog() {
        return catalog;
    }

    public String getSchema() {
        return schema;
    }

    public String getTableName() {
        return tableName;
    }

    public String getFullName() {
        return StringUtils.join(Arrays.asList(catalog,schema,tableName),'.');
    }
}
