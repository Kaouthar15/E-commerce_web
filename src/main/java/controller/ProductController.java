package controller;

import form.ProductFormBean;
import repository.ProductRepository;
import repository.CategoryRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Product;
import model.Category;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final ProductRepository productRepository = new ProductRepository();
	private final CategoryRepository categoryRepository = new CategoryRepository();

	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		List<Category> categoryList = categoryRepository.findAllCategories();
		request.getSession().setAttribute("categoryList", categoryList);

		ProductFormBean pfb = new ProductFormBean();

		if (request.getParameter("add") != null) {
		    System.out.println("add"); 

		    String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";
		    log("Upload Path : " + uploadPath);
		    File uploadDir = new File(uploadPath);

		    if (!uploadDir.exists()) {
		        uploadDir.mkdirs(); 
		    }

		    Part filePart = request.getPart("file"); 
		    String fileName = filePart.getSubmittedFileName();

		    if (filePart != null && fileName != null && !fileName.isEmpty()) {
		        filePart.write(uploadPath + File.separator + fileName);
		        pfb.setPhoto("images/products/" + fileName);
		    } else {
		        pfb.setPhoto(null); 
		    }

		    Double price = Double.parseDouble(request.getParameter("price"));
		    Long idCat = Long.parseLong(request.getParameter("category"));
		    pfb.setName(request.getParameter("name"));
		    pfb.setPrice(price);
		    pfb.setCategoryId(idCat);

		    productRepository.addProduct(pfb.getName(), pfb.getPrice(), pfb.getPhoto(), pfb.getCategoryId());
		    HttpSession session=request.getSession();
		    session.setAttribute("confirmation",new Boolean(true));
		    response.sendRedirect("products");
		}
 else if (request.getParameter("delete") != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			pfb.setId(id);
			productRepository.deleteById(pfb.getId());
			
			response.sendRedirect("products");
			System.out.println("Deleting ID: " + request.getParameter("id"));

		} else if (request.getParameter("search") != null) {
			pfb.setName(request.getParameter("name"));
			List<Product> searchResults = productRepository.searchByName(pfb.getName());
			request.getSession().setAttribute("products", searchResults);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
			System.out.println("Search using: " + request.getParameter("name"));
		} else {
			
			List<Product> allProducts = productRepository.findAllProducts();
			request.getSession().setAttribute("products", allProducts);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
