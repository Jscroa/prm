package cn.prm.server.api.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/download")
	public void download(HttpServletResponse response) throws IOException{
		response.setContentType("application/zip");
		response.addHeader("Content-Disposition", "attachment; filename=tsadcz.zip");
		OutputStream os = response.getOutputStream();
		String content = "asjkbnkdvbcasdjkhvbasjkdhvbcxnmvbxnmv";
		os.write(content.getBytes());
		os.flush();
		os.close();
	}
	
}
