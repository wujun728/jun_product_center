package generate;


import java.io.IOException;


public class Runer{
	/**
	 * icon可以从此网址查看
	 * http://fontawesome.io/icons/
	 */
	public static void main(String[] args) throws IOException {
		LinGenerater lg = new LinGenerater("thing");
//		此方法可以生成代码
		lg.execute(); 
//		此方法可以插入菜单数据
//		lg.insertMenu("thing", "测试生成", "globe");
	}

}
