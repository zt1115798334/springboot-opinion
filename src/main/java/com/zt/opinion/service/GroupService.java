package com.zt.opinion.service;

import com.zt.opinion.entity.Group;

import java.util.List;

public interface GroupService {
	
	/**
	 * 
	 * <p>Description: 添加</p>
	 * @param entity
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean add(Group entity);
	
	/**
	 * 
	 * <p>Description: 根据id删除</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean del(Integer id);
	
	/**
	 * 
	 * <p>Description: 根据id更新分组名称</p>
	 * @param id
	 * @param groupName
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean updateGroupName(Integer id, String groupName);
	
	/**
	 * 
	 * <p>Description: 根据用户账号查询</p>
	 * @param userAccount
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	List<Group> listGroup(String userAccount);
	
	/**
	 * 
	 * <p>Description: 默认分组添加</p>
	 * @param userAccount
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	Group defaultGroup(String userAccount);
	
	/**
	 * 
	 * <p>Description: 根据分组名称和账户名称获取分组信息</p>
	 * @param userAccount
	 * @param groupName
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	Group findOneByUserAccountGroupName(String userAccount, String groupName);
	
	/**
	 * 
	 * <p>Description: 根据分组名称和账户名称判断分组是否存在</p>
	 * @param userAccount
	 * @param groupName
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean existsByUserAccountAndGroupName(String userAccount, String groupName);
	
	/**
	 * 
	 * <p>Description: 根据分组名称和账户名,和不等于该id称判断分组是否存在</p>
	 * @param userAccount
	 * @param groupName
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	boolean existsByUserAccountAndGroupNameAndIdNot(String userAccount, String groupName, Integer id);
}
