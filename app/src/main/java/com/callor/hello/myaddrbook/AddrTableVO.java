package com.callor.hello.myaddrbook;

public class AddrTableVO {
    private String sname ;
    private String sbirth ;
    private String stel ;

    public String getSname() {
        return sname;
    }

    public AddrTableVO setSname(String sname) {
        this.sname = sname;
        return this;
    }

    public String getSbirth() {
        return sbirth;
    }

    public AddrTableVO setSbirth(String sbirth) {
        this.sbirth = sbirth;
        return this;
    }

    public String getStel() {
        return stel;
    }

    public AddrTableVO setStel(String stel) {
        this.stel = stel;
        return this;
    }
}
