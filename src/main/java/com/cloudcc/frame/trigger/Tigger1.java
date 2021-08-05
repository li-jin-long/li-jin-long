package com.cloudcc.frame.trigger;


import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.ServiceResult;
import com.cloudcc.deveditor.trigger.TriggerEditor;

import com.cloudcc.frame.Utils.CommonMethod;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

    public void 自定义页面触发(HttpServletRequest request, HttpServletResponse response) {
        String uName = request.getParameter("username") == null ? "" : request.getParameter("username");
        String phone1 = request.getParameter("phone") == null ? "" : request.getParameter("phone");
        String startTime = request.getParameter("startTime") == null ? "" : request.getParameter("startTime");
        String endTime = request.getParameter("endTime") == null ? "" : request.getParameter("endTime");
        CCService ccService = new CCService(userInfo);
        ArrayList<Object> objects = new ArrayList<>();
        if (uName.equals("") && phone1.equals("") && startTime.equals("") && endTime.equals("")) {
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
            if ((!uName.equals("")) && phone1.equals("")) {
                user1 = ccService.cqlQuery("order_info1", "select * from order_info1 where 	khxm1  = '" + uName + "' And is_deleted='0'");
            } else if (uName.equals("") && (!phone1.equals(""))) {
                user1 = ccService.cqlQuery("order_info1", "select * from order_info1 where khdhhm = '" + phone1 + "' And is_deleted='0'");
            } else if ((!uName.equals("")) && (!phone1.equals("")) && (!startTime.equals("")) && (!endTime.equals(""))) {
                user1 = ccService.cqlQuery("user1", "select * from user1 where phone = '" + phone1 + "' And name='" + uName + "' And createdate >='" + startTime + "'And createdate < '" + endTime + "' And is_deleted='0'");
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
        request.setAttribute("data", objects);
    }

    public void test() {
        Object id = record_new.get("id");
        CCService ccs = new CCService(userInfo);
        List<CCObject> rkmx = ccs.cqlQuery("rkmx", "select * from rkmx where id = '" + id + "' And is_deleted='0'");
        if (rkmx.size() > 0) {
            CCObject object = rkmx.get(0);
            String depotProductName = object.get("mc") == null ? "" : object.get("mc") + "";
            String depotProductTotal = object.get("sl") == null ? "" : object.get("sl") + "";
            String depotProductNumber = object.get("bh") == null ? "" : object.get("bh") + "";
            String depotProductType = object.get("lx") == null ? "" : object.get("lx") + "";
            String depotProductPerson = object.get("rkr") == null ? "" : object.get("rkr") + "";
            String depotProductFormat = object.get("gg") == null ? "" : object.get("gg") + "";
            String ids = object.get("name") == null ? "" : object.get("name") + "";
            String sql = "insert into bzwkc (mc, bm, sl,  name) values (" +
                    " '" + depotProductName + "','" + depotProductNumber + "'," +
                    "'" + depotProductTotal + "','" + ids + "') ";
            ccs.cqlQuery("bzwkc", sql);
            if (!depotProductFormat.equals("")) {
                ccs.cqlQuery("bzwkc", "update bzwkc set gg='" + depotProductFormat + "' where  name = '" + ids + "' And is_deleted='0' ");
            } else if (!depotProductPerson.equals("")) {
                ccs.cqlQuery("bzwkc", "update bzwkc set rkr='" + depotProductPerson + "' where  name = '" + ids + "' And is_deleted='0' ");
            } else if (!depotProductType.equals("")) {
                ccs.cqlQuery("bzwkc", "update bzwkc set lx='" + depotProductType + "' where  name = '" + ids + "' And is_deleted='0' ");
            }

        } else {
            if (true) trigger.addErrorMessage("请联系管理员，插入异常");

        }
    }

    public void 配送单() {
        String thspzb = record_new.get("thspzb") == null ? "" : record_new.get("thspzb") + "";
        String thspzbOld = record_old.get("thspzb") == null ? "" : record_old.get("thspzb") + "";
        String pid = record_new.get("id") == null ? "" : record_new.get("id") + "";
//        String date = record_new.get("Deliverytime") == null ? "" : record_new.get("Deliverytime") + "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!thspzb.equals(thspzbOld)) {
            if (true) trigger.addErrorMessage("触发");
            if (thspzb.equals("审批通过")) {
                CCObject object = new CCObject("Delivery_n");
                object.put("billoflading", pid);
                object.put("pszt", "车辆安排");
                object.put("Deliverytime", df.format(new Date()));
                object.put("billoflading", pid);
                object.put("num", "101010010");
                object.put("delivery_last", "王师傅");
                object.put("delivery_plates", "长城");
                object.put("delivery_plate", "川A1231241");
                object.put("num", "10210012");
                ServiceResult insert = this.insert(object);
                String thdid = insert.get("id") + "";
                this.cquery("psmx", "psd='" + thdid + "'");
                List<CCObject> orderDetails = this.cquery("thmx", "thd='" + thdid + "'");
                if (orderDetails.size() > 0) {
                    for (CCObject a : orderDetails) {
                        CCObject psmx = new CCObject("psmx");
                        psmx.put("mxjg", a.get("mxjg") == null ? "" : a.get("mxjg") + "");//	明细价格
                        psmx.put("spxx", a.get("spxx") == null ? "" : a.get("spxx") + "");//	商品信息
                        psmx.put("gmsl", a.get("gmsl") == null ? "" : a.get("gmsl") + "");//	购买数量
                        psmx.put("spdj", a.get("spdj") == null ? "" : a.get("spdj") + "");//	商品单价
                        psmx.put("spxh", a.get("spxh") == null ? "" : a.get("spxh") + "");// 	商品型号
                        psmx.put("spdm", a.get("spdm") == null ? "" : a.get("spdm") + "");//	商品代码
                        psmx.put("splx", a.get("splx") == null ? "" : a.get("splx") + "");//	商品类型
                        psmx.put("spgg", a.get("spgg") == null ? "" : a.get("spgg") + "");//    商品规格
                        this.insert(psmx);

                    }
                }
            }
        }
    }

    public void testt(){
        CCService service = new CCService(userInfo);

    }

//    public void te(){
//        CCService ccs = new CCService(userInfo);
//        String sql  ="select * from ccuser where  role='20204D7254C5E83kg4sE'  or role ='2020021C6D56D42iqDCW' and is_deleted = '0'";
//        List<CCObject> list= ccs.cqlQuery("ccuser",sql);
//        JSONArray options1=new JSONArray();
//        for (CCObject cc:list) {
//            com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
//            String label = cc.get("name") + "";
//            json.put("value",label);
//            json.put("label",label);
//            options1.add(json);
//        }
//        net.sf.json.JSONObject json = new net.sf.json.JSONObject();
//        net.sf.json.JSONArray data = new net.sf.json.JSONArray();//Json数组
//        String allsql = "select * from((select a.name,m.tjsj as sj,m.xfje as xfje,c.name as xsry "+
//                "from tjjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0') "+
//                "UNION "+
//                "(select a.name,m.jzsj as sj,m.xfje as xfje,c.name as xsry "+
//                "from mzjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0') " +
//                "UNION "+
//                "(select a.name,m.rysj as sj,m.xfjg as xfje,c.name as xsry "+
//                "from zyjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0')) as t where 1=1 ";
//
//        List<CCObject> info = ccs.cqlQuery("", allsql);//全查
//          for (CCObject cc : info ) {
//
//            String name=cc.get("name")+"";//姓名
//            String xfje=cc.get("xfje")+"";//消费金额
//            String sj=cc.get("sj")+"";//时间
//              String[] split = sj.split(".");
//
//              String xsry=cc.get("xsry")+"";//销售人员
//
//            json.put("name",name);
//            json.put("xfje",xfje);
//            json.put("sj",split[0]);
//            json.put("xsry",xsry);
//            data.add(json);
//        }
//
//
//    }

//  public void peis(){
//      CCService ccs = new CCService(userInfo);//声明CCService用于后续数据操作
//      com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4 hisInterface = new com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4(userInfo);
//      com.cloudcc.core.fag.CCUST_U_YGYY.CommonMethod method = new com.cloudcc.core.fag.CCUST_U_YGYY.CommonMethod(userInfo);
//      Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//转换格式
//      String VisitDate = sdf.format(new Date());
//      String code = "GetVisits";
//      String PatId = "";
//      String Type = "ZY";
//      HashMap<String, String> resultMap = hisInterface.sendParamsToHIS(code, PatId, VisitDate , Type);
//      String result = resultMap.get("result") + "";
//      net.sf.json.JSONObject json = JSONObject.fromObject(result);
//      if ("1".equals(json.getString("Code"))) {
//          net.sf.json.JSONArray jsonData = json.getJSONArray("Body");
//          for (int i = 0; i < jsonData.size(); i++) {
//              JSONObject jo = jsonData.getJSONObject(i);
//              String zyid = method.conversionFormat(jo.getString("zyid") + "");
//              String hzid = method.conversionFormat(jo.getString("hzid") + "");
//              String khmc = method.conversionFormat(jo.getString("khmc"));
//              String rysj = method.conversionFormat(jo.getString("rysj"));
//              String zzys = method.conversionFormat(jo.getString("zzys"));
//              String xfje = method.conversionFormat(jo.getString("xfje"));
//              String ryzd = method.conversionFormat(jo.getString("ryzd"));
//              String ysgh = method.conversionFormat(jo.getString("ysgh"));
//              String cwh = method.conversionFormat(jo.getString("cwh"));
//              String ssbq = method.conversionFormat(jo.getString("ssbq"));
//              String nl = method.conversionFormat(jo.getString("nl"));
//              String ssyz = method.conversionFormat(jo.getString("ssyz"));
//
//              String sql1="select * from account where hzid='"+hzid+"' and is_deleted='0'";
//              List<CCObject> list1 = ccs.cqlQuery("Account", sql1);
//              if (list1.size()>0) {
//                  String user_sql = "select id from CCUser where ysgh ='" + ysgh + "' and isusing ='1'";
//                  List<CCObject> user_list = ccs.cqlQuery("CCUser", user_sql);
//                  String ownerid = "";
//                  if (user_list.size() > 0) {
//                      ownerid = user_list.get(0).get("id") == null ? "" : user_list.get(0).get("id").toString();
//                  }
//                  String khmc2 = method.conversionFormat(list1.get(0).get("id") + "");
//                  khmc = khmc2;
//                  String sql="select * from zyjl where name='"+zyid+"' and is_deleted='0'";
//                  List<CCObject> list = ccs.cqlQuery("zyjl", sql);
//                  if (list.size() > 0) {
//                      list.get(0).put("name", hzid);
//                      list.get(0).put("hzid", hzid);
//                      list.get(0).put("khmc", khmc);
//                      list.get(0).put("rysj", rysj);
//                      list.get(0).put("zzys", zzys);
//                      list.get(0).put("xfje", xfje);
//                      list.get(0).put("ryzd", ryzd);
//                      list.get(0).put("ysgh", ysgh);
//                      list.get(0).put("cwh", cwh);
//                      list.get(0).put("ssbq", ssbq);
//                      list.get(0).put("nl", nl);
//                      list.get(0).put("ssyz", ssyz);
//                      list.get(0).put("ownerid", ownerid);
//                      ccs.update(list.get(0));
//                  } else {
//                      CCObject obj= new CCObject("zyjl");
//                      obj.put("name", hzid);
//                      obj.put("khmc", khmc);
//                      obj.put("rysj", rysj);
//                      obj.put("zzys", zzys);
//                      obj.put("xfje", xfje);
//                      obj.put("ryzd", ryzd);
//                      obj.put("ysgh", ysgh);
//                      obj.put("cwh", cwh);
//                      obj.put("ssbq", ssbq);
//                      obj.put("nl",nl);
//                      obj.put("ssyz", ssyz);
//                      obj.put("ownerid", ownerid);
//                      ccs.insert(obj);
//                  }
//              }
//          }
//              out.println("<script>alert('同步成功！');</script>");
//              out.println("<script>window.location='/query.action?obj=a51&m=list&viewId=aec202164C8F0EFr4JAP';</script>");
//          } else {
//              out.println("<script>alert('同步失败！调用接口错误，请联系管理员');</script>");
//              out.println("<script>window.location='/query.action?obj=a51&m=list&viewId=aec202164C8F0EFr4JAP';</script>");
//          }
//
//      }
  }

//    public void tets() {
//        String spzt1 = record_new.get("spzt") == null ? "" : record_new.get("spzt") + "";
//        String spzt2 = record_old.get("spzt") == null ? "" : record_old.get("spzt") + "";
//        String id = record_old.get("id") == null ? "" : record_old.get("id") + "";//订单id
//        if (!spzt1.equals(spzt2)) {
//            if (spzt1.equals("已完成")) {
//                CCObject deliverGoods = new CCObject("Delivery_n ");
//                deliverGoods.put("billoflading", id);
//                ServiceResult insert = this.insert(deliverGoods);
//                String thdid = insert.get("id") + "";
//                //新建提货单完成
//                List<CCObject> orderDetails = this.cquery("thmx ", "ddh='" + id + "'");
//                if (orderDetails.size() > 0) {
//                    for (CCObject cc :
//                            orderDetails) {
//                        String spxx = cc.get("spxx") == null ? "" : cc.get("spxx") + "";
//
//                        CCObject thmx = new CCObject("thmx");
//                        thmx.put("spxx", spxx);
//                        thmx.put("spxx", spxx);
//                        thmx.put("spxx", spxx);
//                        thmx.put("spxx", spxx);
//                        thmx.put("spxx", spxx);
//                        thmx.put("spxx", spxx);
//                        thmx.put("thd", thdid);
//                        this.insert(thmx);
//                    }
//                }
//            }
//        }
//
//    }

//public  void test(){
//
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
//    Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
//    ca.setTime(new Date()); //设置时间为当前时间
//    ca.add(Calendar.DATE, -1);
//    String VisitDate = sdf.format(ca.getTime());
//    com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4 hisInterface = new com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4(userInfo);
//    String code="GetVisits";
//    String PatId="";
//    HashMap<String, String> resultMap = hisInterface.sendParamsToHIS(code, PatId, "","ZY");
//    String result = resultMap.get("result")+"";
//    new JSONObject json =null;
//    CommonMethod method=new CommonMethod(userInfo);
//    if(method.isJSONValid(result)){
//        json = net.sf.json.JSONObject.fromObject(result);
//    }
//    if ("1".equals(json.getString("Code"))) {
//        net.sf.json.JSONArray jsonData = json.getJSONArray("Body");
//
//        for (int i = 0; i < jsonData.size(); i++) {
//            JSONObject jo = jsonData.getJSONObject(i);
//            String zyid = method.conversionFormat(jo.getString("zyid") + "");
//
//            String hzid = method.conversionFormat(jo.getString("hzid") + "");
//
//            String khmc = method.conversionFormat(jo.getString("khmc")+"");
//
//            String rysj = method.conversionFormat(jo.getString("rysj")+"");
//
//            String zzys = method.conversionFormat(jo.getString("zzys")+"");
//
//            String xfje = method.conversionFormat(jo.getString("xfje")+"");
//
//            String ryzd = method.conversionFormat(jo.getString("ryzd")+"");
//
//            String ysgh = method.conversionFormat(jo.getString("ysgh")+"");
//            String cwh = method.conversionFormat(jo.getString("cwh")+"");
//            String ssbq = method.conversionFormat(jo.getString("ssbq")+"");
//            String nl = method.conversionFormat(jo.getString("nl")+"");
//
//            //  String ssyz = method.conversionFormat(jo.getString("ssyz")+"");
//
//            String sql1="select * from account where hzid='"+hzid+"' and is_deleted='0'";
//            List<CCObject> list1 = this.cqlQuery("Account", sql1);
//            if (list1.size()>0) {
//
//                String user_sql = "select id from CCUser where ysgh ='" + ysgh + "' and isusing ='1'";
//                List<CCObject> user_list = this.cqlQuery("CCUser", user_sql);
//                String ownerid = "";
//                if (user_list.size() > 0) {
//                    ownerid = user_list.get(0).get("id") == null ? "" : user_list.get(0).get("id").toString();
//                }
//                String khmc2 = method.conversionFormat(list1.get(0).get("id") + "");
//                khmc = khmc2;
//                String sql="select * from zyjl where name='"+zyid+"' and is_deleted='0'";
//                List<CCObject> list = this.cqlQuery("zyjl", sql);
//                if (list.size() > 0) {
//                    list.get(0).put("name", hzid);
//                    list.get(0).put("hzid", hzid);
//                    list.get(0).put("khmc", khmc);
//                    list.get(0).put("rysj", rysj);
//                    list.get(0).put("zzys", zzys);
//                    list.get(0).put("xfje", xfje);
//                    list.get(0).put("ryzd", ryzd);
//                    list.get(0).put("ysgh", ysgh);
//                    list.get(0).put("cwh", cwh);
//                    list.get(0).put("ssbq", ssbq);
//                    list.get(0).put("nl", nl);
//                    list.get(0).put("ownerid",ownerid);
//                    this.update(list.get(0));
//                } else {
//                    CCObject obj= new CCObject("zyjl");
//                    obj.put("name", hzid);
//                    obj.put("khmc", khmc);
//                    obj.put("rysj", rysj);
//                    obj.put("zzys", zzys);
//                    obj.put("xfje", xfje);
//                    obj.put("ryzd", ryzd);
//                    obj.put("ysgh", ysgh);
//                    obj.put("cwh", cwh);
//                    obj.put("ssbq", ssbq);
//                    obj.put("nl",nl);
//                    obj.put("ownerid",ownerid);
//
//                    this.insert(obj);
//                }
//            }
//        }
//    }
//    }


//class a {
//    public static void main(String[] args) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String a = "已完成";
//        String b = "草稿";
//        System.out.println(!a.equals(b));
//        System.out.println();// new Date()为获取当前系统时间
//    }
//}