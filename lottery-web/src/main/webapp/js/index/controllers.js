'use strict';

var chooseBallApp = angular.module('chooseBallApp', [])
	.factory('$ball', function() {
			return {
				init: function(total) {
					var ballArray = new Array();
					for (var i = 1; i <= total; i++) {
						if (i < 10) {
							ballArray.push({
								'value' : '0' + i
							});
						} else {
							ballArray.push({
								'value' : i
							});
						}
					}
					return ballArray;
				}
			};
		}
	);

/**
 * initialize balls
 */
chooseBallApp.controller('ballCtrl', ['$scope', '$http', '$ball', '$location', function($scope, $http, $ball, $location) {
	var redBallChosenInt = 0;	//count the red balls which have been chosen
	$scope.resultBonus = [];
	$scope.resultMsg = [];
	
	/*
	 * List of red and blue balls
	 */
	$scope.redBall = $ball.init(33);
	$scope.blueBall = $ball.init(16);

	/*
	 * bind onClick to ball image
	 */
	// $scope.redChoosed = [];
	$scope.chooseRedBall = function(ball) {
		if (!ball.isChosen) {
			if (redBallChosenInt >= 7) {
				$scope.validMsg = "Red ball can be chosen 7.";
				$scope.resultMsg = [];
			} else {
				ball.isChosen = true;
				redBallChosenInt++;
			}
			// $scope.redChoosed.push(ball.value);
		} else {
			ball.isChosen = false;
			redBallChosenInt--;
		}
	};

	$scope.blueChosen = [];
	$scope.chooseBlueBall = function(ball) {
		if (!ball.isChosen) {
			if ($scope.blueChosen.length > 0) {
				$scope.validMsg = "Blue ball can be chosen only one.";
				$scope.resultMsg = [];
			} else {
				ball.isChosen = true;
				$scope.blueChosen.push(ball.value);
			}
		} else {
			ball.isChosen = false;
			$scope.blueChosen.pop();
		}
	};

	/*
	 * bind reset button
	 */
	$scope.reset = function() {
		redBallChosenInt = 0;
		$scope.resultBonus = [];
		$scope.resultMsg = [];
		$scope.validMsg = "";
		for (var i = 0; i < $scope.redBall.length; i++) {
			$scope.redBall[i].isChosen = false;
		}

		$scope.blueChosen = [];
		for (var i = 0; i < $scope.blueBall.length; i++) {
			$scope.blueBall[i].isChosen = false;
		}
	};
	
	/*
	 * bind query button
	 */
	$scope.query = function() {
		//clear query or update result message
		$scope.resultMsg = [];
		//clear valid message
		$scope.validMsg = "";
		if(redBallChosenInt < 4) {
			$scope.validMsg = "Please choose 4 red balls at least.";
			return;
		}
		
		//add red ball which has been chosen
		$scope.chosenBall = [];
		for(var i = 0; i < $scope.redBall.length; i++) {
			var ball = $scope.redBall[i];
			if(ball.isChosen) {
				$scope.chosenBall.push(ball.value);
			}
		}
		//if number of red balls is less than 6, fill empty with others.
		while($scope.chosenBall.length < 6) {
			$scope.chosenBall.push("");
		}
		// add blue ball
		if($scope.blueChosen.length > 0) {
			$scope.chosenBall.push($scope.blueChosen[0]);
		}
		
		//use servlet
		$http({
			method: "POST",
			url: "doubleColorBall.do",
			params: $scope.chosenBall
		}).success(function(data) {
			$scope.resultBonus = [];
			
			if(data.indexOf("|") != -1) {
				var bonus = data.split("|");
				for(var i = 0; i < bonus.length; i++) {
					if(bonus[i].indexOf(",") != -1) {
						$scope.bonus = [];
						var single = bonus[i].split(",");
						for(var j = 0; j < single.length; j++) {
							if(j == 6) {
								$scope.bonus.push({'value': single[j], 'blueBall': true});
								//$scope.bonus.push({'value': "2017-1-1"});
							} else {
								$scope.bonus.push({'value': single[j], 'redBall': true});
							}
						}
						$scope.resultBonus.push($scope.bonus);
					}
				}
			} else {
				$scope.resultMsg[0] = data;
			}
		});
	};
	
	$scope.update = function(){
		//clear valid message
		$scope.validMsg = "";
		//Get JSONP data
		$scope.reqData = [];
		//http://www.opencai.net/apifree/
		$http.jsonp("http://f.apiplus.cn/ssq-10.json?callback=JSON_CALLBACK")
		.success(function(data){
			$scope.reqData = data;
			$scope.transfer();
		}).error(function(data){
			alert("fail: " + data);
		});
	};
	
	//Transfer data to action
	$scope.transfer = function(){
		$http({
			method : "POST",
			url : "updateData.do",
			params : $scope.reqData.data
		}).success(function(data){
			var records = data.split("<br/>");
			for(var i = 0; i < records.length; i++) {
				$scope.resultMsg[i] = records[i];
			}
		}).error(function(data){
			$scope.resultMsg = data;
		});
	};
}]);