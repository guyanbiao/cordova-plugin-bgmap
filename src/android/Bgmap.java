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
            try {
              Intent intent = Intent.getIntent(url);
              if(isInstallByread("com.autonavi.minimap")){
                cordova.getActivity().startActivity(intent); //启动调用
              }else{
                callbackContext.error("未安装高德地图");
              }
            } catch (URISyntaxException e) {
              callbackContext.error("url 格式不正确");
              e.printStackTrace();
            }
            return true;
          } else {
            try {
              Intent intent = Intent.getIntent(url);
              if(isInstallByread("com.baidu.BaiduMap")){
                cordova.getActivity().startActivity(intent); //启动调用
              }else{
                callbackContext.error("未安装百度地图");
              }
            } catch (URISyntaxException e) {
              callbackContext.error("url 格式不正确");
              e.printStackTrace();
            }
            return true;
          }

        } else {
          return false;
        }
    }

    private boolean isInstallByread(String packageName) {   
      return new File("/data/data/" + packageName).exists();   
    }

}
