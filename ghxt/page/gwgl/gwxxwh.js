/*!
 * 公文信息维护
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var v_lbid="0", v_lbname="", v_start=0, v_limit=20;
	//查询表单
	
		var date = new Date();
	     date.setMonth(date.getMonth()-12);  //查询日期取前一个月
		var searchForm = new Ext.FormPanel({
		region:'north',
		height: 70,
		border : false,
		layout : 'form',
		padding : '5 20 0 20',
		items:[{
			id:"fieldset",
			xtype:"fieldset",
			title:"查询设置",
			padding:'0 20 0 15',//上右下左的顺序
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:'right',
					layout:"form"
				},
				items:[{
					width:60,
					height:22,
					style:'padding-top:5',
					xtype:'tbtext',
					text:'查询日期:'
				},{
					width:100,
					xtype:"datefield",
					name:'startdate',
					format:'Y-m-d',
					allowBlank : false,
					value:date
				},{
					width:28,
					height:22,
					style:'padding:0 5 0 5',
					xtype:'label',
					text:'至'
				},{
					width:100,
					xtype:"datefield",
					name:'enddate',
					format:'Y-m-d',
					allowBlank : false,
					value:new Date()
				},{
					width:30
				},{
					width:250,
					items:[{
						labelWidth:100,
						xtype:"textfield",
						name:'search',
						fieldLabel:"编号/文号/标题",
						anchor:"90%"
					}]
				},{
					width:180,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'gw014',
							fieldLabel:'收发类别',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['','全部'],['0','收文'],['1','发文']]
							}),
							width:80
					}]
				},{
					width:100,
					items:[{
						width:75,
						xtype:"button",
						text:'查询',
						tooltip:'<s:text name=“查看信息"/>关键字指文件标题、文号、编号中包含的字符</>' ,
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var params = f.getValues();
								params.start=0;
								params.limit =20;
								store.load({params:params});
							}
						}
					}]
				}]
			}]
		}]
	});

	
	

	var GwxxObj = [
		{ name:'dwbh00', type:'string'},
//		{ name:'dwmc00', type:'string'},
//		{ name:'dhua00', type:'string'},
//		{ name:'dizhi0', type:'string'},
//		{ name:'yzbm00', type:'string'},
//		{ name:'lxr000', type:'string'},
		{ name:'bz0000', type:'string'},
		{ name:'lbid', type:'int'},
		{ name:'lbname', type:'string'},
		{ name:'userid',type:'int'},  
		{ name:'username',type:'string'},
		{ name:'gw001',type:'string'},   
		{ name:'gw002',type:'string'},   
		{ name:'gw003',type:'string'},   
		{ name:'gw004',type:'string'},   
		{ name:'gw005',type:'string'},   
		{ name:'gw006',type:'date', mapping : 'gw006.time', dateFormat : 'time' },   
		{ name:'gw007',type:'string'},   
		{ name:'gw008',type:'string'},   
		{ name:'gw009',type:'string'},   
		{ name:'gw010',type:'string'},   
		{ name:'gw011',type:'date', mapping : 'gw011.time', dateFormat : 'time' },   
		{ name:'gw012',type:'date', mapping : 'gw012.time', dateFormat : 'time' },   
		{ name:'gw013',type:'string'},   
		{ name:'gw014',type:'string'}  

	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'gwxx_findPageGwxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:15}},
	    fields: GwxxObj,
	    
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}//----
	});
	
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    sm,
//	            {header: '编号1', width: 50,sortable:true, align:'center', dataIndex: 'dwbh00'},
	            {header: '机构名称', width: 60,sortable:true,  align:'center', dataIndex: 'lbname'},
//	            {header: '公文名称', width: 150,sortable:true, align:'center', dataIndex: 'dwmc00'},
//	            {header: '电话', width: 150, sortable:true, align:'center',dataIndex: 'dhua00'},
//	            {header: '地址', width: 150, sortable:true, align:'center',dataIndex: 'dizhi0'},
//	            {header: '邮政编码', width: 150, sortable:true, align:'center',dataIndex: 'yzbm00'},
//	            {header: '共享', width: 150, sortable:true, align:'center',dataIndex: 'lxr000'},
	            
//				{ header:'用户ID',width:150,sortable:true, align:'center',dataIndex:'userid'},  
				{ header:'用户名',width:50,sortable:true, align:'center',dataIndex:'username'},
				{ header:'编号',width:50,sortable:true, align:'center',dataIndex:'gw001'},   
   
				{ header:'公文标题',width:250,sortable:true, align:'center',dataIndex:'gw003'},   
				{ header:'公文文号',width:150,sortable:true, align:'center',dataIndex:'gw004'},   
				{ header:'发文单位',width:100,sortable:true, align:'center',dataIndex:'gw005'},   
				{ header:'发文日期',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw006'},   
//				{ header:'文件等级',width:150,align:'center',dataIndex:'gw007'},   
//				{ header:'文件密级',width:150,align:'center',dataIndex:'gw008'},   
//				{ header:'文件种类',width:150,align:'center',dataIndex:'gw009'},   
//				{ header:'文件内容',width:150,align:'center',dataIndex:'gw010'},   
				{ header:'录入日期',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw011'},   
//				{ header:'归档日期',width:150,align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw012'},   
				{ header:'文件附件',width:80,sortable:true, align:'center',dataIndex:'gw013'},  
				{ header:'传阅标志',width:50,sortable:true, align:'center',dataIndex:'gw002',
				                 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '0': str='未传阅';  break;
				                 	case '1': str='待拟办';  break;
				                 	case '2': str='已拟办';  break;
				                 	case '3': str='待批示';  break;
				                 	case '4': str='已批示';  break;
				                 	case '5': str='等阅示';  break;
				                 	case '6': str='已阅示';  break;
				                 	case '7': str='正阅处';  break;
				                 	case '8': str='已办结';  break;
				                 	case '9': str='已归档';  break;
				                 	}
			                 return str;
				                 }
				},
				{id:'bz0000', header:'收发标志',width:10,align:'center',dataIndex:'gw014',
									renderer : function(v) {return v==0?"收文":"发文";}
				}]       
//	            {id:'bz0000',header: '公文备注', sortable:true, align:'center',dataIndex: 'bz0000'}
	            
        }),
        sm:sm,
        region:'center',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'bz0000', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 0 0",   //调整边距，第三位调整下边距
	    //style:'border:1px solid',//给表格面板外框添加边框线
        title:'公文信息维护',
        iconCls:'menu-51',
        
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的单位');
				}else{
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的单位');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该单位？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "gwxx_deleteGwxx.do",
					   			params:{ dwbh00 : record.get("dwbh00") },
					   			success : function() {
					   				Ext.Msg.alert('信息提示','删除成功'); 
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        },'->',{
        
        	text:'帮助',
        	iconCls:'btn-help',
        	handler: function() { 
        			 helpWindow.show();
                 }  
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });

  
  
	
	

	
	
	
		//商品类别树-------------------------
	var tree = new Ext.tree.TreePanel({
		title:'机构类别',
		region : 'west',
		width : 180,
        minSize: 150,
        maxSize: 300,
        split : true,
		useArrows: true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: true,
        frame:true,
        dataUrl: 'xmlb_findXmlbTree.do',
        root: {
            nodeType: 'async',
            text: '所有项目',
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
    
	//商品类别树弹出窗口
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
	            text: '所有类别',
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
						addForm.getForm().findField("lbid").setValue(v_lbid);
						addForm.getForm().findField("lbname").setValue(v_lbname);
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
				addForm.getForm().findField("lbid").setValue(v_lbid);
				addForm.getForm().findField("lbname").setValue(v_lbname);
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

	
//---------------------------------------	
	//
   var combstorezl = new Ext.data.ArrayStore({
	   
	    //url: 'gwlb.json',
	    // root: 'root',
	    //totalProperty: 'total',
	    fields : ["id", "name"],

	    
	    autoLoad:true,
	    data:[[0,'命令（令）'],[1,'议案'],[2,'决定'],[3,'指示'],[4,'公告'],[5,'通告'],[6,'通知'],[7,'通报'],[8,'报告'],[9,'请示'],[10,'批复'],[11,'函'],[12,'会议纪要'],[13,'其它']]
	});
	 //combstorezl.load();	
	
	
	
	
	//创建数据源[数组数据源]
		var combstoredj=new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[[0,'普通'],[1,'加急'],[2,'特急']]
		});	
		var combstoremj=new Ext.data.ArrayStore({
			fields:['id','name'],
			data:[[0,'普通'],[1,'秘密'],[2,'机密'],[3,'绝密']]
		});	  
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
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
						width : 318,
						items:[{
							id:'lbtext',
							width : 245,
							xtype : 'textfield',
							name:'lbname',
							fieldLabel:'机构类别',
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
					name:'gw001',
					fieldLabel:'公文编号',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'gw004',
					fieldLabel:'文件文号',
					maxLength :20
				},{		
				
				        id:'gw006',
						xtype:"datefield",
						name:'gw006',
						fieldLabel:"发文日期",
						format:'Y-m-d',
						allowBlank : false,
						value:new Date(),
						anchor:"90%"
						
//					xtype : 'textfield',
//					name:'gw006',
//					fieldLabel:'发文日期',
//					maxLength :10
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '100%'
				},
				items:[{
					xtype : 'combo',
					hiddenName:'gw007',
					mode:'local',
					fieldLabel:'公文等级',
					triggerAction:'all',
					valueField:'id',
					displayField:'name',
					emptyText:'请选择',
					value:0,
					allowBlank : false,
					editable:false,
					store:combstoredj
				},{
					layout:'column',
					items:[{
						layout:'form',
						width : 318,
						items:[{
							width : 245,
							xtype:'combo',
							name:'gw009',
							fieldLabel:'文件类别',
							mode: 'local',
							triggerAction: 'all',
							value:6,
							valueField :'id',
							displayField: 'name',
							allowBlank : false,
							editable : false,
							store : combstorezl
						}]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							//SpdwWindow.show();
						}
					}]
				},{xtype : 'combo',
					hiddenName:'gw008',
					mode:'local',
					fieldLabel:'文件密级',
					triggerAction:'all',
					valueField:'id',
					displayField:'name',
					emptyText:'请选择',
					value:0,
					allowBlank : false,
					editable:false,
					store:combstoremj
					
					//xtype : 'numberfield',
					//xtype : 'textfield',
					//name:'gw008',
					//fieldLabel:'文件密级',
					//maxLength :10
				},{
					
						id:'gw011',
						xtype:"datefield",
						name:'gw011',
						fieldLabel:"录入日期",
						format:'Y-m-d',
						allowBlank : false,
						value:new Date(),
						anchor:"90%"
						
//						listeners:{
//							select : function(a,b){
//								getCode(b.format("Y-m-d"));
//							}
//						}

				}]
			}]
		},{
			xtype : 'textfield',
			name:'gw003',
			fieldLabel:'文件标题',
			maxLength :50
		},{
			xtype:'textarea',
			name:'gw010',
			fieldLabel:'文件摘要',
			height:100,
			maxLength :200
		},{
					xtype : 'textfield',
					name:'gw005',
					fieldLabel:'发文单位',
					maxLength :100
				},{
			xtype : 'hidden',
		    name : 'lbid'
		},{
			xtype : 'hidden',
			name : 'addupdate'
		},{
				xtype : 'hidden',
			    name : 'dwbh00'
			},{
				xtype : 'hidden',
			    name : 'userid',
			    value:2222
			},{
				xtype : 'hidden',
			    name : 'username',
			    value:44444
			},{
					xtype : 'textfield',
					name:'bz0000',
					fieldLabel:'公文备注',
					maxLength :200
				},{
					xtype : 'textfield',
					name:'gw013',
					fieldLabel:'文件附件',
					maxLength :100
				},{
				xtype:'combo',
						hiddenName:'gw014',
						fieldLabel:'收发类别',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						anchor:"50%",
						value:'0',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['0','收文'],['1','发文']]
						})
			},{
				xtype:'combo',
						hiddenName:'gw002',
						fieldLabel:'传阅标志',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						anchor:"50%",
						value:'0',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['0','未传阅'],
						    ['1','待拟办'],
						    ['2','已拟办'],
						    ['3','待批示'],
						    ['4','已批示'],
						    ['5','待阅示'],
						    ['6','已阅示'],
						    ['7','正阅处'],
						    ['8','已办结'],
						    ['9','已归档']]
						})
			},{
					
						id:'gw012',
						xtype:"datefield",
						name:'gw012',
						fieldLabel:"归档日期",
						
						format:'Y-m-d',
						allowBlank : false,
						value:new Date(),
						anchor:"50%"

		  }]
	}); 
  
	
	
	var myPanel = new Ext.Panel({
        layout : 'fit',
        defaults:{padding:'1 0 0 0'},
        html:'<iframe id="mediasArea" name="ifocus" src="../../help/gwxxhelp.html" style="width:100%; height:100%;" frameborder="0"></iframe>',
        frame : true
        })	
var helpWindow = new Ext.Window({

                   title : '帮助窗口',
                    width : 650,
                    height :300,
                    iconCls:'btn-help',
                    resizable : false,
                    closable : false,   //不显示 窗体左上角的关闭按钮，true为显示关闭按钮。
 					//closeAction: 'destroy',//
                    draggable : true,
                    resizable : false,
                    layout : 'fit',
                    modal : false,
                    plain : true, // 表示为渲染window body的背景为透明的背景
                    bodyStyle : 'padding:5px;',
                    items : myPanel,
                    buttonAlign : 'center',
                    buttons : [{
                            text : '关闭',
                            type : 'button',
                            handler : function() {
                                helpWindow.hide();
                                }
                            }]
		
		
		
		
		
		
		//handler: function() { 
        			  
              //     window.open('gwfshelp.html', 'mywindow1', 'width=500, height=800, menubar=no, toolbar=no,location=no,status=no,directories=no,resizable=no,top=100,left=200, scrollbars=yes');  
            //     }  
		



});	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:750,
		height:570,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (addForm.getForm().isValid()) {
					addForm.getForm().submit({
						url : 'gwxx_saveOrUpdateGwxx.do',
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							addWindow.hide();
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
				addWindow.hide();
			}
		}]
	});
		
	
	
	
	
	
	
	
	
	
	
  //布局
//    new Ext.Viewport({
//		layout:"fit",
//		items:[{
//			frame:true,
//			title:"当前库存查询",
//			iconCls:'menu-15',
//			layout:"border",
//			items:[searchForm,kcspGrid]
//		}]
//	});
	
	
	
	
	
	
	
	
	
	
	
	
//    new Ext.Viewport({
//		layout:'fit',
//		items:[{
//			frame:true,
//			layout:'border',
//			items:[searchForm,grid]
//		}]
//	});
	
	
	
	
	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			items:[tree,searchForm,grid]
		}]
	});	
	
	
	
	
	
	

});