package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import model.DoubleColorBall;

/**
 * @author endless
 *
 */
public final class ReadDoubleColorBall {
	private static ReadDoubleColorBall instance = new ReadDoubleColorBall();

	private Map<String, DoubleColorBall> doubleColorBalls;

	/**
	 * Private Constructor
	 */
	private ReadDoubleColorBall() {
		this.doubleColorBalls = new HashMap<>();
		readFile();
	}

	public static ReadDoubleColorBall getInstance() {
		return instance;
	}

	/**
	 * Get all records of Double Color Balls
	 * 
	 * @return
	 */
	public Collection<DoubleColorBall> getAllRecords() {
		return this.doubleColorBalls.values();
	}

	private void processIssueNumber(String issueNumber, DoubleColorBall dcb) {
		dcb.setIssueNumber(issueNumber.trim());
	}

	private void processBalls(String ballNumbers, DoubleColorBall dcb) {
		ballNumbers = ballNumbers.replace(GlobalCst.RED_BLUE_BALL_SEPARATOR, GlobalCst.BALL_SEPARATOR);
		String[] balls = ballNumbers.split(GlobalCst.BALL_SEPARATOR);
		dcb.setFirstBall(balls[0]);
		dcb.setSecondBall(balls[1]);
		dcb.setThirdBall(balls[2]);
		dcb.setFourthBall(balls[3]);
		dcb.setFifthBall(balls[4]);
		dcb.setSixthBall(balls[5]);
		dcb.setBlueBall(balls[6]);
	}

	private void processIssueDate(String issueDateStr, DoubleColorBall dcb) {
		dcb.setIssueDateStr(issueDateStr);
	}

	private BufferedReader getFileReader(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			return reader;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Collection<String> getLines(BufferedReader reader) {
		Collection<String> contents = new ArrayList<>();
		try {
			if (reader != null) {
				String line = reader.readLine();
				while (line != null) {
					contents.add(line);
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}

	/**
	 * Read files by Java 8
	 */
	public void readFile() {
		File dir = new File(this.getClass().getResource("/").getPath());
		// for (File f : dir.listFiles()) {
		// System.out.println(f.getName());
		// }
		File[] txtFiles = dir.listFiles((File f) -> f.getName().endsWith(GlobalCst.FILE_SUFFIX));

		Collection<String> allLines = Arrays.stream(txtFiles).map(eachFile -> getFileReader(eachFile))
				.map(reader -> getLines(reader)).flatMap(lines -> lines.stream()).collect(Collectors.toList());
		for (String line : allLines) {
			String[] doubleColorBall = line.split(GlobalCst.LINE_SEPARATOR);
			DoubleColorBall dcb = new DoubleColorBall();
			processIssueNumber(doubleColorBall[0], dcb);
			processBalls(doubleColorBall[1], dcb);
			processIssueDate(doubleColorBall[2], dcb);
			System.out.println(dcb);
			doubleColorBalls.put(dcb.getIssueNumber(), dcb);
		}
		// System.out.println(allLines.size());
	}

	public static void main(String[] args) throws IOException {
		ReadDoubleColorBall.getInstance().getAllRecords();
	}
}
