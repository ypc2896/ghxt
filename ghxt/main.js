
	/**
	*	获取当前时间
	*/
	function getDate1(){
		var str;
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var date = d.getDate();
		str = year + "年" + month + "月" + date + "日";
		return str;
	}
	
	/**
	*加载登录用户信息
	*/
	function loadLoginUserInfo(){
		var username="欢迎您 ";
		CRUD.sendParameter(
		"loadLoginUserInfo",
		null,
		function success(msg){
			if(msg.type == "data"){
				username = username  + msg.datas[0].aab004;
				
			}else{ 
				$.jBox.info("获取登录用户信息失败!","提示");
			}
		},
		function error(e){
			$.jBox.info(e);
		},
		null,
		null,
		false
		);
		return  username;
	
	}
	/**
	* 监听菜单点击事件
	*/
	function  menuClickEvent(){
		$("#shortcut_menu .menu span").each(function(){
			$(this).click(function(){
				$(this).attr("class","on");
				$(this).siblings().removeAttr("class");
			});
		});
	}
	/**
		初始化头部信息
	*/
	function initHeadInfo(){
		var date = getDate1();
		var username = loadLoginUserInfo();
		$("#userInfo").html(username);
	
	}
	/**
	* 退出
	*/
	function logout(){
		CRUD.sendParameter(
		"logout",
		null,
		function success(msg){
			if(msg.type == "data"){
				parent.window.location.href="loginqy.html";
			}else{
				$.jBox.info(msg.message,"提示");
			}
		},
		function error(e){
			$.jBox(e,"提示");
		},
		"正在退出"
		);
	}
/**
	初始化顶部菜单
*/
function initTopMenu(){
	 $.ajax({
         url : "role_findRoleTopMenu.do",
         dataType : "json",
         data : "",
         success : function(data){
		 
           if(data.success){ //删除成功
        	  var datas = data.data.menutop;
			  var menu =""; 
			  for(var i=0;i<datas.length;i++){
				     menu += "<li class=\"nav-item\"><div class=\""+datas[i].menuurl+"\">"+datas[i].menuname+"</div></li>";
			   }
			$("#J_Nav").html(menu);
			initleftmenu();
           }else{ //删除失败
             BUI.Message.Alert('功能菜单初始化失败！');
           }
         }
     });  
}

function initleftmenu(){
	 $.ajax({
         url : "role_findRoleFunction.do",
         dataType : "json",
         data : "",
         success : function(data){
           if(data.success){ //删除成功
        	  var datas = data.data.menufunction;
        	  BUI.use('common/main',function(){
        		  new PageUtil.MainPage({
        		        modulesConfig : datas
        		      });
        	  });
			 
           }else{ //删除失败
             BUI.Message.Alert('功能菜单初始化失败！');
           }
         }
     });  
}
	/**
	* 初始化
	*/
	$(document).ready(function(){
		
		///initHeadInfo();
		
		initTopMenu();
		 
		//menuClickEvent();
		
	});