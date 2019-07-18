package publi.xz.com.smartcoupon.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.blankj.utilcode.util.Utils;

import java.io.File;

/**
 * 系统相关工具包
 */
public class SystemUtil {
    /**
     *开源工具库安装app
     * @param file
     * @param isNewTask
     * @return
     */
    public static Intent getInstallAppIntent(final File file, final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            String authority = Utils.getApp().getPackageName() + ".utilcode.provider";
            data = FileProvider.getUriForFile(Utils.getApp(), authority, file);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        Utils.getApp().grantUriPermission(Utils.getApp().getPackageName(), data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(data, type);
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    /**
     * 兼容 安卓8 安装器
     * @param context
     * @param file
     * @return
     */
    public static Intent newInstallAppIntent(Context context,File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT >=24){
            Uri apkUri = FileProvider.getUriForFile(context,"publi.xz.com.smartcoupon",file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri,type);
        }else{
            intent.setDataAndType(Uri.fromFile(file),type);
        }


        return intent;

    }
}
