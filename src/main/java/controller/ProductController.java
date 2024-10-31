package controller;


import form.ProductFormBean;
import repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProductRepository productRepository = new ProductRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductFormBean cfb = new ProductFormBean();

		if (request.getParameter("add") != null) {
			System.out.println("add");
			
			Double price = Double.parseDouble(request.getParameter("price"));
			
			cfb.setName(request.getParameter("name"));
			cfb.setPrice(price); 
			cfb.setPhoto(null);
			
			productRepository.addProduct(cfb.getName(), cfb.getPrice(), cfb.getPhoto());

			response.sendRedirect("products");
		} else if (request.getParameter("delete") != null) {
			
			
			Long id = Long.parseLong(request.getParameter("id")); 
			
			cfb.setId(id); 
			productRepository.deleteById(cfb.getId()); 
			
			response.sendRedirect("products");
			System.out.println("delete");
			System.out.println("Deleting ID: " + request.getParameter("id"));

		}
		else if (request.getParameter("search") !=null) {
			
			cfb.setName(request.getParameter("name")); 
		    List<Product> searchResults = productRepository.searchByName(cfb.getName());
		    request.getSession().setAttribute("products", searchResults);
		    request.getRequestDispatcher("Product.jsp").forward(request, response);
		    
			System.out.println("Search");
			System.out.println("Search using : " +request.getParameter("name"));
		}else{
			
			System.out.println("show");
			
			List<Product> rawCategories = productRepository.findAllProducts();
			request.getSession().setAttribute("products", rawCategories);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request,response);
	}

}
