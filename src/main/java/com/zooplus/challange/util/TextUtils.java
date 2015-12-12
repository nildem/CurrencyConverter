package com.zooplus.challange.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * TextUtils
 * 
 * @author Anil TANGUL. (anil.tangul@est.com.tr)
 * @author Sinan OZISIK.
 */
public class TextUtils {
    private static final String ALPHA = "abcçdefgğhıijklmnoöpqrstuüvwxyzABCÇDEFGHIİJKLMNOÖPQRSTUÜVWXYZ0123456789 ";
    private static final String SYMBOLS = ".:,;!'^#$+%&{}[]()=?*-_/|~<>@";
    private static final String DONOTENCODE = ALPHA + SYMBOLS;
    public static final String EMAIL_PATTERN = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
    public static final String WEB_ADDR_PREFIX_HTTP = "http://";
    public static final String WEB_ADDR_PREFIX_HTTPS = "https://";
    public static final String WILDCARD = "*";

    public static boolean isEmptyStr(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isNotEmptyStr(String s) {
        return !isEmptyStr(s);
    }

    public static boolean isEmptyTrimmedStr(String s) {
        return isEmptyStr(s) || "".equals(s.trim());
    }

    public static boolean isNotEmptyTrimmedStr(String s) {
        return !isEmptyTrimmedStr(s);
    }

    public static String replaceMaliciousCharacters(String s, char ch) {
        StringBuffer buf = new StringBuffer();
        if (s == null)
            return null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (DONOTENCODE.indexOf(c) != -1) {
                buf.append(c);
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    public static String getMaxLengthString(String string, int len) {
        if (string != null && string.length() > len) {
            return string.substring(0, len);
        }
        return string;
    }

    public static String minPad(boolean left, String string, int i, String pad) {
        if (string != null && string.length() >= i) {
            return string;
        }
        return pad(left, string, i, pad);
    }

    public static String pad(boolean left, String text, int len, String chr) {
        String ilk = "";
        String son = "";
        if (text != null) {
            ilk = text;
        }
        for (int i = ilk.length(); i < len; i++) {
            son += chr;
        }
        if (left) {
            son = son + ilk;
        } else {
            son = ilk + son;
        }
        // check if already greater than padlen
        if (son.length() > len)
            son = son.substring(0, len);
        return son;
    }

    /**
     * pad
     * 
     * @param intext
     * @param padLength
     * @param padChar
     * @param isRightPad
     * @return
     */
    public static String pad(String intext, int padLength, char padChar, boolean isRightPad) {
        String padded = "";
        for (int i = 0; i < padLength; i++) {
            padded += padChar;
        }
        if (intext != null) {
            if (intext.length() < padLength) {
                if (isRightPad) {
                    padded = padded.substring(0, padLength - intext.length()) + intext;
                } else {
                    padded = intext + padded.substring(0, padLength - intext.length());
                }
            } else if (intext.length() == padLength) {
                padded = intext;
            } else {
                padded = intext.substring(0, padLength - 1) + "~";
            }
        }
        return padded;
    }

    public static String mapTurkishChars(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("Ş", "S").replaceAll("ş", "s").replaceAll("İ", "I").replaceAll("ı", "i")
                .replaceAll("Ğ", "G").replaceAll("ğ", "g").replace("Ü", "U").replace("ü", "u").replace("Ö", "O")
                .replace("ö", "o").replace("Ç", "C").replace("ç", "c");
    }

    public static String mapPolishChars(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("Ą", "A").replaceAll("ą", "a").replaceAll("Ę", "E").replaceAll("ę", "e")
                .replaceAll("Ó", "O").replaceAll("ó", "o").replaceAll("Ć", "C").replaceAll("ć", "c")
                .replaceAll("Ł", "L").replaceAll("ł", "l").replaceAll("Ń", "N").replaceAll("ń", "n")
                .replaceAll("Ś", "S").replaceAll("ś", "s").replaceAll("Ź", "Z").replaceAll("ź", "z")
                .replaceAll("Ż", "Z").replaceAll("ż", "z");
    }

    public static String mapDialecticChars(String str) {
        if (str == null) {
            return null;
        }
        return mapTurkishChars(mapPolishChars(str));
    }

    public static String int2str(int value, int digs) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumIntegerDigits(digs);
        nf.setMinimumIntegerDigits(digs);
        nf.setGroupingUsed(false);
        return nf.format(value);
    }

    public static String long2str(long value, int digs) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumIntegerDigits(digs);
        nf.setMinimumIntegerDigits(digs);
        nf.setGroupingUsed(false);
        return nf.format(value);
    }

    public static String bigdecimal2str(BigDecimal value, int digs, int multiplier) {
        if (value == null) {
            return null;
        }
        return TextUtils.long2str(value.multiply(new BigDecimal(multiplier)).longValue(), digs);
    }

    public static StringBuffer replaceText(String oldtext, String newtext, StringBuffer str) {
        if (newtext == null)
            newtext = "";
        if (oldtext == null)
            return str;
        if (str == null)
            return str;
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = str.indexOf(oldtext, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(newtext);
            s = e + oldtext.length();
        }
        result.append(str.substring(s));
        return result;
    }

    public static String replaceText(String oldtext, String newtext, String str) {
        if (newtext == null)
            newtext = "";
        if (oldtext == null)
            return str;
        if (str == null)
            return str;
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = str.indexOf(oldtext, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(newtext);
            s = e + oldtext.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    /**
     * Returns true if the given text has valid length.
     * 
     * @param s
     * @param greaterThanOrEqualTo
     * @param lessThanOrEqualTo
     * @param allowNull
     * @return
     */
    public static boolean hasValidLength(String s, int greaterThanOrEqualTo, int lessThanOrEqualTo, boolean allowNull) {
        if (allowNull && s == null)
            return true;
        if (allowNull && s.length() == 0)
            return true;
        if (s == null)
            return false;
        return (s.length() >= greaterThanOrEqualTo && s.length() <= lessThanOrEqualTo);
    }

    /**
     * Trims the given text.
     * 
     * @param s
     * @param maxSize
     * @return
     */
    public static String trim(String s, int maxSize) {
        if (s != null && s.length() > maxSize)
            s = s.substring(0, maxSize);
        return s;
    }

    /**
     * returns "" if null, else given text.
     * 
     * @param text
     * @return
     */
    public static String getNotNull(String text) {
        if (text == null || text.equalsIgnoreCase("null"))
            return "";
        return text;
    }

    /**
     * Returns not null string.
     * 
     * @param obj
     * @return
     */
    public static String getNotNull(Object obj) {
        if (obj == null)
            return "";
        return obj.toString();
    }

    /**
     * Equals to each other with sense of address line, name etc.
     * 
     * @param dimUid
     * @param dimUid2
     * @return
     */
    public static boolean isEqualAsText(String s0, String s1) {
        s0 = TextUtils.getNotNull(s0).trim();
        s1 = TextUtils.getNotNull(s1).trim();
        return s0.equalsIgnoreCase(s1);
    }

    public static int getCeilingLimitedValue(int count, int maxValue) {
        return count > maxValue ? maxValue : count;
    }

    /**
     * Replace null char/byte with the replacement.
     * 
     * @param text
     * @param replacement
     * @return
     */
    public static String replaceNull(String text, char replacement) {
        if (text != null) {
            return text.replace('\u0000', replacement);
        }
        return "";
    }

    /**
     * Generic formatter.
     * 
     * @param number
     * @param pattern
     * @return
     */
    public static String format(BigDecimal number, String pattern) {
        try {
            return new DecimalFormat(pattern).format(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number.toString();
    }

    public static String formatIP(String ip, boolean useDots) {
        String[] ipParts = null;
        if (ip == null) {
            ipParts = new String[] { "", "", "", "" };
        } else {
            ipParts = ip.split("\\.");
            if (ipParts.length != 4) {
                ipParts = new String[] { "", "", "", "" };
            }
        }
        StringBuffer formattedIP = new StringBuffer("");
        for (int i = 0; i < ipParts.length; i++) {
            formattedIP.append(TextUtils.pad(true, ipParts[i], 3, "0"));
            if (useDots && i < ipParts.length - 1) {
                formattedIP.append(".");
            }
        }
        return formattedIP.toString();
    }

    /**
     * Generic formatter.
     * 
     * @param number
     * @param pattern
     * @return
     */
    public static String format(Integer number, String pattern) {
        if (number != null) {
            try {
                return new DecimalFormat(pattern).format(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return number.toString();
        }
        return null;
    }

    public static byte[] mapToEnglish(byte[] bytarr) {
        for (int i = 0; i < bytarr.length; i++) {
            switch (bytarr[i]) {
            case (byte) 200: //CONVERT ï¿½ TO I
                bytarr[i] = (byte) 73;
                break;
            case (byte) 221: //CONVERT ï¿½ TO I
                bytarr[i] = (byte) 73;
                break;
            case (byte) 222: //CONVERT ï¿½ TO S
                bytarr[i] = (byte) 83;
                break;
            case (byte) 208: //CONVERT ï¿½ TO G
                bytarr[i] = (byte) 71;
                break;
            case (byte) 220: //CONVERT ï¿½ TO U
                bytarr[i] = (byte) 85;
                break;
            case (byte) 214: //CONVERT ï¿½ TO O
                bytarr[i] = (byte) 79;
                break;
            case (byte) 199: //CONVERT ï¿½ TO C
                bytarr[i] = (byte) 67;
                break;
            }
        }
        return bytarr;
    }

    public static String toOneSpaceFormat(String str) {
        boolean first = true;
        byte[] frombytarr = str.trim().getBytes();
        int length = frombytarr.length;
        int finallength = 0;
        byte[] tobytarr = new byte[length];
        for (int i = 0; i < length; i++) {
            if ((byte) ' ' == frombytarr[i]) {
                if (first) {
                    tobytarr[finallength] = frombytarr[i];
                    finallength++;
                    first = false;
                }
            } else {
                tobytarr[finallength] = frombytarr[i];
                finallength++;
                first = true;
            }
        }
        return new String(tobytarr, 0, finallength);
    }

    public static String getFirstElementOfArray(String text, String delimeter) {
        if (text != null && text.contains(delimeter)) {
            String[] temp1 = text.split(delimeter);
            if (temp1 != null && temp1.length > 0 && temp1[0] != null) {
                return temp1[0].trim();
            }
        }
        return text;
    }

    public static String fixLengthWithZeros(int length, String s) {
        if (s.length() == length) {
            return s;
        } else if (s.length() < length) {
            String out = "";
            for (int i = 0; i < length - s.length(); i++) {
                out += "0";
            }
            out += s;
            return out;
        } else {
            return (s.substring(0, length));
        }
    }

    public static String ignoreZerosFromLeft(String s) {
        for (int ii = 0; ii < s.length(); ii++) {
            if (s.charAt(ii) != '0') {
                return s.substring(ii);
            }
        }
        return s;
    }

    public static String formatXml(String input, int indent) {
        try {
            if (input.contains("DOCTYPE HTML PUBLIC")) {
                return input;
            }
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static String formatXml(String input) {
        return formatXml(input, 2);
    }

    public static String trimNotNull(String string) {
        if (string == null) {
            return null;
        }
        return string.trim();
    }

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmptyStr(email) && email.matches(EMAIL_PATTERN));
    }

    public static String getNumbers(String st1) {
        if (st1 == null) {
            return "";
        }
        StringBuffer buf1 = new StringBuffer();
        for (int i = 0; i < st1.length(); i++) {
            char ch = st1.charAt(i);
            if ((ch >= '0') && (ch <= '9'))
                buf1.append(ch);
        }
        return buf1.toString();
    }

    /**
     * Creates a delimeted string out of given collection
     * 
     * @param c
     * @param delim
     * @return
     */
    public static <T> String collectionToDelimitedString(Collection<T> c, String delim) {
        if (c == null)
            return "";
        StringBuffer buff = new StringBuffer();
        Iterator<T> iterator = c.iterator();
        while (iterator.hasNext()) {
            T obj = iterator.next();
            if (obj == null)
                buff.append("null");
            else
                buff.append(obj.toString());
            if (iterator.hasNext())
                buff.append(delim);
        }
        return buff.toString();
    }

    public static int nthIndex(String string, String target, int n) {
        if (string == null || target == null) {
            throw new NullPointerException();
        }
        int index = string.indexOf(target);
        if (index == -1)
            return -1;
        for (int i = 1; i < n; i++) {
            index = string.indexOf(target, index + 1);
            if (index == -1)
                return -1;
        }
        return index;
    }

    public static byte[] pad(byte[] inputArr) {
        int i = 0;
        int j = 0;
        for (i = 0;; i++)
            if (16 * i >= inputArr.length)
                break;
        byte[] byteArr = new byte[16 * i];
        for (j = 0; j < 16 * i; j++)
            if (j >= inputArr.length)
                byteArr[j] = 32; //pad with space
            else
                byteArr[j] = inputArr[j];
        return byteArr;
    }

    public static String applyEscapeCharOnParamaters(String paramater, String escapeCharacter,
            String characterWillBeEscaped) {
        if (TextUtils.isEmptyTrimmedStr(paramater)) {
            return "";
        }
        String escapedStr = paramater.replace(escapeCharacter, escapeCharacter + escapeCharacter);
        return escapedStr.replace(characterWillBeEscaped, escapeCharacter + characterWillBeEscaped);
    }

    public static boolean isValidRegex(String s, String regex) {
        Pattern r = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = r.matcher(s);
        return matcher.find();
    }

    public static String fixWebAddress(String address) {
        if (TextUtils.isEmptyTrimmedStr(address)) {
            return "";
        }
        String validWebAddress = address.trim();
        if (address.toLowerCase().startsWith(WEB_ADDR_PREFIX_HTTP)
                || address.toLowerCase().startsWith(WEB_ADDR_PREFIX_HTTPS)) {
            return validWebAddress;
        }
        return WEB_ADDR_PREFIX_HTTP + validWebAddress;
    }

    public static boolean listContainsString(String[] list, String param) {
        if (list == null)
            return false;
        for (String s : list) {
            if (param.equals(s))
                return true;
        }
        return false;
    }

    /**
     * 
     * @param String
     *            in the format name1=value1&name2=value2&name3=value3... return Map containg name-value pairs
     * 
     *            Parses a string into a map Keys are converted to Uppercase
     */
    public static Map<String, String> parseMapFromString(String input) {
        final Map<String, String> map = new HashMap<String, String>();
        if (!isEmptyStr(input)) {
            String[] pairs = input.split("&");
            for (String pair : pairs) {
                if (!isEmptyStr(pair)) {
                    String[] kv = pair.split("=");
                    if (kv.length > 0) {
                        String val = (kv.length > 1) ? kv[1] : "";
                        map.put(kv[0].toUpperCase(), val);
                    }
                }
            }
        }
        return map;
    }
}
