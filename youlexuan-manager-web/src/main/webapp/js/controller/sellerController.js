 //商户控制层 
app.controller('sellerController' ,function($scope,$controller   ,sellerService1){
	
	$controller('baseController',{$scope:$scope});//继承


	$scope.statusArr = ['待审核','已审核','审核未通过','关闭 '];  // 0   1  2  3

	$scope.statusMap = {};

	$scope.updateStatus = function(sellerId,status){

        $scope.statusMap.sellerId = sellerId;
        $scope.statusMap.status = status;

		sellerService1.updateStatus($scope.statusMap).success(function (resp) {
			if(resp.success){
				$scope.reloadList();
			}
        });
	}
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		sellerService1.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		sellerService1.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		sellerService1.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=sellerService1.update( $scope.entity ); //修改  
		}else{
			serviceObject=sellerService1.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		sellerService1.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象

	
	//搜索
	$scope.search=function(page,rows){
		if($scope.selectIds!=null && $scope.selectIds.length>0){
			$scope.searchEntity.status = $scope.selectIds.toString();//1,2,3
		}
		sellerService1.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);
	}
    
});	