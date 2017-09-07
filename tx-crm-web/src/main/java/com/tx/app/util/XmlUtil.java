package com.tx.app.util;

public class XmlUtil {

    /***************************************************************************
     * 解析xml add by freeman983 2011.11.04
     * 
     * @param src
     * @param name
     * @return
     */
    public static String getParameter(String src, String name) {
        String tempstr = src;
        if (tempstr == null) {
            return "";
        }
        int si = tempstr.indexOf("<" + name + ">");
        if (si == -1) {
            return "";
        } else {
            tempstr = tempstr.substring(si + name.length() + 2);
        }
        int ei = tempstr.indexOf("</" + name + ">");
        String ret = tempstr.substring(0, ei);
        if (ret == null) {
            return "";
        } else {
            return ret;
        }
    }
}
