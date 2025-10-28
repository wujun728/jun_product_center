# klg-jpa spring-data-jpa 最佳实践

### 项目介绍
JPA是sun为POJO持久化制定的标准规范，用来操作实体对象，执行CRUD操作，让开发者从繁琐的JDBC和SQL代码中解脱出来。
但是JPA有以下两个缺陷：<br>
 **1.臃肿的动态条件查询** <br>
![输入图片说明](https://images.gitee.com/uploads/images/2018/1016/153739_c272c07e_1063744.png "jpa.png")<br>
 **2.众所周知，复杂查询（联接表查询）的效率低** <br>
[spring-data-jpa和mybatis可以整合在一起使用有什么优缺点](https://www.zhihu.com/question/53706909)，这个问答中了解到
[spring-data-jpa-extra](https://github.com/slyak/spring-data-jpa-extra)这个库，让我们可用更方便的写sql查询。

klg-jpa，[spring-data-jpa](https://docs.spring.io/spring-data/jpa/docs/2.1.0.RELEASE/reference/html) 最佳实践，用起来就像开了挂,**更多例子[klg-j2ee-dataacess-demo 演示]( https://gitee.com/klguang/klg-j2ee-dataacess-demo )** 
### 单表查询最佳实践

 **1. find by attribute是一种较灵活的方式，需要用到jpa生成的[metamodel](https://www.objectdb.com/java/jpa/persistence/metamodel)（ide自动生成），快速开发的利器** <br>

BaseRepository api:
```
	//-------------find by attribute------------------------
	public Page<T> findPage(Pageable pageable,AttrExpression...exprs);
	public List<T> findList(Sort sort,AttrExpression...exprs);
	public List<T> findList(AttrExpression...exprs);
	public T getOne(AttrExpression...exprs);
	public long count(AttrExpression...exprs);
```

find by attribute 适合不定条件单表查询，默认是不忽略空值的(null或者"")，如果要忽略空值，请用AExpr属性表达式构造器的igEmpty()方法。

```
Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "userId"));
Page<User> userPage = userDAO.findPage(pageable, 
		AExpr.eq(User_.account, "").igEmpty(),
		AExpr.contain(User_.userName, "fd"));
```
含有开始和结束时间的动态查询的处理方法

```
@ResponseBody
@RequestMapping("/findpage")
public EasyUIPage findpage(
	@RequestParam int page,
	@RequestParam int rows,
	@RequestParam(required=false) Long employeeid,
	@RequestParam(required=false) String typeCode,
	@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
	@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
	Pageable pageable=new PageRequest(page<1?0:page-1, rows, new Sort(Direction.DESC,"numId"));

	Page<DrugOut> pageData=drugOutService.findPage(pageable, 
		AExpr.eq(DrugOut_.employeeid, employeeid).igEmpty(),// igEmpty()忽略空值，包括null和""
		AExpr.eq(DrugOut_.typeCode, typeCode).igEmpty(),
		AExpr.gte(DrugOut_.saledate, startDate).igEmpty(),//大于
		AExpr.lte(DrugOut_.saledate, endDate).igEmpty());//小于


	return new EasyUIPage(pageData);
}
```

 _注意：_ <br>
1).复杂属性的表达式处理：先判断复杂属性实体的id是否为空<br>
2).动态查询，参数(required=false)、查询的属性表达式列表(igEmpty)

##### AExpr 属性表达式工厂
|方法 |说明  |
| --- | --- |
| eq    | =    |
| ne   | <>    |
|  gt   |    > |
|    lt |    < |
|   gte  | >=    |
|   lte  |  <=   |
|  like   |模糊匹配,注意必须包含"%"通配符 |
|  contain   | 包含字符串，即like查询，在两边都有"%"通配符    |
|in |in |
| between| between| 
|isNull | isNull| 
| isNotNull|isNotNull | 

 **2. 通过解析Repository中的方法名创建查询，是spring-data-jpa的一大特色。符合经典的java三层架构：表现层（UI），业务逻辑层（BLL），数据访问层（DAL）**  <br>

这是spring官网的例子：通过EmailAddress和Lastname来查找用户
```
public interface UserRepository extends Repository<User, Long> {

  List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);
}
```
详见[spring-data-jpa#Query Creation](https://docs.spring.io/spring-data/jpa/docs/2.1.0.RELEASE/reference/html/#jpa.query-methods.query-creation)

 **3. 页面参数封装到实体，然后运用qbe(query by example)。qbe会自动忽略null值** <br>
BaseRepository api:

```
	//--------------qbe(query by example)--------------------
	public Page<T> findPage(T example,Pageable pageable);
	public List<T> findList(T example,Sort sort);
	public List<T> findList(T example);
	public T getOne(T example);
	public long count(T example);
```
qbe复杂查询只支持String的模糊查询，大于、小于、between and均不支持。<br>
ExampleMatcher例字，嵌套属性模糊匹配
```
//User有属性logrole
ExampleMatcher matcher=ExampleMatcher.matching()
	.withMatcher("logrole.logRoleName", GenericPropertyMatcher.of(StringMatcher.CONTAINING).ignoreCase());
List<User> users=userDAO.findAll(Example.of(user,matcher));
```
详见[spirng-data-jpa#query-by-example](https://docs.spring.io/spring-data/jpa/docs/2.1.0.RELEASE/reference/html/#query-by-example)
### 复杂查询最佳实践
复杂查询，无论是用java代码进行sql拼接还是臃肿的Criteria动态查询都是不推荐的。因为这么做一方面是不够优雅，更重要的是难以维护。Mybatis的流行给了我们很多启发，复杂查询用sql配置文件，是一种灵活且方便维护的方式。<br>
以下两种方式是我推荐大家使用的：<br>
1. 可用使用@NamedQuery,或spring-data-jpa的@Query<br>
2. 引入[spring-data-jpa-extra](https://github.com/slyak/spring-data-jpa-extra)这个库，快速入手：<br>

Repository接口
```
public interface SampleRepository extends BaseRepository<Sample, Long> {

	@TemplateQuery
	Page<Sample> findByContent(@Param("content") String content, Pageable pageable);
}
```
com.slyak.spring.jpa.extra.Sample.sftl模板文件
```
--findByContent
  SELECT * FROM t_sample WHERE 1 = 1
  <#if content??>
        AND content LIKE :content
  </#if>
```
### 使用说明
1. 本项目依赖：<br>
https://gitee.com/klguang/coderfun-bom<br>
https://gitee.com/klguang/xutils<br>
将这两个项目下载到本地，并执行maven install
2. 项目中Repository继承BaseRepository，Service接口继承BaseService，ServiceImpl继承BaseServiceImpl;BaseService的接口中有BaseRepository的大部分方法<br>
3. spring配置如下：
```
	<!-- 指定 BaseRepositoryFactoryBean -->
	<jpa:repositories base-package="demo,com.slyak.spring.jpa"
		factory-class="klg.common.dataaccess.BaseRepositoryFactoryBean"
		entity-manager-factory-ref="entityManagerFactory"
		transaction-manager-ref="transactionManager" repository-impl-postfix="Impl" />
	
	<!-- 配置 freemarkerSqlTemplates解析相关-->
	<bean id="freemarkerSqlTemplates" class="com.slyak.spring.jpa.FreemarkerSqlTemplates">
		<property name="suffix" value=".sftl" />
		<property name="templateLocation" value="classpath*:/sqls"/>
	</bean>
```


如有疑问下载[klg-j2ee-dataacess-demo 演示]( https://gitee.com/klguang/klg-j2ee-dataacess-demo )<br>


#### 注意
本项目依赖spring-data-jpa-extra-2.1.2.RELEASE,但我做了一些改动，然后打成jar包，放在lib目录下<br>
项目地址：https://github.com/klguang/spring-data-jpa-extra/tree/2.1.2.RELEASE
1. com.slyak.spring.jpa.ContextHolder 访问控制为public，目的是让原来项目中已经存在的BaseRepositoryFactoryBean能工作。
2. 模板文件名为Entity的java full name
3. 测试用例跑通

### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request