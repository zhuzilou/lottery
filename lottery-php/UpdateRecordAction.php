<?php
include 'LoadRecordService.php';
include_once 'DoubleColorBall.php';

	$reqData = file_get_contents("php://input");
	$updateForFree = json_decode($reqData, true);
// 	var_dump($updateForFree);
	
	$loadRecordService = new LoadRecordService();
	$history = $loadRecordService->getHistory();
	$lastIssueNum = "";
	foreach ($history as $item) {
		//find the last isssue number in history
		if(strcmp($item->getIssueNumber(), $lastIssueNum) > 0) {
			$lastIssueNum = $item->getIssueNumber();
		}
	}
// 	var_dump($lastIssueNum);
	
	$needUpdateData = array();
	$needUpdateDataCount = 0;
	$regexBalls = '/(\d{2}),(\d{2}),(\d{2}),(\d{2}),(\d{2}),(\d{2})\+(\d{2})/';
	$regexIssueDate = '/(\d{4}-\d{2}-\d{2})/';
	foreach ($updateForFree as $freeData) {
		//echo $freeData['expect'].$freeData['opentime'].$freeData['opencode'];
		$expect = $freeData['expect'];
		if(strcmp($lastIssueNum, $expect) < 0) {
			$updateData = new DoubleColorBall ();
			$updateData->setIssueNumber ( $freeData['expect']);
			
			$matches = array();
			if (preg_match ( $regexIssueDate, $freeData['opentime'], $matches )) {
				$updateData->setIssueDateStr ( $matches [1] );
			}
			
			$matches = array();
			if (preg_match ( $regexBalls, $freeData['opencode'], $matches )) {
				$index = 1;
				$updateData->setFirstBall ( $matches [$index] );
				$updateData->setSecondBall ( $matches [++ $index] );
				$updateData->setThirdBall ( $matches [++ $index] );
				$updateData->setFourthBall ( $matches [++ $index] );
				$updateData->setFifthBall ( $matches [++ $index] );
				$updateData->setSixthBall ( $matches [++ $index] );
				$updateData->setBlueBall ( $matches [++ $index] );
			}
			
			$needUpdateData[$needUpdateDataCount++] = $updateData;
		}
	}
// 	var_dump($needUpdateData);

	if(count($needUpdateData) > 0) {
		$filename = 'resources/update.txt';
		$lastIssueDate = "";
		foreach ($needUpdateData as $item) {
			$appendContent = $item->getIssueNumber()."    ".$item->getFirstBall().",".$item->getSecondBall().",".$item->getThirdBall().",".$item->getFourthBall().",".$item->getFifthBall().",".$item->getSixthBall()."|".$item->getBlueBall()."    ".$item->getIssueDateStr();
			file_put_contents($filename, $appendContent.PHP_EOL, FILE_APPEND);
			if(strcmp($lastIssueNum, $item->getIssueNumber()) < 0) {
				$lastIssueNum = $item->getIssueNumber();
				$lastIssueDate = $item->getIssueDateStr();
			}
		}
		echo "更新成功，最新期号：".$lastIssueNum."，最新开奖日期：".$lastIssueDate;
	} else {
		echo "更新失败，已是最新记录。";
	}
?>