package com.cloudcc.frame.Utils;

import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.UserInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonMethod{
    @SuppressWarnings("unused")
    private CCService css;
    public CommonMethod(UserInfo userInfo) {
        css = new CCService(userInfo);
    }
    //===========================================常用通用转换方法============================
    //验证是否json格式
    public  boolean isJSONValid(String jsonstr) {
        try {
            net.sf.json.JSONObject.fromObject(jsonstr);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public  boolean isJSONArrayValid(Object JSONArraystr) {
        try {
            net.sf.json.JSONArray.fromObject(JSONArraystr);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public String md5(String str){
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
    //将判断处理是否空或"null"
    public  String conversionFormat(Object data) {
        String str=data+"";
        if("null".equals(str)){
            str="";
        }
        return str;
    }

    //SHA-1算法
    public String shaEncode(String inStr){
        MessageDigest sha = null;
        byte[] byteArray=null;
        StringBuffer hexValue=null;
        try {
            sha = MessageDigest.getInstance("SHA");
            byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

