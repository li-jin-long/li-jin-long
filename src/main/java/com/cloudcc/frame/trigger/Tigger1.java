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
//debug
        if (true) trigger.addErrorMessage("测试");

    }


    public void 根据商品名获取详细信息() {

    }

    public void 积分触发() {
        CCService ccs = new CCService(userInfo);
        double jifeng = 0;
        double zqbili = 0;
        double moneys = 0;
        List<CCObject> cquery = ccs.cquery("jfgz");

        Object money1 = record_new.get("money");
        if (true) trigger.addErrorMessage(money1.toString());

        List<CCObject> ddxx = ccs.cquery("ddxx");

        String money = ddxx.get(0).get("money").toString();
        if (money != null) {
            moneys = Double.parseDouble(money);
        }
        for (CCObject cquer : cquery) {
            Object zxsz = cquer.get("zxsz");
            Double min = Double.parseDouble(zxsz.toString());
            Object zdsz = cquer.get("zdsz");
            Double max = Double.parseDouble(zdsz.toString());
            if (moneys >= min && moneys < max) {
                Object bili = cquer.get("name");
                zqbili = Double.parseDouble(bili.toString());
            }
        }
        jifeng = (moneys * zqbili);

        ddxx.get(0).put("jf", String.valueOf(jifeng));

    }

    public void 积分() {
        {
            String khname = record_new.get("khname") == null ? "" : record_new.get("khname") + "";//获取客户的id
            String spzt = record_new.get("spzt") == null ? "" : record_new.get("spzt") + "";//审批状态最新
            String spzt2 = record_old.get("spzt") == null ? "" : record_old.get("spzt") + "";//审批状态旧数据
            String id = record_new.get("id") == null ? "" : record_new.get("id") + "";//订单id
            double total = 0;
            if (!spzt.equals(spzt2)) {
                if (spzt.equals("审批通过")) {
                    List<CCObject> ddmx = this.cquery("dd", "shangp='" + id + "'");
                    for (CCObject cc :
                            ddmx) {
                        String money = cc.get("money") == null ? "0" : cc.get("money") + "";//订单明细金额
                        String quantity = cc.get("quantity") == null ? "0" : cc.get("quantity") + "";//商品数量
                        double v = Double.parseDouble(money) * Double.parseDouble(quantity);
                        total += v;
                    }
//            for(int i=0;i<ddmx.size();i++){
//                String money = ddmx.get(i).get("money") == null ? "0" : ddmx.get(i).get("money") + "";
//                double v = Double.parseDouble(money);
//                total+=v;
//            }
                    List<CCObject> jfgz = this.cqlQuery("jfgz", "select * from jfgz where '" + total + "'<zdsz and '" + total + "'>=zxsz and is_deleted='0'");
                    if (jfgz.size() > 0) {
                        String bl = jfgz.get(0).get("name") == null ? "0" : jfgz.get(0).get("name") + "";
                        double jf = Double.parseDouble(bl) * total;
                        this.cqlQuery("ddxx", "update ddxx set jf='" + jf + "' and zje='" + total + "' where id='" + id + "'");
                    }
                }
            }
        }
    }

}
