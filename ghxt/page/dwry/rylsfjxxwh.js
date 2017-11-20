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
							name:"aab002",
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
var RylsfjxxObj = [
				{name:'sae201',type:'string'}, //单档ID             
     	        {name:'sae041',type:'string'}, //理算业务ID         
     	        {name:'sae202',type:'string'}, //档案类型           
     	        {name:'sae203',type:'string'},  //档案文件类型      
     	        {name:'sae204',type:'string'}, //文件名             
     	        {name:'sae205',type:'string'}, //文件地址           
     	        {name:'sae036',type:'string'}, //上传人             
     	        {name:'sae011',type:'string'}, //上传日期           
     	        {name:'sae012',type:'string'} //标志0未绑定，已绑定 
     	                                                            
                            

	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'rylsfjxx_findPageRylsfjxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: RylsfjxxObj 
	});
	
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			     //{header: 'ID号', width: 35,align:'center', dataIndex: 'sae201'},
	            {header: '理算业务ID', width: 100,align:'center', dataIndex: 'sae041'},
	            {header: '档案类型', width: 100,align:'center', dataIndex: 'sae202',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='医保结算';  break;
										                 	case '1': str='个人证件';  break;
										                 	case '2': str='审核审批';  break;
										                 	case '3': str='其它相关';  break;
										                 	}
									                 		return str;
										                 	}},
	            {header: '文件名', width: 100, align:'center',dataIndex: 'sae204'},
	            {header: '档案文件类型', width: 100, align:'center',dataIndex: 'sae203',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='电子文件';  break;
										                 	case '1': str='图像文件';  break;
										                 	case '2': str='PDF文档';  break;
										                 	case '3': str='其它';  break;
										                 	}
									                 		return str;
										                 	}},
	            {id:'sae205',header: '文件地址', width: 100, align:'center',dataIndex: 'sae205'},
	            {header: '标志', width: 100, align:'center',dataIndex: 'sae012',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='未绑定';  break;
										                 	case '1': str='已绑定';  break;
										                 	 
										                 	}
									                 		return str;
										                 	}},
	            {header: '上传人', width: 100, align:'center',dataIndex: 'sae036'},
	            {header: '上传日期', width: 100, align:'center',dataIndex: 'sae011'} 
	            
	            
	            ]
        }),
        region:'center',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'sae205', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 5 0",
	    style:'border:1px solid',
        title:'上传附件管理',
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
					   			url : "rylsfjxx_deleteRylsfjxx.do",
					   			params:{ sae201 : record.get("sae201") },
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
				name:'sae041',
				fieldLabel:'理算业务ID',
				maxLength :20,
				allowBlank : false
			},{
				xtype:'combo',
						hiddenName:'sae202',
						fieldLabel:'档案类型',
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
						    data : [['0','医保结算'],['1','个人证件'],['2','审核审批'],['3','其它相关']]
	 
						})
			},{
				name:'sae204',
				fieldLabel:'文件名',
				maxLength :30
			},{ 
				name:'sae205',
				fieldLabel:'文件地址',
			 
				//allowBlank: false,
				maxLength :200
  				//regex: /^\d+(\.\d{1,2})?$/,
                //regexText: '请输入正确的数据类型'     
			},{
				xtype:'combo',
						hiddenName:'sae203',
						fieldLabel:'档案文件类型',
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
						    data : [['0','电子文件'],['1','图像文件'],['2','PDF文档'],['3','其它']]
						})
			},{
				name:'sae036',
				fieldLabel:'上传人',
				maxLength :30
			},{
				name:'sae011',
				fieldLabel:'上传日期',
				maxLength :30
			},{
				xtype:'combo',
						hiddenName:'sae012',
						fieldLabel:'标志',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						//anchor:"50%",
						value:'1',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['0','未绑定'],['1','已绑定']]
						})	
			},{
				xtype : 'hidden',
			    name : 'sae201'
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
						url : 'rylsfjxx_saveOrUpdateRylsfjxx.do',
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