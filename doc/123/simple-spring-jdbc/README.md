## simple-spring-jdbc
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/me.wuwenbin/template-modules-jpa/badge.svg)](http://search.maven.org/#artifactdetails%7Cme.wuwenbin%7Ctemplate-modules-jpa%7C1.10.5.RELEASE%7Cjar)
---
simple-spring-jdbc 帮你轻松完成对数据的操作，无需额外编写dao，一个dao满足你所有需求。支持多数据源（多数据源也只需定义一个dao即可）。
## 当前版本：
   ```xml
       <dependency>
            <groupId>me.wuwenbin</groupId>
            <artifactId>simple-spring-jdbc</artifactId>
            <version>1.0</version>
        </dependency>
   ```
   ---
   ## 入门使用
   #### 配置数据源（此处示例使用DruidDataSource）
  + xml配置方法（具体属性值配置此处省略，根据开发情况配置）
   
   ```xml
      <bean id="ds1" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close"/>
      <bean id="ds2" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close"/>
   ```
   + java配置方法（推荐Druid Spring Boot Starter快速配置）
   
   ```java
      @Primary
      @Bean
      @ConfigurationProperties("spring.datasource.druid.one")
      public DataSource dataSourceOne(){
          return DruidDataSourceBuilder.create().build();
      }
   
      @Bean
      @ConfigurationProperties("spring.datasource.druid.two")
      public DataSource dataSourceTwo(){
          return DruidDataSourceBuilder.create().build();
      }
   ```
   #### 配置增强数据源（DataSourceX类）
   + xml配置方法
   
   ```xml
          <bean id="dsx1" class="me.wuwenbin.modules.jpa.factory.business.DataSourceX">
              <property name="dataSource" ref="ds1"/>
              <property name="initDbType" value="Mysql"/><!-- 此处的Mysql为DbType.Mysql,默认Mysql-->
          </bean>
      
          <bean id="dsx2" class="me.wuwenbin.modules.jpa.factory.business.DataSourceX">
              <property name="dataSource" ref="ds2"/>
              <property name="initDbType" value="Mysql"/><!-- 此处的Mysql为DbType.Mysql,默认Mysql -->
          </bean>
   ```
   + java配置方法
   
   ```java
          @Bean
          public DataSourceX dataSourceX(DruidDataSource dataSource) {
              DataSourceX dataSourceX = new DataSourceX();
              dataSourceX.setDataSource(dataSource);
              dataSourceX.setInitDbType(DbType.Mysql);
              return dataSourceX;
          }
   ```
#### 配置DaoFactory（核心配置）
+ xml配置

```xml
        <bean id="daoFactory" class="me.wuwenbin.modules.jpa.factory.DaoFactory">
           <!--多个增强数据源的map集合-->
            <property name="dataSourceMap">
                <map key-type="java.lang.String">
                    <entry key="tp" value-ref="dsx1"/>
                    <entry key="ct" value-ref="dsx2"/>
                </map>
            </property>
         <!--默认的增强数据源-->
            <property name="defaultDao" ref="dsx1"/>
        </bean>
```
+ java配置

```java
       @Bean
       public DaoFactory daoFactory(DataSourceX dataSourceX) {
           DaoFactory daoFactory = new DaoFactory();
           Map<String, DataSourceX> multiDao = new ConcurrentHashMap<>();
           multiDao.put("template_default_dao", dataSourceX);
           daoFactory.setDataSourceMap(multiDao);
           daoFactory.setDefaultDao(dataSourceX);
           return daoFactory;
       }
```
---
## 基本使用
+ 使用方法1：在Spring项目的一个Bean中注入DaoFactory即可。

```java
    @Autowired
     private DaoFactory daoFactory;
 
     public List<Student> findStudentsByCampusNo(String campusNo){
       String sql = "select * from t_stu_info where campus_no = ?";
       return daoFactory.dynamicDao.findListBeanByArray(sql, Student.class, campusNo);
     }
```
+ 使用方法2：在Spring项目的一个Bean中注入AncestorDao即可。

```java
   private AncestorDao daoTemplate;

   @Autowired
   public void setDaoTemplate(DaoFactory daoFactory){
       this.daoTemplate = daoFactory.dynamicDao; 
   }

   public List<Student> findStudentsByCampusNo(String campusNo){
      String sql = "select * from t_stu_info where campus_no = ?";
      return daoTemplate.findListBeanByArray(sql, Student.class, campusNo);
    }
```
## 进阶使用
+ 如果切换多数据源的操作 _（在方法上加上@DynamicDataSource注解即可，其中参数为配置何种指定的key）_
    
```java
    /**
    * 当程序进入此方法时，数据源切换到key为tp的数据上，访问完毕后，会切回配置中默认的数据源。
    */
    @DynamicDataSource("tp")
     public List<Student> findStudentsByCampusNo(String campusNo){
          String sql = "select * from t_stu_info where campus_no = ?";
          return daoTemplate.findListBeanByArray(sql, Student.class, campusNo);
     }
```
+ 如果某方法内含有调用其他方法的操作 _（譬如a方法中有调用b方法的操作，且b方法切换了与a不同的数据源）_，则需以下操作

```java
        @DynamicDataSource("ct")
        public List<UserA> findUserAs() {
            String sql = "select * from t_user";
            return daoFactory.dynamicDao.findListMapByArray(sql);
        }
    
        /**
        * 此方法为默认数据源 ，所以无需注解，写注解也可以
        */
        public List<UserB> findUserBs() {
            String sql = "select * from t_oauth_user";
            return daoFactory.dynamicDao.findListMapByArray(sql);
        }
        
        @DynamicDataSource("tp")
        public List<UserB> findUserBs2(String sql) {
            return daoFactory.dynamicDao.findListMapByArray(sql);
        }
        
        /**
        * 以下为错误的用法 ，此处数据源则不会切换，一直使用默认的访问
        */
        public void testError(){
            tp1();
            ct();
            tp2();
        }
        
        /**
        * 正确的用法如下。
        * 原因：因为被aop代理之后，代理类中内部方法的调用并不能编译到上面的注解，aop没有拦截到，故需要显式调用（此处使用JDK8的函数式接口模式）。
        */
        public void testCorrect(){
            List<UserB> bs1 = InternalCall.transfer(PublicService::findUserBs);
            List<UserB> bs2 =
                    InternalCall.transfer((Function<PublicService, List<UserB>>) bean -> bean.findUserBs2("select * from t_oauth_role"));
            List<UserA> as = InternalCall.transfer(PublicService::campusTalk);
        }
```
## 自定义使用
+ 如果AncestorDao中的方法还不能满足你的需求（基本上99%都可以），你可以自己获取相关原始对象来操作
```java
    private AncestorDao daoTemplate;

    //获取simpleJdbcCall，一般用来执行存储过程的
    daoTemplate.getSimpleJdbcCallObj();
    
    //获取jdbcTemplate对象，用来执行？参数的sql语句
    daoTemplate.getJdbcTemplateObj();
    
    //获取namedParameterJdbcTemplate对象，一般用来执行：参数的sql语句
    daoTemplate.getNamedParameterJdbcTemplateObj();
```
---
## API参考
+ 执行存储过程
> 以 _callProcedure_ 为开头的方法
+ 执行插入语句，并且需要返回值的
> 类似 _insertXxxAutoGenKeyReturnXxx_ 这样的方法
> 
> 根据插入的类型选择前缀匹配，以及根据返回的需求进行后缀匹配即可
+ 执行插入、更新、删除语句，返回影响条目数的，或者没什么要求的
> 类似 _executeXxx_ 的方法
>
> 方法名后缀根据sql语句以及方法传的参数来选择
+ 批量执行插入、更新、删除语句，返回影响条目数的，或者没什么要求的
> 类似 _executeBatchByXxx_ 的方法
>
> 方法名后缀根据sql语句以及方法传的参数来选择
+ 执行统计的语句
> 以 _findNumberBy_ 开头的则返回类型为Number需自行转换对象的数字类型包装类
>
> 以 _queryNumberBy_ 开头的则返回指定的数字类型包装类
> 方法名后缀根据sql语句以及方法传的参数来选择
+ 执行单列单行的查找/单列多行的查询语句
> 以 _findPrimitiveBy_ 开头的方法查找单列单行
>
> 以 _findListPrimitiveBy_ 开头的方法查找单列多行
>
> 方法名后缀根据sql语句以及方法传的参数来选择
+ 执行N列单行查找/N列N行的查询语句（某种程度上来讲此条包含上一条，只不过上面的是此条情况的一种特殊情况）
> 以 _findMapBy_ 开头的是查找N列单行的无Java类对象匹配的方法
>
> 以 _findBeanBy_ 开头的是查找N列单行的有相应的Java类对象匹配的方法
>
> 以 _findListMapBy_ 开头的是查找N列N行的无Java类对象匹配的方法
>
> 以 _findListBeanBy_ 开头的是查找N列N行的有相应的Java类对象匹配的方法
>
> 方法名后缀根据sql语句以及方法传的参数来选择
+ 执行分页方法的查找，情况大致同上一条
> 此处不再列举，仅仅是方法名前面多了Page，情况和上一条一致

