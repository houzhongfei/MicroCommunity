package com.java110.store.bmo.contractChangePlanDetail.impl;

import com.java110.core.annotation.Java110Transactional;
import com.java110.intf.store.IContractChangePlanDetailInnerServiceSMO;
import com.java110.po.contract.ContractChangePlanDetailPo;
import com.java110.store.bmo.contractChangePlanDetail.IDeleteContractChangePlanDetailBMO;
import com.java110.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("deleteContractChangePlanDetailBMOImpl")
public class DeleteContractChangePlanDetailBMOImpl implements IDeleteContractChangePlanDetailBMO {

    @Autowired
    private IContractChangePlanDetailInnerServiceSMO contractChangePlanDetailInnerServiceSMOImpl;

    /**
     * @param contractChangePlanDetailPo 数据
     * @return 订单服务能够接受的报文
     */
    @Java110Transactional
    public ResponseEntity<String> delete(ContractChangePlanDetailPo contractChangePlanDetailPo) {

        int flag = contractChangePlanDetailInnerServiceSMOImpl.deleteContractChangePlanDetail(contractChangePlanDetailPo);

        if (flag > 0) {
            return ResultVo.createResponseEntity(ResultVo.CODE_OK, "保存成功");
        }

        return ResultVo.createResponseEntity(ResultVo.CODE_ERROR, "保存失败");
    }

}
