package com.zt.opinion.repository;

import java.util.List;

import com.zt.opinion.entity.MonitorEnterprise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * <p>Title: MonitorEnterpriseRepository</p>
 * <p>Description: 监控企业信息数据库接口</p>
 *
 * @author zhangtong
 * @date 2017年6月21日
 */
public interface MonitorEnterpriseRepository extends CrudRepository<MonitorEnterprise, Long> {

    /**
     * <p>Description: 根据账户查询监控企业信息</p>
     *
     * @param userAccount
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    List<MonitorEnterprise> findListByUserAccount(String userAccount);

    /**
     * <p>Description: 根据账户和分组id，公司名称，并且不等于该id的查询企业信息</p>
     *
     * @param userAccount
     * @param groupId
     * @param enterpriseName
     * @param id
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    @Query(value = "SELECT a.* FROM t_monitor_enterprise a INNER JOIN t_group_monitor_enterprise b ON a.id = b.id WHERE a.user_account =?1 AND b.group_id =?2 AND a.enterprise_name = ?3 AND a.id <> ?4", nativeQuery = true)
    MonitorEnterprise findListByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(String userAccount, Integer groupId, String enterpriseName, Long id);
}
