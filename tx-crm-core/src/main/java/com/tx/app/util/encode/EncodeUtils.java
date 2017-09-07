package com.tx.app.util.encode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * @类功能说明：编码通用类EncodeUtils,集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.

 */
public class EncodeUtils
{

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * 函数功能: Hex编码
     * 
     * @author chenqingxia 2012-8-30
     * @param input byte[]
     * @return String
     * @throws Exception
     */
    public static String hexEncode(byte[] input)
    {
        return Hex.encodeHexString(input);
    }

    /**
     * 函数功能: Hex解码
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] hexDecode(String input)
    {
        try
        {
            return Hex.decodeHex(input.toCharArray());
        }
        catch (DecoderException e)
        {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * 函数功能: Base64编码
     * 
     * @author chenqingxia 2012-8-30
     * @param input byte[]
     * @return String
     * @throws Exception
     */
    public static String base64Encode(byte[] input)
    {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * 函数功能: Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548)
     * 
     * @author chenqingxia 2012-8-30
     * @param input byte[]
     * @return String
     * @throws Exception
     */
    public static String base64UrlSafeEncode(byte[] input)
    {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * 函数功能: Base64解码
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String input)
    {
        return Base64.decodeBase64(input);
    }

    /**
     * 函数功能: URL 编码, Encode默认为UTF-8
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @return String
     * @throws Exception
     */
    public static String urlEncode(String input)
    {
        return urlEncode(input, DEFAULT_URL_ENCODING);
    }

    /**
     * 函数功能: URL 编码
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @param encoding String
     * @return String
     * @throws Exception
     */
    public static String urlEncode(String input, String encoding)
    {
        try
        {
            return URLEncoder.encode(input, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * 函数功能: URL 解码, Encode默认为UTF-8
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @return String
     * @throws Exception
     */
    public static String urlDecode(String input)
    {
        return urlDecode(input, DEFAULT_URL_ENCODING);
    }

    /**
     * 函数功能: URL 解码
     * 
     * @author chenqingxia 2012-8-30
     * @param input String
     * @param encoding String
     * @return String
     * @throws Exception
     */
    public static String urlDecode(String input, String encoding)
    {
        try
        {
            return URLDecoder.decode(input, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * 函数功能: Html 转码
     * 
     * @author chenqingxia 2012-8-30
     * @param html String
     * @return String
     * @throws Exception
     */
    public static String htmlEscape(String html)
    {
        return StringEscapeUtils.escapeHtml(html);
    }

    /**
     * 函数功能: Html 解码
     * 
     * @author chenqingxia 2012-8-30
     * @param htmlEscaped String
     * @return String
     * @throws Exception
     */
    public static String htmlUnescape(String htmlEscaped)
    {
        return StringEscapeUtils.unescapeHtml(htmlEscaped);
    }

    /**
     * 函数功能: Xml 转码
     * 
     * @author chenqingxia 2012-8-30
     * @param xml String
     * @return String
     * @throws Exception
     */
    public static String xmlEscape(String xml)
    {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * 函数功能: Xml 解码
     * 
     * @author chenqingxia 2012-8-30
     * @param xmlEscaped String
     * @return String
     * @throws Exception
     */
    public static String xmlUnescape(String xmlEscaped)
    {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * 函数功能: MD5签名
     * 
     * @author chenqingxia 2012-8-30
     * @param myinfo String
     * @return String
     * @throws Exception
     */
    public static String testDigest(String myinfo)
    {
        byte[] digesta = null;
        try
        {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(myinfo.getBytes("gbk"));
            digesta = alga.digest();
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("-----------------------------------------------");
            System.out.println("MD5发生错误！");
            System.out.println("-----------------------------------------------");
            return null;
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.out.println("-----------------------------------------------");
            System.out.println("MD5发生错误！");
            System.out.println("-----------------------------------------------");
            return null;
        }
        return byte2hex(digesta);
    }

    /**
     * 函数功能: MD5签名
     *
     * @author chenqingxia 2012-8-30
     * @param myinfo String
     * @param encode String
     * @return String
     * @throws Exception
     */
    public static String testDigest(String myinfo, String encode) throws UnsupportedEncodingException
    {
        byte[] digesta = null;
        try
        {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(myinfo.getBytes(encode));
            digesta = alga.digest();

        }
        catch (NoSuchAlgorithmException ex)
        {
            System.out.println("MD5发生错误！");
            return null;
        }
        return byte2hex(digesta);
    }

    /**
     * 函数功能: 二进制转为十六进制串
     *
     * @author chenqingxia 2012-8-30
     * @param b byte[]
     * @return String
     * @throws Exception
     */
    public static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
            {
                hs = hs + "0" + stmp;
            }
            else
            {
                hs = hs + stmp;
            }
            if (n < b.length - 1)
            {
                // hs = hs;
            }
        }
        return hs;
    }

    /**
     * 函数功能: 生成随即密码
     * 
     * @author chenqingxia 2012-8-30
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int pwd_len)
    {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        /*
         * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
         * 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
         * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
         */

        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        while (count < pwd_len)
        {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length)
            {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }


    public static String toHex(byte input[]) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

  public static void main(String[] args) {
      String ss="111111";
      String result=testDigest(ss);
      System.out.println("result="+result);

  }
}
