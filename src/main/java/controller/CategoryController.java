package controller;

import form.CategoryFormBean;
import repository.CategoryRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/categories")
public class CategoryController extends HttpServlet {

	private final CategoryRepository categoryRepository = new CategoryRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("show");
		List<Category> rawCategories = categoryRepository.findAllCategories();
		request.getSession().setAttribute("categories", rawCategories);
		request.getRequestDispatcher("Category.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryFormBean cfb = new CategoryFormBean();

		if (request.getParameter("add") != null) {
			System.out.println("add");
			cfb.setName(request.getParameter("name"));
			cfb.setDescription(request.getParameter("description"));

			categoryRepository.addCategory(cfb.getName(), cfb.getDescription());

			response.sendRedirect("categories");
		} else if (request.getParameter("delete") != null) {
			
			
			Long id = Long.parseLong(request.getParameter("id")); 
			cfb.setId(id); 
			categoryRepository.deleteById(cfb.getId()); 
			
			response.sendRedirect("categories");
			System.out.println("delete");
			System.out.println("Deleting ID: " + request.getParameter("id"));

		}
		else if (request.getParameter("search") !=null) {
			cfb.setName(request.getParameter("name")); 
		    List<Category> searchResults = categoryRepository.searchByName(cfb.getName());
		    request.getSession().setAttribute("categories", searchResults);
		    request.getRequestDispatcher("Category.jsp").forward(request, response);
			System.out.println("Search");
			System.out.println("Search using : " +request.getParameter("name"));
		}
	}

}
