package com.zt.opinion.repository;

import java.util.List;

import com.zt.opinion.entity.GroupMonitorEnterprise;
import org.springframework.data.repository.CrudRepository;


/**
 * <p>Title: GroupMonitorEnterpriseRepository</p>
 * <p>Description: 企业分组和监控企业关联信息数据库链接接口</p>
 *
 * @author zhangtong
 * @date 2017年6月21日
 */
public interface GroupMonitorEnterpriseRepository extends CrudRepository<GroupMonitorEnterprise, Long> {

    /**
     * <p>Description: 根据企业分组id删除</p>
     *
     * @param groupId
     * @author zhangtong
     * @date 2017年6月22日
     */
    void deleteByGroupId(Integer groupId);

    /**
     * <p>Description: 企业id删除</p>
     *
     * @param enterpriseId
     * @param enterpriseId
     * @author zhangtong
     * @date 2017年6月22日
     */
    void deleteByEnterpriseId(Long enterpriseId);

    /**
     * <p>Description: 根据企业分组id查询企业分组和监控企业关联信息</p>
     *
     * @param groupId
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    List<GroupMonitorEnterprise> findListByGroupId(Integer groupId);

    /**
     * <p>Description: 根据分组id查看条数</p>
     *
     * @param groupId
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    long countByGroupId(Integer groupId);
}
