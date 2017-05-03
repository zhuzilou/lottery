package action.doublecolorball;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utils.GlobalCst;
import utils.ReadDoubleColorBall;

public class UpdateDataAction extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2916257674582523694L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int count = 10;
		final File file = new File(this.getClass().getResource("/").getPath() + "/update.txt");
		final PrintWriter out = resp.getWriter();
		for (int i = 0; i < count; i++) {
			JSONObject jsonObj = JSONObject.fromObject(req.getParameter(String.valueOf(i)));
			// jsonObj.get("expect");
			// jsonObj.get("opencode");
			// jsonObj.get("opentime");
			convertRecord(jsonObj, file, out);
		}
		ReadDoubleColorBall.getInstance().readFile();
		out.flush();
		out.close();
	}

	private void convertRecord(JSONObject jsonObj, final File file, final PrintWriter out) {
		BufferedWriter writer;
		StringBuilder sb = new StringBuilder();
		sb.append((String) jsonObj.get("expect")); // issue number: 2016032
		sb.append(GlobalCst.LINE_SEPARATOR);
		String numbers = (String) jsonObj.get("opencode");
		sb.append(numbers.replace("+", GlobalCst.RED_BLUE_BALL_SEPARATOR)); // number:
																			// 08,12,14,15,21,27|15
		sb.append(GlobalCst.LINE_SEPARATOR);
		String dateStr = (String) jsonObj.get("opentime"); // date: 2016-03-22
		sb.append(dateStr.substring(0, 10));
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(sb.toString());
			writer.write("\r\n");
			writer.flush();
			writer.close();
			out.print("Updated: ");
			out.print(sb.toString());
			out.print("<br/>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
