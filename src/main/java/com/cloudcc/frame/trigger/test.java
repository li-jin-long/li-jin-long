package com.cloudcc.frame.trigger;

import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.trigger.TriggerEditor;

import java.util.HashMap;
import java.util.Map;

public class test extends TriggerEditor {
    private  void 当提交审核时修改(){
        String approvalStatus = record_new.get("spzt")== null ? "" : record_new.get("spzt")+"";
        double jifeng = 0;
        if (approvalStatus.equals("审批通过")){
            String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
            Integer moneys = Integer.parseInt(money);
            if (moneys < 10000) {
            } else if (moneys > 10000 && 50000 > moneys) {
                jifeng = moneys;
            } else if (moneys > 50000 && moneys < 100000) {
                jifeng = (moneys * 1.5);
            }else if (moneys>100000){
                jifeng = (moneys * 2);
            }

            record_new.put("jf",jifeng);
        }else if (approvalStatus.equals("审批拒绝")){
            String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
            Integer moneys = Integer.parseInt(money);
            jifeng = moneys+jifeng;
            record_new.put("jf",jifeng);
        }
    }

    private void test(){
        CCService ccService = new CCService(userInfo);
            String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
            System.out.println(money);
    }
}

