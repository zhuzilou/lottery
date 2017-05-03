package model;

import java.text.MessageFormat;

/**
 * Mapping table LOTQTDCB
 * 
 * @author E377712
 *
 */
public class DoubleColorBall {
	private String issueNumber;

	private String firstBall;

	private String secondBall;

	private String thirdBall;

	private String fourthBall;

	private String fifthBall;

	private String sixthBall;

	private String blueBall;

	/** yyyy-mm-dd */
	private String issueDateStr;

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getFirstBall() {
		return firstBall;
	}

	public void setFirstBall(String firstBall) {
		this.firstBall = firstBall;
	}

	public String getSecondBall() {
		return secondBall;
	}

	public void setSecondBall(String secondBall) {
		this.secondBall = secondBall;
	}

	public String getThirdBall() {
		return thirdBall;
	}

	public void setThirdBall(String thirdBall) {
		this.thirdBall = thirdBall;
	}

	public String getFourthBall() {
		return fourthBall;
	}

	public void setFourthBall(String fourthBall) {
		this.fourthBall = fourthBall;
	}

	public String getFifthBall() {
		return fifthBall;
	}

	public void setFifthBall(String fifthBall) {
		this.fifthBall = fifthBall;
	}

	public String getSixthBall() {
		return sixthBall;
	}

	public void setSixthBall(String sixthBall) {
		this.sixthBall = sixthBall;
	}

	public String getBlueBall() {
		return blueBall;
	}

	public void setBlueBall(String blueBall) {
		this.blueBall = blueBall;
	}

	public String getIssueDateStr() {
		return issueDateStr;
	}

	public void setIssueDateStr(String issueDateStr) {
		this.issueDateStr = issueDateStr;
	}

	@Override
	public String toString() {
		return MessageFormat.format("{0}    {1},{2},{3},{4},{5},{6}|{7}    {8}", this.issueNumber, this.firstBall,
				this.secondBall, this.thirdBall, this.fourthBall, this.fifthBall, this.sixthBall, this.blueBall,
				this.issueDateStr);
	}
}
