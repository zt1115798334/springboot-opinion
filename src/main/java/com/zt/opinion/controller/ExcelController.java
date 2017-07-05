package com.zt.opinion.controller;

import com.google.common.collect.Lists;
import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.framework.beans.AjaxResult;
import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.service.ArticleService;
import com.zt.opinion.utils.ExcelImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

	@Autowired
	private ArticleService articleService;

	private List<String> filaPathList = Lists.newArrayList();

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

			if (!isExcel(fileName)) {
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
	 * <p>Description: 根据文件名判断该文件是否是excel文件</p>
	 *
	 * @param fileName
	 * @return
	 * @author wjc
	 * @date 2017年1月16日
	 */
	private boolean isExcel(String fileName) {
		if (fileName == null) {
			return false;
		}
		return (fileName.toLowerCase().endsWith(".xlsx")
				|| fileName.toLowerCase().endsWith(".xls"));
	}

	/**
	 * <p>Description: 导入文章列表</p>
	 *
	 * @param fileName
	 * @param fis
	 * @author wjc
	 * @date 2017年6月27日
	 */
	public void importArticleList(String fileName, InputStream fis) {
		StringBuilder messages = new StringBuilder();

		ExcelImporter importer = new ExcelImporter(fileName);
		Map<String, Article> map = importer.getArticleList(fis);
		if (map != null) {
			List<Article> list = new ArrayList<Article>();
			StringBuilder message = null;
			for (Map.Entry<String, Article> entry : map.entrySet()) {
				Article item = entry.getValue();
				message = new StringBuilder();
				if (articleService.isExist(item.getUrlMD5())) {
					message.append("url[").append(item.getUrl())
							.append("]的文章在系统中已经存在，");
				}
				if (message.length() < 1) {
					list.add(item);
				} else {
					messages.append(message);
				}
			}

			ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
			int count = 0;
			int pageSize = 400;
			List<List<Article>> newList = splitList(list, pageSize);
			for (List<Article> subList : newList) {
				try {
					Future<Integer> future = executor.submit(new Callable<Integer>() {

						@Override
						public Integer call() throws Exception {
							try {
								articleService.batchAdd(subList);
								return subList.size();
							} catch (Exception e) {
								logger.error("批量添加文章时发生异常，异常信息：{}", e);
							}
							return 0;
						}

					});
					count += future.get();
				} catch (ExecutionException | InterruptedException e) {
					logger.error("批量添加文章，获取执行结果时发生异常，异常信息：{}", e);
				}
			}
			executor.shutdown();
			messages.append("共有[").append(count).append("]条数据导入成功");
		}
		logger.info("导入结果：{}", messages);
	}

	private static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		List<List<T>> result = new ArrayList<List<T>>();
		int size = list.size();
		List<T> subList = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			T user = list.get(i);
			if (subList.size() == pageSize) {
				result.add(subList);
				subList = new ArrayList<T>();
				subList.add(user);
			} else {
				subList.add(user);
			}
		}
		result.add(subList);

		return result;
	}

	public void traverseFolder(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						traverseFolder(file2.getAbsolutePath());
					} else {
						filaPathList.add(file2.getAbsolutePath());
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}

}
