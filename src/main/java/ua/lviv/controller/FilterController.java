package ua.lviv.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import ua.lviv.model.ReadFilter;

@WebServlet("/readLines")
public class FilterController extends HttpServlet {

	private static final long serialVersionUID = -1030571397742821276L;
	private ReadFilter filter;
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.config = config;

		filter = ReadFilter.getReadFilter();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		JSONObject jo = new JSONObject();
		try {
			jo = filter.findLines(str, lim, length, meta);
		} catch (IOException | NullPointerException e) {
			req.setAttribute("msg", "File not found");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(jo.toString(4));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("f");
		String path = config.getServletContext().getRealPath("/" + p);

		File f = new File(path);
		System.out.println("#INF: " + f.getAbsolutePath());
		if (!f.isFile()) {

			req.setAttribute("msg", "File not found");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			System.out.println("#INF: "+"File not found " + path);
			return;

		}
		filter.setFile(f);
		req.getRequestDispatcher("readFilter.jsp").forward(req, resp);
	}

}
