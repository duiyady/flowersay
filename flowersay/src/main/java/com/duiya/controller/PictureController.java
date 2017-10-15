package com.duiya.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.duiya.utils.CommonUtil;
import com.duiya.utils.DefaultSet;
import com.duiya.utils.EnumUtil;
import com.duiya.utils.StringUtil;

@Controller
@RequestMapping("upload")
public class PictureController {
	private Logger logger = LoggerFactory.getLogger(PictureController.class);
	
	@RequestMapping("flowerpicupload")
	@ResponseBody
	public JSONObject flowerpicUpload(HttpServletRequest request, HttpServletResponse response) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// //判断 request 是否有文件上传,即多部分请求
		if (cmr.isMultipart(request)) {
			// 转换成多部分
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// //取得request中的所有文件名
			Iterator<String> iter = mhsr.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = mhsr.getFile((String) iter.next());
				// //取得当前上传文件的文件名称
				String filename = file.getOriginalFilename();
				// 获得文件后缀
				String fileSuffixName = filename.substring(filename.indexOf("."));
				if (file.getSize() > 1048576) {
					return CommonUtil.constructArgErrorResponse("图片过大，请选择小一点的图片");
				} else if (!fileSuffixName.equals(".jpg") && !fileSuffixName.equals(".png")
						&& !fileSuffixName.equals(".JPG") && !fileSuffixName.equals(".JPEG")
						&& !fileSuffixName.equals(".bmp") && !fileSuffixName.equals(".jpeg")
						&& !fileSuffixName.equals(".gif")) {
					return CommonUtil.constructArgErrorResponse("图片格式不对");
				}
				String UUID = StringUtil.getUuid();

				String filename1 = UUID + fileSuffixName;
				File file3 = new File(DefaultSet.DEFAULT_FLOWERDIR + File.separator + filename1);
				if (!file3.exists()) {
					file3.mkdir();
				}
				try {
					// transfer方法是MultipartFile包中提供的方法，直接可以写入文件到指定目录
					file.transferTo(file3);
					JSONObject jo = new JSONObject();
					return CommonUtil.constructResponse(EnumUtil.OK, "成功", file3.getAbsolutePath());
				} catch (Exception e) {
					logger.error("failed to upload picture", e);
					return CommonUtil.constructResponse(EnumUtil.UNKNOW_ERROR, "未知错误上传失败", null);
				}
			}
		}
		return CommonUtil.constructArgErrorResponse("没有指定上传文件");
	}
	@RequestMapping("userpicupload")
	@ResponseBody
	public JSONObject userpicUpload(HttpServletRequest request, HttpServletResponse response) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// //判断 request 是否有文件上传,即多部分请求
		if (cmr.isMultipart(request)) {
			// 转换成多部分
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// //取得request中的所有文件名
			Iterator<String> iter = mhsr.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = mhsr.getFile((String) iter.next());
				// //取得当前上传文件的文件名称
				String filename = file.getOriginalFilename();
				// 获得文件后缀
				String fileSuffixName = filename.substring(filename.indexOf("."));
				if (file.getSize() > 1048576) {
					return CommonUtil.constructArgErrorResponse("图片过大，请选择小一点的图片");
				} else if (!fileSuffixName.equals(".jpg") && !fileSuffixName.equals(".png")
						&& !fileSuffixName.equals(".JPG") && !fileSuffixName.equals(".JPEG")
						&& !fileSuffixName.equals(".bmp") && !fileSuffixName.equals(".jpeg")
						&& !fileSuffixName.equals(".gif")) {
					return CommonUtil.constructArgErrorResponse("图片格式不对");
				}
				String UUID = StringUtil.getUuid();

				String filename1 = UUID + fileSuffixName;
				File file3 = new File(DefaultSet.DEFAULT_USERDIR + File.separator + filename1);
				if (!file3.exists()) {
					file3.mkdir();
				}
				try {
					// transfer方法是MultipartFile包中提供的方法，直接可以写入文件到指定目录
					file.transferTo(file3);
					JSONObject jo = new JSONObject();
					return CommonUtil.constructResponse(EnumUtil.OK, "成功", file3.getAbsolutePath());
				} catch (Exception e) {
					logger.error("failed to upload picture", e);
					return CommonUtil.constructResponse(EnumUtil.UNKNOW_ERROR, "未知错误上传失败", null);
				}
			}
		}
		return CommonUtil.constructArgErrorResponse("没有指定上传文件");
	}
}
