package cn.rongcloud.imlib.react;

import android.content.Context;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class RCPushReceiver extends PushMessageReceiver {
    static RCTDeviceEventEmitter eventEmitter;

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage message) {
        if (eventEmitter != null) {
            eventEmitter.emit("rcimlib-push-arrived", Convert.toJSON(message, pushType));
        }
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage message) {
        if (eventEmitter != null) {
            eventEmitter.emit("rcimlib-push-clicked", Convert.toJSON(message, pushType));
        }
        return false;
    }

    /**
     * 第三方push状态回调
     *
     * @param pushType   push类型
     * @param action     当前的操作，连接或者获取token
     * @param resultCode 返回的错误码
     */
    @Override
    public void onThirdPartyPushState(PushType pushType, String action, long resultCode) {
        super.onThirdPartyPushState(pushType, action, resultCode);
    }
}
