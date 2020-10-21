package io.github.talelin.latticy.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>公共方法类</p>
 * <p>提供字符串处理的实用方法集</p>
 *
 * @author vino
 * @version 1.0
 */

public class StringUtil {


    public StringUtil() {
    }

    public static final String escapeForIntro(String string) {
        //			 String str = escapeHTMLTags(string);
        String str = string;
        str = replace(str, "\r\n", "<br>");
        str = replace(str, "\n", "<br>");
        str = replace(str, "'", "\\'");
        return replace(str, "\r", "");

    }

    /**
     * 判断Map是否包含key以及key对应的值不为空
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean hasKeyValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return false;
        } else {
            return map.get(key) == null ? false : ("".equals(map.get(key).toString()) ? false : ("[]".equals(map.get(key).toString()) ? false : true));
        }
    }

    /**
     * 获取map对应key的值是List
     *
     * @param map
     * @param key
     * @return
     */
    public static List<Object> getMapKeyList(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        } else if (map.get(key) == null) {
            return null;
        } else if (map.get(key) instanceof List) {
            return (ArrayList<Object>) (map.get(key));
        } else {
            return null;
        }
    }

    /**
     * 判断两个对象是否相同
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean equals(Object o1, Object o2) {
        boolean res = true;
        if (o1 == null && o2 != null) {
            res = false;
        } else if (o1 != null && o2 == null) {
            res = false;
        } else if (o1 == null && o2 == null) {
            res = true;
        } else if (!o1.toString().equals(o2.toString())) {
            res = false;
        }
        return res;
    }

    public static boolean isNull(Object obj) {
        if (null == obj) {
            return true;
        } else {
            return "".equals(obj.toString().trim()) || "{}".equals(obj.toString().trim());
        }
        //return obj==null || isNull(obj.toString());
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 得到非空的字符串，若字符串对象为null，则返回""。
     *
     * @param objValue Object待转换的原字符串对象
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(Object objValue) {
        return (objValue == null ? "" : objValue.toString());
    }

    /**
     * 得到非空的字符串，若字符串为null，则返回""
     *
     * @param strValue String待转换的原字符串
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(String strValue) {
        return (strValue == null ? "" : strValue.trim());
    }

    /**
     * 将中文转化成AscII码以便存入数据库
     *
     * @param s 中文字符串
     * @return 16进制字符串
     */
    public static String ChineseStringToAscii(String s) {
        return null;
    }

    /**
     * 将UTF-8转化成AscII码以便存入数据库
     *
     * @param s 中文字符串
     * @return 16进制字符串
     */
    public static String ChineseStringToUTF(String s) {
        return null;
    }

    /**
     * 将AscII字符转换成汉字
     *
     * @param s -  ASCII字符串
     * @return 汉字
     */
    public static String AsciiToChineseString(String s) {
        return null;
    }

    /**
     * 判断一个字符串是否为 NUll 或为空
     *
     * @param inStr inStr
     * @return boolean
     */
    public static boolean isValid(String inStr) {
        if (inStr == null) {
            return false;
        } else if (inStr.equals("")) {
            return false;
        } else if (inStr.equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkNotNull(String str) {
        boolean flag = false;

        if (str != null && str.trim().length() != 0)
            flag = true;
        return flag;
    }

    /**
     * 转换字符串第一个字符为大写
     *
     * @param str String
     * @return String
     */
    public static String getStrByUpperFirstChar(String str) {
        try {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 转换字符串第一个字符为小写
     *
     * @param str String
     * @return String
     */
    public static String getStrByLowerFirstChar(String str) {
        try {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 通过字符串转换成相应的整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return int 转换完成的整型
     */
    public static int getStrToInt(String strValue) {
        if (null == strValue) {
            return 0;
        }
        int iValue = 0;
        try {
            iValue = new Integer(strValue.trim()).intValue();
        } catch (Exception ex) {
            iValue = 0;
        }
        return iValue;
    }

    /**
     * 通过字符串转换成相应的DOUBLE，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return double 转换完成的DOUBLE
     */
    public static double getStrToDouble(String strValue) {
        if (null == strValue) {
            return 0;
        }
        double dValue = 0;
        try {
            dValue = Double.parseDouble(strValue.trim());
        } catch (Exception ex) {
            dValue = 0;
        }
        return dValue;
    }

    /**
     * 通过字符串转换成相应的短整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return short 转换完成的短整型
     */
    public static short getStrToShort(String strValue) {
        if (null == strValue) {
            return 0;
        }
        short iValue = 0;
        try {
            iValue = new Short(strValue.trim()).shortValue();
        } catch (Exception ex) {
            iValue = 0;
        }
        return iValue;
    }

    /**
     * 通过字符串转换成相应的长整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return long 转换完成的长整型
     */
    public static long getStrToLong(String strValue) {
        if (null == strValue) {
            return 0;
        }
        long lValue = 0;
        try {
            lValue = new Long(strValue.trim()).longValue();
        } catch (Exception ex) {
            lValue = 0;
        }
        return lValue;
    }

    public static String toLengthForEn(String str, int length) {
        if (null != str) {
            if (str.length() <= length) {
                return str;
            } else {
                str = str.substring(0, length - 2);
                str = str + "..";
                return str;
            }
        } else {
            return "";
        }
    }

    /**
     * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
     *
     * @param str    String
     * @param length int
     * @return String
     */
    public static String toLengthForIntroduce(String str, int length) {
        str = delTag(str);

        byte[] strByte = str.getBytes();
        int byteLength = strByte.length;
        char[] charArray;
        StringBuffer buff = new StringBuffer();
        if (byteLength > (length * 2)) {
            charArray = str.toCharArray();
            int resultLength = 0;
            for (int i = 0; i < charArray.length; i++) {
                resultLength += String.valueOf(charArray[i]).getBytes().length;
                if (resultLength > (length * 2)) {
                    break;
                }
                buff.append(charArray[i]);

            }
            buff.append("..");
            str = buff.toString();
        }

        //		str = replace(str, "'", "\\'");
        str = replace(str, "\"", "\\\"");
        str = replace(str, "，", ",");
        return str;

    }

    public static String delTag(String str) {
        str = str + "<>";
        StringBuffer buff = new StringBuffer();
        int start = 0;
        int end = 0;

        while (str.length() > 0) {
            start = str.indexOf("<");
            end = str.indexOf(">");
            if (start > 0) {
                buff.append(str.substring(0, start));
            }
            if (end > 0 && end <= str.length()) {
                str = str.substring(end + 1, str.length());
            } else {
                str = "";
            }

        }
        String result = buff.toString();

        while (result.startsWith(" ")) {

            result = result.substring(result.indexOf(" ") + 1, result.length());

        }
        return result;

    }

    public static final String replace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;

    }

    //	Replace
    public static String Replace(String source, String oldString, String newString) {
        if (source == null) {
            return null;
        }
        StringBuffer output = new StringBuffer();
        int lengOfsource = source.length();
        int lengOfold = oldString.length();
        int posStart = 0;
        int pos;
        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));
            output.append(newString);
            posStart = pos + lengOfold;
        }
        if (posStart < lengOfsource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }

    //此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtml(String s) {
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        s = Replace(s, "\n", "<br>");
        //s = Replace(s, " ", "&nbsp;");
        s = Replace(s, "'", "&#39;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "%", "％");
        //	s = Replace(s, "&", "&amp;");
        return s;
    }

    //	逆
    public static String unHtml(String s) {

        //s = Replace(s, "&lt;", "<");
        //s = Replace(s, "&gt;", ">");
        //		s = Replace(s, "    ", "\t");
        //		s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        //		s = Replace(s, "&nbsp;", " ");
        //		s = Replace(s, "&amp;", "&");
        //		s = Replace(s, "&#39;", "'");
        //		s = Replace(s, "&#92;", "\\");
        //		s = Replace(s, "％", "%");
        return s;
    }

    //	此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtmlBack(String s) {
        //显示
        s = Replace(s, "&", "&amp;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        //		s = Replace(s, "\n", "<br>");
//		s = Replace(s, " ", "&nbsp;");
        //		s = Replace(s, "'", "&#39;");
        //		s = Replace(s, "%", "%");

        return s;
    }

    //	逆
    public static String unHtmlBack(String s) {
        s = Replace(s, "&lt;", "<");
        s = Replace(s, "&gt;", ">");
        s = Replace(s, "    ", "\t");
        s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        s = Replace(s, "&nbsp;", " ");
        s = Replace(s, "&amp;", "&");
        s = Replace(s, "&#39;", "'");
        s = Replace(s, "&#92;", "\\");
        s = Replace(s, "％", "%");
        return s;
    }

    //判断是否含有中文，如果含有中文返回ture
    public static boolean containsChinese(String str) throws UnsupportedEncodingException {

        if (str.length() < (str.getBytes()).length)
            return true;

        return false;

        //	  for (int i = 0; i < username.length(); i++) {
        //		String unit=Character.toString(username.charAt(i));
        //		byte[] unitByte=unit.getBytes("GBK");
        ////  		((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
        //	   if (unitByte.length == 2)
        //		{
        //		  return true;
        //		}
        //	  }
        //	  return false;

    }

    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        return "".equals(str.trim());
    }

    public static boolean isEmptyList(Object list) {
        if (isNull(list)) {
            return true;
        } else {
            if (list instanceof List) {
                if (((List) list).isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }


    /**
     * <br>
     * <b>功能：</b>替换回车<br>
     * <b>日期：</b> Oct 26, 2011 <br>
     *
     * @param str
     * @return
     */
    public static String replaceHuiche(String str) {
        String after = str.replaceAll("\r\n", "");
        return after;
    }


    /**
     * Escapes a <code>String</code> according the JavaScript string literal
     * escaping rules. The resulting string will not be quoted.
     * <p>
     * <p>It escapes both <tt>'</tt> and <tt>"</tt>.
     * In additional it escapes <tt>></tt> as <tt>\></tt> (to avoid
     * <tt>&lt;/script></tt>). Furthermore, all characters under UCS code point
     * 0x20, that has no dedicated escape sequence in JavaScript language, will
     * be replaced with hexadecimal escape (<tt>\x<i>XX</i></tt>).
     */
    public static String javaScriptStringEnc(String s) {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
                StringBuffer b = new StringBuffer(ln + 4);
                b.append(s.substring(0, i));
                while (true) {
                    if (c == '"') {
                        b.append("\\\"");
                    } else if (c == '\'') {
                        b.append("\\'");
                    } else if (c == '\\') {
                        b.append("\\\\");
                    } else if (c == '>') {
                        b.append("\\>");
                    } else if (c < 0x20) {
                        if (c == '\n') {
                            b.append("\\n");
                        } else if (c == '\r') {
                            b.append("\\r");
                        } else if (c == '\f') {
                            b.append("\\f");
                        } else if (c == '\b') {
                            b.append("\\b");
                        } else if (c == '\t') {
                            b.append("\\t");
                        } else {
                            b.append("\\x");
                            int x = c / 0x10;
                            b.append((char)
                                    (x < 0xA ? x + '0' : x - 0xA + 'A'));
                            x = c & 0xF;
                            b.append((char)
                                    (x < 0xA ? x + '0' : x - 0xA + 'A'));
                        }
                    } else {
                        b.append(c);
                    }
                    i++;
                    if (i >= ln) {
                        return b.toString();
                    }
                    c = s.charAt(i);
                }
            } // if has to be escaped
        } // for each characters
        return s;
    }


    private static StringUtil instance = null;

    public static synchronized StringUtil getInstance() {
        if (instance == null) {
            instance = new StringUtil();
        }
        return instance;
    }

    public static String toFloatNumber(String num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(Double.parseDouble(num));
    }


    public static String toFloatNumber(Double num, int accuracy) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(accuracy);
        nf.setMinimumFractionDigits(accuracy);
        return nf.format(num);
    }


    public static String wcsUnescape(String str) {
        str = str.replace("#lt;", "<");
        str = str.replace("#gt;", ">");
        str = str.replace("#quot;", "\"");
        str = str.replace("#amp;amp;", "&");
        str = str.replace("#amp;", "&");
        str = str.replace("#039;", "'");
        return str;
    }

    /**
     * <br>
     * <b>功能：</b>返回string型的字节数<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str
     * @return
     */
    public static int getByteLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes().length;
    }

    /**
     * <br>
     * <b>功能：</b>详细的功能描述<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str      字符
     * @param limitLen 长度
     * @return
     */
    public static String getByteStr(String str, int limitLen) {
        StringBuffer sb = new StringBuffer();
        char[] chars = getNotNullStr(str).toCharArray();
        int len = 0;
        for (char c : chars) {
            len += getByteLength(String.valueOf(c));
            if (len <= limitLen) {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static String InputStreamTOString(InputStream in, String encoding) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count = -1;
        while ((count = in.read(data, 0, 1024)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }

    /**
     * 判断字符是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(\\-?)\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 获取字符串字符第n次出现的位置
     *
     * @param string
     * @return
     */
    public static int getCharacterPosition(String string, String characterString, int index) {
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(characterString).matcher(string);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if (mIdx == index) {
                break;
            }
        }
        return slashMatcher.start();
    }

    /**
     * 字符串转换为16进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHex(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}
