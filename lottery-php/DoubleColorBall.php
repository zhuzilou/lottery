<?php
/**
 * Created by PhpStorm.
 * User: dxinfor
 * Date: 4/17/2017
 * Time: 11:38 AM
 */
class DoubleColorBall {
	private $issueNumber;
	private $firstBall;
	private $secondBall;
	private $thirdBall;
	private $fourthBall;
	private $fifthBall;
	private $sixthBall;
	private $blueBall;
	private $issueDateStr;
	
	/**
	 *
	 * @return mixed
	 */
	public function getIssueNumber() {
		return $this->issueNumber;
	}
	
	/**
	 *
	 * @param mixed $issueNumber        	
	 */
	public function setIssueNumber($issueNumber) {
		$this->issueNumber = $issueNumber;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getFirstBall() {
		return $this->firstBall;
	}
	
	/**
	 *
	 * @param mixed $firstBall        	
	 */
	public function setFirstBall($firstBall) {
		$this->firstBall = $firstBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getSecondBall() {
		return $this->secondBall;
	}
	
	/**
	 *
	 * @param mixed $secondBall        	
	 */
	public function setSecondBall($secondBall) {
		$this->secondBall = $secondBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getThirdBall() {
		return $this->thirdBall;
	}
	
	/**
	 *
	 * @param mixed $thirdBall        	
	 */
	public function setThirdBall($thirdBall) {
		$this->thirdBall = $thirdBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getFourthBall() {
		return $this->fourthBall;
	}
	
	/**
	 *
	 * @param mixed $fourthBall        	
	 */
	public function setFourthBall($fourthBall) {
		$this->fourthBall = $fourthBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getFifthBall() {
		return $this->fifthBall;
	}
	
	/**
	 *
	 * @param mixed $fifthBall        	
	 */
	public function setFifthBall($fifthBall) {
		$this->fifthBall = $fifthBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getSixthBall() {
		return $this->sixthBall;
	}
	
	/**
	 *
	 * @param mixed $sixthBall        	
	 */
	public function setSixthBall($sixthBall) {
		$this->sixthBall = $sixthBall;
	}
	
	/**
	 *
	 * @return mixed
	 */
	public function getBlueBall() {
		return $this->blueBall;
	}
	
	/**
	 *
	 * @param mixed $blueBall        	
	 */
	public function setBlueBall($blueBall) {
		$this->blueBall = $blueBall;
	}
	
	public function getIssueDateStr() {
		return $this->issueDateStr;
	}
	
	public function setIssueDateStr($issueDateStr) {
		$this->issueDateStr = $issueDateStr;
	}
}

?>