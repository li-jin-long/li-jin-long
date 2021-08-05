package com.cloudcc.frame.daoru;

public class shiti {
    private Integer leadNumbers;
    private Integer numberClients;
    private Integer contracts;
    private Integer moneySum;
    private Integer payBack;
    private String moon;

    public Integer getLeadNumbers() {
        return leadNumbers;
    }

    public void setLeadNumbers(Integer leadNumbers) {
        this.leadNumbers = leadNumbers;
    }

    public Integer getNumberClients() {
        return numberClients;
    }

    public void setNumberClients(Integer numberClients) {
        this.numberClients = numberClients;
    }

    public Integer getContracts() {
        return contracts;
    }

    public void setContracts(Integer contracts) {
        this.contracts = contracts;
    }

    public Integer getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(Integer moneySum) {
        this.moneySum = moneySum;
    }

    public Integer getPayBack() {
        return payBack;
    }

    public void setPayBack(Integer payBack) {
        this.payBack = payBack;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    @Override
    public String toString() {
        return "[" +
                "线索数量='" + leadNumbers + '\'' +
                ", 新增客户数量='" + numberClients + '\'' +
                ", 合同签约的情况='" + contracts + '\'' +
                ", 总金额='" + moneySum + '\'' +
                ", 回款='" + payBack + '\'' +
                ", 月度='" + moon + '\'' +
                ']';
    }
}
