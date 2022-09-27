package com.goblin.log.config;

import java.util.regex.Pattern;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public   class PatternConfig {

    public static Pattern mobile = Pattern.compile("(?<!\\d)(1\\d{10})(?!\\d)");

    public static Pattern card = Pattern.compile("(?<!\\d)(\\d{6})([19,20]\\d{7})(\\d{3}[0-9Xx])(?!\\d)");

    public static Pattern email = Pattern.compile("\\w[-\\w.+]@([A-Za-z0-9][-A-Za-z0-9]+.)+[A-Za-z]{2,14}");

    public static Pattern gps = Pattern.compile("(?<!\\d)[0-9]+(.[0-9]+),[0-9]+(.[0-9]+)");

    /**
     * 华为提供的统一需要拦截的日志
     * 这里的直接过滤掉
     */
    public static Pattern[] HuaWeiPattern = new Pattern[]{
            Pattern.compile("(?i)([A-Za-z]00[0-9]{6})"),
            Pattern.compile("(?i)(key=|key =|key:|key :|key \"|key\"|key '|key'|key <|key<|key >|key>|key @|key@)"),
            Pattern.compile("(?i)(password|passwd|pass2|Partner123!@#|Padmin12|paasSoft321|paas@123|ossuser|ossdbuser|ossadm|Orderftp@12345|Oracle88|oracle_ocm|Oracle_88|op_cloudvpn|omu800@HW|OCRest|Notebook1@#huawei|Nihao@123|Netrixdb_123|netrix123|netrix|netopen_123|nesshkey|nerestuser|neftpuser|Mt2016_User_Ftp|mt2013@HW|mt2013|Modifyme_123|mmsbillsftp|mcca@USER|matchKey)"),
            Pattern.compile("(?i)(token \\(|token\\(|token=|token =|token:|token :|token \"|token\"|token '|token'|token <|token<|token >|token>|token @|token@)"),
            Pattern.compile("[\\w\\-\\.]+@(?:(?:\\[([0-9]{1,3}?\\.){3})|(?:(?:[a-zA-Z0-9\\-]+\\.)+))(?:[a-zA-Z]{2,4}|[0-9]{1,3})\\]?"),
            Pattern.compile("(?i)(PRIVATE KEY|passw.+huawei.|passw.+admin.|passw.+@1|'Huawei@|'Huawei#|\"Huawei1|\"Huawei_|\"Huawei@|\"Huawei#|\"Admin1|\"Admin@|!QAZxsw2|!QAZ2wsx|!QAZ1qaz|!5Hty*@LfMasbIiT|PWD=\".+\"|SECRET_KEY|secospace.db.passwd|rsaPublicKey=)"),
            Pattern.compile("[A-Za-z\\d]{8}(-[A-Za-z\\d]{4}){3}-[A-Za-z\\d]{12}"),
            Pattern.compile("(?i)(dsdp_Db123|Dsdp_12345|Dsdp@12345|Dsdp@1234|Dis_13579|Dis_12345|Df_sftp1|Df_scr12|Df_app12|Derby123|Dcnsj98%^|dbuser|DBadmin_2013|dbadmin|data2@HW|data1@HW|data0@HW|D4I$awOD7k|csmuser|CSmBP_123|Compute@|community|cnp200@HWei|cnp200@HW123|cnp200@HW|cnp200|cnp100@HW@TT|Cmctest_1|CLOUDVPN_WPD|CLOUDVPN_USER|Cloudedge_12345|CLOUD8|cloud_admin|ChangMe_123|Changeme123)"),
            Pattern.compile("(?i)(sk=|sk =|sk:|sk :|sk \"|sk\"|sk '|sk'|sk <|sk<|sk >|sk>|sk @|sk@)"),
            Pattern.compile("(?i)(Changeme_789|Changeme_456|Changeme_1234567|Changeme_1234|Changeme_123|Changeme_029|Changeme_|Changeme@123|Changeme|Change_|CGAUser|certf_passwd|ccuser|cce2OMUrest600@HW|cce2OMUrest600|Cassandra983_|caiyuwu_12345|bmu_hss|BMEIMPL@20370101|bmeB6000|BITShuaweinms|Bfm123!@#|BES@PRM|BBbb11!!|autotest123|authpass|ats9900?@HW|Atae1234|asd_())"),
            Pattern.compile("(?i)(appsecret|app_secret})"),
            Pattern.compile("(?i)(appkey|app_key})"),
            Pattern.compile("(?i)(rootOs_123|Root1234|Root@12345|Root@123|remotePWD|REDIS@B2B_HWsdp-2015|readdbuser|Read1234|read@123|Qwer_123|Public123|Public@1234|public@123|Public@1|Psftp123|Pscript1|proKey|ProDefKey|privpass|Private123|private@123|Private@1|Printer_12|Portal123!@#|Portal123|Pinter12|Pinter_12)"),
    };

    public static Pattern commonKVPattern = Pattern.compile("(?i)(password|passwd|pwd|secret|token|Authorization|session|clientSecret)\"{0,1}\\s*[:|=]\\s*\"{0,1}[a-zA-Z0-9\\@\\_\\-]+\"{0,1}");

    public static Pattern commonKPattern = Pattern.compile("(?i)(password|passwd|pwd|secret|token|Authorization|session)");

    public static Pattern[] skipPattern = new Pattern[]{
            Pattern.compile("(?i)(jdbcUrl)")
    };
}
