<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>
</head>
<body>

	<h1>在前面的示例已经演示了多级抓取，返回格式是</h1>
	<h3>[{"id":1,"name":"我是C","demob":{"id":1,"name":"我是B","demoa":{"id":1,"name":"我是A"}}}]</h3>
	<h2>现在我要自定义返回json格式，C对象我要id和name属性，demob对象和demoa对象我要name属性</h2>
	<h3>并且这里也演示了后台创建QueryFilter的示例</h3>

	<form action="${pageContext.request.contextPath}/demoCController/find2.do" method="post" target="_blank">
		<button type="submit" style="color: red;">过滤属性后的查询</button>
	</form>
	<br />
	<form action="${pageContext.request.contextPath}/demoCController/find.do" method="post" target="_blank">
		<input name="joinFetch" value="t.demob,demob.demoa" type="hidden" />
		<button type="submit">没有经过过滤的查询</button>
	</form>

	<h1>请看后台的写法</h1>
	<div>super.returnJsonByIncludeProperties(service.find(filter), new String[] { "[].id", "[].name", "[].demob.name", "[].demob.demoa.name" }, response);</div>
	<h1>主要看"[].id", "[].name", "[].demob.name", "[].demob.demoa.name"这部分</h1>
	<h3>这里为什么要[].xxx？？因为这个find返回的是一个集合，而不是一个对象</h3>
	<hr />
	<h1>这里的过滤使用的是一个Mapl的概念</h1>
	<hr />
	借鉴Nutz的解释：
	<br />
	<div>
		什么是 Mapl 结构?<br />
		<p>一种以 Map, List 接口对象所组织起来的结构体系. 类似于JSON结构便于JAVA在内存中处理的结构. 主要提供键值对, 与列表的有机组合, 因此这种结构只由Map, List组成, 所以称其为Mapl结构.</p>
		<p>
			Map a = new HashMap();<br /> a.put("name","a");<br /> Map b = new HashMap();<br /> b.put("name","b");<br /> Map c = new HashMap();<br /> c.put("name","c");<br /> List list = new ArrayList();<br /> list.add(a);<br /> list.add(b);<br /> list.add(c);<br /> Map d = new HashMap();<br /> d.put("items", list);<br />
		</p>
		<p>通过上面的代码我们就组织了一个Mapl结构, 它等效于以下的JSON文档</p>
		<p>{"items":[{"name":"a"},{"name":"b"},{"name":"c"}]}</p>
		<br />
	</div>
	<div>
		<br />String json = "{'name':'b', 'as':[{'name':'nutz','id':1},{'name':'jk','id':2}]}";//这样得到的就是Mapl结构的数据了.<br /> Object obj = Json.fromJson(json);<br />
	</div>
	<div>
		<br />上面的obj, 如果我想取as索引为1的name的值, 怎么办? 只能这样
	</div>
	<div>
		Map map = (Map) obj;<br /> List list = map.get("as");<br /> Map item = list.get(1);<br /> String name = item.get("name");<br />
	</div>
	<div>
		<br />亲, 看到没, 看到没~~~妈哦, 还好这里只有几层, 要是再多几次这样的, 我一定会疯的, 你肯定也跟我一样吧. 所以咯, 让我们解脱吧~~~<br />
	</div>
	<div>
		<br />String name = (String) Mapl.cell(obj, "as[1].name");<br />
	</div>
	<div>
		<br />完了? 这就样? 是的, 完了, 就这样, 一句话搞定. so easy~~<br />
	</div>
	关于里面path的规则:
	<br /> map的值访问直接使用 '.', 如: abc.name
	<br /> list的访问使用 "名称[索引]", 如: as[1]. 当然要是不想写[]也可以使用 as.1.name的形式.
	<br /> 顶层为list时, 使用 "[索引].其它", 如: [1].name
	<br /> 如果想得到一个List, 而不是它某个值, 则可以使用 "名称" 不加 "[索引]". 如: as
	<br /> 如果List后加了"[]"中间却没有索引, 则默认访问第一个元素, 如: user[] 等效 user[1]
	<br />
</body>
</html>