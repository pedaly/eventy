package pl.nonamesevent.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletCreateEvent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		super.doGet(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new todo ");
		//
		// String summary = checkNull(req.getParameter("summary"));
		// String longDescription = checkNull(req.getParameter("description"));
		// String url = checkNull(req.getParameter("url"));

//		Dao.INSTANCE.add();

		resp.sendRedirect("home");
	}

	@SuppressWarnings("unused")
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

}
