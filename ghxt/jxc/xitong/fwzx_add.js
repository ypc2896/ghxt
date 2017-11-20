	/**
	 * 初始化验证
	 */
	function initValidator(){
		var validation = {
				aab001:{
				independentEnabled:true,
				independentValidate:function(){
				var msg="";
				var aab001 = $("#aab001").val();
				if(aab001.length==0)
					msg="error,服务中心编号不能为空！";
				return "idependent["+msg+"]";
		      }
		     },
				aab004 : {
					independentEnabled:true,
					independentValidate:function(){
						var msg="";
						var aab004 = $("#aab004").val();
						if(aab004.length == 0)
							msg="error,服务中心名称不能为空";
						return "idependent["+msg+"]"; 
					}
				},
				aab008: {
					independentEnabled:true,
					independentValidate:function(){
						var msg="";
						var aab008 = $("#aab008").val();
						if(aab008.length == 0)
							msg="error,会员人数，不能为空";
						else if( !/^[0-9]\d*$/.exec(aab008) )
							msg="error,会员人数必须整数";
						return "idependent["+msg+"]"; 
					}
				}
		};
		
		
		var formConfig = {
							ajaxEnabled : false,
							ajaxFunc : function() {
								saveForm();
							}
				          };
         $("#aab001").formValidator(validation.aab001);
         $("#aab004").formValidator(validation.aab004);
         
		 $("#form1").formConfig(formConfig);
		 $.formValidator.ajaxBind($("#form1"),"#save"); 
	};
	
     $(document).ready(function( ){
     	initValidator();
     	generateSelectItems("aab050", "aab050");
     	//var d = new Date();
     	//var year = d.getFullYear();
		//$("#aae001").attr("value",year-1);
		//$("#aae041").attr("value",year + "01");
		//$("#aae042").attr("value",year + "12");
     });
    
    /**
    *保存
    **/
     function saveForm(){
    	 var param = CRUD.getValues("#form1");    
      	 var postData = $.toJSON(param);
     	 jBox.tip("正在保数据","loading");
     	$.ajax({
             url: "addFwzx.action",
             // 数据发送方式
             type: "post",
             // 接受数据格式
             dataType : "json",
             // 要传递的数据
             data : postData,
             // 回调函数，接受服务器端返回给客户端的值，即result值
             
     		 success:function(msg)
              {   
     		   if (msg.message == "ok") {
     			  var submit = function (v, h, f) {
  				    if (v == true){
  				    	window.location.href='./fwzx_add.html';
  				    	//window.location.reload();
  				    }else{
  				    	//$.jBox.info("数据已经保存，请尽快进行第二步数据上传社保，提交相应的业务数据。", "提示");
  				    	// $.jBox.info("保存成功", "提示");
  				    	 $("#save").attr("disabled","disabled");
  				    	
  				    }
  				    return true;
  				};
  			// 自定义按钮
  				$.jBox.confirm("保存成功,是否继续新增?", "提示", submit, { buttons: { '是': true, '否': false} });
     			   
     			}else {
     			  $.jBox.info(msg.message, "提示");
     					
     			}	   	
     	       },
     	       error:function error(e) {
     				$.jBox.info("系统错误，请联系管理员", "提示");
     	       }
            	     
              })
              jBox.closeTip();
	  
     }
     
   



