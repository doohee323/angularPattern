package com.tz.common.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : StringUtil
 * 설    명 : String Handling 을 위한  Utility Class
 * 작 성 자 : TZ
 * 작성일자 : 2013.04.10
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
public class StringUtil {

    // 한글이 3 byte 일 경우
    // public static int UNICODE_BYTE = 3;

    /**
     * <pre>
     * String data가 null 일 경우 null String으로 리턴
     * </prE>
     *
     * @param data
     */
    public static String getText(String data){
        if(data == null)
            data = "";
        return data;
    }

    /**
     * <pre>
     * String data가 null 일 경우 null String으로 리턴
     * </prE>
     *
     * @param data
     */
    public static String getText(String data, String sDefault){
        if(data == null)
            data = sDefault;
        return data;
    }

    /**
     * <pre>
     * data가 null 일 경우 null String으로 리턴
     * </pre>
     *
     * @param data
     */
    public static String getText(Object data){
        if(data == null)
            data = "";
        if(data instanceof String){
            return data.toString();
        }else if(data instanceof java.lang.String[]){
            return StringUtil.arrayToString((String[])data, ",");
        }else{
            return data.toString();
        }
    }

    /**
     * <pre>
     * data가 null 일 경우 null String으로 리턴
     * </pre>
     *
     * @param data
     */
    public static String getText(Object data, String sDefault){
        if(data == null)
            data = sDefault;
        if(data instanceof String){
            return data.toString();
        }else if(data instanceof java.lang.String[]){
            return StringUtil.arrayToString((String[])data, ",");
        }else{
            return data.toString();
        }
    }

    /**
     * <pre>
     * 대상문자열(strTarget)에서 특정문자열(strSearch)을 찾아 지정문자열(strReplace)로
     * 변경한 문자열을 반환한다.
     * </pre>
     *
     * @param strTarget 대상문자열
     * @param strSearch 변경대상의 특정문자열
     * @param strReplace 변경 시키는 지정문자열
     * @exception Exception
     * @return 변경완료된 문자열
     */
    public static String replace(String strTarget, String strSearch, String strReplace) throws Exception{
        String result = null;
        try{

            String strCheck = new String(strTarget);
            StringBuffer strBuf = new StringBuffer();
            while(strCheck.length() != 0){
                int begin = strCheck.indexOf(strSearch);
                if(begin == -1){
                    strBuf.append(strCheck);
                    break;
                }else{
                    int end = begin + strSearch.length();
                    strBuf.append(strCheck.substring(0, begin));
                    strBuf.append(strReplace);
                    strCheck = strCheck.substring(end);
                }
            }

            result = strBuf.toString();
        }catch(Exception e){
            throw new Exception("[StringUtil][replace]" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * <pre>
     * 대상문자열(strTarget)에서 구분문자열(strDelim)을 기준으로 문자열을 분리하여
     * 각 분리된 문자열을 배열에 할당하여 반환한다.
     * </pre>
     *
     * @param strTarget 분리 대상 문자열
     * @param strDelim 구분시킬 문자열로서 결과 문자열에는 포함되지 않는다.
     * @param bContainNull 구분되어진 문자열중 공백문자열의 포함여부. true : 포함, false : 포함하지 않음.
     * @return 분리된 문자열을 순서대로 배열에 격납하여 반환한다.
     * @exception Exception
     */
    public static String[] split(String strTarget, String strDelim, boolean bContainNull) throws Exception{

        // StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
        // 따라서 아래와 같이 작성함.
        int index = 0;
        String[] resultStrArray = null;

        try{

            resultStrArray = new String[search(strTarget, strDelim) + 1];
            String strCheck = new String(strTarget);
            while(strCheck.length() != 0){
                int begin = strCheck.indexOf(strDelim);
                if(begin == -1){
                    resultStrArray[index] = strCheck;
                    break;
                }else{
                    int end = begin + strDelim.length();
                    if(bContainNull){
                        resultStrArray[index++] = strCheck.substring(0, begin);
                    }
                    strCheck = strCheck.substring(end);
                    if(strCheck.length() == 0 && bContainNull){
                        resultStrArray[index] = strCheck;
                        break;
                    }
                }
            }

        }catch(Exception e){
            throw new Exception("[StringUtil][split]" + e.getMessage(), e);
        }
        return resultStrArray;
    }

    /**
     * <pre>
     * 대상문자열(strTarget)에서 지정문자열(strSearch)이 검색된 횟수를,
     * 지정문자열이 없으면 0 을 반환한다.
     * </pre>
     *
     * @param strTarget 대상문자열
     * @param strSearch 검색할 문자열
     * @return 지정문자열이 검색되었으면 검색된 횟수를, 검색되지 않았으면 0 을 반환한다.
     * @exception Exception
     */
    public static int search(String strTarget, String strSearch) throws Exception{
        int result = 0;
        try{

            String strCheck = new String(strTarget);
            for(int i = 0; i < strTarget.length();){
                int loc = strCheck.indexOf(strSearch);
                if(loc == -1){
                    break;
                }else{
                    result++;
                    i = loc + strSearch.length();
                    strCheck = strCheck.substring(i);
                }
            }

        }catch(Exception e){
            throw new Exception("[StringUtil][search]" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * <pre>
     * escapeDollarMarker
     * </pre>
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String escapeDollarMarker(String str) throws Exception{
        return StringUtil.replace(str, "$", "\\$");
    }

    /**
     * <pre>
     * split(String str, char separatorChar)
     * </pre>
     *
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] split(String str, char separatorChar){
        return splitWorker(str, separatorChar, false);
    }

    /**
     * <pre>
     * split(String str, String separatorChars)
     * </pre>
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] split(String str, String separatorChars){
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * <pre>
     * splitWorker(String str, char separatorChar, boolean preserveAllTokens)
     * </pre>
     *
     * @param str
     * @param separatorChar
     * @param preserveAllTokens
     * @return
     */
    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens){
        if(str == null)
            return null;
        int len = str.length();
        if(len == 0)
            return new String[0];
        List<String> list = new ArrayList<String>();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while(i < len)
            if(str.charAt(i) == separatorChar){
                if(match || preserveAllTokens){
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
            }else{
                lastMatch = false;
                match = true;
                i++;
            }
        if(match || preserveAllTokens && lastMatch)
            list.add(str.substring(start, i));
        return (String[])list.toArray(new String[list.size()]);
    }

    /**
     * <pre>
     * splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens)
     * </pre>
     *
     * @param str
     * @param separatorChars
     * @param max
     * @param preserveAllTokens
     * @return
     */
    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens){
        if(str == null)
            return null;
        int len = str.length();
        if(len == 0)
            return new String[0];
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if(separatorChars == null)
            while(i < len)
                if(Character.isWhitespace(str.charAt(i))){
                    if(match || preserveAllTokens){
                        lastMatch = true;
                        if(sizePlus1++ == max){
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }else{
                    lastMatch = false;
                    match = true;
                    i++;
                }
        else if(separatorChars.length() == 1){
            char sep = separatorChars.charAt(0);
            while(i < len)
                if(str.charAt(i) == sep){
                    if(match || preserveAllTokens){
                        lastMatch = true;
                        if(sizePlus1++ == max){
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }else{
                    lastMatch = false;
                    match = true;
                    i++;
                }
        }else{
            while(i < len)
                if(separatorChars.indexOf(str.charAt(i)) >= 0){
                    if(match || preserveAllTokens){
                        lastMatch = true;
                        if(sizePlus1++ == max){
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }else{
                    lastMatch = false;
                    match = true;
                    i++;
                }
        }
        if(match || preserveAllTokens && lastMatch)
            list.add(str.substring(start, i));
        return (String[])list.toArray(new String[list.size()]);
    }

    /**
     * <pre>
     * 입력된 스트링에서 특정 문자를 제거한다.
     * </pre>
     *
     * @param s
     * @param delims
     * @return
     */
    public static String strip(String s, String delims){
        if(s == null || s.length() == 0 || delims == null)
            return s;
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(s, delims);
        while(st.hasMoreTokens()){
            sb.append(st.nextToken());
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 입력된 문자열의 첫번째 문자를 대문자로 치환하여 리턴한다.
     * </pre>
     *
     * @param s
     * @return
     */
    public static String upperFirst(String s){
        if(s == null || s.length() < 1)
            return s;
        return (s.substring(0, 1).toUpperCase() + s.substring(1));
    }

    /**
     * <pre>
     * 입력된 문자열의 첫번째 문자를 소문자로 치환하여 리턴한다.
     * </pre>
     *
     * @param s
     * @return
     */
    public static String lowerFirst(String s){
        if(s == null || s.length() < 1)
            return s;
        return (s.substring(0, 1).toLowerCase() + s.substring(1));
    }

    /**
     * <pre>
     * 입력값을 String으로 변환하여 반환한다.
     * 만약 변환중 에러가 발생하거나 null이면 ""을 리턴한다.<br>
     * 단)생성자에서 noDefault = true이면서 에러이면 RuntimeException
     * </pre>
     *
     * @return String
     */
    public static String cString(Object value){
        String out = null;
        if(value == null){
            out = "";
        }else if(value instanceof Double){
            out = new DecimalFormat("#0.0#################").format((Double)value);
        }else{
            out = value.toString();
        }
        return out;
    }

    /**
     * <pre>
     * String array 정보를 gubun 자를 포함한 하나의 문자열로 리턴한다.
     * </pre>
     *
     * @param values
     * @param gubun
     * @return
     */
    public static String arrayToString(String[] values, String gubun){
        StringBuffer sb = new StringBuffer();
        if(values == null || values.length < 1)
            return "";
        sb.append(values[0]);
        for(int i = 1; i < values.length; i++){
            sb.append(gubun).append(values[i]);
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 입력된 문자열을 gubun을 delimeter로 분할하여 String array로 리턴한다.
     * </pre>
     *
     * @param text
     * @param gubun
     * @return
     */
    public static String[] stringToArray(String text, String gubun){
        List<String> array = new ArrayList<String>();
        String cur = text;
        while(cur != null){
            int i = cur.indexOf(gubun);
            if(i < 0){
                array.add(cur);
                cur = null;
            }else{
                array.add(cur.substring(0, i));
                cur = cur.substring(i + gubun.length());
            }
        }
        return (String[])array.toArray(new String[array.size()]);
    }

    /**
     * <pre>
     * List stringToList(String text, String gubun)
     * </pre>
     *
     * @param text
     * @param gubun
     * @return
     */
    public static List<String> stringToList(String text, String gubun){
        List<String> list = new ArrayList<String>();
        String cur = text;
        while(cur != null){
            int i = cur.indexOf(gubun);
            if(i < 0){
                list.add(cur);
                cur = null;
            }else{
                list.add(cur.substring(0, i));
                cur = cur.substring(i + gubun.length());
            }
        }
        return list;
    }

    /**
     * <pre>
     * stringToHtml(String s)
     * </pre>
     *
     * @param s
     * @return
     */
    public static String stringToHtml(String s){
        if(s == null)
            return null;
        StringBuffer buf = new StringBuffer();
        char[] c = s.toCharArray();
        int len = c.length;
        for(int i = 0; i < len; i++){
            if(c[i] == '&')
                buf.append("&amp;");
            else if(c[i] == '<')
                buf.append("&lt;");
            else if(c[i] == '>')
                buf.append("&gt;");
            else if(c[i] == '"')
                buf.append("&quot;");
            else if(c[i] == '\'')
                buf.append("&#039;");
            else
                buf.append(c[i]);
        }
        return buf.toString();
    }

    /**
     * <pre>
     * htmlToString(String s)
     * </pre>
     *
     * @param s
     * @return
     */
    public static String htmlToString(String s){
        if(s == null)
            return null;
        try{
            s = StringUtil.replace(s, "&amp;", "&");
            s = StringUtil.replace(s, "&lt;", "<");
            s = StringUtil.replace(s, "&gt;", ">");
            s = StringUtil.replace(s, "&quot;", "\"");
            s = StringUtil.replace(s, "&#039;", "\'");
        }catch(Exception e){
        }
        return s;
    }

    /**
     * <pre>
     * 지정된 문자열에 '$', '\'가 포함되어 있으면 해당 문자앞에 '\'를 추가하여 리턴한다.
     * </pre>
     *
     * @param s '$', '\'앞에 '\'가 없는 문자열
     * @return \가 추가된 문자열
     */
    public static String quoteReplacement(String s){
        if((s.indexOf('\\') == -1) && (s.indexOf('$') == -1))
            return s;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '\\'){
                sb.append('\\');
                sb.append('\\');
            }else if(c == '$'){
                sb.append('\\');
                sb.append('$');
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 유틸리티 형태로 sql문을 필터링 해주는 메소드이다.<BR>
     * </pre>
     *
     * @param str 입력값
     * @return String 필터링된 sql문
     */
    public static String filterSql(String str){
        str = str.replaceAll("'", "''");
        str = str.replaceAll("\"", "\"\"");
        str = str.replaceAll("\\\\", "\\\\\\\\");
        str = str.replaceAll(";", "");
        str = str.replaceAll("#", "");
        str = str.replaceAll("--", "");
        str = str.replaceAll(" ", "");

        return (str);
    }

    /**
     * <pre>
     * 문자열을 지정된 길이만큼의(공백 설정도 옵션처리 가능) 문자열로 변환하고 왼쪽으로 정렬하여 반환한다.
     * 주의사항) Java는 Double Byte Character Set, DBCS의 경우에도 한 글자의 length는 1이다.
     * 이에 반해 현재 C/S단에서 주로 사용하는 Delphi의 경우는 2이다.
     * 그러나 이 메서드는 화면상에서 요구하는 절대길이에 변환되는 문자열을 일률적으로 맞추기 위해서
     * DBCS 한 글자의 length를 one Byte Character처럼 2로 계산, 변환작업을 수행한다.
     * 따라서 자르려고 하는 size를 지정할 때 이 점을 유의하여 사용하여야 한다.
     * </pre>
     *
     * @param arg 변환할 문자열
     * @param size 문장 전체의 길이
     * @param isFill 변환된 문장이 지정된 길이보다 짧은 경우 여백처리 여부
     * @return 변환된 문자열 또는 오류시에는 Blank String을 반환
     */
    public static String RPadString(String arg, int size, char fillChar){
        return StringUtils.rightPad(arg, size, fillChar);
    }

    /**
     * <pre>
     * 문자열을 지정된 길이만큼의(공백 설정도 옵션처리 가능) 문자열로 변환하고 오른쪽으로 정렬하여 반환한다.
     * 주의사항) Java는 Double Byte Character Set, DBCS의 경우에도 한 글자의 length는 1이다.
     * 이에 반해 현재 C/S단에서 주로 사용하는 Delphi의 경우는 2이다.
     * 그러나 이 메서드는 화면상에서 요구하는 절대길이에 변환되는 문자열을 일률적으로 맞추기 위해서
     * DBCS 한 글자의 length를 one Byte Character처럼 2로 계산, 변환작업을 수행한다.
     * 따라서 자르려고 하는 size를 지정할 때 이 점을 유의하여 사용하여야 한다.
     * </pre>
     *
     * @param arg 변환할 문자열
     * @param size 문장 전체의 길이
     * @param isFill 변환된 문장이 지정된 길이보다 짧은 경우 여백처리 여부
     * @return 변환된 문자열 또는 오류시에는 Blank String을 반환
     */
    public static String LPadString(String arg, int size, char fillChar){
        return StringUtils.leftPad(arg, size, fillChar);
    }

    /**
     * <pre>
     * Left Trim
     * </pre>
     *
     * @param source
     * @return
     */
    public static String LTrim(String source){
        return source.replaceAll("^\\s+", "");
    }

    /**
     * <pre>
     * Right Trim
     * </pre>
     *
     * @param source
     * @return
     */
    public static String RTrim(String source){
        return source.replaceAll("\\s+$", "");
    }

    /**
     * <pre>
     * replace multiple whitespaces between words with single blank
     * </pre>
     *
     * @param source
     * @return
     */
    public static String ITrim(String source){
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /**
     * <pre>
     * remove all superfluous whitespaces in source string
     * </pre>
     *
     * @param source
     * @return
     */
    public static String Trim(String source){
        return ITrim(LTrim(RTrim(source)));
    }

    /**
     * <pre>
     * Left / Right Trim
     * </pre>
     *
     * @param source
     * @return
     */
    public static String LRTrim(String source){
        return LTrim(RTrim(source));
    }

    /**
     * <pre>
     * idex의 문자가 대문자인지 확인
     * </pre>
     *
     * @param col
     * @param idex
     * @return
     * @throws Exception
     */
    public static boolean isUpperCase(String col, int idex) throws Exception{
        char[] str = col.toCharArray();
        if((str[idex] >= 65) && (str[idex] <= 90)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * <pre>
     * convertArray
     * </pre>
     *
     * @param aParam
     * @param sep
     * @return paramList
     */
    public static List convertArray(String aParam, String sep){
        List paramList = new ArrayList();
        if(aParam.startsWith("[")){
            aParam = aParam.substring(1, aParam.length() - 1);
            aParam = aParam.replace("+", ",");
            sep = ",";
        }
        if(aParam.indexOf(sep) > -1){
            String paramArray[] = aParam.split("\\" + sep);
            for(int i = 0; i < paramArray.length; i++){
                paramList.add(paramArray[i].trim());
            }
        }else{
            paramList.add(aParam);
        }
        return paramList;
    }

    public static boolean isEquals(Object o1, Object o2){
        if(o1 == null && o2 == null)
            return true;
        if(o1 == null || o2 == null)
            return false;
        return o1.equals(o2);
    }

    public static String nvls(Object o){
        if(o == null)
            return "";
        return o.toString();
    }
    
}