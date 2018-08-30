package com.frank.myclock.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.net.URISyntaxException;

public class Pay {
    /**
     * 支付宝转账
     * @param activity 上下文
     */
    public static void aLi(Activity activity,String accountNumber){
        String url1="intent://platformapi/startapp?saId=10000007&" +
                "clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F"+accountNumber+"%3F_s" +
                "%3Dweb-other&_t=1472443966571#Intent;" +
                "scheme=alipayqr;package=com.eg.android.AlipayGphone;end";
        Intent intent = null;
        Toast.makeText(activity,"感谢您的捐赠！٩(๑❛ᴗ❛๑)۶",Toast.LENGTH_SHORT).show();
        if(hasInstalledAlipayClient(activity)){
            try {
                intent = Intent.parseUri(url1 ,Intent.URI_INTENT_SCHEME );
                activity.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                Toast.makeText(activity,"出错啦！",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity,"您未安装支付宝哦！(>ω･* )ﾉ",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断支付宝客户端是否已安装，建议调用转账前检查
     * @param context 上下文
     * @return 支付宝客户端是否已安装
     */
    private static boolean hasInstalledAlipayClient(Context context) {
        String ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
