<?php
include 'DoubleColorBall.php';
/**
 * Created by PhpStorm.
 * User: dxinfor
 * Date: 4/17/2017
 * Time: 11:48 AM
 */
class LoadRecordService {
	private $history = array ();
	private $count = 0;
	public function getHistory() {
		if ($this->count == 0) {
			//print($this->count);
			$dir = "resources";
			// Open a known directory, and proceed to read its contents
			if (is_dir ( $dir )) {
				if ($dh = opendir ( $dir )) {
					while ( ($file = readdir ( $dh )) !== false ) {
						if ($file != "." && $file != "..") {
							// echo "filename: $file : filetype: " . filetype ( $dir . $file ) . "\n";
							$this->readHistroyFromTxt ( $dir . "/" . $file );
						}
					}
					closedir ( $dh );
					// echo $this->count;
					return $this->history;
				}
			}
		}
	}
	private function readHistroyFromTxt($file_path) {
		if (file_exists ( $file_path )) {
			$file_arr = file ( $file_path );
			$regex = '/(\d{7})\s{4}(\d{2}),(\d{2}),(\d{2}),(\d{2}),(\d{2}),(\d{2})\|(\d{2})\s{4}(\d{4}-\d{2}-\d{2})/';
			$matches = array ();
			for($i = 0; $i < count ( $file_arr ); $i ++) { 
				if (preg_match ( $regex, $file_arr [$i], $matches )) {
					// var_dump ( $matches );
					// echo "<br>";
					$index = 1;
					$item = new DoubleColorBall ();
					$item->setIssueNumber ( $matches [$index] );
					$item->setFirstBall ( $matches [++ $index] );
					$item->setSecondBall ( $matches [++ $index] );
					$item->setThirdBall ( $matches [++ $index] );
					$item->setFourthBall ( $matches [++ $index] );
					$item->setFifthBall ( $matches [++ $index] );
					$item->setSixthBall ( $matches [++ $index] );
					$item->setBlueBall ( $matches [++ $index] );
					$item->setIssueDateStr ( $matches [++ $index] );
					$this->history [$this->count ++] = $item;
				}
			}
			// echo $this->count."<br>";
		}
	}
}

// $testService = new LoadRecordService();
// $testService->readHistroyFromTxt("resources/2013.txt");
// $testService->getHistory();
?>