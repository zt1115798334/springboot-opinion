package com.zt.opinion.repository;

import java.util.List;

import com.zt.opinion.entity.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * <p>Title: GroupRepository</p>
 * <p>Description: 企业分组信息数据库接口</p>
 * @author zhangtong
 * @date 2017年6月20日
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {
	
	/**
	 * 
	 * <p>Description: 根据id修改分组名称</p>
	 * @param id
	 * @param groupName
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Modifying
	@Query("UPDATE Group SET groupName = ?2 WHERE id = ?1")
	Integer updateGroupName(Integer id, String groupName);
	
	/**
	 * 
	 * <p>Description: 根据用户账户查看企业分组信息</p>
	 * @param userAccount
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	List<Group> findListByUserAccount(String userAccount);
	
	/**
	 * 
	 * <p>Description: 根据用户账户，分组名称查看企业分组信息</p>
	 * @param userAccount
	 * @param groupName
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	Group findOneByUserAccountAndGroupName(String userAccount, String groupName);
	
	/**
	 * 
	 * <p>Description: 根据用户账户，分组名称,不是本id查看企业分组信息</p>
	 * @param userAccount
	 * @param groupName
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	Group findOneByUserAccountAndGroupNameAndIdNot(String userAccount, String groupName, Integer id);
}
