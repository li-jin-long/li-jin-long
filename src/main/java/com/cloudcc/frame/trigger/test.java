package com.cloudcc.frame.trigger;

import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.ServiceResult;
import com.cloudcc.deveditor.trigger.TriggerEditor;
import com.cloudcc.frame.Utils.CommonMethod;
import com.cloudcc.frame.Utils.HISGetFeeDatas_V4;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.util.*;

public class test extends TriggerEditor {
//    public void test() {
//        CCService ccs = new CCService(userInfo);//声明CCService用于后续数据操作
//        com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4 hisInterface = new com.cloudcc.core.fag.CCUST_U_YGYY.HISGetFeeDatas_V4(userInfo);
//        com.cloudcc.core.fag.CCUST_U_YGYY.CommonMethod method = new com.cloudcc.core.fag.CCUST_U_YGYY.CommonMethod(userInfo);
//        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//转换格式
//
//        ca.setTime(new Date()); //设置时间为当前时间
//        ca.add(Calendar.DATE, -1);
//        String VisitDate = sdf.format(ca.getTime());
//        String code = "GetVisits";
//        String PatId = "";
//        String Type = "ZY";
//        HashMap<String, String> resultMap = hisInterface.sendParamsToHIS(code, PatId, VisitDate, Type);
//        String result = resultMap.get("result") + "";
//        net.sf.json.JSONObject json = JSONObject.fromObject(result);
//        if ("1".equals(json.getString("Code"))) {
//            net.sf.json.JSONArray jsonData = json.getJSONArray("Body");
//            for (int i = 0; i < jsonData.size(); i++) {
//                JSONObject jo = jsonData.getJSONObject(i);
//                String zyid = method.conversionFormat(jo.getString("zyid") + "");
//                String hzid = method.conversionFormat(jo.getString("hzid") + "");
//                String khmc = method.conversionFormat(jo.getString("khmc"));
//                String rysj = method.conversionFormat(jo.getString("rysj"));
//                String zzys = method.conversionFormat(jo.getString("zzys"));
//                String xfje = method.conversionFormat(jo.getString("xfje"));
//                String ryzd = method.conversionFormat(jo.getString("ryzd"));
//                String ysgh = method.conversionFormat(jo.getString("ysgh"));
//                String cwh = method.conversionFormat(jo.getString("cwh"));
//                String ssbq = method.conversionFormat(jo.getString("ssbq"));
//                String nl = method.conversionFormat(jo.getString("nl"));
//                String ssyz = method.conversionFormat(jo.getString("ssyz"));
//
//                String sql1="select * from account where hzid='"+hzid+"' and is_deleted='0'";
//                List<CCObject> list1 = ccs.cqlQuery("Account", sql1);
//                if (list1.size()>0) {
//                    String khmc2 = method.conversionFormat(list1.get(0).get("id") + "");
//                    khmc = khmc2;
//                }
//                String sql="select id from zyjl where name='"+zyid+"' and is_deleted='0'";
//                List<CCObject> list = ccs.cqlQuery("zyjl", sql);
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
//                    list.get(0).put("ssyz", ssyz);
//                    ccs.update(list.get(0));
//                } else {
//                    CCObject object = new CCObject("zyjl");
//                    object.put("name", hzid);
//                    object.put("hzid", hzid);
//                    object.put("khmc", khmc);
//                    object.put("rysj", rysj);
//                    object.put("zzys", zzys);
//                    object.put("xfje", xfje);
//                    object.put("ryzd", ryzd);
//                    object.put("ysgh", ysgh);
//                    object.put("cwh", cwh);
//                    object.put("ssbq", ssbq);
//                    object.put("nl", nl);
//                    object.put("ssyz", ssyz);
//                    ServiceResult insert = ccs.insert(object);
//                }
//            }
////            out.println("<script>alert('同步成功！');</script>");
////            out.println("<script>window.location='/query.action?obj=a51&m=list&viewId=aec202164C8F0EFr4JAP';</script>");
//        } else {
////            out.println("<script>alert('同步失败！调用接口错误，请联系管理员');</script>");
////            out.println("<script>window.location='/query.action?obj=a51&m=list&viewId=aec202164C8F0EFr4JAP';</script>");
//        }
//
//    }

}

