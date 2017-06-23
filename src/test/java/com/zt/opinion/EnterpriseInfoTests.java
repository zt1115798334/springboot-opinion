package com.zt.opinion;

import com.zt.opinion.entity.EnterpriseInfo;
import org.junit.Test;

public class EnterpriseInfoTests extends BaseTests {
	
	@Test
	public void testAddEnterprise(){
		String url = "/enterprise/addEnterprise";
		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
		enterpriseInfo.setEnterpriseName("江苏裕顺汽车电子传动科技有限公司");
		enterpriseInfo.setRegisteredCapital("5000万元人民币");
		enterpriseInfo.setBusinessScope("汽车电子传动控制系统研发；汽车电子传动控制设备、液力变矩器、液力缓速器、自动变速器、汽车电子传动装置、机械零部件、金属结构件、运动防护用具、模具、车辆配件的制造,加工；冶金专用设备的制造、安装；塑料制品、金属材料、建筑材料、电子产品、化工产品及原料（除危险品）的销售；自营和代理各类商品及技术的进出口业务，国家限定企业经营或禁止进出口的商品和技术除外。（依法须经批准的项目，经相关部门批准后方可开展经营活动）");
		enterpriseInfo.setLegalPerson("邵长兵");
		enterpriseInfo.setEnterpriseAbbreviation("江苏裕顺汽车电子传动科技");
		String response = performAndGetResponse(url, enterpriseInfo);
		logger.info("添加企业，参数：{}，响应结果：{}", enterpriseInfo, response);
	}
	
}
