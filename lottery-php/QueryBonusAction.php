<?php
include 'QueryBonusService.php';
include_once 'DoubleColorBall.php';
	//echo $_POST["0"];
	//echo $_POST["1"];
	$reqData = file_get_contents("php://input");
// 	var_dump(json_decode($reqData));
	$queryData = json_decode($reqData);
	$hasBlueBall = count($queryData);
	
// 	var_dump($queryData);
	if('' == $queryData || null == $queryData || count($queryData) != 7) {
		return;
	}
	
	$queryRedBalls = array();
	$queryBlueBall = '';
	if($hasBlueBall == 7) {
		$queryBlueBall= $queryData[6];
	}
	
	$queryRedCount = 0;
	for ($i = 0; $i < 6; $i++) {
		$ball = $queryData[$i];
		if($ball != "") {
			// 			echo $ball;
			$queryRedBalls[$queryRedCount++] = $ball;
		}
	}
	
// 	var_dump($queryRedBalls);
	
	$queryBonusService = new QueryBonusService();
	$queryResult = $queryBonusService->queryBonus($queryRedBalls, $queryBlueBall);
// 	var_dump($queryResult);
	if(count($queryResult) > 0) {
		$strResult = "";
// 		var_dump($queryResult);
		foreach ($queryResult as $result) {
// 			echo "期号：".$result->getIssueNumber()." 开奖日期：".$result->getIssueDateStr()."  开奖号码：".$result->getFirstBall().",".$result->getSecondBall().",".$result->getThirdBall().",".$result->getFourthBall().",".$result->getFifthBall().",".$result->getSixthBall().",".$result->getBlueBall();
			$strResult = $strResult."期号：".$result->getIssueNumber()." 开奖日期：".$result->getIssueDateStr()."  开奖号码：".$result->getFirstBall().",".$result->getSecondBall().",".$result->getThirdBall().",".$result->getFourthBall().",".$result->getFifthBall().",".$result->getSixthBall()."|".$result->getBlueBall()."&";
		}
		echo $strResult;
	} else {
		echo "该方案尚未中过大奖，您还有机会，Good luck!!!";
	}
?>
