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
						name:"aaa013",
						fieldLabel:"年度"
					}]
				  },
				  {
						width:310,
					  items:[{
							width:180,
							labelWidth:100,
							xtype:"textfield",
							name:"aaa002",
							fieldLabel:"期别编号"
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
var AaacsxxObj = [
				 { name:'aaa001',type:'string'},
				 { name:'aaa002',type:'string'},
				 { name:'aaa003',type:'string'},
				 { name:'aaa004',type:'string'},
				 { name:'aaa005',type:'string'},
				 { name:'aaa006',type:'string'},
				 { name:'aaa007',type:'float'},
				 { name:'aaa008',type:'float'},
				 { name:'aaa009',type:'float'},
				 { name:'aaa010',type:'float'},
				 { name:'aaa011',type:'float'},
				 { name:'aaa012',type:'string'},
				 { name:'aaa013',type:'string'},
				 { name:'aaa014',type:'string'},
				 { name:'aaa015',type:'string'},
				 { name:'aaa016',type:'string'}
	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'aaacsxx_findPageAaacsxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: AaacsxxObj 
	});
	
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    //{header: 'ID号', width: 35,align:'center', dataIndex: 'aaa001'},
	            {header: '期别', width: 35,align:'center', dataIndex: 'aaa002'},
	            {header: '期别年度', width: 60,align:'center', dataIndex: 'aaa013'},
	            {header: '报名起始日期', width: 100, align:'center',dataIndex: 'aaa003'},
	            {header: '报名截止日期', width: 100, align:'center',dataIndex: 'aaa004'},
	            {header: '年缴费', width: 50, align:'center',dataIndex: 'aaa007'},
	            {header: '互助起始日期', width: 80, align:'center',dataIndex: 'aaa005'},
	            {header: '互助截止日期', width: 80, align:'center',dataIndex: 'aaa006'},
	            {header: '单笔最高互助限额', width: 120, align:'center',dataIndex: 'aaa008'},
	            {header: '单笔最低互助限额', width: 120, align:'center',dataIndex: 'aaa010'},
	            {header: '期内最高互助限额', width: 120, align:'center',dataIndex: 'aaa009'},
	            {header: '期内最多互助次数', width: 120, align:'center',dataIndex: 'aaa011'},
	            {header: '状态', width: 40, align:'center',dataIndex: 'aaa014',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='有效';  break;
										                 	case '1': str='过期';  break;
										                 	case '2': str='无效';  break;
										                 	}
									                 		return str;
										                 	}},
	            {id:'aaa012',header: '配置依据文件说明', align:'center',dataIndex: 'aaa012'}
	            
	            
	            ]
        }),
        region:'center',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'aaa012', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 5 0",
	    style:'border:1px solid',
        title:'期别管理',
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
                	Ext.Msg.alert('信息提示','请选择要修改的信息');
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
                	Ext.Msg.alert('信息提示','请选择要删除的信息');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该数据？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "aaacsxx_deleteAaacsxx.do",
					   			params:{ aaa001 : record.get("aaa001") },
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
		labelWidth:110,
		labelAlign:"right",
		border : false,
		padding : '15 10 5 8',
		defaults : {
			anchor : '99%',
			xtype : 'textfield'
		},
		 
		items:[{
				name:'aaa002',
				fieldLabel:'期别',
				maxLength :20,
				allowBlank : false
			},{
				name:'aaa003',
				fieldLabel:'报名起始日期',
				maxLength :8
			},{
				name:'aaa004',
				fieldLabel:'报名截止日期',
				maxLength :8
			},
			{
				name:'aaa005',
				fieldLabel:'互助起始时间',
				maxLength :8
			},{
				name:'aaa006',
				fieldLabel:'互助截止时间',
				maxLength :8
			},{
				name:'aaa007',
				fieldLabel:'年缴费标准',
				maxLength :30
			},{
				name:'aaa008',
				fieldLabel:'单笔最大报销限额',
				maxLength :30	
			},{
				name:'aaa009',
				fieldLabel:'期内最大报销限额',
				maxLength :30
			},{
				name:'aaa010',
				fieldLabel:'单笔最低报销限额',
				maxLength :10
			},{
				name:'aaa011',
				fieldLabel:'期内最大报销次数',
				maxLength :30
			},{
				name:'aaa013',
				fieldLabel:'年度',
				maxLength :4
			},{
				xtype:'combo',
						hiddenName:'aaa014',
						fieldLabel:'状态',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						//anchor:"50%",
						value:'0',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['0','有效'],['1','过期'],['2','无效']]
						})
			},{
				name:'aaa015',
				fieldLabel:'用户',
				maxLength :20
			},{
				name:'aaa016',
				fieldLabel:'配置时间',
				maxLength :8
			},{
				xtype:'textarea',
				name:'aaa012',
				fieldLabel:'配置依据文件说明',
				height:80,
				maxLength :200
			},{
				xtype : 'hidden',
			    name : 'aaa001'
			}]
	});
    
    var addWindow = new Ext.Window({
		title : '添加窗口',
		width:500,
		height:540,
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
						url : 'aaacsxx_saveOrUpdateAaacsxx.do',
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