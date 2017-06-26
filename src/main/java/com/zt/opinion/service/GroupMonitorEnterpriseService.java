package com.zt.opinion.service;

import com.zt.opinion.entity.GroupMonitorEnterprise;

import java.util.List;


public interface GroupMonitorEnterpriseService {

    /**
     * <p>Description: 添加</p>
     *
     * @param entity
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    boolean add(GroupMonitorEnterprise entity);

    /**
     * <p>Description: 批量添加</p>
     *
     * @param list
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    boolean batchAdd(List<GroupMonitorEnterprise> list);

    /**
     * <p>Description: 根据企业分组id删除</p>
     *
     * @param groupId
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    boolean deleteByGroupId(Integer groupId);

    /**
     * <p>Description: 根据企业id删除</p>
     *
     * @param groupId
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    boolean deleteByEnterpriseId(Long enterpriseId);

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
     * @param enterpriseId
     * @return
     * @author zhangtong
     * @date 2017年6月22日
     */
    long countByEnterpriseId(Integer groupId);
}
