package sy.test.org.apache.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.app.CustDept;
import sy.model.app.CustUser;
import sy.model.app.CustomerInfo;
import sy.service.app.CustDeptServiceI;
import sy.service.app.CustUserServiceI;
import sy.service.app.CustomerInfoServiceI;
import sy.util.app.ColData;
import sy.util.app.CustUserData;
import sy.util.app.ReadCellCallBack;
import sy.util.app.ReadExceRowFilter;
import sy.util.base.DateUtil;
import sy.util.base.HqlFilter;
import sy.util.base.PoiUtil;

/**
 * 
 * @author Wujun
 * 
 */
public class TestPoi1 {

	public static void main(String[] args) throws InvalidFormatException,
			IOException {
		// testReadExcel();
		//testForeachReadExcel();
		// testWriteExcel();
		TestPoi1  testPoi1 = new TestPoi1();
		
		testPoi1.handleForeachReadExcel() ;
		
	}

	/**
	 * 读取一个已存在的excel
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static void testReadExcel() throws InvalidFormatException,
			IOException {
		Workbook workbook = WorkbookFactory.create(new File("D:/temp/1.xlsx"));
		// 获取第一个工作目录,下标从0开始
		Sheet sheet = workbook.getSheetAt(0);
		// 获取该工作目录最后一行的行数
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			// 获取下标为i的行
			Row row = sheet.getRow(i);
			if (row != null) {
				// 获取该行单元格个数
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					// 获取下标为j的单元格
					Cell cell = row.getCell(j);
					// 调用获取方法
					String cellValue = PoiUtil.getCellValue(cell);
					System.out.print("[" + cellValue + "]");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 使用Foreach方式读取Excel
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static void testForeachReadExcel() throws InvalidFormatException,
			IOException {
		Workbook workbook = WorkbookFactory.create(new File("E:/12月员工信息.xlsx"));
		// 根据sheet的名字获取
		Sheet sheet = workbook.getSheet("Sheet1");
		final ReadExceRowFilter readExceRowFilter = new ReadExceRowFilter(false);
		
		// 处了上面testReadExcel的方式读取以外,还支持foreach的方式读取
		for (final Row row : sheet) {
			for (final Cell cell : row) {

//				readExceRowFilter.handleRow(new ReadCellCallBack() {
//						
//					@Override
//					public void filt(Row row, Cell cell) {
//						int index = cell.getColumnIndex();
//						if (!PoiUtil.getCellValue(cell).isEmpty()) {
//							System.out.print("[" + PoiUtil.getCellValue(cell)
//									+ "]");
//						} else {
//							// readExceRowFilter.addToList();
//						}
//
//						System.out.print("[index]" + index);
//					}
//
//					@Override
//					public Row getRow() {
//						return row;
//					}
//
//					@Override
//					public Cell getCell() {
//						return cell;
//					}
//
//				});
			}
			System.out.println();
		}
	}
	
	/**
	 * 创建简单的Excel
	 * 
	 * @throws IOException
	 */
	public static void testWriteExcel() throws IOException {
		// 创建一个XSSF的Excel文件
		Workbook workbook = new XSSFWorkbook();
		FileOutputStream fos = new FileOutputStream("D:/temp/test111.xlsx");
		// 创建名称为test的工作目录
		Sheet sheet = workbook.createSheet("test");
		/*
		 * 创建1个10行x10列的工作目录
		 */
		for (int i = 0; i < 10; i++) {
			// 创建一行
			Row row = sheet.createRow(i);
			for (int j = 0; j < 10; j++) {
				// 创建一个单元格
				Cell cell = row.createCell(j);
				// 设置单元格value
				cell.setCellValue("test");
				// 此处为设置Excel的样式,设置单元格内容居中,但这样设置方式并不常用,请留意下面的方法
				CellStyle cs = workbook.createCellStyle();
				cs.setAlignment(CellStyle.ALIGN_CENTER);
				cell.setCellStyle(cs);
			}
		}
		// 将Excel写出到文件流
		workbook.write(fos);
	}
	
	
	
	
	
	
	 
	public   CustomerInfo createCustomerInfo(String customerName) {
		HqlFilter hqlFilter = new HqlFilter();

		hqlFilter.addFilter("QUERY_t#customerName_S_EQ",
				StringUtils.defaultString(customerName));
//		List<CustomerInfo> list = customerInfoServiceI
//				.findCustomerInfoByFilter(hqlFilter);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		}

		CustomerInfo CustomerInfo = new CustomerInfo();
		CustomerInfo.setCustomerName(customerName);

		return CustomerInfo;
	}

	public CustDept createCustDept(String deptName) {
		HqlFilter hqlFilter = new HqlFilter();

//		hqlFilter.addFilter("QUERY_t#deptName_S_EQ",
//				StringUtils.defaultString(deptName));
//		List<CustDept> list = custDeptServiceI.findDeptByFilter(hqlFilter);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		}

		CustDept custDept = new CustDept();

		custDept.setDeptName(deptName);
		return custDept;
	}

	public CustUser createCustUser(String userCode) {
		HqlFilter hqlFilter = new HqlFilter();

//		hqlFilter.addFilter("QUERY_t#userCode_S_EQ",
//				StringUtils.defaultString(userCode));
//		List<CustUser> list = custUserServiceI.findByFilter(hqlFilter);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		}

		CustUser custUser = new CustUser();

		custUser.setUserCode(userCode);

		return custUser;
	}

	/**
	 * 使用Foreach方式读取Excel
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public  void handleForeachReadExcel() throws InvalidFormatException,
			IOException {
		Workbook workbook = WorkbookFactory.create(new File("E:/12月员工信息.xlsx"));
		// 根据sheet的名字获取
		final Sheet sheet = workbook.getSheet("Sheet1");
		final ReadExceRowFilter readExceRowFilter = new ReadExceRowFilter(false);

		// 处了上面testReadExcel的方式读取以外,还支持foreach的方式读取
		for (final Row row : sheet) {
			CustUser custUser = (CustUser) readExceRowFilter.handleRow(row,
					new ReadCellCallBack() {
						private List<CustUserData> dataList;

						private ColData getColDataByIndex(	int index,
								List<ColData> colList) {
							if(colList != null){
							for (ColData colData : colList) {
								if (colData.getColIndex() == index) {
									return colData;
								}
							}
							}
							return null;
						}

						@Override
						public Object returnRowObject(Row row) {
							int rowIndex = row.getRowNum();
							if(rowIndex == 0){
								return null;
							}
							List<ColData> colList = getCellList(rowIndex);
							
							if(colList == null){
								return null;
							}
							CustUser custUser = createCustUser((String) this
									.getColDataByIndex(0, colList).getCellObj());
							CustomerInfo customerInfo = createCustomerInfo((String) this
									.getColDataByIndex(2, colList).getCellObj());
							CustDept custDept = createCustDept((String) this
									.getColDataByIndex(3, colList).getCellObj());

							for (ColData colData : colList) {

								switch (colData.getColIndex()) {
								case 0:
									// sgi NO
									String cellObj = (String) colData
											.getCellObj();
									custUser.setUserCode(cellObj);
									break;
								case 1:
									// 姓名Name
									cellObj = (String) colData.getCellObj();
									custUser.setUserName(cellObj);
									break;
								case 2:
									// 客户
									custUser.setCustomerInfo(customerInfo);
									break;
								case 3:
									custUser.setCustDept(custDept);
									// 部门Department /SOA [YTD]
									break;
								case 4:
									// 岗位名称（中文）Position (Chinese) [YTD]
									cellObj = (String) colData.getCellObj();
									custUser.setPositionZh(cellObj);
									break;
								case 5:
									// 岗位名称（英语）Position (English) [YTD]
									cellObj = (String) colData.getCellObj();
									custUser.setPositionEn(cellObj);
									break;
								case 6:
									// 在职离职状况 In & Out [YTD]
									cellObj = (String) colData.getCellObj();
									/**
									 * 1 在职 2 离职 3 待业 99 删除
									 */
									byte job = 1;
									switch (cellObj) {
									case "在职":
										job = 1;
										break;
									case "离职":
										job = 2;
										break;
									case "待业":
										job = 3;
										break;
									case "删除":
										job = 99;
										break;
									}
									custUser.setOnJob(job);
									break;
								case 7:
									// 岗位类别(正式工／临时工) Post Category
									// (Permenant/Temp)
									// [YTD]
									cellObj = (String) colData.getCellObj();
									custUser.setPositionZh(cellObj);
									break;
								case 8:
									// 成本中心Cost Center [YTD]
									cellObj = (String) colData.getCellObj();
									custUser.setPositionZh(cellObj);
									break;
								case 9:
									// 性别Gendar
									cellObj = (String) colData.getCellObj();
									byte sex = 1;
									switch (cellObj) {
									case "男Male":
										sex = 1;
										break;
									case "女Female":
										sex = 2;
										break;
									}
									custUser.setSex(sex);
									break;
								case 10:
									// 身份证号码 ID Card No.
									cellObj = (String) colData.getCellObj();
									custUser.setCertificateType(10100101);
									custUser.setCertificate(cellObj);
									break;
								case 11:
									// 出生年月Birth Date
									cellObj = (String) colData.getCellObj();
									if(StringUtils.isEmpty(cellObj)){
										break;
									}
									Date date = DateUtil.stringToDate(cellObj,
											"yyyy-MM-dd hh:mm:ss");
									custUser.setBirthdate(date);
									break;
								case 12:
									// 年龄Age
									cellObj = (String) colData.getCellObj();
									// custUser.set(cellObj);
									break;
								case 13:
									// 入司日期Join Company Date
									cellObj = (String) colData.getCellObj();
									if(StringUtils.isEmpty(cellObj)){
										break;
									}
									Date employeTime = DateUtil.stringToDate(
											cellObj, "yyyy-MM-dd hh:mm:ss");
									custUser.setEmployeTime(employeTime);
									break;
								case 14:
									// 离职日期Out Date
									cellObj = (String) colData.getCellObj();
									if(StringUtils.isEmpty(cellObj)){
										break;
									}
									Date outdutyTime = DateUtil.stringToDate(
											cellObj, "yyyy-MM-dd hh:mm:ss");
									custUser.setOutdutyTime(outdutyTime);
									break;
								default:

									break;
								}
							}
							return custUser;
						}

						@Override
						public void init() {
							this.dataList = new ArrayList<CustUserData>();
						}

						@Override
						public List<ColData> getCellList(int index) {
							for (CustUserData custUserData : dataList) {
								if (custUserData.getIndex() == index) {
									return custUserData.getCellList();
								}
								return null;
							}

							return null;
						}

						@Override
						public Row getRow() {
							return row;
						}

						@Override
						public Object handleCell(Row row, Cell cell) {
							int rowIndex = row.getRowNum();
							if(rowIndex == 0){
								return null;
							}
							 
							List<ColData> colList = getCellList(rowIndex);

							int colIndex = cell.getColumnIndex();
							String cellValue = PoiUtil.getCellValue(cell);
							Object cellObj = null;
							if (isStartColumn(colIndex)) {
								System.out.print("[index] 开始了新的一行数据处理!!");
								if (cellValue.isEmpty()) {
									// 忽略该行

								} else {

									if (colList == null) {
										colList = new ArrayList<ColData>();
									}

									this.setCurrentCellList(rowIndex, colList);
								}
							}

							if (colList == null) {
								System.out.println("该行[" + rowIndex
										+ "]没有初始化!!"  );
								readExceRowFilter.addColMessage(rowIndex,
										colIndex, "系统读取怀疑该行["+rowIndex+"]是空行!!");
								readExceRowFilter.setHasErrorCell(true);
								return null;
							}

							if (!cellValue.isEmpty()) {
								System.out.print("[" + cellValue + "]");
							}

							System.out.print("[index]" + colIndex);
							cellObj = (String) cellValue;
							ColData colData = new ColData();
							colData.setColIndex(colIndex);

							switch (colIndex) {
							case 0:
								// sgi NO
								cellObj = (String) cellValue;
								break;
							case 1:
								// 姓名Name

								cellObj = (String) cellValue;
								break;
							case 2:
								// 客户
								break;
							case 3:
								// 部门Department /SOA [YTD]

								break;
							case 4:
								// 岗位名称（中文）Position (Chinese) [YTD]

								break;
							case 5:
								// 岗位名称（英语）Position (English) [YTD]

								break;
							case 6:
								// 在职离职状况 In & Out [YTD]
								/**
								 * 1 在职 2 离职 3 待业 99 删除
								 */
								if (cellObj != null
										&& (cellObj.equals("在职In")
												|| cellObj.equals("离职Out") || cellObj
													.equals("待业"))) {

								} else {
									readExceRowFilter.addColMessage(rowIndex,
											colIndex, "在职状况取值范围[在职,离职,待业]");
									readExceRowFilter.setHasErrorCell(true);
								}
								break;
							case 7:
								// 岗位类别(正式工／临时工) Post Category (Permenant/Temp)
								// [YTD]
								/**
								 * 正式Permanent 临时Temp
								 */
								if (cellObj != null
										&& (cellObj.equals("正式Permanent") || cellObj
												.equals("临时Temp"))) {
								} else {
									readExceRowFilter.addColMessage(rowIndex,
											colIndex,
											"岗位类别取值范围[正式Permanent,临时Temp]");
									readExceRowFilter.setHasErrorCell(true);
								}
								break;
							case 8:
								// 成本中心Cost Center [YTD]

								break;
							case 9:
								// 性别Gendar
								// 男Male
								// 女Female
								if (cellObj != null
										&& (cellObj.equals("男Male") || cellObj
												.equals("女Female"))) {
								} else {
									readExceRowFilter.addColMessage(rowIndex,
											colIndex, "性别取值范围[男Male,女Female]");
									readExceRowFilter.setHasErrorCell(true);
								}
								break;
							case 10:
								// 身份证号码 ID Card No.

								break;
							case 11:
								// 出生年月Birth Date

								break;
							case 12:
								// 年龄Age

								break;
							case 13:
								// 入司日期Join Company Date

								break;
							case 14:
								// 离职日期Out Date

								break;
							default:

								break;
							}
							colData.setCellObj(cellObj);
							colList.add(colData);
							return null;
						}

						private void setCurrentCellList(int index,
								List<ColData> colList) {
							List<ColData> colData = getCellList(index);

							if (colData == null) {
								this.dataList.add(new CustUserData(index,
										colList));
							} else {
								this.dataList.set(index, new CustUserData(
										index, colList));
							}
						}

						private boolean isStartColumn(int index) {
							if (index == 0) {
								return true;
							}
							return false;
						}
					});
			/**
			 * 执行保存
			 */
			if (readExceRowFilter.isHasErrorCell()) {
				System.out.println("发现标有有些错误!纠正后再重新导入 !!");
				return;
			}
			if (custUser != null) {
				 
				System.out.println("开始保存导入的用户的数据");
			}

		}
	}

}
