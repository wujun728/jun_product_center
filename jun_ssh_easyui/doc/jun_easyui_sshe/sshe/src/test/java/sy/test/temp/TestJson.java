package sy.test.temp;

import java.util.ArrayList;
import java.util.List;

import sy.model.app.CustUser;
import sy.model.app.MedicalReportData;
import sy.model.easyui.TreeGrid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestJson {
	
	public static void main(String[] args) {
		TreeGrid<MedicalReportData> treeGrid = new TreeGrid<MedicalReportData>();
		treeGrid.setTotal(1);
		List<MedicalReportData> list = new ArrayList<MedicalReportData>();
		MedicalReportData o = new MedicalReportData();
		o.setAttrId(100);
		list.add(o);
		treeGrid.setRows(list);
		
		String json = JSON.toJSONString(treeGrid);
		
		TreeGrid<JSONObject> t = 	(TreeGrid<JSONObject>)JSON.parseObject("{\"total\":2,\"rows\":[{\"attrId\":10,\"attrName\":\"ddddd\",\"attrDesc\":\"asdfas11111\",\"custUser.userId\":8,\"opOrder\":\"201312250000000015\",\"medicalType\":10104102,\"templateId\":10},{\"attrId\":12,\"attrName\":\"白细胞\",\"custUser.userId\":8,\"opOrder\":\"201312250000000015\",\"medicalType\":10104102,\"templateId\":10}]}",(TreeGrid.class));
		
		list = new ArrayList<MedicalReportData>();
		
		for (int i = 0; i < t.getRows().size(); i++) {
			int userId = (int) (t.getRows().get(i)).get("custUser.userId");
			CustUser custUser = new CustUser();
			custUser.setUserId(userId);
			MedicalReportData tt = JSON.toJavaObject(
					(JSONObject) (t.getRows().get(i)),
					MedicalReportData.class);
			tt.setCustUser(custUser);
			list.add(tt);
		}
		
		for(int i =0; i < t.getRows().size();i++){
			
			MedicalReportData tt = JSON.toJavaObject((JSONObject) (t.getRows().get(i)), MedicalReportData.class);
			
			System.out.println(tt);
		}
		String ss = "{&quot;treeGrid.total&quot;:2,&quot;treeGrid.rows&quot;:[{&quot;attrId&quot;:10,&quot;attrName&quot;:&quot;ddddd&quot;,&quot;attrDesc&quot;:&quot;asdfas11111&quot;,&quot;custUser.userId&quot;:8,&quot;opOrder&quot;:&quot;201312250000000015&quot;,&quot;medicalType&quot;:10104102,&quot;templateId&quot;:10},{&quot;attrId&quot;:12,&quot;attrName&quot;:&quot;白细胞&quot;,&quot;custUser.userId&quot;:8,&quot;opOrder&quot;:&quot;201312250000000015&quot;,&quot;medicalType&quot;:10104102,&quot;templateId&quot;:10}]}";
		
		String ss0 = ss.replaceAll("&quot;", "\"");
		
		System.out.println(json);
		System.out.println(ss0);
	}
	
			
}
