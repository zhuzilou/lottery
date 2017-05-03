<?php
include 'LoadRecordService.php';

class QueryBonusService {
	
	public function queryBonus($redBalls, $blueBall) {
		$result = array ();
		$count = 0;
		$loadRecordService = new LoadRecordService();
		$history = $loadRecordService->getHistory();
		foreach ($history as $item) {
			if('' != $blueBall) {
				//choose blue ball
				if($blueBall != $item->getBlueBall()) {
					continue;
				}
			} 
			$findOne = $this->compareRedBalls($redBalls, $item);
			if('' != $findOne) {
				$result[$count++] = $findOne;
			}
		}
		//$result = array_search('01', $history, true);
// 		var_dump($result);
		return $result;
	}
	
	private function compareRedBalls($redBalls, $doubleColorBall) {
		$notFind = '';
		$size = count($redBalls);
		if(4 == $size) {
			//12
			if($redBalls[0] == $doubleColorBall->getThirdBall()
					&& $redBalls[1] == $doubleColorBall->getFourthBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//13
			if($redBalls[0] == $doubleColorBall->getSecondBall()
					&& $redBalls[1] == $doubleColorBall->getFourthBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//14
			if($redBalls[0] == $doubleColorBall->getSecondBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//15
			if($redBalls[0] == $doubleColorBall->getSecondBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//16
			if($redBalls[0] == $doubleColorBall->getSecondBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()) {
				return $doubleColorBall;
			}
			//23
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getFourthBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//24
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//25
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//26
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()) {
				return $doubleColorBall;
			}
			
			//34
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getFifthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
					
			//35
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			//36
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()) {
				return $doubleColorBall;
			}
			
			//45
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//46
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()) {
				return $doubleColorBall;
			}
			
			//56
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFourthBall()) {
				return $doubleColorBall;
			}
		} else if(5 == $size) {
			//1
			if($redBalls[0] == $doubleColorBall->getSecondBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()
					&& $redBalls[4] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//2
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getThirdBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()
					&& $redBalls[4] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//3
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getFourthBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()
					&& $redBalls[4] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//4
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFifthBall()
					&& $redBalls[4] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//5
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFourthBall()
					&& $redBalls[4] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
			
			//6
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFourthBall()
					&& $redBalls[4] == $doubleColorBall->getFifthBall()) {
				return $doubleColorBall;
			}
		} else if(6 == $size) {
			//1~6
			if($redBalls[0] == $doubleColorBall->getFirstBall()
					&& $redBalls[1] == $doubleColorBall->getSecondBall()
					&& $redBalls[2] == $doubleColorBall->getThirdBall()
					&& $redBalls[3] == $doubleColorBall->getFourthBall()
					&& $redBalls[4] == $doubleColorBall->getFifthBall()
					&& $redBalls[5] == $doubleColorBall->getSixthBall()) {
				return $doubleColorBall;
			}
		}
		return $notFind;
	}
}
?>