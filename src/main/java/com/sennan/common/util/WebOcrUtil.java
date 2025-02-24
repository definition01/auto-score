package com.sennan.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.sennan.common.properties.XunFeiProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**手写文字识别WebAPI接口调用示例接口文档(必看):https://doc.xfyun.cn/rest_api/%E6%89%8B%E5%86%99%E6%96%87%E5%AD%97%E8%AF%86%E5%88%AB.html
  *图片属性：jpg/png/bmp,最短边至少15px，最长边最大4096px,编码后大小不超过4M,识别文字语种：中英文
  *webapi OCR服务参考帖子(必看)：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=39111&highlight=OCR
  *(Very Important)创建完webapi应用添加服务之后一定要设置ip白名单，找到控制台--我的应用--设置ip白名单，如何设置参考：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=41891
  *错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
  *@author iflytek
  */

@Component
public class WebOcrUtil {

	@Autowired
	XunFeiProperties xunFeiProperties;

	// 手写文字识别webapi接口地址
	private  final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/handwriting";
	// 应用APPID(必须为webapi类型应用,并开通手写文字识别服务,参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
	private  final String TEST_APPID = xunFeiProperties.getAppid();
	// 接口密钥(webapi类型应用开通手写文字识别后，控制台--我的应用---手写文字识别---相应服务的apikey)
	private  final String TEST_API_KEY = xunFeiProperties.getApikey();


	/**
	 * 组装http请求头
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException 
	 */
	private  Map<String, String> constructHeader(String language, String location) throws UnsupportedEncodingException, ParseException {
		// 系统当前时间戳
		String X_CurTime = System.currentTimeMillis() / 1000L + "";
		// 业务参数
		String param = "{\"language\":\""+language+"\""+",\"location\":\"" + location + "\"}";
		String X_Param = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		// 接口密钥
		String apiKey = TEST_API_KEY;
		// 讯飞开放平台应用ID
		String X_Appid = TEST_APPID;
		// 生成令牌
		String X_CheckSum = DigestUtils.md5Hex(apiKey + X_CurTime + X_Param);
		// 组装请求头
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		header.put("X-Param", X_Param);
		header.put("X-CurTime", X_CurTime);
		header.put("X-CheckSum", X_CheckSum);
		header.put("X-Appid", X_Appid);
		return header;
	}

	public String recognize(String imagePath) throws IOException,ParseException{
		Map<String, String> header = constructHeader("en", "false");
		// 读取图像文件，转二进制数组，然后Base64编码
		byte[] imageByteArray = FileUtil.read2ByteArray("D:\\picture\\1.jpg");
		String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
		String bodyParam = "image=" + imageBase64;
		String result = HttpUtil.doPost(WEBOCR_URL, header, bodyParam);
		return result;
	}
}
