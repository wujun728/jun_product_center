package lin;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.du.lin.LinApplication;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
	
	@org.junit.Test
	public void hi(){
		System.out.println("hi");
	}
	
}
