package com.acec.core.utils;//package com.acec.core.utils;
//
//import java.sql.Timestamp;
//import java.util.logging.Logger;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.acec.wgt.models.ser.SMSSendDAO;
//import com.acec.wgt.models.ser.SMSSend;
//import com.acec.wgt.service.ser.SMSServiceImpl;
//import com.acec.wgt.service.ser.SMSServiceImplService;
//import com.acec.core.utils.SpringInit;
//import com.acec.core.utils.StringUtil;
//import com.acec.core.utils.RequestQueue;
//
///**
// * 发送短信线程
// * 
// * @author lau
// * 
// */
//public class ReqSaveThread extends Thread {
//
//    private final RequestQueue channel;
//    Logger logger = Logger.getLogger("com.dong");
//
//    SMSSendDAO sMSSendDAO = (SMSSendDAO) SpringInit.getApplicationContext().getBean("SMSSendDAO");
//
//    private static final String SUBPASS = com.bestnet.base.util.helper.BaseConfigHelper.getProperty("subpass");
//    private static final int CONTENTNUM = 67;
//
//    private volatile boolean shutdownRequested = false;
//
//    public ReqSaveThread(String name, RequestQueue channel) {
//	super(name);
//	this.channel = channel;
//
//    }
//
//    public void run() {
//	while (true) {
//	    try {
//		SMSSend sms = channel.takeRequest();
//
//		if (shutdownRequested && sms == null) {// 发出停止请求并且队列为空，停止线程
//		    break;
//		}
//
//		// String destPhoneList = sms.getMobilePhones();
//
//		// 去除前面,号
//		// if (!StringUtils.isEmpty(destPhoneList) &&
//		// destPhoneList.startsWith(","))
//		// destPhoneList = destPhoneList.substring(1);
//
//		// 取出手机号 接收人姓名数组
//		// String[] mobilePhones = destPhoneList.split(",");
//		// String receivtmp = sms.getReceiveName();
//		// String[] receivenames = null;
//		// if (!StringUtils.isEmpty(receivtmp))
//		// receivenames = receivtmp.split(",");
//
//		// String phones = "";
//		// String receive = "";
//
//		// // 剔除非手机号及对应姓名
//		// for (int j = 0; j < mobilePhones.length; j++) {
//		// if (StringUtil.isMobileNO(mobilePhones[j])) {
//		// phones += mobilePhones[j] + ",";
//		// if (receivenames != null && receivenames.length >= j)
//		// receive += receivenames[j] + ",";
//		// }
//		// }
//		sms.setId(null);
//		// sms.setMobileNum(mobilePhones.length);
//		sms.setMobileNum(1);
//
//		// sms.setMobilePhones(phones);
//		//
//		// sms.setReceiveName(receive);
//
//		// if (StringUtils.isEmpty(sms.getType()))
//		sms.setType("0");
//
//		// if (StringUtil.isEmpty(sms.getContent()))
//		// throw new RuntimeException("发送内容为空");
//
//		SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
//
//		String ret = impl.sendSMSGetDev(sms.getSubid(), SUBPASS, sms.getMobilePhones(), sms.getContent());
//
//		sms.setState(ret);
//
//		// 判断内容长度
//		int tmp = sms.getContent().length() % CONTENTNUM;
//		int countentnum = sms.getContent().length() / CONTENTNUM;
//		if (tmp == 0)
//		    sms.setContentNum(countentnum);
//		else
//		    sms.setContentNum(countentnum + 1);
//
//		// 设置当前发送时间
//		if (sms.getSendTime() == null)
//		    sms.setSendTime(new Timestamp(System.currentTimeMillis()));
//		
//		if(ret!=null&&ret.length()>3){
//			sms.setMsgid(ret.substring(3));
//		}
//		sMSSendDAO.save(sms);
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//	}
//	logger.info("ReqSaveThreads接收线程终止");
//    }
//
//    // 终止请求
//    public void shutdownRequest() {
//	shutdownRequested = true;
//	interrupt();
//    }
//}
