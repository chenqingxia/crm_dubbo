package com.tx.app.util;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class mdencode {

    public String testDigest(String myinfo) {
        byte[] digesta = null;
        try {
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
            alga.update(myinfo.getBytes());
            digesta = alga.digest();

        } catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        }
        return this.byte2hex(digesta);
    }

    public String byte2hex(byte[] b) { // 二行制转字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    /**
     * md5加密，增加密钥key，如key==null，则返回testDigest(myinfo)
     * 
     * @param myinfo
     * @param key
     * @return
     */
    public String testDigest(String myinfo, String key) {
        if (key == null) {
            return testDigest(myinfo);
        } else {
            return testDigest(myinfo + key);
        }
    }


    /**
     *
     * @param myinfo
     * @param encode
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public  String Digest(String myinfo, String encode) throws UnsupportedEncodingException {
        byte[] digesta = null;
        try {
            java.security.MessageDigest alga = java.security.MessageDigest
                    .getInstance("MD5");
            alga.update(myinfo.getBytes(encode));
            digesta = alga.digest();

        } catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("MD5发生错误！");
        }
        return byte2hex(digesta);
    }


}