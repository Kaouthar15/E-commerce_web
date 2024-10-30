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
        List<Category> rawCategories = categoryRepository.findAllCategories();

   

        request.getSession().setAttribute("categories", rawCategories); 
        request.getRequestDispatcher("Category.jsp").forward(request, response);
    }
}
