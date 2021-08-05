package com.cloudcc.frame.trigger.英格;

public class asdas {

//    public void  test(){
//        String lx = request.getParameter("lx") == null ? "" : request.getParameter("lx") + "";//类型
//        String xs = request.getParameter("xs") == null ? "" : request.getParameter("xs") + "";//销售
//        String start_time = request.getParameter("start_time") == null ? "" : request.getParameter("start_time") + "";//开始时间
//        String end_time = request.getParameter("end_time") == null ? "" : request.getParameter("end_time") + "";//结束时间
//        //Date date=new Date();
//        //SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//        //String today= sf.format(date);
//        //if("".equals(start_time)){
//        //start_time=today;
//        //}
//        //if("".equals(end_time)){
//        //end_time=today;
//        //}
//        String qd = request.getParameter("qd") == null ? "" : request.getParameter("qd") + "";//渠道
//        CCService ccs = new CCService(userInfo);
//        net.sf.json.JSONObject result = new net.sf.json.JSONObject();//Json对象
//        net.sf.json.JSONArray data = new net.sf.json.JSONArray();//Json数组
//        net.sf.json.JSONObject json = new net.sf.json.JSONObject();
//        String msg = "{\"code\",\"1\"}";
//
//        String allsql = "select * from((select a.name,DATE_FORMAT(m.tjsj,'%Y-%m-%d') as sj,m.xfje as xfje,c.name as xsry "+
//                "from tjjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0') "+
//                "UNION "+
//                "(select a.name,DATE_FORMAT(m.jzsj,'%Y-%m-%d') as sj,m.xfje as xfje,c.name as xsry "+
//                "from mzjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0') " +
//                "UNION "+
//                "(select a.name,DATE_FORMAT(m.rysj,'%Y-%m-%d') as sj,m.xfjg as xfje,c.name as xsry "+
//                "from zyjl m "+
//                "left join (Account a "+
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id "+
//                "left join ccuser c on b.ownerid=c.id "+
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0')) as t where 1=1 ";
//
//        String mzsql = "select a.name,DATE_FORMAT(m.jzsj,'%Y-%m-%d') as sj,m.xfje as xfje,c.name as xsry " +
//                "from mzjl m " +
//                "left join (Account a " +
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id " +
//                " left join ccuser c on b.ownerid=c.id " +
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0' ";
//
//
//        String tjsql = "select a.name,DATE_FORMAT(m.jtsj,'%Y-%m-%d') as sj,m.xfje as xfje,c.name as xsry " +
//                "from tjjl m " +
//                "left join (Account a " +
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id " +
//                "left join ccuser c on b.ownerid=c.id " +
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0' ";
//
//        String zysql = "select a.name,DATE_FORMAT(m.rysj,'%Y-%m-%d') as sj,m.xfje as xfje,c.name as xsry " +
//                "from zyjl m " +
//                "left join (Account a " +
//                "inner join scbkh  b on a.id=b.khmc) on m.khmc=a.id " +
//                "left join ccuser c on b.ownerid=c.id " +
//                "where a.is_deleted='0' and b.is_deleted='0' and m.is_deleted='0' ";
//
//        //类型不等于空
//        if(!"".equals(lx)){
//            if("门诊".equals(lx)){
//                //开始时间
//                if (!"".equals(start_time)) {
//                    mzsql += "and m.jzsj >= '" + start_time + "' ";
//                }
//                //结束时间
//                if (!"".equals(end_time)) {
//                    mzsql += "and m.jzsj <= '" + end_time + "' ";
//                }
//                List<CCObject> info = ccs.cqlQuery("mzjl", mzsql);//查询门诊
//
//                for (CCObject cc:info) {
//                    String name=cc.get("name")+"";//姓名
//                    String xfje=cc.get("xfje")+"";//消费金额
//                    String sj=cc.get("sj")+"";//时间
//                    String xsry=cc.get("xsry")+"";//销售人员
//                    json.put("name",name);
//                    json.put("xfje",xfje);
//                    json.put("sj",sj);
//                    json.put("xsry",xsry);
//                    data.add(json);
//                }
//                result.put("total", data.size());
//                result.put("rows", data);
//                result.put("sql", mzsql);
//                msg = result.toString();
//
//            }else if ("体检".equals(lx)){
//                //开始时间
//                if (!"".equals(start_time)) {
//                    tjsql += "and m.tjsj >= '" + start_time + "' ";
//                }
//                //结束时间
//                if (!"".equals(end_time)) {
//                    tjsql += "and m.tjsj <= '" + end_time + "' ";
//                }
//                List<CCObject> info = ccs.cqlQuery("tjjl", tjsql);//查询体检
//
//                for (CCObject cc:info) {
//                    String name=cc.get("name")+"";//姓名
//                    String xfje=cc.get("xfje")+"";//消费金额
//                    String sj=cc.get("sj")+"";//时间
//                    String xsry=cc.get("xsry")+"";//销售人员
//                    json.put("name",name);
//                    json.put("xfje",xfje);
//                    json.put("sj",sj);
//                    json.put("xsry",xsry);
//                    data.add(json);
//
//                }
//                result.put("total", data.size());
//                result.put("rows", data);
//                result.put("sql", tjsql);
//                msg = result.toString();
//            }else if ("住院".equals(lx)){
//                //开始时间
//                if (!"".equals(start_time)) {
//                    zysql += "and m.rysj >= '" + start_time + "' ";
//                }
//                //结束时间
//                if (!"".equals(end_time)) {
//                    zysql += "and m.rysj <= '" + end_time + "' ";
//                }
//                List<CCObject> info = ccs.cqlQuery("zyjl", zysql);//查询住院
//
//                for (CCObject cc:info) {
//                    String name=cc.get("name")+"";//姓名
//                    String xfje=cc.get("xfje")+"";//消费金额
//                    String sj=cc.get("sj")+"";//时间
//                    String xsry=cc.get("xsry")+"";//销售人员
//                    json.put("name",name);
//                    json.put("xfje",xfje);
//                    json.put("sj",sj);
//                    json.put("xsry",xsry);
//                    data.add(json);
//
//                }
//                result.put("total", data.size());
//                result.put("rows", data);
//                result.put("sql", zysql);
//                msg = result.toString();
//
//            }
//
//        }
//        //销售不等于空
//        if(!"".equals(xs)){
//            allsql+="and t.xsry ='"+xs+ "' ";
//        }
//        //开始时间
//        if(!"".equals(start_time)){
//            allsql+="and t.sj >= '"+start_time+ "' ";
//        }
//        //结束时间
//        if(!"".equals(end_time)){
//            allsql+="and t.sj <= '"+end_time+ "' ";
//        }
//        //渠道  暂时没数据
//        if("是".equals(qd)){
//            allsql+="and a.sffc='是' ";
//        }
//        if (lx.equals("") && xs.equals("")){
//            List<CCObject> info = ccs.cqlQuery("", allsql);//全查
//            for (CCObject cc : info ) {
//
//                String name=cc.get("name")+"";//姓名
//                String xfje=cc.get("xfje")+"";//消费金额
//                String sj=cc.get("sj")+"";//时间
//                String xsry=cc.get("xsry")+"";//销售人员
//
//                json.put("name",name);
//                json.put("xfje",xfje);
//                json.put("sj",sj);
//                json.put("xsry",xsry);
//                data.add(json);
//            }
//            result.put("total", data.size());
//            result.put("rows", data);
//            result.put("sql", allsql);
//            msg = result.toString();
//        }
//        request.setAttribute("jsonmsg", msg);
//    }
}
