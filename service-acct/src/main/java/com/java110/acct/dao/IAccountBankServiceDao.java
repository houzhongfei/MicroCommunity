package com.java110.acct.dao;


import com.java110.utils.exception.DAOException;


import java.util.List;
import java.util.Map;

/**
 * 开户行组件内部之间使用，没有给外围系统提供服务能力
 * 开户行服务接口类，要求全部以字符串传输，方便微服务化
 * 新建客户，修改客户，删除客户，查询客户等功能
 *
 * Created by wuxw on 2016/12/27.
 */
public interface IAccountBankServiceDao {


    /**
     * 保存 开户行信息
     * @param info
     * @throws DAOException DAO异常
     */
    void saveAccountBankInfo(Map info) throws DAOException;




    /**
     * 查询开户行信息（instance过程）
     * 根据bId 查询开户行信息
     * @param info bId 信息
     * @return 开户行信息
     * @throws DAOException DAO异常
     */
    List<Map> getAccountBankInfo(Map info) throws DAOException;



    /**
     * 修改开户行信息
     * @param info 修改信息
     * @throws DAOException DAO异常
     */
    void updateAccountBankInfo(Map info) throws DAOException;


    /**
     * 查询开户行总数
     *
     * @param info 开户行信息
     * @return 开户行数量
     */
    int queryAccountBanksCount(Map info);

}
