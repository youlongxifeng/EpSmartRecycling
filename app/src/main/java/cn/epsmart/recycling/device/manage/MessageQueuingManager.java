package cn.epsmart.recycling.device.manage;

import com.company.project.android.utils.LogUtils;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 8:31
 * @description: （添加一句描述）
 */
public class MessageQueuingManager implements Runnable {
    private final static String TAG = MessageQueuingManager.class.getSimpleName();
    LinkedBlockingQueue<String> concurrentLinkedQueue = new LinkedBlockingQueue();

    public MessageQueuingManager() {


    }

    public void start() {
        new Thread(this).start();
    }

    public void put(String element) {
        concurrentLinkedQueue.add(element);
    }

    @Override
    public void run() {
        try {

            LogUtils.i(TAG, "MessageQueuingManager=======start concurrentLinkedQueue=" + concurrentLinkedQueue.size());

            while (concurrentLinkedQueue.take()!=null) {
                String poll = concurrentLinkedQueue.take();
                if (poll != null) {
                    //这里是业务逻辑
                    LogUtils.i(TAG, "MessageQueuingManager======poll=" + poll);
                }
            }
            LogUtils.i(TAG, "MessageQueuingManager=======end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
