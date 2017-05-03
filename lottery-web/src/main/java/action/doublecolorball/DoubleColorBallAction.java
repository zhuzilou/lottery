package action.doublecolorball;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SelectDoubleColorBallService;

public class DoubleColorBallAction extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8609203274347082322L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int ballNum = 7;
		String[] chosenBall = new String[ballNum];
		for (int i = 0; i < ballNum; i++) {
			chosenBall[i] = req.getParameter(String.valueOf(i));
		}
		SelectDoubleColorBallService service = new SelectDoubleColorBallService();
		// ServletOutputStream out = resp.getOutputStream();
		// out.println(service.selectRecord(chosenBall));
		// out.flush();
		// out.close();
		PrintWriter writer = resp.getWriter();
		writer.print(service.selectRecord(chosenBall));
		writer.flush();
		writer.close();
	}

}
