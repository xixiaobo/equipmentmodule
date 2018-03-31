package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hcycom.jhipster.domain.Authority;
import com.hcycom.jhipster.web.rest.UrlController;


@Mapper
public interface AuthorityMapper {
	
	@InsertProvider(type = UrlController.class, method = "insertAll")  
	public int addMoreInterfaceAuthority(@Param("list") List<Authority> list);
	
	/**
	 * 根据权限类型获取权限
	 * @return
	 */
	@Select("select * from authority where authority_type=#{authority_type}")
	public List<Authority> getAllAuthorityByAuthority_type(String authority_type);

}
