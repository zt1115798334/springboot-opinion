package com.zt.opinion.service;

import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.entity.MonitorEnterprise;

import java.util.List;


public interface MonitorEnterpriseService {
	
	/**
	 * 
	 * <p>Description: 添加</p>
	 * @param entity
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean add(MonitorEnterprise entity);
	
	/**
	 * 
	 * <p>Description: 删除</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean del(Long id);
	
	/**
	 * 
	 * <p>Description: 根据监控企业id集合删除企业</p>
	 * @param ids
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean batchDel(List<Long> ids);
	
	/**
	 * 
	 * <p>Description: 修改</p>
	 * @param entity
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean update(MonitorEnterprise entity);
	
	/**
	 * 
	 * <p>Description: 根据用户账户查询</p>
	 * @param userAccount
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	List<MonitorEnterprise> listMonitorEnterprise(String userAccount);
	
	/**
	 * 
	 * <p>Description: 批量添加</p>
	 * @param list
	 * @param groupId
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean batchAdd(List<MonitorEnterprise> list, Integer groupId);
	
	/**
	 * 
	 * <p>Description: 根据id查询</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	MonitorEnterprise findOne(Long id);
	
	/**
	 * 
	 * <p>Description: 根据id集合获取</p>
	 * @param ids
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	List<MonitorEnterprise> findListByIds(List<Long> ids);
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件查询</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月23日
	 */
	List<MonitorEnterprise> findByMonitorEnterprise(MonitorEnterprise monitorEnterprise);
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件分页查询</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月23日
	 */
	PageResult<MonitorEnterprise> findPageByMonitorEnterprise(MonitorEnterprise monitorEnterprise);
	
	/**
	 * 
	 * <p>Description: 根据用户账户，分组id和监控企业名称查询企业信息是否存在</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean existsByUserAccountAndGroupIdAndEnterpriseName(MonitorEnterprise monitorEnterprise);
	
	/**
	 * 
	 * <p>Description: 根据用户账户，分组id和监控企业名称,不是本id查询企业信息是否存在</p>
	 * @param userAccount
	 * @param groupId
	 * @param enterpriseName
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean existsByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(String userAccount, Integer groupId, String enterpriseName, Long id);
}
