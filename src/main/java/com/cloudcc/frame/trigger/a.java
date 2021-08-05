package com.cloudcc.frame.trigger;

import com.cloudcc.client.CCService;
import com.cloudcc.frame.Utils.CommonMethod;
import com.cloudcc.frame.Utils.HISGetFeeDatas_V4;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class a {
//    public static void main(String[] args) {
//        CCService ccs = new CCService(userInfo);//声明CCService用于后续数据操作
//       HISGetFeeDatas_V4 hisInterface = new HISGetFeeDatas_V4(userInfo);
//
//        CommonMethod method = CommonMethod(userInfo);
//        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//转换格式
//        String VisitDate = sdf.format(new Date());
//        String code = "GetVisits";
//        String PatId = "";
//        String Type = "ZY";
//        HashMap<String, String> resultMap = hisInterface.sendParamsToHIS(code, PatId, VisitDate, Type);
//        String result = resultMap.get("result") + "";
//        JSONObject json = JSONObject.fromObject(result);
//        if ("1".equals(json.getString("Code"))) {
//            net.sf.json.JSONArray jsonData = json.getJSONArray("Body");
//
//            for (int i = 0; i < jsonData.size(); i++) {
//                JSONObject jo = jsonData.getJSONObject(i);
//                String zyid = method.conversionFormat(jo.getString("zyid") + "");
//
//                String hzid = method.conversionFormat(jo.getString("hzid") + "");
//
//                String khmc = method.conversionFormat(jo.getString("khmc") + "");
//
//                String rysj = method.conversionFormat(jo.getString("rysj") + "");
//
//                String zzys = method.conversionFormat(jo.getString("zzys") + "");
//
//                String xfje = method.conversionFormat(jo.getString("xfje") + "");
//
//                String ryzd = method.conversionFormat(jo.getString("ryzd") + "");
//
//                String ysgh = method.conversionFormat(jo.getString("ysgh") + "");
//                String cwh = method.conversionFormat(jo.getString("cwh") + "");
//                String ssbq = method.conversionFormat(jo.getString("ssbq") + "");
//                String nl = method.conversionFormat(jo.getString("nl") + "");
//
//                //  String ssyz = method.conversionFormat(jo.getString("ssyz")+"");
//
//                        String sql1 = "select * from account where hzid='" + hzid + "' and is_deleted='0'";
//                        List<CCObject> list1 = ccs.cqlQuery("Account", sql1);
//                if (list1.size() > 0) {
//
//                    String user_sql = "select id from CCUser where ysgh ='" + ysgh + "' and isusing ='1'";
//                    List<CCObject> user_list = ccs.cqlQuery("CCUser", user_sql);
//                    String ownerid = "";
//                    if (user_list.size() > 0) {
//                        ownerid = user_list.get(0).get("id") == null ? "" : user_list.get(0).get("id").toString();
//                    }
//                    String khmc2 = method.conversionFormat(list1.get(0).get("id") + "");
//                    khmc = khmc2;
//                    String sql = "select * from zyjl where name='" + zyid + "' and is_deleted='0'";
//                    List<CCObject> list = ccs.cqlQuery("zyjl", sql);
//                    if (list.size() > 0) {
//                        list.get(0).put("name", hzid);
//                        list.get(0).put("hzid", hzid);
//                        list.get(0).put("khmc", khmc);
//                        list.get(0).put("rysj", rysj);
//                        list.get(0).put("zzys", zzys);
//                        list.get(0).put("xfje", xfje);
//                        list.get(0).put("ryzd", ryzd);
//                        list.get(0).put("ysgh", ysgh);
//                        list.get(0).put("cwh", cwh);
//                        list.get(0).put("ssbq", ssbq);
//                        list.get(0).put("nl", nl);
//                        list.get(0).put("ownerid", ownerid);
//                        ccs.update(list.get(0));
//                    } else {
//                        CCObject obj = new CCObject("zyjl");
//                        obj.put("name", hzid);
//                        obj.put("khmc", khmc);
//                        obj.put("rysj", rysj);
//                        obj.put("zzys", zzys);
//                        obj.put("xfje", xfje);
//                        obj.put("ryzd", ryzd);
//                        obj.put("ysgh", ysgh);
//                        obj.put("cwh", cwh);
//                        obj.put("ssbq", ssbq);
//                        obj.put("nl", nl);
//                        obj.put("ownerid", ownerid);
//
//                        ccs.insert(obj);
//                    }
//                }
//            }
//        }
//    }
}
