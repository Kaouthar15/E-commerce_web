package controller;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@WebServlet("/uploadFile")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UploadFile() {
        super();
    }


	@SuppressWarnings("removal")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("") + "images"+ File.separator + "produit";
	    log("Upload Path : "+ uploadPath);
		File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	    	uploadDir.mkdir();
	    }
	    
		Part filePart = request.getPart("file");
	    String fileName = filePart.getSubmittedFileName();
	    
	    for (Part part : request.getParts()) {
	      part.write(uploadPath + File.separator + fileName);
	    }
	    
	    HttpSession session=request.getSession();
		session.setAttribute("confirmation",new Boolean(true));
		response.sendRedirect("testUpload.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
