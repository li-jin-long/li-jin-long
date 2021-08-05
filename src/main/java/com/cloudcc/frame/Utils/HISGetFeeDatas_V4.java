package com.cloudcc.frame.Utils;

import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.UserInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class HISGetFeeDatas_V4 {
    private static CCService css;
    public HISGetFeeDatas_V4(UserInfo userInfo) {
        css = new CCService(userInfo);
    }
    private static HashMap<String,String> sendParamsToHIS(HashMap<String, String> map) {
        HashMap<String, String> resultMap=new HashMap<>();
        String PatId =map.get("PatId");
        String VisitDate =map.get("VisitDate");
        String AppId =map.get("AppId");
        String Signature =map.get("Signature");
        String Timestamp =map.get("Timestamp");
        String Nonce =map.get("Nonce");
        String urlString =map.get("url");
        String Type =map.get("Type");
        String json ="{\n" +
                "  \"Body\": {\n" +
                "    \"PatId\": \""+PatId+"\",\n" +
                "    \"VisitDate\": \""+VisitDate+"\",\n" +
                "    \"Type\": \""+Type+"\"\n" +
                "  },\n" +
                "  \"AppId\": \""+AppId+"\",\n" +
                "  \"Signature\": \""+Signature+"\",\n" +
                "  \"Timestamp\": \""+Timestamp+"\",\n" +
                "  \"Nonce\": \""+Nonce+"\"\n" +
                "}";
        String result="";
        try {
            //创建连接
            URL url = new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); //设置请求方法
            connection.setRequestProperty("Charsert", "UTF-8"); //设置请求编码
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(30000);    //30秒连接超时
            connection.setReadTimeout(30000);       //30秒读取超时
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();
            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream()); //关键的一步
            out.writeBytes(json);
            out.flush();
            out.close();
            // 读取响应
            if (connection.getResponseCode()==200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String lines;
                StringBuffer sb = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                result=sb.toString();
                reader.close();
            }//返回值为200输出正确的响应信息
            if (connection.getResponseCode()==400) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String lines;
                StringBuffer sb = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sb.append(lines);
                }
                result=sb.toString();
                reader.close();
            }//返回值错误，输出错误的返回信息
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("result", result);
        resultMap.put("json",json);
        resultMap.put("url",urlString);
        return resultMap;
    }
    //获取接口配置表中，appid等相关参数
    public HashMap<String,String> sendParamsToHIS(String code,String PatId,String VisitDate,String Type) {
        List<CCObject> list = css.cquery("jkpz","code='"+code+"' and is_deleted='0'");
        HashMap<String, String> map=new HashMap<String, String>();
        String url="";
        String appid="";
        String secretkey="";
        String timestamp= Calendar.getInstance().getTimeInMillis()+"";
        String nonce=getNonce(32);
        if(!"".equals(timestamp)){
            timestamp=timestamp.substring(0, 10);
        }
        if(list.size()>0){
            String ip=list.get(0).get("url")==null?"":list.get(0).get("url")+"";
            String jklx=list.get(0).get("jklx")==null?"":list.get(0).get("jklx")+"";
            appid=list.get(0).get("appid")==null?"":list.get(0).get("appid")+"";
            secretkey=list.get(0).get("secretkey")==null?"":list.get(0).get("secretkey")+"";
            url=ip+jklx;
        }

        String signature=getSignature(appid, nonce, timestamp, secretkey);
        map.put("url",url);
        map.put("AppId",appid);
        map.put("Signature",signature);
        map.put("Timestamp",timestamp);
        map.put("Nonce",nonce);
        map.put("PatId",PatId);
        map.put("VisitDate",VisitDate);
        map.put("Type",Type);

        HashMap<String,String> resultMap = sendParamsToHIS(map);
        return resultMap;
    }

    //根据规则获取签名
    public static String getSignature(String appId, String nonce,String timestamp,String appSecret){
        String str="appId="+appId+"&nonce="+nonce+"&timestamp="+timestamp+"&appSecret="+appSecret+"";
        System.out.println(str);
        String md5 =md5(str);
        String upperCase = md5.toUpperCase();
        return upperCase;

    }
    public static String getNonce(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
    public static String md5(String str){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            result="";
        }
        return result;
    }
}
