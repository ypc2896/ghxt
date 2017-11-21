/*!
 * 单位信息维护
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	//查询表单
	var searchForm = new Ext.FormPanel({
		region:"north",
		height: 80,
		border : false,
		layout : "form",
		padding : "5 0 0 0",
		items:[{
			xtype:"fieldset",
			title:"查询条件",
			padding:"0 0 0 0",
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:"right",
					layout:"form"
				},
				items:[{
					width:310,
					items:[{
						width:180,
						labelWidth:100,
						xtype:"textfield",
						name:"aab001",
						fieldLabel:"单位编号"
					}]
				  },
				  {
						width:310,
					  items:[{
							width:180,
							labelWidth:100,
							xtype:"textfield",
							name:"aab003",
							fieldLabel:"单位名称"
					   }]
				   },
				   {
					width:250,
					items:[{
						width:75,
						xtype:"button",
						iconCls:"btn-list",
						text:"查询",
						handler:function(){
							var f = searchForm.getForm();
							if (f.isValid()) {
								var params = f.getValues();
								params.start=0;
								params.limit =15;
								store.load({params:params});
							}
						}
					}]
				}]
			}]
		}]
	});
	var DwxxObj = [
		{ name:'dwbh00', type:'string'},
 
		{ name:'aab001',type:'string'}, 
		{ name:'aab002',type:'string'}, 
		{ name:'aab003',type:'string'}, 
		{ name:'aab004',type:'string'}, 
		{ name:'aab005',type:'string'}, 
		{ name:'aab006',type:'string'}, 
		{ name:'aab007',type:'string'}, 
		{ name:'aab008',type:'string'}, 
		{ name:'aab009',type:'string'}, 
		{ name:'aab010',type:'string'}, 
		{ name:'aab011',type:'string'}, 
		{ name:'aab012',type:'string'}  
	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'dwxx_findPageDwxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: DwxxObj 
	});
	
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    {header: '上级编号', width: 60,align:'left', dataIndex: 'aab002'},
				{header: '单位编号', width: 60,align:'left', dataIndex: 'aab001'},	            
	            {id:'aab003',header: '单位名称', width: 150, align:'center',dataIndex: 'aab003'},
	            {header: '是否参保', width: 60, align:'center',dataIndex: 'aab004',
									renderer : function(v) {return v=="0"?"否":"是";}},
	            {header: '负责人', width: 60, align:'center',dataIndex: 'aab005'},
	            {header: '负责人电话', width: 60, align:'center',dataIndex: 'aab006'}, 
				{header: '单位地址', width: 100,align:'center', dataIndex: 'aab007'},
	            {header: '单位类型', width: 60,align:'center', dataIndex: 'aab008'},
	            {header: '是否登记', width: 60, align:'center',dataIndex: 'aab009',
									renderer : function(v) {return v=="0"?"否":"是";}},
	            
	            {header: '负责人', width: 60, align:'center',dataIndex: 'aab011'},
	            {header: '负责人电话', width: 100, align:'center',dataIndex: 'aab012'},
	            {header: '备注', width: 60, align:'center',dataIndex: 'aab010'}
	            
	            
	            ]
        }),
        region:'center',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'aab003', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 5 0",
	    style:'border:1px solid',
        title:'单位管理',
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
					   			url : "dwxx_deleteDwxx.do",
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
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            displayInfo: true
        })
    });

    var addForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		labelWidth:70,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		 
		items:[{
				name:'aab001',
				fieldLabel:'单位编号',
				maxLength :20,
				allowBlank : false
			},{
				name:'aab002',
				fieldLabel:'上级编号',
				maxLength :20
			},{
				name:'aab003',
				fieldLabel:'单位名称',
				maxLength :100
			},
			{
				name:'aab004',
				fieldLabel:'是否参保',
				maxLength :1
			},{
				name:'aab005',
				fieldLabel:'负责人',
				maxLength :30
			},{
				name:'aab006',
				fieldLabel:'负责人电话',
				maxLength :30
			},{
				name:'aab007',
				fieldLabel:'单位地址',
				maxLength :30	
			},{
				name:'aab008',
				fieldLabel:'单位类型',
				maxLength :3
			},{
				name:'aab009',
				fieldLabel:'是否登记',
				maxLength :1
			},{
				name:'aab011',
				fieldLabel:'工会主席',
				maxLength :30
			},{
				name:'aab012',
				fieldLabel:'主席电话',
				maxLength :30
			},{
				xtype:'textarea',
				name:'aab010',
				fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
				height:80,
				maxLength :200
			},{
				xtype : 'hidden',
			    name : 'dwbh00'
			}]
	});
    
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:400,
		height:500,
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
						url : 'dwxx_saveOrUpdateDwxx.do',
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
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			items:[searchForm,grid]
		}]
	});

});