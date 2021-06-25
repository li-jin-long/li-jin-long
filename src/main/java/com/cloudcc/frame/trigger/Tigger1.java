package com.cloudcc.frame.trigger;

import com.cloudcc.deveditor.core.CCObject;
import com.cloudcc.deveditor.core.CCService;
import com.cloudcc.deveditor.core.ServiceResult;
import com.cloudcc.deveditor.trigger.TriggerEditor;

import java.util.List;

public class Tigger1  extends TriggerEditor {

    public void 打印用户名字(){
        CCService ccs = new CCService(userInfo);
        String money = record_new.get("money") == null ? "" : record_new.get("money") + "";//金额
        String user = record_new.get("username") == null ? "" : record_new.get("username") + "";//用户
        List<CCObject> ccuser = ccs.cquery("ccuser", "id='" + user + "'");
        if(ccuser.size()>0){
            String name = ccuser.get(0).get("name") == null ? "" : ccuser.get(0).get("name") + "";
            if(true)trigger.addErrorMessage(name);

        }
    }

}
