package com.zt.opinion.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 
 * <p>Title: ExcelExporter</p>
 * <p>Description: excel导出工具类</p>
 * @author wjc
 * @date 2017年2月18日
 */
public class ExcelExporter {

	protected Workbook workbook;//工作簿对象
	protected OutputStream os;
	// 显示的导出表的标题
	protected String sheetName;
	// 导出表的列名
	protected String[] columnNames;
	//每行数据的字段名称的映射
	protected String[] mappingKeys;
	//需要导出的数据
	protected List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

	// 构造方法，传入要导出的数据
	public ExcelExporter(Workbook workbook, OutputStream os, 
			String sheetName) {
		this.workbook = workbook;
		this.os = os;
		this.sheetName = sheetName;
	}
	
	/**
	 * 设置表格数据
	 * @param columnNames 导出Excel表格的列名
	 * @param mappingKeys 每行数据对应的字段名称映射
	 * @param dataList 表格数据列表
	 */
	public void setTableData(String[] columnNames, 
			String[] mappingKeys, List<Map<String, Object>> dataList){
		this.dataList = dataList;
		this.columnNames = columnNames;
		this.mappingKeys = mappingKeys;
	}

	/**
	 * 导出excel文件
	 * 说明：该方法导出的是表格数据，如果需要导出图片，请调用exportImage()方法
	 * @param out
	 */
	public void export(HttpServletRequest request,
			HttpServletResponse response,
			String fileName) throws Exception {
		try {
			if(workbook != null){
				Sheet sheet = workbook.createSheet(sheetName); // 创建工作表

				CellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
				CellStyle style = this.getStyle(workbook); // 单元格样式对象

				setTableTitle(sheet, columnTopStyle, 0);

				// 定义所需列数
				setColumnHeader(sheet, 0, columnTopStyle);
				setCellData(sheet, 0, " ", style);
				adjustCellWidth(sheet);

				setHeader(request, response, fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportWithoutTitle(HttpServletRequest request,
			HttpServletResponse response,
			String fileName) throws Exception {
		try {
			if(workbook != null){
				Sheet sheet = workbook.createSheet(sheetName); // 创建工作表

				CellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
				CellStyle style = this.getStyle(workbook); // 单元格样式对象

				// 定义所需列数
				setColumnHeaderWithoutTitle(sheet, columnTopStyle);
				setCellData(sheet, 0, " ", style);
				adjustCellWidth(sheet);

				setHeader(request, response, fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置表格标题
	 * @param sheet
	 * @param columnTopStyle
	 */
	protected void setTableTitle(Sheet sheet, CellStyle columnTopStyle, 
			int blankRow){
		// 产生表格标题行
		Row rowm = sheet.createRow(0);
		Cell cellTiltle = rowm.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (columnNames.length - 1)));
		cellTiltle.setCellStyle(columnTopStyle);
		cellTiltle.setCellValue(sheetName);
	}
	
	/**
	 * 设置列头
	 * @param sheet
	 * @param blankRow 空白行的行号
	 * @param columnNum 列数
	 * @param columnTopStyle 列头的单元格样式
	 */
	protected void setColumnHeader(Sheet sheet, int blankRow, 
			CellStyle columnTopStyle){
		Row row = sheet.createRow(blankRow+2);

		CreationHelper factory = workbook.getCreationHelper();
		// 将列头设置到sheet的单元格中
		for (int n = 0, columnNum = columnNames.length; n < columnNum; n++) {
			Cell cellRowName = row.createCell(n); // 创建列头对应个数的单元格
			cellRowName.setCellType(Cell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
			RichTextString text = factory.createRichTextString(columnNames[n]);
			cellRowName.setCellValue(text); // 设置列头单元格的值
			cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}
	}
	
	/**
	 * 设置列头
	 * @param sheet
	 * @param blankRow 空白行的行号
	 * @param columnNum 列数
	 * @param columnTopStyle 列头的单元格样式
	 */
	protected void setColumnHeaderWithoutTitle(Sheet sheet, CellStyle columnTopStyle){
		Row row = sheet.createRow(0);
		sheet.setColumnWidth(row.getRowNum(), 12*256);
		CreationHelper factory = workbook.getCreationHelper();
		// 将列头设置到sheet的单元格中
		for (int n = 0, columnNum = columnNames.length; n < columnNum; n++) {
			Cell cellRowName = row.createCell(n); // 创建列头对应个数的单元格
			cellRowName.setCellType(Cell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
			RichTextString text = factory.createRichTextString(columnNames[n]);
			cellRowName.setCellValue(text); // 设置列头单元格的值
			cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}
	}
	
	/*
	 * 列头单元格样式
	 */
	protected CellStyle getColumnTopStyle(Workbook workbook) {
		// 设置字体
		Font font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		CellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(CellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(IndexedColors.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(CellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(IndexedColors.BLACK.index);
		// 设置右边框;
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(IndexedColors.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(CellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(IndexedColors.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		return style;
	}
	
	/*
	 * 列数据信息单元格样式
	 */
	protected CellStyle getStyle(Workbook workbook) {
		// 设置字体
		Font font = workbook.createFont();
		// 设置字体大小
		// font.setFontHeightInPoints((short)10);
		// 字体加粗
		//font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		CellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(CellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(IndexedColors.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(CellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(IndexedColors.BLACK.index);
		// 设置右边框;
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(IndexedColors.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(CellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(IndexedColors.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		return style;
	}
	
	public void write() throws IOException{
		if(isReady()){
			try {
				workbook.write(os);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				workbook.close();
				if(os != null){
					os.close();
				}
			}
		}else {
			throw new IOException("the parameter [XSSFWorkbook workbook] is null or the parameter [OutputStream os] is null");
		}
	}
	
	private boolean isReady(){
		return (workbook != null && os != null);
	}
	
	protected void setHeader(HttpServletRequest request,
			HttpServletResponse response, String fileName) {
		try {
			if(response != null){
				String filename = "";
				if (StringUtils.isIE(request)) {
					filename = new String(fileName + ".xlsx");
					filename = URLEncoder.encode(filename, "UTF-8");
				} else {
					filename = new String((fileName + ".xlsx").getBytes(),
							"iso-8859-1");
				}
				String header = "attachment; filename=\"" + filename + "\"";
				response.reset();
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", header);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据单元格的内容动态调整单元格的宽度，让列宽随着导出的列长自动适应
	 * @param sheet
	 * @param columnNum
	 */
	protected void adjustCellWidth(Sheet sheet){
		for (int i = 0, columnNum = columnNames.length; i < columnNum; i++) {
			int columnWidth = sheet.getColumnWidth(i) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				Row currentRow = null;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(i) != null) {
					Cell currentCell = currentRow.getCell(i);
					currentCell.setCellType(Cell.CELL_TYPE_STRING);
					int length = currentCell.getStringCellValue().getBytes().length;
					if (columnWidth < length) {
						columnWidth = length;
					}
				}
			}
			if (i == 0) {
				sheet.setColumnWidth(i, (columnWidth - 2) * 256);
			} else {
				sheet.setColumnWidth(i, (columnWidth + 4) * 256);
			}
		}
	}
	
	/**
	 * 设置单元格的数据
	 * @param sheet
	 * @param blankRow
	 * @param defaultValue
	 * @param style
	 */
	protected void setCellData(Sheet sheet, int blankRow, String defaultValue, 
			CellStyle style){
		if(dataList != null){
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> item = dataList.get(i);// 遍历每个对象
				Row row = sheet.createRow(i + 3+blankRow);// 创建所需的行数

				for (int j = 0; j < mappingKeys.length; j++) {
					Cell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
					Object value = item.get(mappingKeys[j]);
					if(value == null){
						cell.setCellValue(defaultValue);
					}else{
						cell.setCellValue(value.toString());
					}
					cell.setCellStyle(style); // 设置单元格样式
				}
			}
		}
	}
	
	/**
	 * 设置单元格的数据
	 * @param sheet
	 * @param blankRow
	 * @param defaultValue
	 * @param style
	 * @param mappingKeys
	 * @param dataList
	 */
	protected void setCellData(Sheet sheet, int blankRow, String defaultValue, 
			CellStyle style, String[] mappingKeys, List<Map<String, Object>> dataList){
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, Object> item = dataList.get(i);// 遍历每个对象
			Row row = sheet.createRow(i + 3+blankRow);// 创建所需的行数

			for (int j = 0; j < mappingKeys.length; j++) {
				Cell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
				Object value = item.get(mappingKeys[j]);
				if(value == null){
					cell.setCellValue(defaultValue);
				}else{
					cell.setCellValue(value.toString());
				}
				cell.setCellStyle(style); // 设置单元格样式
			}
		}
	}
	
}
