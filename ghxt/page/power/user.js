/*!
 * 项目管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var v_lbid="0", v_lbname="", v_start=0, v_limit=20;
	
	var UuserObj = [
		{ name:'userid', type:'int'},
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
		
		{ name:'gangwei', type:'string'},		
		{ name:'dengji', type:'string'},		
		{ name:'shangjiid', type:'int'},	
		{ name:'xianzhong', type:'string'},		
		{ name:'dengip', type:'string'},
		{ name:'dengdate',type:'date', mapping : 'dengdate.time', dateFormat : 'time' }  
        //{ name:'dengdate', type:'date', mapping : 'dengdate.time', dateFormat : 'time' }		
 
		
		
	];
	
	//商品数据
	var store = new Ext.data.JsonStore({
	    url: 'user_findPageUser.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: UuserObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}
	});
	
	//商品列表
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var grid = new Ext.grid.GridPanel({
        store: store,
        
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    sm,//添加复选框
			    {header: '分类ID', width: 80,sortable:true, align:'center',dataIndex: 'lbid'},
	            {header: '机构名称', width: 80,sortable:true, align:'center',dataIndex: 'lbname'},
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
	            {header: '登录账号', width: 80,sortable:true,align:'center', dataIndex: 'logincode'},
	            {header: '用户姓名', width: 80,sortable:true,align:'center', dataIndex: 'username'},
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
	            {header: '角色', width: 80,sortable:true, align:'center',dataIndex: 'rolename'},
	            
	            {header: '用户状态', width: 80,sortable:true, align:'center',dataIndex: 'state',
						 renderer: function(v){
				                 	var str1=null;
				                 	switch(v){ 
				                 	case 0: str1='正常';  break;
				                 	case 1: str1='停用';  break;
				                 	case 2: str1='注销';  break;
				                 	}
			                 return str1;            
							 }	            
	            },
	            {header: '排位', width: 80,sortable:true, align:'center',dataIndex: 'baiwei'},
	            
	            {header: '证书编号', width: 80,sortable:true, align:'center',dataIndex: 'zhengshuid'},
	            
	            { header:'最后登录',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'dengdate'}, 
	            {id:'userbz',header: '用户说明',sortable:true, align:'center',dataIndex: 'bz'}]
       }),
        sm:sm,//复选框名称
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		frame:true,
		region:'center',
		title:'用户信息',
        iconCls:'menu-62',
        
        //顶部工具栏
        tbar:[{
	             text:'增加',
	             iconCls:'btn-add',
	             handler:function(){
	        		uWindow.show();
	        		//uForm.getForm().reset();
	        		//uForm.getForm().findField("logincode").setDisabled(false);	             
	             
	             }
             },'-',{
	             text:'修改',
	             iconCls:'btn-edit',
	             handler:function(){
		        		var record= grid.getSelectionModel().getSelected(); 
						if(!record){
		                	Ext.Msg.alert('信息提示','请选择要修改的信息');
						}else{
			        		uWindow.show();
							uForm.getForm().loadRecord(record);
							uForm.getForm().findField("addupdate").setValue("update");
							if(record.get("roleid")==0)
								uForm.getForm().findField("roleid").setValue();
						}	             
	             
	             }
             },'-',{
            text:"删除",
        	iconCls:'btn-remove',
        	tooltip:'<s:text name=“查看信息"/>进行勾选，后进行批量删除，谨慎使用。</>' ,
            handler: function(){
            	//选择多行时 返回为数组。
   				 var rs= grid.getSelectionModel().getSelections();
	
            	      Ext.each(rs,function(){
           	 	            alert(this.get("userid"));
 								if(this.get("logincode") == "admin"){
									Ext.Msg.alert('信息提示','admin用户不能被删除');
									return;
								}    				 			
								Ext.Ajax.request({
									url : "user_deleteUser.do",
									params:{ userid : this.get("userid") },
									success : function() {
										store.reload();
									}
								});
			 	     });
             }
          },'-',{
          	     text:"重置密码",
          	     iconCls:'btn-edit2',
          	     handler:function(){
            			//选择多行时 返回为数组。
   					  var rs= grid.getSelectionModel().getSelections();
			                 if(rs.length>0){
			                 Ext.each(rs,function(){
		           	 	           // alert(this.get("userid"));
		           	 	            var aaa=this.get("username")
		    				 			
										Ext.Ajax.request({
											url : "user_updatemimaUser.do",
											params:{ userid : this.get("userid") },
											success : function() {
												store.reload();
												Ext.Msg.alert("提示信息","用户"+aaa+"的密码重置成功，密码为123456。");
											}
										});
					 	     });   
	                 
	                 }else{
	                		 Ext.Msg.alert('信息提示','请选择要修改的信息');
	                 
	                 }
	                 
	                 
            	             	     	
          	     	
          	     }
          
          	
          	
          	
          	
          }],
             
             
             	  /**单个删除
             		{
	             text:'删除',
	             iconCls:'btn-remove',
	             
	           
	             handler:function(){
			        		var record= grid.getSelectionModel().getSelected();
							if(!record){
			                	Ext.Msg.alert('信息提示','请选择要删除的数据');  
							}else{
								if(record.get("logincode") == "admin"){
									Ext.Msg.alert('信息提示','admin用户不能被删除');
									return;
								}
								Ext.MessageBox.confirm('删除提示', '是否删除该用户？', function(c) {
								   if(c=='yes'){
								   		Ext.Ajax.request({
								   			url : "user_deleteUser.do",
								   			params:{ userid : record.get("userid") },
								   			success : function() {
								   				store.reload();
								   			}
								   		});
								    }
								});
							}	             
				             
	             }
	             
	             
	             
             }],*/
             
             
             
 
          
          //底部分页栏
          bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })           
        
        

        
 
    });
    
 
	
	var submitXmxx = function(next){
		if (addForm.getForm().isValid()) {
			addForm.getForm().submit({
				url : 'user_saveOrUpdateUser.do',
				success : function(form, action) {
				    if(next){
				    	addForm.getForm().reset();
			       		addForm.getForm().findField("addupdate").setValue("add");
			       		addForm.getForm().findField("lbid").setValue(v_lbid);
						addForm.getForm().findField("lbname").setValue(v_lbname);
						getCode();
				    }else{
						Ext.Msg.alert('信息提示',action.result.message);
						addWindow.hide();
				    }
					store.reload({params:{lbid:v_lbid}});
				},
				failure : function(form, action) {
					if(action.result.errors){
						Ext.Msg.alert('信息提示',action.result.errors);
					}else{
						Ext.Msg.alert('信息提示','连接失败');
					}
				},
				waitTitle : '提交',
				waitMsg : '正在保存数据，稍后...'
			});
		}
	}
	
	//商品类别树
	var tree = new Ext.tree.TreePanel({
		title:'机构类别',
		region : 'west',
		width : 180,
        minSize: 150,
        maxSize: 300,
        split : true,
        iconCls:'menu-53',
		useArrows: true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: true,
        frame:true,
        dataUrl: 'xmlb_findXmlbTree.do',
        root: {
            nodeType: 'async',
            text: '山西省',
            draggable: false,
            id: '0'
        },
        buttonAlign : 'left',
        buttons:[{
        	text:'新增类别',
        	handler:function(){
        		xmlbWindow.show();
        		xmlbForm.getForm().reset();
        	}
        },{
        	text:'删除类别',
        	disabled:true,
        	handler:function(){
        		if(v_lbid){
	        		var node = tree.getNodeById(v_lbid);
	        		var pnode = node.parentNode;
					Ext.MessageBox.confirm('删除提示', '是否删除"'+node.text+'"类别？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "xmlb_deleteXmlb.do",
					   			params:{ lbid : v_lbid },
					   			success : function(o) {
					   				if(o.responseText=="false"){
					   					Ext.Msg.alert("信息提示","该类别有商品信息不能删除");
					   				}else{
						   				v_lbid = "0";     //设为默认节点
						   				v_lbname = "";
						   				tree.buttons[1].setDisabled(true);  //禁用删除按钮
						   				pnode.removeChild(node);	//删除节点
						   				if(pnode.childNodes.length==0){	//如果无子节点则修改属性
						   					pnode.leaf = true;
						   				}
						   				var wtree = xmlbTreeWindow.get(0);
						   				wtree.getLoader().load(wtree.root); //更新类别编辑窗口的树
					   				}
					   			}
					   		});
					    }
					});
				}
        	}
        }],
        listeners:{
        	click:function(n){
        		v_lbid = n.id;
        		v_lbname = n.text;
        		//设置删除按钮状态
        		if(!n.leaf||v_lbid=="0"){
        			tree.buttons[1].setDisabled(true);
        		}else{
        			tree.buttons[1].setDisabled(false);
        		}
        		//更新商品数据
        		store.load({params:{start:v_start, limit:v_limit,lbid:v_lbid}});
        	}
        }
	});
	
	//展开节点
	tree.getRootNode().expand();
	
	//商品类别表单
	var xmlbForm = new Ext.FormPanel({
		layout : 'form',
		baseCls: 'x-plain',
		labelWidth:60,
		border : false,
		padding : 20,
		items:[{
			xtype:'textfield',
			anchor : '100%',
			name:'lbname',
			fieldLabel:'商品类别',
			allowBlank : false,
			maxLength :20
		},{
			xtype : 'hidden',
		    name : 'lbid'
		}]
	});
    
	//增加商品类别窗口
    var xmlbWindow = new Ext.Window({
		title : '增加类别',
		width:250,
		height:140,
		closeAction:'hide',
		modal : true,
		resizable : false,
		layout : 'fit',
		buttonAlign : 'center',
		items : [xmlbForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (xmlbForm.getForm().isValid()) {
					xmlbForm.getForm().submit({
						url : 'xmlb_saveOrUpdateXmlb.do',
						params:{pid : v_lbid},
						success : function(form, action) {
							xmlbWindow.hide();
							var id = action.result.message;
							//创建新节点
							var node = new Ext.tree.TreeNode({
								id:id,
								text:form.findField("lbname").getValue(),
								iconCls:'menu-folder',
								leaf:true
							});
							//修改父节点
							var pnode = tree.getNodeById(v_lbid);
							pnode.appendChild(node);
							pnode.leaf=false;
							pnode.expand();
							//更新类别编辑窗口的树
							var wtree = xmlbTreeWindow.get(0);
						   	wtree.getLoader().load(wtree.root); 
						},
						failure : function(form, action) {
							if(action.result.errors){
								Ext.Msg.alert('信息提示',action.result.errors);
							}else{
								Ext.Msg.alert('信息提示','连接失败');
							}
						},
						waitTitle : '提交',
						waitMsg : '正在保存数据，稍后...'
					});
				}
			}
		}, {
			text : '取消',
			handler : function() {
				xmlbWindow.hide();
			}
		}]
	});
    
	//商品类别树窗口  在新增表单时调用此窗口
    var xmlbTreeWindow = new Ext.Window({
		width:200,
		height:300,
		closeAction:'hide',
		layout : 'fit',
		buttonAlign : 'center',
		items : [{
			xtype:'treepanel',
			useArrows: true,
	        autoScroll:true,
	        enableDD:false,
	        containerScroll: true,
	        dataUrl: 'xmlb_findXmlbTree.do',
	        root: {
	            nodeType: 'async',
	            text: '山西省',
	            draggable: false,
	            id: '0'
	        },
	        listeners:{
	        	click:function(n){
	        		if(n.id=="0"){
	        			xmlbTreeWindow.buttons[0].setDisabled(true);
	        		}else{
		        		xmlbTreeWindow.buttons[0].setDisabled(false);
		        		v_lbid = n.id;
		        		v_lbname = n.text;
	        		}
	        	},
	        	dblclick:function(n){
	        		if(n.id=="0"){
	        			xmlbTreeWindow.buttons[0].setDisabled(true);
	        		}else{
		        		xmlbTreeWindow.buttons[0].setDisabled(false);
		        		v_lbid = n.id;
			        	v_lbname = n.text;
		        		xmlbTreeWindow.hide();
						uForm.getForm().findField("lbid").setValue(v_lbid);
						uForm.getForm().findField("lbname").setValue(v_lbname);
	        		}
	        	}
	        }
		}],
		listeners:{
			beforeshow:function(){
				var xy = Ext.getCmp("lbtext").getPosition();
				xmlbTreeWindow.setPosition(xy[0],xy[1]+25);
			},
        	show:function(){
        		this.items.get(0).getRootNode().expand();
        	}
        },
		buttons : [{
			width:60,
			text : '选择',
			disabled : true,
			handler : function() {
				xmlbTreeWindow.hide();
				uForm.getForm().findField("lbid").setValue(v_lbid);
				uForm.getForm().findField("lbname").setValue(v_lbname);
			}
		}, {
			width:60,
			text : '取消',
			handler : function() {
				xmlbTreeWindow.hide();
				v_lbid = "0";
		        v_lbname = "";
			}
		}]
	});

	
	
	
	//角色列表的数据
   var roleStore = new Ext.data.JsonStore({
	    url: 'role_findRoleType.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields : ["value", "text"]
	});
	roleStore.load();
	//加载角色数据到表单下拉列表。
	
	
	
	
	//创建数据源[数组数据源]
		var combostore=new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[[0,'正常'],[1,'停用'],[2,'注销']]
		});
	//创建数据源[数组数据源]
		var zhiwustore=new Ext.data.ArrayStore({
			 fields:['id','name'],
			   data:[['990000','局长'],
					['990001','副局长'],
					['990002','副书记'],
					['990003','主任'],
					['990004','副主任'],
					['990005','处长'],
					['990006','副处长'],
					['990007','调研员'],
					['990008','副调研员'],
					['990009','科长'],
					['990010','副科长'],
					['990011','主任科员'],
					['990012','副主任科员'],
					['990013','科员'],
					['990098','公文拟办'],//主要用于拟办公文的用户。
					['990099','其他']]
		});		
			//创建数据源[数组数据源]
		var deptStore =new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[['990000','局领导'],
			['990001','办公室'],
			['990002','综合业务处'],
			['990003','登记征缴处'],
			['990004','城镇职工处'],
			['990005','城乡居民处'],
			['990006','待遇发放处'],
			['990007','基金管理处'],
			['990008','审计稽核处'],
			['990009','信息统计处'],
			['990010','其他部门']]
		});
		
			//公文岗位[数组数据源]
		var gangweiStore =new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[['1','1公文管理'],
			['2','2公文拟办'],
			['3','3公文批示'],
			['4','4公文阅示'],
			['5','5公文阅处'],
			['6','6公文办理']]
		});
		
		//行政级别数据源
			var dengjiStore =new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[['1','1省级'],
			['2','2市级'],
			['3','3县区级'],
			['4','4乡镇级'],
			['5','5村社区']]
		});	
		
				//险种数据源
			var xianzhongStore =new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[['0','0社会保险'],
			['1','1企业养老'],
			['2','2机关养老'],
			['3','3居民养老'],
			['4','4职工医疗'],
			['5','5居民医疗'],
			['6','6合作医疗'],
			['7','7工伤保险'],
			['8','8生育保险'],
			['9','9失业保险']
			]
		});	
	/**4444
//创建Combobox
//             var state = new Ext.form.ComboBox({
//             	 hiddenName:'state',
//             	 fieldLabel: '用户状态',
//                 store: combostore,
//                 displayField: 'name',
//                 valueField: 'id',
//                 triggerAction: 'all',
//                 emptyText: '请选择...',
//                 allowBlank: false,
//                 blankText: '请选状态',
//                 editable: false,
//                 mode: 'local'
//            });
	 //Combobox获取值
             //state.on('select', function () {
             	//UuserObj.setState(state.getValue());
                 // alert(state.getValue());//此句为了检查获取的值是否正常，仅为了查看值。
            // })

	*/
	
	var uForm = new Ext.FormPanel({
 
		layout : 'form',
		frame:true,
		//baseCls:'x-plain',
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					layout:'column',
					items:[{
						layout:'form',
						width : 218,
						items:[{
							id:'lbtext',
							width : 145,
							xtype : 'textfield',
							name:'lbname',
							fieldLabel:'机构类别1',
							allowBlank : false,
							maxLength :50,
							enableKeyEvents:true,
							listeners:{
								focus:function(){
									xmlbTreeWindow.show();
								},
								blur:function(){
									addForm.getForm().clearInvalid();
								}
							}
						}]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							xmlbTreeWindow.show();
						}
					}]
				},{
					xtype : 'textfield',
					name:'logincode',
					fieldLabel:'登录账号',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'username',
					fieldLabel:'用户姓名',
					maxLength :20
				},{
							xtype:'combo',
							hiddenName:'jgid',
							fieldLabel:'部门名称',
							mode: 'local',
							value:'990001',
							triggerAction: 'all',
							valueField :'id',
							displayField: 'name',
							emptyText: '请选择',
							allowBlank : false,
							editable : false,
							store : deptStore           //加载角色下拉数据。				
  			   },{
							xtype:'combo',
							hiddenName:'dengji',
							fieldLabel:'行政级别',
							mode: 'local',
							value:'1',
							triggerAction: 'all',
							valueField :'id',
							displayField: 'name',
							emptyText: '请选择',
							allowBlank : false,
							editable : false,
							store : dengjiStore           //加载角色下拉数据。				
  			   },{
							xtype:'combo',
							hiddenName:'xianzhong',
							fieldLabel:'险种',
							mode: 'local',
							value:'1',
							triggerAction: 'all',
							valueField :'id',
							displayField: 'name',
							emptyText: '请选择',
							allowBlank : false,
							editable : false,
							store : xianzhongStore           //加载角色下拉数据。				
  			   }]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					
					
							xtype:'combo',
							hiddenName:'roleid',
							fieldLabel:'角色',
							mode: 'local',
							value:1,
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							emptyText: '请选择',
							allowBlank : false,
							editable : false,
							store : roleStore           //加载角色下拉数据。
				},{
					xtype : 'hidden',
					//inputType:'password',
					name:'password',
					//fieldLabel:'登录密码',
					value:'123456'
					//maxLength :10
				},{
				
				            xtype:'combo',
							hiddenName:'state',
							fieldLabel:'用户状态',
							mode: 'local',
							triggerAction: 'all',//query all
							valueField :'id',    //值的字段名
							displayField: 'name',   //名子要于数据的字段名一致
							emptyText: '请选择',
							value:0,
							allowBlank : false,
							editable : false,
							store : combostore           //加载状态下拉数据。
				
				},{
				
				            xtype:'combo',
							hiddenName:'zhiwu',
							fieldLabel:'职务级别',
							mode: 'local',
							triggerAction: 'all',
							valueField :'id',    //值的字段名
							displayField: 'name',   //名子要于数据的字段名一致
							emptyText: '请选择',
							value:'990013',
							allowBlank : false,
							editable : false,
							store : zhiwustore           //加载状态下拉数据。
				
				},{
				
				            xtype:'combo',
							hiddenName:'gangwei',
							fieldLabel:'公文岗位',
							mode: 'local',
							triggerAction: 'all',
							valueField :'id',    //值的字段名
							displayField: 'name',   //名子要于数据的字段名一致
							emptyText: '请选择',
							value:'5',
							allowBlank : false,
							editable : false,
							store : gangweiStore           //加载状态下拉数据。
				
				},{
					xtype : 'textfield',
					name:'baiwei',
					fieldLabel:'排位',
					maxLength :20				
				
				},{

					 
				    xtype : 'textfield',
					name:'zhengshuid',
					fieldLabel:'证书编号',
					maxLength :50
				
					
					
				}]

			}]
		},{
			xtype:'textarea',
			name:'bz',
			fieldLabel:'用户说明',
			height:100,
			maxLength :200
		},{
			xtype : 'hidden',
		    name : 'lbid'
		},{
			xtype : 'hidden',
			name : 'addupdate'
		},{
				xtype : 'hidden',
			    name : 'userid'       //修改用户时主要接收需要修改用户的ID，然后将iD传到后台，以此判断是新增还是修改。
			},{
				xtype : 'hidden',
			    name : 'shangjiid',
			    value:'1212'//修改用户时主要接收需要修改用户的ID，然后将iD传到后台，以此判断是新增还是修改。
			},{
				xtype : 'hidden',
			    name : 'dengip',
			    value:'127.0.0.1'//修改用户时主要接收需要修改用户的ID，然后将iD传到后台，以此判断是新增还是修改。
			},{
				xtype : 'hidden',
			    name : 'dengdate',
			    value:'2017-9-9'//修改用户时主要接收需要修改用户的ID，然后将iD传到后台，以此判断是新增还是修改。
			}]
	});
	
	
	
/**	
    var uForm = new Ext.FormPanel({
    		layout:'form',
    		baseCls:'x-plain',
    		labelWith:60,
    		border:false,
    		padding:'15 10 0 8',
    		defaults:{
    			anchor:'100%',
    			xtype:'textfield'
    		},
    		items:[{  		id:'lbtext',
							width : 145,
							xtype : 'textfield',
							name:'lbname',
							fieldLabel:'所属类别',
							allowBlank : false,
							maxLength :50,
							enableKeyEvents:true,
							listeners:{
								focus:function(){
									xmlbTreeWindow.show();
								},
								blur:function(){
									addForm.getForm().clearInvalid();
								}
							}
						},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							xmlbTreeWindow.show();
						}
					
				},
					
				{
    			name:'logincode',
    			fieldLabel:'登录账号',
    			maxLength:20,
    			allowBland:false
    		},{
				inputType: 'password',
				name:'password',
				fieldLabel:'密码',
				maxLength :20,
				allowBlank : false
			},{
				name:'username',
				fieldLabel:'用户姓名',
				maxLength :20,
				allowBlank : false
			},{
				xtype:'combo',
				hiddenName:'roleid',
				fieldLabel:'角色',
				mode: 'local',
				triggerAction: 'all',
				valueField :'value',
				displayField: 'text',
				emptyText: '请选择',
				allowBlank : false,
				editable : false,
				store : roleStore           //加载角色下拉数据。
			},{
				xtype:'textarea',
				name:'bz',
				fieldLabel:'用户说明',
				height:80,
				maxLength :100
			},{
				xtype : 'hidden',
			    name : 'userid'
			}]
    
    });

    */
    
    
//增加用户窗体
	    var uWindow = new Ext.Window({
		title : '添加用户',
		width:550,
		height:370,
		closeAction:'hide',
		modal : true,
		border:false,   //边框架粗禁止。
		layout : 'fit',
		buttonAlign : 'center',
		items : [uForm], //先定义表单为uForm,然后在些直接调用即可。
		buttons : [{
			text : '保存',
			handler : function() {
				if (uForm.getForm().isValid()) {
					uForm.getForm().submit({
						url : 'user_saveOrUpdateUser.do',
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							uWindow.hide();
							store.reload();
						},
						failure : function(form, action) {
							if(action.result.errors){
								Ext.Msg.alert('信息提示',action.result.errors);
							}else{
								Ext.Msg.alert('信息提示','连接失败');
							}
						},
						waitTitle : '提交',
						waitMsg : '正在保存数据，稍后...'
					});
				}
			}
		}, {
			text : '取消',
			handler : function() {
				uWindow.hide();
			}
		}]
	});

	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			items:[tree,grid]
		}]
	});

});