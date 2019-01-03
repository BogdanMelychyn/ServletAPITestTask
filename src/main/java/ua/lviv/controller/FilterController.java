package ua.lviv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ua.lviv.domain.TextFile;
import ua.lviv.view.ReadFilter;

@WebServlet("/readLines")
public class FilterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ReadFilter fs;

	public FilterController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getServletContext().getRealPath("/test.txt");
		fs = new ReadFilter(new TextFile(path));
		String str, length, meta;
		int lim = 0;
		str = req.getParameter("q");
		try {
			lim = Integer.parseInt(req.getParameter("limit"));
		} catch (NumberFormatException e) {
			lim = 10000;
		}
		length = req.getParameter("length");
		meta = req.getParameter("metaData");
		JSONObject jo = fs.findLines(str, lim, length,meta);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(jo.toString(4));
		
	}
	  

}
