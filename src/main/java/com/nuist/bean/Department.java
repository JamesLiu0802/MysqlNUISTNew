package com.nuist.bean;

/**
 * 从数据库读取Department数据并转换的Bean
 */
public class Department {
    //Dm Mc Fjjg Jglx Qyzt CC PX JC
    private String Dm;
    private String Mc;
    private String Fjjg;
    private String Jglx;
    private String Qyzt;
    private String CC;
    private String PX;
    private String JC;

    @Override
    public String toString() {
        return "Department{" +
                "Dm='" + Dm + '\'' +
                ", Mc='" + Mc + '\'' +
                ", Fjjg='" + Fjjg + '\'' +
                ", Jglx='" + Jglx + '\'' +
                ", Qyzt='" + Qyzt + '\'' +
                ", CC='" + CC + '\'' +
                ", PX='" + PX + '\'' +
                ", JC='" + JC + '\'' +
                '}';
    }

    public Department() {
    }

    public Department(String dm, String mc, String fjjg, String jglx, String qyzt, String CC, String PX, String JC) {
        Dm = dm;
        Mc = mc;
        Fjjg = fjjg;
        Jglx = jglx;
        Qyzt = qyzt;
        this.CC = CC;
        this.PX = PX;
        this.JC = JC;
    }

    public String getDm() {
        return Dm;
    }

    public void setDm(String dm) {
        Dm = dm;
    }

    public String getMc() {
        return Mc;
    }

    public void setMc(String mc) {
        Mc = mc;
    }

    public String getFjjg() {
        return Fjjg;
    }

    public void setFjjg(String fjjg) {
        Fjjg = fjjg;
    }

    public String getJglx() {
        return Jglx;
    }

    public void setJglx(String jglx) {
        Jglx = jglx;
    }

    public String getQyzt() {
        return Qyzt;
    }

    public void setQyzt(String qyzt) {
        Qyzt = qyzt;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getPX() {
        return PX;
    }

    public void setPX(String PX) {
        this.PX = PX;
    }

    public String getJC() {
        return JC;
    }

    public void setJC(String JC) {
        this.JC = JC;
    }
}
