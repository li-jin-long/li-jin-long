package com.cloudcc.frame.daoru;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class daor {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            shiti duix = new shiti();
            JSONObject jsonObject = new JSONObject();
            Random ra = new Random();
            int leadNumbers = ra.nextInt(20); //线索数量
            duix.setLeadNumbers(leadNumbers);
            jsonObject.put("线索数量", leadNumbers);
            Random client = new Random();
            int numberClients = client.nextInt(20); //新增客户数量
            duix.setNumberClients(numberClients);
            jsonObject.put("新增客户数量", numberClients);

            Random contract = new Random();
            int contracts = contract.nextInt(20); //合同签约的情况
            duix.setContracts(contracts);
            jsonObject.put("合同签约的情况", contracts);

            Random sum = new Random();
            int moneySum = sum.nextInt(400000); //总金额
            duix.setMoneySum(moneySum);
            jsonObject.put("总金额", moneySum);

            Random back = new Random();
            int payBack = back.nextInt(500000); //回款
            duix.setPayBack(payBack);
            jsonObject.put("回款", payBack);

            duix.setMoon(i + "月");
            jsonObject.put("月份", i + "月");
            System.out.println(duix);

            objects.add(jsonObject);
        }
        System.out.println(objects);
    }
}
