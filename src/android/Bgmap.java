package com.sinfotech.bgmap;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import java.io.File;
import android.util.Log;
import java.net.URISyntaxException;


public class Bgmap extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (action.equals("openMapDirection")) {
          String url = data.getString(0);
          if (url.startsWith("androidamap")) {
            return callMap(url, "com.autonavi.minimap", "未安装高德地图", callbackContext);
          } else {
            return callMap(url, "com.baidu.BaiduMap", "未安装百度地图", callbackContext);
          }
        } else {
          return false;
        }
    }

    private boolean callMap(String url, String packageName, String errorMessage, CallbackContext callbackContext) {
            try {
              Intent intent = Intent.getIntent(url);
              if(isInstallByread(packageName)){
                cordova.getActivity().startActivity(intent); //启动调用
              }else{
                callbackContext.error(errorMessage);
              }
            } catch (URISyntaxException e) {
              callbackContext.error("url 格式不正确");
              e.printStackTrace();
              return false;
            }
            return true;
    }

    private boolean isInstallByread(String packageName) {   
      return new File("/data/data/" + packageName).exists();   
    }

}
