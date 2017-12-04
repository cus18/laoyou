package com.yhl.laoyou.common.servlet;

import com.yhl.laoyou.common.utils.OSSObjectTool;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.sys.entity.healthData.HealthData;
import com.yhl.laoyou.modules.sys.service.EasemobService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UploadFileServlet extends HttpServlet {
	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 300;  // 300MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 400; // 400MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 500; // 500MB

	private static ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

	public UploadFileServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		// 检测是否为多媒体上传
		PrintWriter writer = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			writer.flush();
			return;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		//factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		//upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		//upload.setSizeMax(MAX_REQUEST_SIZE);

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;

		String key = request.getParameter("key");

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			List<FileItem> formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						item.write(storeFile);
						InputStream in = new FileInputStream(storeFile);
						if((formItems.size()>1) || (formItems.size()==1 && StringUtils.isNull(key))){//多图上传或单图上传且key为空时使用文件名做key
							key = fileName;
						}

						String s = OSSObjectTool.uploadFileInputStream(key,storeFile.length(),in,"yhllaoyou");
						System.out.println(s);
						/*Runnable thread = new uploadFileOss(key,storeFile.length(),in);
						threadExecutor.execute(thread);*/
					}
				}
			}
			request.setAttribute("message",
					"文件上传成功!");
			writer.write("success");
			writer.flush();
		} catch (Exception ex) {
			request.setAttribute("message",
					"错误信息: " + ex.getMessage());
			ex.printStackTrace();
		}

	}


	public void uploadFileOss(String key,Long length,InputStream in) {
		String s = OSSObjectTool.uploadFileInputStream(key,length,in,"yhllaoyou");
	}

	public class uploadFileOss extends Thread {

		private String key;
		private Long length;
		private InputStream in;

		public uploadFileOss(String key,Long length,InputStream in) {
			this.key = key;
			this.length = length;
			this.in = in;
		}

		@Override
		public void run() {
			uploadFileOss(key,length,in);
		}
	}

}
