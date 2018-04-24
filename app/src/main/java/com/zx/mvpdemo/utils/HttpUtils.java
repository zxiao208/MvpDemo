package com.zx.mvpdemo.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//POST请求
public class HttpUtils {

	public static String post(String urlStr, String username, String password)
			throws Exception {
		StringBuffer sb = null;
		String param = "username=" + username + "&password=" + password;
		Log.i("TAG",urlStr);
		URL url = new URL(urlStr);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 设置参数
		httpConn.setDoOutput(true); // 需要输出
		httpConn.setDoInput(true); // 需要输入
		httpConn.setUseCaches(false); // 不允许缓存
		httpConn.setRequestMethod("POST"); // 设置POST方式连接
		httpConn.setConnectTimeout(8000);
		// 设置请求属性
		httpConn.setRequestProperty("Charset", "UTF-8");
		// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		httpConn.connect();
		// 建立输入流，向指向的URL传入参数
		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
		dos.writeBytes(param.toString());
		dos.flush();
		dos.close();
		// 获得响应状态
		int resultCode = httpConn.getResponseCode();
		sb = new StringBuffer();
		if (HttpURLConnection.HTTP_OK == resultCode) {
			String readLine = new String();
			//解析网络请求数据
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine).append("\n");
			}
			responseReader.close();
			return sb.toString();
		}
		return null;

//			             url=new URL(urlStr);
//			             HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
//
//			            httpURLConnection.setConnectTimeout(2000);//设置连接超时时间，单位ms
//			             httpURLConnection.setReadTimeout(2000);//设置读取超时时间，单位ms
//
//			             //设置是否向httpURLConnection输出，因为post请求参数要放在http正文内，所以要设置为true
//			             httpURLConnection.setDoOutput(true);
//
//			             //设置是否从httpURLConnection读入，默认是false
//			             httpURLConnection.setDoInput(true);
//
//			             //POST请求不能用缓存，设置为false
//			             httpURLConnection.setUseCaches(false);
//
//			             //传送的内容是可序列化的
//			             //如果不设置此项，传送序列化对象时，当WEB服务默认的不是这种类型时，会抛出java.io.EOFException错误
//			             httpURLConnection.setRequestProperty("Charset", "UTF-8");
//
//			             //设置请求方法是POST
//			             httpURLConnection.setRequestMethod("POST");
//
//			             //连接服务器
//			             httpURLConnection.connect();
//
//			             //getOutputStream会隐含调用connect()，所以不用写上述的httpURLConnection.connect()也行。
//			             //得到httpURLConnection的输出流
//			             OutputStream os= httpURLConnection.getOutputStream();
//
//			             //构建输出流对象，以实现输出序列化的对象
//			             ObjectOutputStream objOut=new ObjectOutputStream(os);
//
//			             //dataPost类是自定义的数据交互对象，只有两个成员变量
////			             dataPost data= new dataPost("Tom",null);
//
//			            //向对象输出流写出数据，这些数据将存到内存缓冲区中
////			             objOut.writeObject(data);
//
//			             //刷新对象输出流，将字节全部写入输出流中
//			             objOut.flush();
//
//			             //关闭流对象
//			             objOut.close();
//			             os.close();
//
//			             //将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端，并获取访问状态
//			             if(HttpURLConnection.HTTP_OK==httpURLConnection.getResponseCode()){
//
//			                 //得到httpURLConnection的输入流，这里面包含服务器返回来的java对象
//			                 InputStream in=httpURLConnection.getInputStream();
//
//			                 //构建对象输入流，使用readObject()方法取出输入流中的java对象
//			                 ObjectInputStream inObj=new ObjectInputStream(in);
////			                 data= (dataPost) inObj.readObject();
//
//			                 //取出对象里面的数据
////			                 result=data.password;
//
//			                 //输出日志，在控制台可以看到接收到的数据
////			                 Log.w("HTTP",result+"  :by post");
//
//			                 //关闭创建的流
//			                 in.close();
//			                 inObj.close();
//			             }else{
//			                Log.w("HTTP","Connction failed"+httpURLConnection.getResponseCode());
//			             }
//					return  null;
	}

	public interface OnHttpResultListener {
		public void onResult(String result);
	}
}
