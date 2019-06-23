package com.joyance.demo.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BaseMongo {

	public void insert(){
		//获取数据库连接对象
	    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //要插入的数据
	    Document document = new Document("name","张三")
	                            .append("sex", "男")
	                            .append("age", 18);
	    //插入一个文档
	    collection.insertOne(document);
	}
	
	public void find(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //查找集合中的所有文档
	    FindIterable findIterable = collection.find();
	    MongoCursor cursor = findIterable.iterator();
	    while (cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
	
	public void insertMany(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //要插入的数据
	    List<Document> list = new ArrayList<>();
	    for(int i = 1; i <= 3; i++) {
	        Document document = new Document("name", "张三")
	                .append("sex", "男")
	                .append("age", 18+i);
	        list.add(document);
	    }
	    //插入多个文档
	    collection.insertMany(list);
	}
	
	public void updateOneTest(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //修改过滤器
	    Bson filter = Filters.eq("name", "张三");
	    //指定修改的更新文档
	    Document document = new Document("$set", new Document("age", 100));
	    //修改单个文档
	    collection.updateOne(filter, document);
	}
	
	public void deleteOneTest(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //申明删除条件
	    Bson filter = Filters.eq("age",19);
	    //删除与筛选器匹配的单个文档
	    collection.deleteOne(filter);
	}
	
	public static void main(String[] args) {
		BaseMongo bm = new BaseMongo();
//		bm.insert();
//		bm.insertMany();
//		bm.updateOneTest();
		bm.deleteOneTest();
		bm.find();
	}
}
