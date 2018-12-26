package ua.lviv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.view.ReadFilter;

public class FilterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FilterController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReadFilter fs = new ReadFilter();
		String str = req.getParameter("q");
		String length = req.getParameter("length");
		String limit = req.getParameter("limit");
		String meta = req.getParameter("metaData");
		if (str == null) str = "";
		JsonObject jo = fs.findLines(str, length, limit, meta);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(jo);
		out.flush();
		out.close();
	}
	
}
