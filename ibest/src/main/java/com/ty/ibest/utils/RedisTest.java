package com.ty.ibest.utils;

import java.util.Random;

import org.dom4j.DocumentException;

public class RedisTest extends DataHandler{
  
	public static void main(String[] args) throws DocumentException {
        String x = "18754800737";
        String xd = MD5(x);
        RegValid reg = new RegValid();
        System.out.println(reg.validPhone(x));
        System.out.println(xd);
        Double random = new Random().nextDouble();
        System.out.println(random);
        String str = random.toString().substring(2);
        System.out.println(str);
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String param = "";
        
        String xmls = "<xml>"
        		+ "<appid>wx2421b1c4370ec43b</appid>"
        		+ "<attach>支付测试</attach>"
        		+ "<body>JSAPI支付测试</body>"
        		+ "<mch_id>10000100</mch_id>"
        		
        		+ "<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>"
        		+ "<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>"
        		+ "<openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>"
        		+ "<out_trade_no>1415659990</out_trade_no>"
        		+ "<spbill_create_ip>14.23.150.211</spbill_create_ip>"
        		+ "<total_fee>1</total_fee>"
        		+ "<trade_type>JSAPI</trade_type>"
        		+ "<sign>0CB01533B8C1EF103065174F50BCA001</sign>"
        		+ "</xml>";
        
/*        Map<String, String> map = new HashMap<String, String>();
       
        Document doc = null;
        doc = DocumentHelper.parseText(xmls); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        @SuppressWarnings("unchecked")
        List<Element> list = rootElt.elements();// 获取根节点下所有节点
        for (Element element : list) { // 遍历节点
        	param += element.getName()+"="+element.getText()+"&";
            map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
        }
        param = param.substring(0,param.length() - 1);*/
       
     /*   + "<sign>0CB01533B8C1EF103065174F50BCA001</sign>"*/
        /**
         * https://pay.weixin.qq.com/wiki/tools/signverify/ 签名生成
         */
        param += "appid=wx2421b1c4370ec43b"
        		+ "&body=JSAPI支付测试&mch_id=10000100"
        		+ "&nonce_str=1add1a30ac87aa2db72f57a2375d8fec&notify_url=http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php"
        		+ "&openid=oUpF8uMuAJO_M2pxb1Q9zNjWeS6o&out_trade_no=1415659990"
        		+ "&spbill_create_ip=14.23.150.211&total_fee=1" 
        		+ "&trade_type=JSAPI";
        String sign = MD5(param);
       // xmls += "<sign>"+sign+"</sign></xml>";
        System.out.println("签名"+sign);
     
        String sr=HttpRequestUtil.wxPayPost(url,xmls);
        System.out.println(sr);
    }
}
