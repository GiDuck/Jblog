package com.bit.jblog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.PostVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int makeBlog(BlogVo vo) {
		
		return sqlSession.insert("blog.makeBlog", vo);
	}
	
	public BlogVo getBlogInfo (int memberNo) {
		return sqlSession.selectOne("blog.getBlogInfo", memberNo);
	}
	
	public int updateBlogInfo(BlogVo vo) {
		
		return sqlSession.update("blog.updateBlogInfo", vo);

	}
	
	public int insertCategory(CategoryVo categoryVo) {
		
		return sqlSession.insert("blog.insertCategory", categoryVo);
	}
	
	public List<CategoryVo> getCategories(int userNo){
		
		return sqlSession.selectList("blog.getCategories", userNo);
	}
	
	public int removeCategory(int categoryNo) {
		return sqlSession.delete("blog.removeCategory", categoryNo);
	}
	
	public List<Map<String, String>> getCategoriesByMap (int userNo){
		
		return sqlSession.selectList("blog.getCategoriesByMap", userNo);
	}
	
	public int countPostToCategory (int categoryNo) {
		
		return sqlSession.selectOne("blog.countPostToCategory", categoryNo); 
	}
	
	public int insertPost(PostVo vo) {
		return sqlSession.insert("blog.insertPost", vo);
	}
	
}
