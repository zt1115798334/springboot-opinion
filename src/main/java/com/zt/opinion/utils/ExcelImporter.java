package com.zt.opinion.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * <p>
 * Title: ExcelImporter
 * </p>
 * <p>
 * Description: excel导入工具类
 * </p>
 * 
 * @author wjc
 * @date 2017年1月16日
 */
public class ExcelImporter {

	private String fileName;
	
	public ExcelImporter(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 
	 * <p>Description: 获取关键字列表</p>
	 * @param fis
	 * @param keywordOwner 关键字所有者的用户账号
	 * @param createUser 创建人的账号
	 * @return
	 * @author wjc
	 * @date 2017年1月18日
	 */
//	public Map<String, Keyword> getKeywordList(InputStream fis, 
//			String keywordOwner, String createUser){
//		Map<String, Keyword> result = new HashMap<String, Keyword>();
//		try {
//			Workbook book = getWorkbook(fis);
//			if(book != null){
//				Date currentDate = DateUtils.currentDate();
//				
//				Sheet sheet = book.getSheetAt(0);
//				int firstRow = sheet.getFirstRowNum();
//				int lastRow = sheet.getLastRowNum();
//				// 除去标题行
//				for (int i = firstRow + 1; i < lastRow + 1; i++) {
//					Row row = sheet.getRow(i);
//					
//					Keyword kw = new Keyword();
//					int firstCell = row.getFirstCellNum();
//					int lastCell = row.getLastCellNum();
//					for (int j = firstCell; j < lastCell; j++) {
//						Cell cell = row.getCell(j);
//						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//							cell.setCellType(Cell.CELL_TYPE_STRING);
//						}
//						String value = cell.getStringCellValue();
//						if(j == 0){
//							kw.setKeyword(value);//关键字
//						}
//					}
//					String keyword = kw.getKeyword();
//					
//					kw.setUserAccount(keywordOwner);
//					kw.setCreateDate(currentDate);
//					kw.setCreateUser(createUser);
//					kw.setUpdateDate(currentDate);
//					kw.setUpdateUser(createUser);
//					kw.setStatus(SysConst.KeywordStatus.ENABLE.getStatus());
//					kw.setKeywordInitial(StringUtils.getFirstCharOfWord(keyword));
//					if(result.get(keyword) != null){
//						Keyword tempKw = result.get(keyword);
//						tempKw.setOccurrenceNumber(tempKw.getOccurrenceNumber()+1);
//						result.put(keyword, tempKw);
//					}else{
//						kw.setOccurrenceNumber(1);
//						result.put(keyword, kw);
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	public Workbook getWorkbook(InputStream fis) throws IOException{
		Workbook book = null;
		if(isExcel2003()){
			book = new HSSFWorkbook(fis);
		}else if(isExcel2007()){
			book = new XSSFWorkbook(fis);
		}
		return book;
	}
	
	/**
	 * 
	 * <p>Description: 根据文件名判断是否是2007格式的excel</p>
	 * @return
	 * @author wjc
	 * @date 2017年1月17日
	 */
	private boolean isExcel2007(){
		if(fileName == null){
			return false;
		}
		return fileName.toLowerCase().endsWith(".xlsx");
	}
	
	/**
	 * 
	 * <p>Description: 根据文件名判断是否是2007格式的excel</p>
	 * @return
	 * @author wjc
	 * @date 2017年1月17日
	 */
	private boolean isExcel2003(){
		if(fileName == null){
			return false;
		}
		return fileName.toLowerCase().endsWith(".xls");
	}
	
}
