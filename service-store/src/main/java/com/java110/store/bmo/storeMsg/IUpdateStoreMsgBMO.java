package com.java110.store.bmo.storeMsg;
import com.java110.po.store.StoreMsgPo;
import org.springframework.http.ResponseEntity;

public interface IUpdateStoreMsgBMO {


    /**
     * 修改商户消息
     * add by wuxw
     * @param storeMsgPo
     * @return
     */
    ResponseEntity<String> update(StoreMsgPo storeMsgPo);


}
