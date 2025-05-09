package com.java110.community.dao;


import com.java110.utils.exception.DAOException;


import java.util.List;
import java.util.Map;

/**
 * 信息分类组件内部之间使用，没有给外围系统提供服务能力
 * 信息分类服务接口类，要求全部以字符串传输，方便微服务化
 * 新建客户，修改客户，删除客户，查询客户等功能
 *
 * Created by wuxw on 2016/12/27.
 */
public interface IActivitiesTypeServiceDao {


    /**
     * 保存 信息分类信息
     * @param info
     * @throws DAOException DAO异常
     */
    void saveActivitiesTypeInfo(Map info) throws DAOException;




    /**
     * 查询信息分类信息（instance过程）
     * 根据bId 查询信息分类信息
     * @param info bId 信息
     * @return 信息分类信息
     * @throws DAOException DAO异常
     */
    List<Map> getActivitiesTypeInfo(Map info) throws DAOException;



    /**
     * 修改信息分类信息
     * @param info 修改信息
     * @throws DAOException DAO异常
     */
    void updateActivitiesTypeInfo(Map info) throws DAOException;


    /**
     * 查询信息分类总数
     *
     * @param info 信息分类信息
     * @return 信息分类数量
     */
    int queryActivitiesTypesCount(Map info);

}
