package com.zt.opinion.controller;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.framework.beans.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

	@RequestMapping("/importEnterpriseList")
	@ResponseBody
	public AjaxResult importEnterpriseList(@RequestParam("fileName") MultipartFile file) {
		try {
			if (null == file) {
				return fail("导入文件为空，请选择文件");
			}
			String fileName = file.getOriginalFilename();
			String fieldName = file.getName();//表单中file字段的id
			logger.info("fileName: {}, fieldName: {}", fileName, fieldName);
			
			if(!isExcel(fileName)){
				return fail("导入文件必须为excel文件，请重新上传文件");
			}

//			importUserList(fileName, file.getInputStream());
			
			return success("导入成功");
		} catch (Exception e) {
			logger.error("批量添加企业出错，异常信息：{}", e);
		}
		return fail("批量添加企业失败，请联系管理员解决");
	}
	
	/**
	 * 
	 * <p>Description: 根据文件名判断该文件是否是excel文件</p>
	 * @param fileName
	 * @return
	 * @author wjc
	 * @date 2017年1月16日
	 */
	private boolean isExcel(String fileName){
		if(fileName == null){
			return false;
		}
		return (fileName.toLowerCase().endsWith(".xlsx") 
				|| fileName.toLowerCase().endsWith(".xls"));
	}
	
}
