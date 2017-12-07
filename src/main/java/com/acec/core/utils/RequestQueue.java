package com.acec.core.utils;//package com.acec.core.utils;
//
//import java.util.LinkedList;
//
//import com.acec.wgt.models.ser.SMSSend;
//
///**
// * 启用线程发送短信
// * 
// */
//public class RequestQueue {
//
//    // private static final int MAX_REQUEST = 100;
//    private final LinkedList<SMSSend> resquestQueue;// 保存发送队列
//
//    // private final ReqSaveThread[] threadPool;
//    private final ReqSaveThread threadPool;
//
//    // public RequestQueue(int threadsNum) {
//    public RequestQueue() {
//	this.resquestQueue = new LinkedList<SMSSend>();
//
//	// threadPool = new ReqSaveThread[threadsNum];
//	// for (int i = 0; i < threadPool.length; i++) {
//	// threadPool[i] = new ReqSaveThread("reqSave" + i, this);
//	// }
//	threadPool = new ReqSaveThread("reqSave", this);
//    }
//
//    public void startWorkers() {
//	// for (int i = 0; i < threadPool.length; i++) {
//	// threadPool[i].start();
//	// }
//	threadPool.start();
//    }
//
//    public synchronized SMSSend takeRequest() {
//	while (resquestQueue.isEmpty()) {
//	    try {
//		wait();
//	    } catch (InterruptedException ire) {
//		return null;
//	    }
//	}
//	SMSSend sms = resquestQueue.removeFirst();
//	return sms;
//    }
//
//    public synchronized void putRequest(SMSSend sms) {
//	resquestQueue.addLast(sms);
//	notifyAll();
//    }
//
//    public int getSize() {
//	return resquestQueue.size();
//    }
//
//    public void stopWorkers() {
//	// for (int i = 0; i < threadPool.length; i++) {
//	// threadPool[i].shutdownRequest();
//	// try {
//	// threadPool[i].join();
//	// } catch (Exception e) {
//	// }
//	// ;
//	// }
//	threadPool.shutdownRequest();
//	try {
//	    threadPool.join();
//	} catch (Exception e) {
//	}
//    }
//
//}
