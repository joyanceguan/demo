package com.joyance.demo.elasticsearch;

import java.io.IOException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

@Service
public class ESBase {

	@Resource(name="esTransportClient")
	TransportClient client;
	
	String index = "my-index";
	String type = "my-type";
	
	/*
	 * 创建索引
	 */
	private void createIndex(TransportClient client,String index) {
		CreateIndexRequest request = new CreateIndexRequest(index);
		IndicesAdminClient indices  = client.admin().indices();
		indices.create(request).actionGet();
	}
	
	/*
	 * 初始化索引结构
	 */
	public void initStruct(TransportClient client) throws IOException {
		createIndex(client,index);
		XContentBuilder builder=XContentFactory.jsonBuilder()
				.startObject()
				.startObject(type)
				.startObject("properties")
				.startObject("title").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("show_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("type").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("author").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
		        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
		        .startObject("start_time").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("sub_content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("visits").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("keywords").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("time_degree").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("pictures").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("pic_num").field("type", "integer").field("index", "not_analyzed").endObject()
				.startObject("recommend").field("type", "nested")
				.startObject("properties")
				.startObject("sort").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.endObject()
				.endObject()
				.endObject();
	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
	   client.admin().indices().putMapping(mapping).actionGet();
	}
	
	/*
	 * 新增
	 */
	@SuppressWarnings("deprecation")
	public <T> String insert(String index,String type,T t) {
		if(t == null){
			return null;
		}
		IndexRequestBuilder indexRequestBuilder =client.prepareIndex(index, type);
		IndexResponse response = indexRequestBuilder.setSource(JSON.toJSONString(t)).execute().actionGet();
		return response.getId();
	}
	
	/*
	 * 删除
	 */
	public String deleteById(String id) {
		DeleteResponse response=client.prepareDelete(index, type, id).execute().actionGet();
		return response.getId();
	}
	
	/*
	 * 修改
	 */
	@SuppressWarnings("deprecation")
	public <T> int update(T t,String id) throws Exception {
		 UpdateRequest updateRequest = new UpdateRequest(index, type,id);
		 updateRequest.doc(JSON.toJSONString(t));
		 client.update(updateRequest).get();
		 return 1;
	}
	
//	/*
//	 * id查询(需要将Object.class 换成真正用的类型)
//	 */
//	public <T> T selectById(String id) {
//		TransportClient client=transportClient.getClient();
// 		GetResponse response = client.prepareGet(index, type , id).get();
// 		T t= JSON.parseObject(response.getSourceAsString(), T.class);
// 		return t;
//	}
}
