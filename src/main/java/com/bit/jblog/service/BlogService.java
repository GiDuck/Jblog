package com.bit.jblog.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit.jblog.dao.BlogDao;
import com.bit.jblog.utils.file.FileUploadInfo;
import com.bit.jblog.utils.file.FileUploadUtil;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.PostVo;

@Service
public class BlogService {
	
	Logger logger = LoggerFactory.getLogger(BlogService.class);
	
	@Autowired
	private BlogDao dao;
	
	
	
	public BlogStatusCode makeBlog(BlogVo blogVo) {
	
		if(dao.makeBlog(blogVo) > 0)
		return BlogStatusCode.BLOG_MAKE_SUCCESS;
		
		return BlogStatusCode.BLOG_MAKE_FAIL;
		
	}
	
	public BlogVo getBlogInfo(int memberNo) {
		return dao.getBlogInfo(memberNo);
	}
	
	
	public BlogStatusCode updateBlogInfo(BlogVo blogVo, MultipartFile multiPartFile) {
		
		if(multiPartFile != null && multiPartFile.getSize() > 0) {
			blogVo.setLogo(FileUploadUtil.uploadFile(multiPartFile, multiPartFile.getOriginalFilename(), FileUploadInfo.PARENT_PATH.getValue(), FileUploadInfo.UPLOAD_IMAGE_PATH.getValue()));
		}
		if(dao.updateBlogInfo(blogVo) == 1) return BlogStatusCode.BLOG_UPDATE_SUCCESS;
		return BlogStatusCode.BLOG_UPDATE_FAIL;
		
	}
	
	public BlogStatusCode insertCategory(CategoryVo categoryVo) {
		
		if(dao.insertCategory(categoryVo) == 1 ) return BlogStatusCode.CATEGORY_ADD_SUCCESS;		
		return BlogStatusCode.CATEGORY_ADD_FAIL;
		
	}
	
	
	public List<CategoryVo> getCategories(int userNo) {
		return dao.getCategories(userNo);
	}
	
	public BlogStatusCode removeCategory(int categoryNo) {
		
		
		if(dao.countPostToCategory(categoryNo) > 0 ) return BlogStatusCode.CATEGORY_HAS_POSTING;
		if(dao.removeCategory(categoryNo) > 0) return BlogStatusCode.CATEGORY_DEL_SUCCESS;
		return BlogStatusCode.CATEGORY_DEL_FAIL;
		
	}
	
	public List<Map<String, String>> getCategoriesByMap (int userNo){
		
		return dao.getCategoriesByMap(userNo);
		
	}
	
	public BlogStatusCode insertPost(PostVo postVo) {
		System.out.println(postVo.toString());
		if(dao.insertPost(postVo) > 0) return BlogStatusCode.POST_SUCCESS;
		return BlogStatusCode.POST_FAIL;
	}
	
	public List<Map<String, String>> getPosts (int userNo){
		return dao.getPosts(userNo);
	}
	
	public List<Map<String, String>> getPostsByCategory (int categoryNo){
		
		return dao.getPostsByCategory(categoryNo);
	}
	
	
	public BlogStatusCode deletePost (int postNo) {

		if(dao.deletePost(postNo) > 0) return BlogStatusCode.POST_DEL_SUCCESS;
		return BlogStatusCode.POST_DEL_FAIL;
		
	};
	
	public BlogStatusCode updatePost (PostVo postVo) {
		if(dao.updatePost(postVo) > 0) return BlogStatusCode.POST_UPDATE_SUCCESS;
		return BlogStatusCode.BLOG_UPDATE_FAIL;
	}
	
	
	public PostVo getSinglePost(int postNum) {
		
		return dao.getSinglePost(postNum);
	}
}
