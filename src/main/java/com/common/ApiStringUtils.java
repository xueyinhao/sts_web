package com.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ApiStringUtils extends StringUtils {
    private static final String SYSTEM_FORBIDDEN_CHARS = "!\"#$%&'()=~|\\@[+;:]<>,._ ";

    private static final char UTF8NULLCHAR = '\000';

    public static char chkForbiddenChar(String pstrTarget, String pstrForbidden) {
        if ((pstrForbidden == null) || (pstrForbidden.length() == 0)) {
            pstrForbidden = "!\"#$%&'()=~|\\@[+;:]<>,._ ";
        }
        return findChar(pstrTarget, pstrForbidden);
    }

    public static char findChar(String pstrTarget, String pstrMatching) {
        if ((pstrTarget == null) || (pstrMatching == null)) {
            return '\000';
        }

        String chars = pstrMatching;
        char chrRet = '\000';

        CharacterIterator iter = new StringCharacterIterator(pstrTarget);
        for (char c = iter.first(); c != 65535; c = iter.next()) {
            int idx = chars.indexOf(c);
            if (idx >= 0) {
                chrRet = chars.charAt(idx);
                break;
            }
        }
        return chrRet;
    }

    public static String formatDouble2(Double d) {
        BigDecimal big = BigDecimal.valueOf(d.doubleValue());
        big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
        return big.toString();
    }

    public static String formatFloordDouble2(Double d) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(d);
    }

    public static String noHtml(String htmlStr) {
        if (StringUtils.isEmpty(htmlStr)) {
            return "";
        }
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";
        Pattern p_script = Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        Pattern p_html = Pattern.compile(regEx_html, 2);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        return htmlStr;
    }

    public static String filterHtml(String htmlStr) {
        if (StringUtils.isEmpty(htmlStr)) {
            return "";
        }
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";

        Pattern p_script = Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        return htmlStr;
    }

    public static String nullToBlank(String str) {
        if (str == null) {
            str = "";
        }

        return str;
    }

    public static String nullToZero(String str) {
        String result = "0";
        if ((str == null) || (str.trim().equals(""))) {
            return result;
        }

        return str;
    }

    public static String removeImg(String str) {
        Pattern p = Pattern.compile("(?is)<img\\s+?[^<>]*?/?>");
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return p.matcher(str).replaceAll("");
    }

    

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            return "md5 加密异常";
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    public static String fillTemplet(String templet, String phstr, String[] paras) {
        StringBuffer templetSB = new StringBuffer(templet);
        int i = 0;
        while ((templetSB.indexOf(phstr) >= 0) && (i < paras.length)) {
            templetSB.replace(templetSB.indexOf(phstr), templetSB.indexOf(phstr) + phstr.length(), paras[i]);
            i++;
        }
        return templetSB.toString();
    }

    public static String hideFirstChar(String str, int len) {
        if (StringUtils.isEmpty(str))
            return "";
        char[] chars = str.toCharArray();
        if (str.length() <= len) {
            for (int i = 0; i < 1; i++)
                chars[i] = '*';
        } else {
            for (int i = 0; i < len; i++) {
                chars[i] = '*';
            }
        }
        str = new String(chars);
        return str;
    }

    public static String hideLastChar(String str, int len) {
        if (StringUtils.isEmpty(str))
            return "";
        char[] chars = str.toCharArray();
        if (str.length() <= len) {
            for (int i = 0; i < chars.length; i++)
                chars[i] = '*';
        } else {
            for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
                chars[i] = '*';
            }
        }
        str = new String(chars);
        return str;
    }

    public static String getSafeStringXSS(String s) {
        if ((s == null) || ("".equals(s))) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '\'':
                sb.append("&prime;");
                break;
            case '′':
                sb.append("&prime;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case '＂':
                sb.append("&quot;");
                break;
            case '&':
                sb.append("＆");
                break;
            case '#':
                sb.append("＃");
                break;
            case '\\':
                sb.append(65509);
                break;
            case '=':
                sb.append("&#61;");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String generatedcode() {
        List<Integer> list = GetRandomNumber();
        Iterator<Integer> iterator = list.iterator();
        String temp = "";
        while (iterator.hasNext()) {
            temp = temp + iterator.next();
        }
        return temp;
    }

    public static List<Integer> GetRandomNumber() {
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        while (list.size() < 6) {
            list.add(Integer.valueOf(random.nextInt(10)));
        }
        return list;
    }
}