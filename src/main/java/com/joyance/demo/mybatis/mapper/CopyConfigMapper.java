package com.joyance.demo.mybatis.mapper;

import java.util.List;

import com.joyance.demo.mybatis.persistence.ConfigCopy;


public interface CopyConfigMapper {

	public int save(ConfigCopy config);
	
	public ConfigCopy selectById(Integer id);
	
	public int batchSave(List<ConfigCopy> list);
	
}
