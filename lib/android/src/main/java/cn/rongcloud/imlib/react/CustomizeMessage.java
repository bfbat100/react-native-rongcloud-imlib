package cn.rongcloud.imlib.react;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * 自定义消息
 * 注解名：MessageTag ；属性：value ，flag； value 即 ObjectName 是消息的唯一标识不可以重复，且三端必须一致。
 * 开发者命名时不能以 RC 开头，避免和融云内置消息冲突；flag 是用来定义消息的可操作状态。
 * 如下面代码段，自定义消息名称 CustomizeMessage ，vaule 是 system:noPush ，
 * flag 是 MessageTag.ISCOUNTED | MessageTag.ISPERSISTED 表示消息计数且存库。
 */

@MessageTag(value = "system:noPush", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeMessage extends MessageContent {

    private String content;//消息属性，可随意定义
    private String extra;//消息属性，可随意定义

    /**
     * 实现 encode() 方法，该方法的功能是将消息属性封装成 json 串，
     * 再将 json 串转成 byte 数组，该方法会在发消息时调用，如下面示例代码：
     */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("content", content);
            jsonObj.put("extra", extra);
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContent(){
            Log.e("CustomizeMessage", "CustomizeMessage getContent");
        return content;
    }

    public String getExtra(){
        return extra;
    }

    /**
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，
     * 先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     */
    public CustomizeMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }
            Log.e("CustomizeMessage", "CustomizeMessage 1");
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("content")){
                this.content = jsonObj.optString("content");
                this.extra = jsonObj.optString("extra");
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
            // RLog.e(this, "JSONException", e.getMessage());
        }
    }

    /**
     * 给消息赋值。
     */
    public CustomizeMessage(Parcel in) {
            Log.e("CustomizeMessage", "CustomizeMessage 2");
        this.content = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        this.extra = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性TODO
        //这里可继续增加你消息的属性
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<CustomizeMessage> CREATOR = new Creator<CustomizeMessage>() {

        @Override
        public CustomizeMessage createFromParcel(Parcel source) {
            return new CustomizeMessage(source);
        }

        @Override
        public CustomizeMessage[] newArray(int size) {
            return new CustomizeMessage[size];
        }
    };

    /**
     * 描述了包含在 Parcelable 对象排列信息中的特殊对象的类型。
     *
     * @return 一个标志位，表明Parcelable对象特殊对象类型集合的排列。
     */
    public int describeContents() {
        return 0;
    }

    /**
     * 将类的数据写入外部提供的 Parcel 中。
     *
     * @param dest  对象被写入的 Parcel。
     * @param flags 对象如何被写入的附加标志。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, content);//该类为工具类，对消息中属性进行序列化
        //这里可继续增加你消息的属性
        ParcelUtils.writeToParcel(dest,extra);
    }




}