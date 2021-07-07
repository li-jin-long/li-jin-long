package com.cloudcc.frame.trigger;


import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.trigger.TriggerEditor;

import com.cloudcc.frame.Utils.DateTimeUtils;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
        if (true) trigger.addErrorMessage(String.valueOf("测试"));

    }

    public void 根据商品名获取详细信息() {
        CCService ccs = new CCService(userInfo);
        List<Object> objects = new ArrayList<>();
        objects.add("CCObjectAPI=jfgz, lastmodifybyidccname=, owneridccname=, zdsz=100000, createdate=2021-06-29 14:08:43, ownerid=0052017BE8702F1PIi4j, recordtypeccname=null, createbyid=0052017BE8702F1PIi4j, createbyidccname=, recordtype=null, name=1.5, currency=CNY, id=a122021567D0A6EVvHMc, lbsaddress=null, zxsz=50000, lastmodifybyid=0052017BE8702F1PIi4j, lastmodifydate=2021-06-29 14:08:43");
        objects.add("CCObjectAPI=jfgz, lastmodifybyidccname=, owneridccname=, zdsz=50000, createdate=2021-06-29 14:08:16, ownerid=0052017BE8702F1PIi4j, recordtypeccname=null, createbyid=0052017BE8702F1PIi4j, createbyidccname=, recordtype=null, name=1, currency=CNY, id=a122021915BCDD2L4vFg, lbsaddress=null, zxsz=10000, lastmodifybyid=0052017BE8702F1PIi4j, lastmodifydate=2021-06-29 14:08:16");
        objects.add("CCObjectAPI=jfgz, lastmodifybyidccname=, owneridccname=, zdsz=90000000, createdate=2021-06-29 14:09:14, ownerid=0052017BE8702F1PIi4j, recordtypeccname=null, createbyid=0052017BE8702F1PIi4j, createbyidccname=, recordtype=null, name=2, currency=CNY, id=a122021ACEAF29DnGsAY, lbsaddress=null, zxsz=100000, lastmodifybyid=0052017BE8702F1PIi4j, lastmodifydate=2021-06-30 11:28:22");
        List<CCObject> jfgz = ccs.cquery("jfgz");
        for (Object c : objects) {
            c.toString().replaceFirst("=", ":");
            System.out.println(c);
        }

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

                    List<CCObject> jfgz = this.cqlQuery("jfgz", "select * from jfgz where '" + total + "'&lt; zdsz and '" + total + "'&gt;= zxsz and is_deleted='0'");
                    if (true)
                        trigger.addErrorMessage(jfgz + "" + "select * from jfgz where '" + total + "' < zdsz and '" + total + "' >= zxsz and is_deleted='0'");

                    if (jfgz.size() > 0) {

                        String bl = jfgz.get(0).get("name") == null ? "0" : jfgz.get(0).get("name") + "";
                        double jf = Double.parseDouble(bl) * total;
                        if (true)
                            trigger.addErrorMessage("update ddxx set jf='" + jf + "' And zje='" + total + "' where id ='" + id + "'");
                        this.cqlQuery("ddxx", "update ddxx set jf='" + jf + "' and zje='" + total + "' where id='" + id + "'");
                    }
                }
            }
        }
    }

    public void 自定义页面触发(HttpServletRequest request,HttpServletResponse response) {
        String uName = request.getParameter("username")==null ? "":request.getParameter("username");
        String phone1 = request.getParameter("phone")==null ? "":request.getParameter("phone");
        String startTime = request.getParameter("startTime")==null ? "":request.getParameter("startTime");
        String endTime = request.getParameter("endTime")==null ? "":request.getParameter("endTime");
        CCService ccService = new CCService(userInfo);
        ArrayList<Object> objects = new ArrayList<>();
        if (uName.equals("") && phone1.equals("")  && startTime.equals("") && endTime .equals("")) {
            List<CCObject> kh = ccService.cquery("user1 ");
            for (CCObject object : kh) {
                String name = object.get("name") == null ? "" : object.get("name") + "";
                String phone = object.get("phone") == null ? "" : object.get("phone") + "";
                String address = object.get("address") == null ? "" : object.get("address") + "";
                String createdate = object.get("createdate") == null ? "" : object.get("createdate") + "";
                Map<Object, Object> objectObjectMap = new HashMap<>();
                objectObjectMap.put("name", name);
                objectObjectMap.put("phone", phone);
                objectObjectMap.put("address", address);
                String[] split = createdate.split("\\.");
                String date = split[0];
                objectObjectMap.put("createdate", date);
                JSONObject jsonObject = JSONObject.fromObject(objectObjectMap);
                objects.add(jsonObject);
            }
        } else {
            List<CCObject> user1 = new ArrayList<>();
            if ((!uName.equals("")) && phone1 .equals("")){
                user1 = ccService.cqlQuery("order_info1", "select * from order_info1 where 	khxm1  = '" + uName + "' And is_deleted='0'");
            }else  if (uName .equals("") && (!phone1.equals(""))){
                user1 = ccService.cqlQuery("order_info1", "select * from order_info1 where khdhhm = '" + phone1 + "' And is_deleted='0'");
            }else if ((!uName.equals(""))&& (!phone1.equals("")) && (!startTime.equals("")) && (!endTime .equals(""))){
                user1 = ccService.cqlQuery("user1", "select * from user1 where phone = '" + phone1 + "' And name='"+uName+"' And createdate >='"+startTime+"'And createdate < '"+endTime+"' And is_deleted='0'");
            }
            for (CCObject object : user1) {
                String name = object.get("khxm1") == null ? "" : object.get("khxm1") + "";
                String phone = object.get("khdhhm") == null ? "" : object.get("khdhhm") + "";
                String address = object.get("khdz") == null ? "" : object.get("khdz") + "";
                String createdate = object.get("createdate") == null ? "" : object.get("createdate") + "";
                String moeny = object.get("order_zje") == null ? "" : object.get("order_zje") + "";
                Map<Object, Object> objectObjectMap = new HashMap<>();
                objectObjectMap.put("name", name);
                objectObjectMap.put("phone", phone);
                objectObjectMap.put("address", address);
                String[] split = createdate.split("\\.");
                String date = split[0];
                objectObjectMap.put("createdate", date);
                objectObjectMap.put("moeny", moeny);
                JSONObject jsonObject = JSONObject.fromObject(objectObjectMap);
                objects.add(jsonObject);
            }
        }
        request.setAttribute("data",objects);
    }


}

