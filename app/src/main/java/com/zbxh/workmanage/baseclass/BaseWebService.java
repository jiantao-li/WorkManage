package com.zbxh.workmanage.baseclass;

import android.os.Handler;

import com.zbxh.workmanage.basetools.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;

/**
 * Created by ljt on 2017-11-21 17:05
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company: 北京中北信号软件有限公司
 * <p>
 * Description:webservice 基本类
 */

public class BaseWebService {

    private final String nameSpace = "http://zbxhupdata.com/webservices/";
    private static String url = "http://60.6.254.186:190/updataService.asmx";
    private final int timeout = 5 * 1000; // 超时
    private Handler mHandler = null;

    public static class WebService {
        public static final int SocketTimeout = 0x0001;
        public static final int SocketException = 0x0002;
        public static final int IOException = 0x0003;
        public static final int XmlPullParserException = 0x0004;
        public static final int Exception = 0x0005;
    }

    public String getResult(String MethodName) {

        SoapObject request = new SoapObject(nameSpace, MethodName);
        return GetWebServiceResult(url, nameSpace, MethodName, request, timeout);
    }

    public String getResult(String MethodName, Map<String, String> params) {
        SoapObject request = new SoapObject(nameSpace, MethodName);

        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                request.addProperty(entry.getKey(), entry.getValue());
            }
        }
        return GetWebServiceResult(url, nameSpace, MethodName, request, timeout);
    }

    public String getResult(String MethodName, Map<String, String> params, int timeout) {

        SoapObject request = new SoapObject(nameSpace, MethodName);

        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                request.addProperty(entry.getKey(), entry.getValue());
            }
        }

        return GetWebServiceResult(url, nameSpace, MethodName, request, timeout);
    }

    /**
     * 测试连接
     *
     * @return
     */
    public boolean testConnect(String ip) {

        final String MethodName = "HelloWorld";
        SoapObject request = new SoapObject(nameSpace, MethodName);
        request.addProperty("helloworld", MethodName);

        String result = GetWebServiceResult(url, nameSpace, MethodName, request, timeout);
        return result != null && result.equals(MethodName);
    }

    /**
     * 调用web 返回查询结果
     */
    private String GetWebServiceResult(String URL, String nameSpace, String methodName, SoapObject request, int TimeOut) {

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        new MarshalDate().register(envelope); // 序列化 传复杂参数
        // new MarshalHashtable().register(envelope);
        // new MarshalBase64().register(envelope);
        envelope.dotNet = true;
        envelope.bodyOut = request;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transport = new HttpTransportSE(URL, TimeOut);

        // 使用call方法调用WebService方法
        try {
            transport.call(nameSpace + methodName, envelope);
            // 使用getResponse方法获得WebService方法的返回结果，代码如下：
            if (envelope.getResponse() != null) {
                Object object = envelope.getResponse();
                return object.toString();
            }
        } catch (SocketTimeoutException e) {
            if (mHandler != null)
                mHandler.sendEmptyMessage(WebService.SocketTimeout);
            //Log.e("webservice socket 连接超时错误\n" + e);
            Log.e("URL=" + URL);
            e.printStackTrace();
            return "-1";
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            if (mHandler != null)
                mHandler.sendEmptyMessage(WebService.SocketException);
            //Log.e("webservice socket 连接错误\n" + e);
            Log.e("URL=" + URL);
            e.printStackTrace();
            return "-1";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            if (mHandler != null)
                mHandler.sendEmptyMessage(WebService.IOException);
            //Log.e("webservice IO 输出错误\n" + e);
            Log.e("URL=" + URL);
            e.printStackTrace();
            return "-1";
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            if (mHandler != null)
                mHandler.sendEmptyMessage(WebService.XmlPullParserException);
            // Log.e("webservice xml 解析错误\n" + e);
            Log.e("URL=" + URL);
            e.printStackTrace();
            return "-1";
        } catch (Exception e) {
            if (mHandler != null)
                mHandler.sendEmptyMessage(WebService.Exception);
            // Log.e("webservice 其他错误\n" + e);
            Log.e("URL=" + URL);
            e.printStackTrace();
            return "-1";
        }
        return null;
    }
}
