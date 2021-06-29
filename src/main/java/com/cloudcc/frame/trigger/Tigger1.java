package com.cloudcc.frame.trigger;

import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.ServiceResult;
import com.cloudcc.deveditor.trigger.TriggerEditor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 触发器，
 * 相当于service
 * */
public class Tigger1 extends TriggerEditor {

    public void 打印用户名字() {
        double jifeng = 0;
        String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
        Integer moneys = Integer.parseInt(money);
        if (moneys < 10000) {
        } else if (moneys > 10000 && 50000 > moneys) {
            jifeng = moneys;
        } else if (moneys > 50000 && moneys < 100000) {
            jifeng = (moneys * 1.5);
        } else if (moneys > 100000) {
            jifeng = (moneys * 2);
        }
        Map m = new HashMap();
        m.put("beizhu", "当前积分为：" + jifeng);
    }


    public void 根据商品名获取详细信息() {

    }

    public void 积分触发() {
        CCService ccs = new CCService(userInfo);
        double jifeng = 0;
        double zqbili = 0;
        List<CCObject> cquery = ccs.cquery("jfgz");
        String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
        Integer moneys = Integer.parseInt(money);
        for (CCObject cquer : cquery) {
            Object zxsz = cquer.get("zxsz");
            Integer min = Integer.parseInt(zxsz.toString());
            Object zdsz = cquer.get("zdsz");
            Integer max = Integer.parseInt(zdsz.toString());
            if (moneys >= min && moneys < max) {
                Object bili = cquer.get("name");
                zqbili = Integer.parseInt(bili.toString());
            }
        }
        jifeng = (moneys * zqbili);
        record_new.put("jf",jifeng);
    }

}
