package com.cloudcc.frame.trigger.瀚华;

import com.cloudcc.client.CCObject;
import com.cloudcc.client.CCService;
import com.cloudcc.deveditor.core.ServiceResult;
import com.cloudcc.deveditor.core.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

public class upsertLeadV2 {
    public void upsertLead(HttpServletRequest request) {
           //  <cc:page type="sitepage" style="standard" showSidebar="false" showHeader="false" /><cc>
        //这是一个Site页面，可以通过URL直接访问
        ServiceResult sr = CCService.getUserInfo("61ee0c8c32fe6ea38850a3475fa15750fbc1bb1a20768b71", "test_hhtest@cloudcc.com", "111111");
        //        ServiceResult sr = CCService.getUserInfo("f6c729c175119d3aa2942d814a63df8564dfe85e8959db2d","interface@cloudcc.com","111111");


        UserInfo uuserInfo = (UserInfo) sr.get("COMMON_USERINFO");
        CCService cs = new CCService(uuserInfo);
        String invokeId = request.getParameter("invokeId") == null ? "" : request.getParameter("invokeId") + "";
        String data = request.getParameter("data") == null ? "" : request.getParameter("data") + "";
        net.sf.json.JSONObject message =new net.sf.json.JSONObject();
        net.sf.json.JSONObject message_data =new net.sf.json.JSONObject();
        try {
            JSONObject data_json = JSONObject.fromObject(data);
            JSONArray individualInfo = new JSONArray();
            if (!JSONNull.getInstance().equals(data_json.get("individualInfo"))) {
                individualInfo = data_json.getJSONArray("individualInfo");
            }
            JSONArray files = new JSONArray();
            if (!JSONNull.getInstance().equals(data_json.get("files"))) {
                files = data_json.getJSONArray("files");
            }
            String leadId = data_json.getString("leadId");
            String referrerId = data_json.getString("referrerId");
            String referrerName = data_json.getString("referrerName");
            String leadSource = data_json.getString("leadSource");
            String jbr_leadName = "";
            String jbr_certificateNum = "";
            String jbr_leadPhone = "";
            String jbr_realNameStatus = "";
            String leadType = "";
            String jbr_individualProvince = "";
            String jbr_individualCity = "";
            String jbr_individualCounty = "";
            String jbr_individualAddress = "";
            String jbr_registerCapital =""; //注册资本
            String fr_leadName = "";
            String fr_certificateNum = "";
            String fr_leadPhone = "";
            String fr_realNameStatus = "";
            String fr_individualProvince = "";
            String fr_individualCity = "";
            String fr_individualCounty = "";
            String fr_individualAddress = "";
            for (Object o : individualInfo) {
                net.sf.json.JSONObject jsonObject =(net.sf.json.JSONObject)o;
                leadType = jsonObject.getString("leadType");
                if ("法人".equals(leadType)) {
                    fr_leadName = jsonObject.getString("leadName");    //领导姓名
                    fr_certificateNum = jsonObject.getString("certificateNum"); //证书编号
                    fr_leadPhone = jsonObject.getString("leadPhone");   // 潜客电话
                    fr_realNameStatus = jsonObject.getString("realNameStatus"); //实名状态

                    if ("1".equals(fr_realNameStatus)) {
                        fr_realNameStatus = "已实名";
                    } else {
                        fr_realNameStatus = "未实名";
                    }
                    fr_individualProvince = jsonObject.getString("individualProvince");  //省份
                    String dataDictionarySQL = "select zdz from data_dictionary where dictionary_item='STD_PROVINCE_CODE' and mz ='" + fr_individualProvince + "' and is_deleted ='0'";
                    List<CCObject> dataDictionaryList = cs.cqlQuery("data_dictionary ", dataDictionarySQL); //选项值查询数据字典
                    if (dataDictionaryList.size() > 0) {
                        fr_individualProvince = dataDictionaryList.get(0).get("zdz") == null ? "" : dataDictionaryList.get(0).get("zdz") + ""; //获取数据名称
                    }
                    fr_individualCity = jsonObject.getString("individualCity"); //获取地区编码。
                    List<CCObject> city_list = cs.cquery("city", "dqbm='" + fr_individualCity + "' and is_deleted='0'");
                    if (city_list.size() > 0) {
                        fr_individualCity = city_list.get(0).get("name") == null ? "" : city_list.get(0).get("name").toString(); //	获取市级城市名称
                    }
                    fr_individualCounty = jsonObject.getString("individualCounty");
                    List<CCObject> country_list = cs.cquery("country", "dqbm='" + fr_individualCounty + "' and is_deleted='0'"); //	区/县
                    if (country_list.size() > 0) {
                        fr_individualCounty = country_list.get(0).get("name") == null ? "" : country_list.get(0).get("name").toString(); //拿到区县级名称
                    }
                    fr_individualAddress = jsonObject.getString("individualAddress"); //个人地址
                } else {
                    jbr_leadName = jsonObject.getString("leadName");
                    jbr_certificateNum = jsonObject.getString("certificateNum");
                    jbr_leadPhone = jsonObject.getString("leadPhone");
                    jbr_registerCapital = jsonObject.getString("registerCapital"); // 注册资本
                    if ("".equals(jbr_leadName)) {
                        jbr_leadName = jbr_leadPhone;
                    }
                    jbr_realNameStatus = jsonObject.getString("realNameStatus");
                    if ("1".equals(jbr_realNameStatus)) {
                        jbr_realNameStatus = "已实名";
                    } else {
                        jbr_realNameStatus = "未实名";
                    }
                    jbr_individualProvince = jsonObject.getString("individualProvince");
                    String dataDictionarySQL = "select zdz from data_dictionary where dictionary_item='STD_PROVINCE_CODE' and mz ='" + jbr_individualProvince + "' and is_deleted ='0'";
                    List<CCObject> dataDictionaryList = cs.cqlQuery("data_dictionary ", dataDictionarySQL);
                    if (dataDictionaryList.size() > 0) {
                        jbr_individualProvince = dataDictionaryList.get(0).get("zdz") == null ? "" : dataDictionaryList.get(0).get("zdz") + "";
                    }
                    jbr_individualCity = jsonObject.getString("individualCity");
                    List<CCObject> city_list = cs.cquery("city", "dqbm='" + jbr_individualCity + "' and is_deleted='0'");
                    if (city_list.size() > 0) {
                        jbr_individualCity = city_list.get(0).get("name") == null ? "" : city_list.get(0).get("name").toString();
                    }
                    jbr_individualCounty = jsonObject.getString("individualCounty");
                    List<CCObject> country_list = cs.cquery("country", "dqbm='" + jbr_individualCounty + "' and is_deleted='0'");
                    if (country_list.size() > 0) {
                        jbr_individualCounty = country_list.get(0).get("name") == null ? "" : country_list.get(0).get("name").toString();
                    }
                    jbr_individualAddress = jsonObject.getString("individualAddress");
                }
            }
            if ("".equals(leadId)) {
                if ("".equals(jbr_leadPhone)) {
                    message.put("invokeId", invokeId);
                    message.put("resultCode", "204");
                    message.put("resultMessage", "电话不能为空！");
                } else {
                    CCObject lead = new CCObject("Lead");
                    lead.put("name", jbr_leadName);
                    lead.put("zczb", jbr_registerCapital);
                    lead.put("dianhua", jbr_leadPhone);
                    lead.put("certificate_num", jbr_certificateNum);
                    lead.put("realNameStatus", jbr_realNameStatus);
                    lead.put("individualProvince", jbr_individualProvince);
                    lead.put("individualCity", jbr_individualCity);
                    lead.put("individualCounty", jbr_individualCounty);
                    lead.put("individualAddress", jbr_individualAddress);
                    lead.put("referrerId", referrerId);
                    lead.put("referrerName", referrerName);
                    lead.put("qzkhly", leadSource);
                    lead.put("isMobileRegister", "true");
//                    lead.put("recordtype", "2020F5E1802867Fsfq0B");
                    lead.put("recordtype", "20206778074FE83qv5mJ");
                    ServiceResult insert = cs.insert(lead);
                    String lead_id = insert.get("id") == null ? "" : insert.get("id").toString();
                    if ("".equals(lead_id)) {
                        message.put("invokeId", invokeId);
                        message.put("resultCode", "201");
                        message.put("resultMessage", "新建潜在客户失败");
                    } else {
                        List<CCObject> lead_list = cs.cquery("Lead", "id ='" + lead_id + "' and is_deleted='0'");
                        if (lead_list.size() > 0) {
                            leadId = lead_list.get(0).get("leadId") == null ? "" : lead_list.get(0).get("leadId").toString();
                        }
                        if ("".equals(leadId)) {
                            message.put("invokeId", invokeId);
                            message.put("resultCode", "202");
                            message.put("resultMessage", "获取潜在客户编号失败");
                        } else {
                            message.put("invokeId", invokeId);
                            message.put("resultCode", "0000");
                            message.put("resultMessage", "SUCCESS");
                            message_data.put("leadId", leadId);
                            message.put("data", message_data);
                        }
                    }
                }
            } else {
                List<CCObject> lead_list = cs.cquery("Lead", "leadId='" + leadId + "' and is_deleted='0'");
                if (lead_list.size() > 0) {
                    String id = lead_list.get(0).get("id") == null ? "" : lead_list.get(0).get("id").toString();
                    String qzkhzt = lead_list.get(0).get("qzkhzt") == null ? "" : lead_list.get(0).get("qzkhzt").toString();
                    String sfdyfkgz = lead_list.get(0).get("sfdyfkgz") == null ? "" : lead_list.get(0).get("sfdyfkgz").toString();
                    String company = data_json.getString("company");
                    String socialCreditCode = data_json.getString("socialCreditCode");
                    String establishDate = data_json.getString("establishDate");
                    String wprmValidity = data_json.getString("wprmValidity");
                    String registeredProvince = data_json.getString("registeredProvince");
                    String registeredCity = data_json.getString("registeredCity");
                    String registeredCounty = data_json.getString("registeredCounty");
                    String registeredAddress = data_json.getString("registeredAddress");
                    String enterpriseContactProvinc = data_json.getString("enterpriseContactProvince");
                    String enterpriseContactCity = data_json.getString("enterpriseContactCity");
                    String enterpriseContactCounty = data_json.getString("enterpriseContactCounty");
                    String enterpriseContactAddress = data_json.getString("enterpriseContactAddress");
                    String enterpriseContactNumber = data_json.getString("enterpriseContactNumber");
                    for (Object o : files) {
                        net.sf.json.JSONObject jsonObject =(net.sf.json.JSONObject)o;
                        String fileCode = jsonObject.getString("fileCode");
                        String fileGuid = jsonObject.getString("fileGuid");
                        String fileName = jsonObject.getString("fileName");
                        CCObject gather_data = new CCObject("gather_data"); //收集资料明细
                        gather_data.put("fileId", fileCode);
                        gather_data.put("filePuth", fileGuid);
                        gather_data.put("fileNameR", fileName);
                        gather_data.put("correlation_id", id);
                        gather_data.put("fk_lead_name", id);
                        cs.insert(gather_data);
                    }
                    if ("".equals(registeredProvince) || "".equals(company) || "".equals(socialCreditCode)) {
                        List<CCObject> account_list = cs.cquery("Account", "certificate_num='" + jbr_certificateNum + "' and is_deleted='0'");
                        if (account_list.size() > 0) {
                            cs.cqlQuery("Lead", "update Lead set qzkhzt ='关闭已转换' where id='" + id + "'  ");
                            account_list.get(0).put("sflzqzkh", id);
                            cs.update(account_list.get(0));
                        }
                        if (!"".equals(jbr_registerCapital)){
                            lead_list.get(0).put("zczb",jbr_registerCapital);
                        }
                        if (!"".equals(jbr_leadName)) {
                            lead_list.get(0).put("name", jbr_leadName);
                        }
                        if (!"".equals(jbr_leadPhone)) {
                            lead_list.get(0).put("dianhua", jbr_leadPhone);
                        }
                        if (!"".equals(jbr_certificateNum)) {
                            lead_list.get(0).put("certificate_num", jbr_certificateNum);
                        }
                        if (!"".equals(jbr_realNameStatus)) {
                            lead_list.get(0).put("realNameStatus", jbr_realNameStatus);
                        }
                        if (!"".equals(jbr_individualProvince)) {
                            lead_list.get(0).put("individualProvince", jbr_individualProvince);
                        }
                        if (!"".equals(jbr_individualCity)) {
                            lead_list.get(0).put("individualCity", jbr_individualCity);
                        }
                        if (!"".equals(jbr_individualCounty)) {
                            lead_list.get(0).put("individualCounty", jbr_individualCounty);
                        }
                        if (!"".equals(jbr_individualAddress)) {
                            lead_list.get(0).put("individualAddress", jbr_individualAddress);
                        }
                        if (!"".equals(referrerName)) {
                            lead_list.get(0).put("referrerName", referrerName);
                        }
                        if (!"".equals(referrerId)) {
                            lead_list.get(0).put("referrerId", referrerId);
                        }

                        cs.update(lead_list.get(0));
                        message.put("invokeId", invokeId);
                        message.put("resultCode", "0000");
                        message.put("resultMessage", "更新公司信息成功");
                        message_data.put("leadId", leadId);
                        message.put("data", message_data);
                    } else {
                        List<CCObject> contact_list = cs.cquery("Contact", "socialCreditCode ='" + socialCreditCode + "' and is_deleted='0'");
                        if (contact_list.size() > 0) {
                            contact_list.get(0).put("fk_lead_name", id);
                            cs.update(contact_list);
                        }
                        if ("关闭已转换".equals(qzkhzt)) {
                            List<CCObject> account_list = cs.cquery("Account", "sflzqzkh='" + id + "' and is_deleted='0'");//通过潜在客户Id查询
                            String ownerid = "";
                            if (account_list.size() > 0) {
                                ownerid = account_list.get(0).get("ownerid") == null ? "" : account_list.get(0).get("ownerid").toString();
//                                // 	证件号码 通过证件号码
                                List<CCObject> fr_account_list = cs.cquery("Account", "certificate_num ='" + fr_certificateNum + "' and is_deleted='0'");
                                String account_legal_name = "";
                                if (fr_account_list.size() > 0) {
                                    account_legal_name = fr_account_list.get(0).get("id") == null ? "" : fr_account_list.get(0).get("id").toString();
                                } else {
                                    CCObject cco = new CCObject("Account");
                                    cco.put("name", fr_leadName);
                                    cco.put("leixing", "个人客户");
                                    cco.put("certificate_type", "身份证");
                                    cco.put("certificate_num", fr_certificateNum);
                                    cco.put("account_id", "");
                                    cco.put("recordtype", "202144AED354A948tbSl");
                                    cco.put("source", "云链");
                                    cco.put("ownerid", ownerid);
                                    cco.put("sflzqzkh", id);
                                    ServiceResult insert = cs.insert(cco);
                                    account_legal_name = insert.get("id").toString();
                                }
                                CCObject company_cco = new CCObject("Account");
                                company_cco.put("name", company);
                                company_cco.put("leixing", "企业法人");
                                company_cco.put("zczb",jbr_registerCapital);
                                company_cco.put("certificate_type", "统一社会信用代码");
                                company_cco.put("certificate_num", socialCreditCode);
                                company_cco.put("account_id", "");
                                company_cco.put("recordtype", "20219EDBD4AEB2FIbe2L");
                                company_cco.put("source", "云链");
                                company_cco.put("ownerid", ownerid);
                                company_cco.put("sflzqzkh", id);
                                company_cco.put("account_legal_name", account_legal_name);
                                company_cco.put("startdate", establishDate);
                                company_cco.put("legal_card_num", fr_certificateNum);
                                company_cco.put("khdd", registeredAddress);
                                company_cco.put("business_phone", enterpriseContactNumber);
                                company_cco.put("legal_card_type", "身份证");
                                cs.insert(company_cco);
                                message.put("invokeId", invokeId);
                                message.put("resultCode", "0000");
                                message.put("resultMessage", "更新公司信息成功");
                                message_data.put("leadId", leadId);
                                message.put("data", message_data);
                            } else {
                                message.put("invokeId", invokeId);
                                message.put("resultCode", "206");
                                message.put("resultMessage", "法人对应企业客户不存在，无法开户");
                                message_data.put("leadId", leadId);
                                message.put("data", message_data);
                            }
                            CCObject ylqyxx = new CCObject("ylqyxx"); //云链企业信息
                            ylqyxx.put("corporateCertificateNum", fr_certificateNum);
                            ylqyxx.put("zczb",jbr_registerCapital);
                            ylqyxx.put("full_name", fr_leadName);
                            ylqyxx.put("phone", fr_leadPhone);
                            ylqyxx.put("corporateRealNameStatus", fr_realNameStatus);
                            ylqyxx.put("corporateProvince", fr_individualProvince);
                            ylqyxx.put("corporateCity", fr_individualCity);
                            ylqyxx.put("corporateCounty", fr_individualCounty);
                            ylqyxx.put("corporateAddress", fr_individualAddress);
                            ylqyxx.put("qzkhly", leadSource);
                            ylqyxx.put("company", company);
                            ylqyxx.put("socialCreditCode", socialCreditCode);
                            ylqyxx.put("establishDate", establishDate);
                            ylqyxx.put("wprmValidity", wprmValidity);
                            ylqyxx.put("registeredProvince", registeredProvince);
                            ylqyxx.put("registeredCity", registeredCity);
                            ylqyxx.put("registeredCounty", registeredCounty);
                            ylqyxx.put("registeredAddress", registeredAddress);
                            ylqyxx.put("enterpriseContactProvinc", enterpriseContactProvinc);
                            ylqyxx.put("enterpriseContactCity", enterpriseContactCity);
                            ylqyxx.put("enterpriseContactCounty", enterpriseContactCounty);
                            ylqyxx.put("enterpriseContactAddress", enterpriseContactAddress);
                            ylqyxx.put("enterpriseContactNumber", enterpriseContactNumber);
                            ylqyxx.put("fk_lead_name", id);
                            ylqyxx.put("ownerid", ownerid);
                            cs.insert(ylqyxx);
                        } else {
                            String ownerid = lead_list.get(0).get("ownerid") == null ? "" : lead_list.get(0).get("ownerid").toString();
                            List<CCObject> account_list = cs.cquery("Account", "certificate_num='" + socialCreditCode + "' and source ='云链' and is_deleted='0'");
                            if (!"".equals(jbr_leadName)) {
                                lead_list.get(0).put("name", jbr_leadName);
                            }
                            if (!"".equals(jbr_registerCapital)){
                                lead_list.get(0).put("zczb",jbr_registerCapital);
                            }
                            if (!"".equals(jbr_leadPhone)) {
                                lead_list.get(0).put("dianhua", jbr_leadPhone);
                            }
                            if (!"".equals(jbr_certificateNum)) {
                                lead_list.get(0).put("certificate_num", jbr_certificateNum);
                            }
                            if (!"".equals(jbr_realNameStatus)) {
                                lead_list.get(0).put("realNameStatus", jbr_realNameStatus);
                            }
                            if (!"".equals(jbr_individualProvince)) {
                                lead_list.get(0).put("individualProvince", jbr_individualProvince);
                            }
                            if (!"".equals(jbr_individualCity)) {
                                lead_list.get(0).put("individualCity", jbr_individualCity);
                            }
                            if (!"".equals(jbr_individualCounty)) {
                                lead_list.get(0).put("individualCounty", jbr_individualCounty);
                            }
                            if (!"".equals(jbr_individualAddress)) {
                                lead_list.get(0).put("individualAddress", jbr_individualAddress);
                            }
                            if (!"".equals(referrerName)) {
                                lead_list.get(0).put("referrerName", referrerName);
                            }
                            if (!"".equals(referrerId)) {
                                lead_list.get(0).put("referrerId", referrerId);
                            }

                            lead_list.get(0).put("corporateCertificateNum", fr_certificateNum);
                            lead_list.get(0).put("full_name", fr_leadName);
                            lead_list.get(0).put("phone", fr_leadPhone);
                            lead_list.get(0).put("corporateRealNameStatus", fr_realNameStatus);
                            lead_list.get(0).put("corporateProvince", fr_individualProvince);
                            lead_list.get(0).put("corporateCity", fr_individualCity);
                            lead_list.get(0).put("corporateCounty", fr_individualCounty);
                            lead_list.get(0).put("corporateAddress", fr_individualAddress);
                            lead_list.get(0).put("qzkhly", leadSource);
                            lead_list.get(0).put("company", company);
                            lead_list.get(0).put("socialCreditCode", socialCreditCode);
                            lead_list.get(0).put("establishDate", establishDate);
                            lead_list.get(0).put("wprmValidity", wprmValidity);
                            lead_list.get(0).put("registeredProvince", registeredProvince);
                            lead_list.get(0).put("registeredCity", registeredCity);
                            lead_list.get(0).put("registeredCounty", registeredCounty);
                            lead_list.get(0).put("registeredAddress", registeredAddress);
                            lead_list.get(0).put("enterpriseContactProvinc", enterpriseContactProvinc);
                            lead_list.get(0).put("enterpriseContactCity", enterpriseContactCity);
                            lead_list.get(0).put("enterpriseContactCounty", enterpriseContactCounty);
                            lead_list.get(0).put("enterpriseContactAddress", enterpriseContactAddress);
                            lead_list.get(0).put("enterpriseContactNumber", enterpriseContactNumber);
                            lead_list.get(0).put("isMobileRegister", "true");

                            if (account_list.size() > 0) {
                                com.cloudcc.core.fag.CCUST_20033086DB53B7.SendRequestToYunLian sendRequestToYunLian = new com.cloudcc.core.fag.CCUST_20033086DB53B7.SendRequestToYunLian(uuserInfo);
                                cs.cqlQuery("Lead", "update Lead set qzkhzt ='关闭已转换' where id='" + id + "'  ");
                                String cusId = account_list.get(0).get("account_id") == null ? "" : account_list.get(0).get("account_id").toString();
                                String account_name = account_list.get(0).get("name") == null ? "" : account_list.get(0).get("name").toString();
                                String legal_name = account_list.get(0).get("account_legal_name") == null ? "" : account_list.get(0).get("account_legal_name").toString();
                                List<CCObject> legal_list = cs.cquery("Account", "id='" + legal_name + "' and is_deleted='0'");
                                String legalCusId = "";
                                if (legal_list.size() > 0) {
                                    legalCusId = legal_list.get(0).get("account_id") == null ? "" : legal_list.get(0).get("account_id").toString();
                                }
                                String url = "notify/bindCustRelations";
                                JSONObject yl_data = new JSONObject();
                                yl_data.put("customerId", cusId);
                                yl_data.put("leadId", leadId);
                                yl_data.put("customerName", account_name);
                                yl_data.put("legalCusId", legalCusId);
                                JSONObject yl_data_json = new JSONObject();
                                yl_data_json.put("invokeId", invokeId);

                                yl_data_json.put("data", yl_data);
                                String requestStr = sendRequestToYunLian.sendPost(url, yl_data_json.toString());

                                /**
                                 * 插入接口调用记录
                                 */
                                CCObject interfaceRecord = new CCObject("interface_record");
                                interfaceRecord.put("record_ID", "");
                                interfaceRecord.put("url", url);
                                interfaceRecord.put("datas", yl_data_json);
                                interfaceRecord.put("requestData", requestStr);
                                cs.insert(interfaceRecord);


                                List<CCObject> gr_account_list = cs.cquery("Account", "certificate_num='" + fr_certificateNum + "' and is_deleted='0'");
                                String account_legal_name = "";

                                if (gr_account_list.size() > 0) {
                                    account_legal_name = gr_account_list.get(0).get("id") == null ? "" : gr_account_list.get(0).get("id").toString();
                                } else {
                                    CCObject cco = new CCObject("Account");
                                    cco.put("name", fr_leadName);
                                    cco.put("leixing", "个人客户");
                                    cco.put("certificate_type", "身份证");
                                    cco.put("certificate_num", fr_certificateNum);
                                    cco.put("account_id", "");
                                    cco.put("recordtype", "202144AED354A948tbSl");
                                    cco.put("source", "云链");
                                    cco.put("phone_num", fr_leadPhone);
                                    cco.put("ownerid", ownerid);
                                    cco.put("sflzqzkh", id);
                                    ServiceResult insert = cs.insert(cco);
                                    account_legal_name = insert.get("id").toString();
                                }
                                account_list.get(0).put("sflzqzkh", id);
                                account_list.get(0).put("account_legal_name", account_legal_name);
                                account_list.get(0).put("legal_card_type", "身份证");
                                account_list.get(0).put("legal_card_num", fr_certificateNum);
                                cs.update(account_list.get(0));
                                cs.updateLt(lead_list.get(0));
                            } else {
                                cs.update(lead_list.get(0));
                            }

                            if ("".equals(sfdyfkgz) || "是".equals(sfdyfkgz)) {
                                String rule_url = "rule-web/open-api/notify/invokeRiskControlRule";
                                String rule_invokeId = UUID.randomUUID().toString().replaceAll("-", "");
                                String ruleCalculateApplyId = UUID.randomUUID().toString().replaceAll("-", "");
                                //声明CCService用于后续数据操作

                                com.cloudcc.core.fag.CCUST_20033086DB53B7.SendRequestNoUrl sendRequestNoUrl = new com.cloudcc.core.fag.CCUST_20033086DB53B7.SendRequestNoUrl(uuserInfo);
                                //

                                String systemId = "CRM";//
                                String businessType = "01";//
//                                String noticeUrl = "https://k8mm3cmt3235c7ed72cede6e.cloudcc.com/customize/page/208f9f6b9a/noticeRuleCalculateResult.jsp?name=noticeRuleCalculateResult";//
                                String noticeUrl = "https://k8mm3cmt3235c7ed72cede6e.cloudcc.com/customize/page/eed30c4aea/noticeRuleCalculateResult.jsp?name=noticeRuleCalculateResult";//

                                String productNo = "CRM";//
                                String userType = "1";//


                                net.sf.json.JSONObject rule_data = new net.sf.json.JSONObject();
                                rule_data.put("ruleCalculateApplyId", ruleCalculateApplyId);
                                rule_data.put("systemId", systemId);
                                rule_data.put("businessNo", id);
                                rule_data.put("businessType", businessType);
                                rule_data.put("noticeUrl", noticeUrl);
                                rule_data.put("productNo", productNo);
                                rule_data.put("idNumber", socialCreditCode);
                                rule_data.put("customerName", company);
                                rule_data.put("userType", userType);
                                rule_data.put("mobile", fr_leadPhone);
                                rule_data.put("legalPersonName", fr_leadName);
                                rule_data.put("legalPersonIDCard", fr_certificateNum);
                                net.sf.json.JSONObject rule_data_json = new net.sf.json.JSONObject();
                                rule_data_json.put("invokeId", rule_invokeId);
                                rule_data_json.put("data", rule_data);
                                String rule_requestStr = sendRequestNoUrl.sendPost(rule_url, rule_data_json.toString());
                                lead_list.get(0).put("sfdyfkgz", "否");
                                cs.update(lead_list.get(0));
                                /**
                                 * 插入接口调用记录
                                 */
                                CCObject rule_interfaceRecord = new CCObject("interface_record");
                                rule_interfaceRecord.put("record_ID", "");
                                rule_interfaceRecord.put("url", rule_url);
                                rule_interfaceRecord.put("datas", rule_data_json);
                                rule_interfaceRecord.put("requestData", rule_requestStr);
                                cs.insert(rule_interfaceRecord);
                            }
                            message.put("invokeId", invokeId);
                            message.put("resultCode", "0000");
                            message.put("resultMessage", "更新公司信息成功");
                            message_data.put("leadId", leadId);
                            message.put("data", message_data);
                        }
                    }
                } else {
                    message.put("invokeId", invokeId);
                    message.put("resultCode", "205");
                    message.put("resultMessage", "潜客不存在");
                }
            }
        } catch (Exception e) {
            String str = "";
            if ("".equals(e.getMessage()) || e.getMessage() == null) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            } else {
                str = e.getMessage();
            }
            message.put("invokeId", invokeId);
            message.put("resultCode", "500");
            message.put("resultMessage", str);
        }
        /**
         * 插入接口调用记录
         */
        CCObject interfaceRecord_updata = new CCObject("interface_record");
        interfaceRecord_updata.put("record_ID", invokeId);
        interfaceRecord_updata.put("url", "潜在客户新建更新接口");
        interfaceRecord_updata.put("datas", message);
        interfaceRecord_updata.put("requestData", data);
        cs.insert(interfaceRecord_updata);
        request.setAttribute("jsonmsg", message.toString());
      </cc>
    <cc:forward page="/platform/activity/task/getNodes.jsp">
	<cc:param name="jsonmsg" value="" />
    </cc:forward>
    }
}
