/*
 * Copyright 2017-2020 吴学文 and java110 team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.java110.community.cmd.communitySettingKey;

import com.alibaba.fastjson.JSONObject;
import com.java110.core.annotation.Java110Cmd;
import com.java110.core.context.ICmdDataFlowContext;
import com.java110.core.event.cmd.Cmd;
import com.java110.core.event.cmd.CmdEvent;
import com.java110.dto.community.CommunitySettingDto;
import com.java110.dto.community.CommunitySettingKeyDto;
import com.java110.intf.community.ICommunitySettingInnerServiceSMO;
import com.java110.intf.community.ICommunitySettingKeyV1InnerServiceSMO;
import com.java110.utils.exception.CmdException;
import com.java110.utils.util.Assert;
import com.java110.utils.util.BeanConvertUtil;
import com.java110.vo.ResultVo;
import org.slf4j.Logger;
import com.java110.core.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 类表述：查询
 * 服务编码：communitySettingKey.listCommunitySettingKey
 * 请求路劲：/app/communitySettingKey.ListCommunitySettingKey
 * add by 吴学文 at 2021-10-10 10:56:18 mail: 928255095@qq.com
 * open source address: https://gitee.com/wuxw7/MicroCommunity
 * 官网：http://www.homecommunity.cn
 * 温馨提示：如果您对此文件进行修改 请不要删除原有作者及注释信息，请补充您的 修改的原因以及联系邮箱如下
 * // modify by 张三 at 2021-09-12 第10行在某种场景下存在某种bug 需要修复，注释10至20行 加入 20行至30行
 */
@Java110Cmd(serviceCode = "communitySettingKey.listCommunitySettingKey")
public class ListCommunitySettingKeyCmd extends Cmd {

    private static Logger logger = LoggerFactory.getLogger(ListCommunitySettingKeyCmd.class);
    @Autowired
    private ICommunitySettingKeyV1InnerServiceSMO communitySettingKeyV1InnerServiceSMOImpl;

    @Autowired
    private ICommunitySettingInnerServiceSMO communitySettingInnerServiceSMOImpl;

    @Override
    public void validate(CmdEvent event, ICmdDataFlowContext cmdDataFlowContext, JSONObject reqJson) {
        //super.validatePageInfo(reqJson);
        Assert.hasKeyAndValue(reqJson, "communityId", "未包含小区");
        Assert.hasKeyAndValue(reqJson, "settingType", "未包含类型");
    }

    @Override
    public void doCmd(CmdEvent event, ICmdDataFlowContext cmdDataFlowContext, JSONObject reqJson) throws CmdException {

        CommunitySettingKeyDto communitySettingKeyDto = BeanConvertUtil.covertBean(reqJson, CommunitySettingKeyDto.class);

        int count = communitySettingKeyV1InnerServiceSMOImpl.queryCommunitySettingKeysCount(communitySettingKeyDto);

        List<CommunitySettingKeyDto> communitySettingKeyDtos = null;

        if (count > 0) {
            communitySettingKeyDtos = communitySettingKeyV1InnerServiceSMOImpl.queryCommunitySettingKeys(communitySettingKeyDto);
        } else {
            communitySettingKeyDtos = new ArrayList<>();
        }

        // todo 刷入 小区值
        computeKeyValue(communitySettingKeyDtos, reqJson.getString("communityId"), reqJson.getString("settingType"));

        if (!reqJson.containsKey("row")) {
            reqJson.put("row", 10);
        }

        ResultVo resultVo = new ResultVo((int) Math.ceil((double) count / (double) reqJson.getInteger("row")), count, communitySettingKeyDtos);

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(resultVo.toString(), HttpStatus.OK);

        cmdDataFlowContext.setResponseEntity(responseEntity);
    }

    private void computeKeyValue(List<CommunitySettingKeyDto> communitySettingKeyDtos, String communityId, String settingType) {
        if (communitySettingKeyDtos == null || communitySettingKeyDtos.size() < 1) {
            return;
        }

        CommunitySettingDto communitySettingDto = new CommunitySettingDto();
        communitySettingDto.setSettingType(settingType);
        communitySettingDto.setCommunityId(communityId);
        List<CommunitySettingDto> communitySettingDtos = communitySettingInnerServiceSMOImpl.queryCommunitySettings(communitySettingDto);
        if (communitySettingDtos == null || communitySettingDtos.size() < 1) {
            return;
        }

        for (CommunitySettingKeyDto communitySettingKeyDto : communitySettingKeyDtos) {
            for (CommunitySettingDto tmpCommunitySettingDto : communitySettingDtos) {
                if (!communitySettingKeyDto.getSettingKey().equals(tmpCommunitySettingDto.getSettingKey())) {
                    continue;
                }
                communitySettingKeyDto.setSettingValue(tmpCommunitySettingDto.getSettingValue());
            }

        }
    }
}
