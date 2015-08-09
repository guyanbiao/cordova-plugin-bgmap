package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import java.io.File;
import android.util.Log;
import java.net.URISyntaxException;


public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

          try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            if(isInstallByread("com.baidu.BaiduMap")){
              cordova.getActivity().startActivity(intent); //启动调用
              Log.e("GasStation", "百度地图客户端已经安装") ;
            }else{
              callbackContext.error("未安装地图");
              Log.e("GasStation", "没有安装百度地图客户端") ;
            }
          } catch (URISyntaxException e) {
            e.printStackTrace();
          }
            return true;

        } else {
          return false;
        }
    }

    private boolean isInstallByread(String packageName) {   
      return new File("/data/data/" + packageName).exists();   
    }

}
