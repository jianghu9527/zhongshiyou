package cn.sichuan.cd.zzsy.fragment;

import java.io.Serializable;

public class DynamiteDownloadMap implements Serializable {

    public int level;
    public String msg;
    public String size;

    public DynamiteDownloadMap() {

    }

    public DynamiteDownloadMap(int level, String msg, String size) {
        this.level = level;
        this.msg = msg;
        this.size = size;
    }
}