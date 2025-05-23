package com.java110.fee.dao;


import com.java110.utils.exception.DAOException;


import java.util.List;
import java.util.Map;

/**
 * 月缴费表组件内部之间使用，没有给外围系统提供服务能力
 * 月缴费表服务接口类，要求全部以字符串传输，方便微服务化
 * 新建客户，修改客户，删除客户，查询客户等功能
 * <p>
 * Created by wuxw on 2016/12/27.
 */
public interface IPayFeeDetailMonthServiceDao {


    /**
     * 保存 月缴费表信息
     *
     * @param info
     * @throws DAOException DAO异常
     */
    void savePayFeeDetailMonthInfo(Map info) throws DAOException;

    int savePayFeeDetailMonthInfos(Map info);


    /**
     * 查询月缴费表信息（instance过程）
     * 根据bId 查询月缴费表信息
     *
     * @param info bId 信息
     * @return 月缴费表信息
     * @throws DAOException DAO异常
     */
    List<Map> getPayFeeDetailMonthInfo(Map info) throws DAOException;


    /**
     * 修改月缴费表信息
     *
     * @param info 修改信息
     * @throws DAOException DAO异常
     */
    void updatePayFeeDetailMonthInfo(Map info) throws DAOException;


    /**
     * 查询月缴费表总数
     *
     * @param info 月缴费表信息
     * @return 月缴费表数量
     */
    int queryPayFeeDetailMonthsCount(Map info);


    List<Map> queryPayFeeDetailMaxMonths(Map info);

    /**
     * 处理需要离散的 缴费记录
     *
     * @param info
     * @return
     */
    List<Map> getWaitDispersedFeeDetail(Map info);

    void deletePayFeeDetailMonthInfo(Map info);

    /**
     * 查询 月数据（供页面使用）
     *
     * @param info
     * @return
     */
    List<Map> queryPagePayFeeDetailMonths(Map info);

    /**
     * 查询 月数据总数（供页面使用）
     *
     * @param info
     * @return
     */
    int queryPagePayFeeDetailMonthsCount(Map info);
}
