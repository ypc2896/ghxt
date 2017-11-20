 
 
 //左侧用户类别树数据
                var root=new Ext.tree.AsyncTreeNode({  
                    id:'0',  
                    text:"所有用户",  
                    leaf:false, 
					expanded: true, 
                    children:[  
                     {id:'1',text:'1_公文管理',leaf:true },
					 {id:'2',text:'2_公文拟办',leaf:true},
					 {id:'3',text:'3_公文批示',leaf:true},
					 {id:'4',text:'4_公文阅示',leaf:true},
					 {id:'5',text:'5_公文阅处',leaf:true},
					 {id:'6',text:'6_公文办理',leaf:true}  
                    ]  
                });  		
	//左侧用户类别树
	var userlbTree = new Ext.tree.TreePanel({
		title:"用户岗位分类",
		region : "west",
		width : 180,
        minSize: 150,
        maxSize: 300,
        iconCls:'menu-53',
        split : true,
		useArrows: true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: true,
        rootVisible:true,//是否显示根节点
        frame:true,
        root:root,
        loader: new Ext.tree.TreeLoader(),
        listeners:{
        	click:function(n){
        		v_gangwei = n.id;
        		v_lbname = n.text;
        		
        		//设置删除按钮状态
        		if(!n.leaf||v_gangwei=="0"){
        			//tree.buttons[1].setDisabled(true);
        		}else{
        			//tree.buttons[1].setDisabled(false);
        		}
        		
        		//更新商品数据
        		userstore.load({params:{start:0, limit:100,gangwei:v_gangwei}});
        	}
        }
	});

	
	
   



   var UuserObj = [
		{ name:'userid', type:'string'},
		{ name:'logincode', type:'string'},
		{ name:'password', type:'string'},
		{ name:'username', type:'string'},
		{ name:'roleid', type:'int'},
		{ name:'rolename', type:'string'},
		{ name:'state', type:'int'},
		{ name:'bz', type:'string'},
		{ name:'lbid', type:'int'},
		{ name:'lbname', type:'string'},
		{ name:'jgid', type:'string'},
		
		{ name:'baiwei', type:'string'},		
		{ name:'zhiwu', type:'string'},		
		{ name:'zhengshuid', type:'string'},	
		{ name:'gangwei', type:'string'}	
	];
	
	//商品数据
	var userstore = new Ext.data.JsonStore({
	    url: 'user_findPageUser.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: UuserObj,
	    listeners:{beforeload:function(a){a.baseParams={start:0, limit:100};}}
	});
	
	//商品列表
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var userxxGrid = new Ext.grid.GridPanel({
        store: userstore,
        
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    sm,//添加复选框
			    {header: '用户姓名', width: 80,sortable:true,align:'center', dataIndex: 'username'},
			    {header: '岗位', width: 80,sortable:true,align:'center', dataIndex: 'gangwei',
							 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '1': str='公文管理';  break;
				                 	case '2': str='公文拟办';  break;
				                 	case '3': str='公文批示';  break;
				                 	case '4': str='公文阅示';  break;
				                 	case '5': str='公文阅处';  break;
				                 	case '6': str='公文办理';  break;
				                 	}
			                 		return str;            
							 }
				},
			    //{header: '分类ID', width: 80,sortable:true, align:'center',dataIndex: 'lbid'},
	            //{header: '机构名称', width: 80,sortable:true, align:'center',dataIndex: 'lbname'},
	            {header: '部门名称', width: 80,sortable:true, align:'center',dataIndex: 'jgid',
							 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '990000': str='局领导';  break;
				                 	case '990001': str='办公室';  break;
				                 	case '990002': str='综合业务处';  break;
				                 	case '990003': str='登记征缴处';  break;
				                 	case '990004': str='城镇职工处';  break;
				                 	case '990005': str='城乡居民处';  break;
				                 	case '990006': str='待遇发放处';  break;
				                 	case '990007': str='基金管理处';  break;
				                 	case '990008': str='审计稽核处';  break;
				                 	case '990009': str='信息统计处';  break;
				                 	case '990010': str='其他部门';  break;
				                 	}
			                 return str;            
							 }
	            },
	            //{header: '登录账号', width: 80,sortable:true,align:'center', dataIndex: 'logincode'},

	            
	            {header: '职务', width: 80,sortable:true, align:'center',dataIndex: 'zhiwu',
							 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '990000': str='局长';  break;
				                 	case '990001': str='副局长';  break;
				                 	case '990002': str='副书记';  break;
				                 	case '990003': str='主任';  break;
				                 	case '990004': str='副主任';  break;
				                 	case '990005': str='处长';  break;
				                 	case '990006': str='副处长';  break;
				                 	case '990007': str='调研员';  break;
				                 	case '990008': str='副调研员';  break;
				                 	case '990009': str='科长';  break;
				                 	case '990010': str='副科长';  break;
				                 	case '990011': str='主任科员';  break;
				                 	case '990012': str='副主任科员';  break;
				                 	case '990013': str='科员';  break;
				                 	case '990099': str='其他';  break;
				                 	}
			                 return str;            
							 }	            
	            },
	            {header: '排位', width: 80,sortable:true, align:'center',dataIndex: 'baiwei'}
	            //{id:'userbz',header: '用户说明',sortable:true, align:'center',dataIndex: 'bz'}
	            ]
       }),
        sm:sm,//复选框名称
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		frame:true,
		region:'center',
		title:'用户信息',
        iconCls:'menu-62',
            tbar:[{
        	text:"发送公文",
        	iconCls:"btn-add",
        	handler: function(){
        		
        		
        		
 	 Ext.each(gwselect,function(){      //each,	gwselecty遍历所选择的公文ID  
 	 	//alert(this.get("dwbh00"));
 	 	select_gwid=this.get("dwbh00");
 	 			
        		//选择多行时 返回为数组。
        		var userrecord= userxxGrid.getSelectionModel().getSelections(); 
				if(userrecord.length==0){
                Ext.Msg.alert("信息提示","请选择信息");
				}else{

					

			       			
					
            	    Ext.each(userrecord,function(){
					    //alert(this.get("userid"));
								//Ext.MessageBox.confirm('发送公文提示', '是否要发送公文？', function(c) {
								  // if(c=='yes'){
								   	Ext.Ajax.request({
										url : 'gwyc_saveOrUpdateGwyc.do',
										params:{ yc001:'',yc002:select_gwid,yc003:this.get("jgid"),yc004:this.get("userid")},
									    success : function(action) {
												//Ext.Msg.alert('信息提示',action.result.message);

										},
										failure : function(action) {
												if(action.result.errors){
													Ext.Msg.alert('信息提示',action.result.errors);
												}else{
													Ext.Msg.alert('信息提示','连接失败');
												}
										},
										waitTitle : '提交',
										waitMsg : '正在保存数据，稍后...'							   	
								   	});

								    //}
								//});					    
					});  //each循环体结束
					
				
					
					
					
					
					
				}//else结束
				
		});//each,	gwselecty遍历所选择的公文ID		
	    Ext.MessageBox.confirm('发送公文提示', '发送公文成功，是否关闭？', function(c) {
	    				if(c=='yes'){
	    				bsspWindow.hide();
	    				}else{
	    				//bsspWindow.hidden();
	    				
	    				}
	    	
	    });		
				
        	}
        }]
  
             
                     
    });                  

 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**
 * 
 	
	
	
	var SpxxObj = [
		{ name:"spid", type:"string"},
		{ name:"spname", type:"string"},
		{ name:"tiaoma", type:"string"},
		{ name:"xinghao", type:"string"},
		{ name:"dw", type:"string"},
		{ name:"jhprice", type:"double"},
		{ name:"chprice", type:"double"},
		{ name:"scjj", type:"double"},
		{ name:"kcsl", type:"int"},
		{ name:"minnum", type:"string"},
		{ name:"csname", type:"string"},
		{ name:"bz", type:"string"},
		{ name:"lbid", type:"int"},
		{ name:"lbname", type:"string"}
	];
	
	//商品数据
	var spxxStore = new Ext.data.JsonStore({
	    url: "spxx_findPageSpxx.do",
	    root: "root",
	    totalProperty: "total",
	    fields: SpxxObj,
	    listeners:{beforeload:function(a){a.baseParams={start:0, limit:20};}}
	});
	
	//右侧商品列表
    var spxxGrid = new Ext.grid.GridPanel({
        store: spxxStore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[
				{header: "商品编号", width: 85, sortable:true, dataIndex: "spid"},
	            {header: "商品名称", width: 130, sortable:true, dataIndex: "spname"},
	            {header: "商品型号", width: 80, sortable:true, dataIndex: "xinghao"},
	            {header: "单位", width: 50, sortable:true, dataIndex: "dw"},
	            {header: "上次进价", width: 60, sortable:true, align:"right", renderer:zhMoney, dataIndex: "scjj"},
	            {header: "成本均价", width: 60, sortable:true, align:"right", renderer:zhMoney, dataIndex: "jhprice"},
	            {header: "库存数量", width: 60, sortable:true, align:"right", dataIndex: "kcsl"}]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		frame:true,
		region:"center",
		title:"用户信息",
        iconCls:'menu-51',
        tbar:[{
        	text:"选择",
        	iconCls:"btn-add",
        	handler: function(){
        		var record= spxxGrid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert("信息提示","请选择商品");
				}else{

				}
        	}
        }]
    });
*/	
	
	//备选商品窗口
	var bsspWindow = new Ext.Window({
		title:"用户选择框",
		width:760,
		height:500,
		layout:"border",
		closeAction:"hide",
		iconCls:'menu-51',
 		items:[userlbTree,userxxGrid]
		});
	 

