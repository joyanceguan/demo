package com.joyance.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkBase {

	public static void main(String[] args) throws Exception{
		ZooKeeper zk = null;
		System.out.println("Starting to connencting zookeeper......");
		
		//连接
		String connectString  = "121.42.143.166:2181";
		int sessionTimeout = 2000;
		Watcher watcher = new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getPath()+"已经触发了" + event.getType() + "事件！");
		}};
		zk = new ZooKeeper(connectString, sessionTimeout, watcher);
		System.out.println("Zookeeper Connected Successfully!");
		
		//创建根结点
		System.out.println("开始创建根目录节点/tmp_root_path...");
		zk.exists("/tmp_root_path", true);
		zk.create("/tmp_root_path", "我是根目录节点/tmp_root_path".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("根目录节点/tmp_root_path创建成功");
		//创建子结点
		System.out.println("开始创建第一个子目录节点/tmp_root_path/childPath1...");
		zk.exists("/tmp_root_path/childPath1", true);
		zk.create("/tmp_root_path/childPath1", "我是第一个子目录/tmp_root_path/childPath1".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("第一个子目录节点/tmp_root_path/childPath1创建成功");
		//获取结点值
		String value = new String(zk.getData("/tmp_root_path/childPath1", watcher, null));
		System.out.println("获取第一个子节点的值是"+value);
		System.out.println(zk.exists("/tmp_root_path", true));
		//删除结点
		zk.delete("/tmp_root_path/childPath1", -1);
		zk.delete("/tmp_root_path", -1);
	}
	
	   
}
