先要调用一遍 tables 接口,才能使用其它接口, 根据返回的 catalog, schema  来操作

代码里面使用 catalog.schema.tableName 做表的唯一标识 

|database | catalog | schema | 
|--------|-----------|--------|
|mysql | 数据库 | null | 
|postgresql | null | 模式| 
