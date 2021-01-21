package com.jun.plugin.utils.rss.collect.ganji;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jun.plugin.utils.rss.collect.Main;
import com.jun.plugin.utils.rss.collect.utils.ExcelUtil;
import com.jun.plugin.utils.rss.collect.utils.UtilTools;

/**
 * @author Wujun
 * @Url www.cntaige.com
 * @Date 2014年11月11日
 */
public class GanjiExcel {
	private ExcelUtil excelUtil;

	public GanjiExcel() {
		excelUtil = new ExcelUtil();
		createTitle();
	}

	public void setCell(int row, String company, String tel, String name,
			String category, String addr, String companyItr) {
		// 写入行数据
		excelUtil.setRow(row);
		excelUtil.setCell(0, company);
		// excelUtil.setCell(1, tel);
		try {
			insertImage(tel, (short) 1, row);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		excelUtil.setCell(2, name);
		excelUtil.setCell(3, category);
		excelUtil.setCell(4, addr);
		excelUtil.setCell(5, companyItr);
	}

	public void saveFile(int num) {
		// 保存文件
		String path = Main.getCollectPath() + UtilTools.getNowTimeNumber()
				+ ".xls";
		try {
			excelUtil.createFile(path);
			System.out.println(UtilTools.getNowTime());
			System.out.println("表格导出完成");
			System.out.println("总共导出：" + num + "条");
			System.out.println("路径：" + path);
			GanjiDB.getInstance().closeDB();
			UtilTools.deleteAllFile(Main.getCollectPath() + "cache/");// 删除电话图片缓存
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private void insertImage(String path, short cel, int row)
			throws IOException {
		// 插入图片
		if (path == null)
			return;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedImage bufferedImage = ImageIO.read(new File(path));
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
		HSSFPatriarch hssfPatriarch = excelUtil.getSheet()
				.createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor(4, 4, 83 * 9, 25 * 9,
				cel, row, cel, row);
		hssfPatriarch.createPicture(
				anchor,
				excelUtil.getwork().addPicture(
						byteArrayOutputStream.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_PNG));
	}

	private void createTitle() {
		// 创建第一行标题
		excelUtil.setRow(0);
		excelUtil.setCell(0, "公司名称");
		excelUtil.setCell(1, "电话");
		excelUtil.setCell(2, "联系人");
		excelUtil.setCell(3, "行业");
		excelUtil.setCell(4, "地址");
		excelUtil.setCell(5, "公司介绍");
		excelUtil.getSheet().setColumnWidth(1, 5 * 1000);
		excelUtil.getSheet().setColumnWidth(0, 8 * 1000);
		excelUtil.getSheet().setColumnWidth(4, 6 * 1000);
		excelUtil.getSheet().setDefaultRowHeight((short) (50 * 10));
	}
}
