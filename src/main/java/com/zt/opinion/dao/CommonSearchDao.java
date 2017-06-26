package com.zt.opinion.dao;

import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.entity.MonitorEnterprise;

import java.util.List;



/**
 * 
 * <p>Title: CommonSearchDao</p>
 * <p>Description: 公共的查询Dao接口类</p>
 * @author wjc
 * @date 2017年1月3日
 */
public interface CommonSearchDao {
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件查询</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月23日
	 */
	List<MonitorEnterprise> findListByMonitorEnterprise(MonitorEnterprise monitorEnterprise);
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件分页查询</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月23日
	 */
	PageResult<MonitorEnterprise> findPageByMonitorEnterprise(MonitorEnterprise monitorEnterprise);
}
