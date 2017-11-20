/*!
 * 项目管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var v_lbid="0", v_lbname="", v_start=0, v_limit=20;
	
	var XmxxObj = [
		{ name:'spid', type:'string'},
		{ name:'spname', type:'string'},
		{ name:'xinghao', type:'string'},
		{ name:'dw', type:'string'},
		{ name:'jhprice', type:'string'},
		{ name:'chprice', type:'string'},
		{ name:'minnum', type:'string'},
		{ name:'csname', type:'string'},
		{ name:'bz', type:'string'},
		{ name:'lbid', type:'int'},
		{ name:'lbname', type:'string'}
	];
	
	//商品数据
	var store = new Ext.data.JsonStore({
	    url: 'xmxx_findPageXmxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: XmxxObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}
	});
	
	//商品列表
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var grid = new Ext.grid.GridPanel({
        store: store,
        
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    sm,//添加复选框
				{header: '商品编号', width: 100, sortable:true, dataIndex: 'spid'},
	            {header: '商品名称', width: 130, sortable:true, dataIndex: 'spname'},
	            {header: '商品型号', width: 80, sortable:true, dataIndex: 'xinghao'},
	            {header: '类别', width: 60, sortable:true, dataIndex: 'lbname'},
	            {header: '单位', width: 60, sortable:true, dataIndex: 'dw'},
	            {header: '采购价', width: 60, sortable:true, align:'right', renderer:zhMoney, dataIndex: 'jhprice'},
	            {header: '销售价', width: 60, sortable:true, align:'right', renderer:zhMoney, dataIndex: 'chprice'},
	            {header: '库存下限', width: 60, sortable:true, align:'right', dataIndex: 'minnum'},
	            {header: '生产厂商', width: 100, sortable:true, dataIndex: 'csname'},
	            {header: '备注', width: 200, sortable:true, dataIndex: 'bz'}]
        }),
        sm:sm,//复选框名称
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
		frame:true,
		region:'center',
		title:'项目信息',
        iconCls:'menu-53',
        
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        		addForm.getForm().findField("addupdate").setValue("add");
        		if(v_lbid!="0"){
					addForm.getForm().findField("lbid").setValue(v_lbid);
					addForm.getForm().findField("lbname").setValue(v_lbname);
				}
				getCode();
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的商品');
				}else{
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
					addForm.getForm().findField("addupdate").setValue("update");
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的行');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除信息？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "xmxx_deleteXmxx.do",
					   			params:{ spid : record.get("spid") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	text:"审核",
        	iconCls:'menu-51',
            handler: function(){
            	
            	
            	//选择多行时 返回为数组。
   				 var rs= grid.getSelectionModel().getSelections();
            	 Ext.each(rs,function(){
					    alert(this.get("spid"));
					
					   //Ext.Msg.alert("特别提示",this.get("spid")+"项目删除！");
					   		Ext.Ajax.request({
					   			url : "xmxx_deleteXmxx.do",
					   			params:{ spid : this.get("spid") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});					
					
					
					
					
					
					
					
					
					
					
					});
            	 
            	 
 	 
            	
            	
            	//选择单行时
            	//var record = grid.getSelectionModel().getSelected();
                //alert(record.get("spid"));
 

            }
        },'-',{
        	text:"导出",
        	iconCls:'menu-11',
            handler: function(){
            	
            	var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要导出的商品');  
				}else{
					 Ext.MessageBox.confirm('审核提示', '是否审核导出该商品？', function(c) {
					   if(c=='yes'){
					   	
					   	Ext.Msg.alert("特别提示","你选择了导出商品！");
//					   		Ext.Ajax.request({
//					   			url : "xmxx_deleteXmxx.do",
//					   			params:{ spid : record.get("spid") },
//					   			success : function() {
//					   				store.reload();
//					   			}
//					   		});
					    }else{
					    Ext.Msg.alert("特别提示","你选择了未导出！");
					    
					    }
					});
					
                    
				     }
            }
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        }),
        
        listeners:{
        	rowdblclick:function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(record){
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
					addForm.getForm().findField("addupdate").setValue("update");
				}
        	}
        }
    });
    
    //商品单位下拉数据
    var zdflStore = new Ext.data.JsonStore({
	    xtype:'jsonstore',
		url: 'zdfl_findAllZdfl.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: [{name:'dwid',type:'int'},'dwname'],
		autoLoad:true
	});
	
	//商品表单
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
						width : 218,
						items:[{
							id:'lbtext',
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
					name:'spname',
					fieldLabel:'商品名称',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'xinghao',
					fieldLabel:'商品型号',
					maxLength :20
				},{
					xtype : 'numberfield',
					name:'jhprice',
					fieldLabel:'采购价',
					maxLength :10
				},{
					xtype : 'numberfield',
					name:'chprice',
					fieldLabel:'销售价',
					maxLength :10
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'spid',
					fieldLabel:'商品编码',
					allowBlank : false,
					style:"background:#F6F6F6",
					readOnly:true,
					maxLength :10
				},{
					layout:'column',
					items:[{
						layout:'form',
						width : 218,
						items:[{
							width : 145,
							xtype:'combo',
							name:'dw',
							fieldLabel:'单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位',
							mode: 'local',
							triggerAction: 'all',
							valueField :'dwid',
							displayField: 'dwname',
							allowBlank : false,
							editable : false,
							store : zdflStore
						}]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							ZdflWindow.show();
						}
					}]
				},{
					xtype : 'numberfield',
					name:'minnum',
					fieldLabel:'库存下限',
					maxLength :10
				}]
			}]
		},{
			xtype : 'textfield',
			name:'csname',
			fieldLabel:'生产厂商',
			maxLength :50
		},{
			xtype:'textarea',
			name:'bz',
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			height:100,
			maxLength :200
		},{
			xtype : 'hidden',
		    name : 'lbid'
		},{
			xtype : 'hidden',
			name : 'addupdate'
		}]
	});
    
	//增加商品窗口
    var addWindow = new Ext.Window({
		title : '增加商品',
		width:550,
		height:370,
		closeAction:'hide',
		modal : true,
		border:false,
		resizable : false,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '新增下一商品',
			handler : function() {
				submitXmxx(true);
			}
		},{
			text : '保存',
			handler : function() {
				submitXmxx(false);
			}
		}, {
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}]
	});
	
	var submitXmxx = function(next){
		if (addForm.getForm().isValid()) {
			addForm.getForm().submit({
				url : 'xmxx_saveOrUpdateXmxx.do',
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
		title:'项目类别',
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
    
	//商品类别树窗口
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
	
	//商品单位编辑窗口
	var ZdflWindow = new Ext.Window({
		xtype:'window',
		title:'单位',
		width:250,
		height:250,
		resizable : false,
		closeAction:'hide',
		layout:'fit',
		items:[{
			xtype:'grid',
			store:zdflStore,
			columns:[new Ext.grid.RowNumberer(),{
					header:'单位名称',
					sortable:true,
					menuDisabled : true,
					dataIndex:'dwname',
					width:190
				}],
			tbar:[{
					text:'增加',
					handler:function(){
						ZdflAddWin.show();
					}
				},'-',{
					text:'删除',
					handler:function(){
						var grid = ZdflWindow.get(0);
						var record= grid.getSelectionModel().getSelected();
						if(!record){
		                	Ext.Msg.alert('信息提示','请选择要删除的单位');  
						}else{
							Ext.MessageBox.confirm('删除提示', '是否删除该单位？', function(c) {
							   if(c=='yes'){
							   		Ext.Ajax.request({
							   			url : "zdfl_deleteZdfl.do",
							   			params:{ dwid : record.get("dwid") },
							   			success : function() {
							   				grid.getStore().load();
							   			}
							   		});
							    }
							});
						}
					}
				},'-',{
					text:'确定',
					handler:function(){
						var record= ZdflWindow.get(0).getSelectionModel().getSelected();
						if(!record){
		                	Ext.Msg.alert('信息提示','请选择单位');  
						}else{
							addForm.getForm().findField("dw").setValue(record.get("dwname"));
							ZdflWindow.hide();
						}
					}
				},'-',{
					text:'取消',
					handler:function(){
						ZdflWindow.hide();
					}
				}]
		}]
	});
	
	//商品单位添加窗口
	var ZdflAddWin = new Ext.Window({
		title:'增加单位',
		width:250,
		height:140,
		resizable : false,
		closeAction:'hide',
		layout:'fit',
		fbar:[{
				text:'保存',
				handler:function(){
					var form = ZdflAddWin.get(0).getForm();
					if (form.isValid()) {
							form.submit({
								url : 'zdfl_saveOrUpdateZdfl.do',
								success : function(form, action) {
									ZdflAddWin.hide();
									zdflStore.load();
								},
								failure : function(form, action) {
									if(action.result.errors){
										Ext.Msg.alert('信息提示',action.result.errors);
									}else{
										Ext.Msg.alert('信息提示','连接失败');
									}
								}
							});
						}
				}
			},{
				text:'取消',
				handler:function(){
					ZdflAddWin.hide();
				}
		}],
		items:[{
				id:'dwform',
				xtype:'form',
				baseCls: 'x-plain',
				labelWidth:60,
				padding:20,
				layout:'form',
				items:[{
						xtype:'textfield',
						name:'dwname',
						fieldLabel:'单位名称',
						allowBlank : false,
						maxLength :20,
						anchor:'100%'
				}]
		}]
	});
	
	//设置商品编号
	var getCode = function(){
		Ext.Ajax.request({
   			url : "xmxx_getXmxxCode.do",
   			success : function(o) {
   				if(o.responseText){
   					addForm.getForm().findField("spid").setValue(o.responseText);
   				}
   			}
   		});
	};
	
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