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
var AabcsxxObj = [
				    {name:'aab001',type:'string'},   //ID                                         
  	        {name:'aab002',type:'string'},   //期别                                       
  	        {name:'aab003',type:'string'},   //救助方式名称                               
  	        {name:'aab004',type:'float'},   //救助比例                                    
  	        {name:'aab005',type:'string'},   //状态0正常1为禁用                           
  	        {name:'aab006',type:'string'},   //备注                                       
  	        {name:'aab007',type:'string'},   //使用等级0为普通用户1中层领导2高级领导      
  	        {name:'aab008',type:'string'},   //是否要求有医保                             
  	        {name:'aab009',type:'float'},   //基数下限                                    
  	        {name:'aab010',type:'float'}   //基数上限                                    

	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'aabcsxx_findPageAabcsxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: AabcsxxObj 
	});
	
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    //{header: 'ID号', width: 35,align:'center', dataIndex: 'aaa001'},
	            {header: '期别', width: 100,align:'center', dataIndex: 'aab002'},
	            {header: '互助方式', width: 100,align:'center', dataIndex: 'aab003',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='补助';  break;
										                 	case '1': str='救助';  break;
										                 	case '2': str='帮扶';  break;
										                 	}
									                 		return str;
										                 	}},
	            {header: '比例', width: 100, align:'center',dataIndex: 'aab004'},
	            {header: '状态', width: 100, align:'center',dataIndex: 'aab005',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='有效';  break;
										                 	case '1': str='过期';  break;
										                 	case '2': str='无效';  break;
										                 	}
									                 		return str;
										                 	}},
	            {header: '适用', width: 100, align:'center',dataIndex: 'aab007'},
	            {header: '是否医保', width: 100, align:'center',dataIndex: 'aab008',
							             renderer: function(v){
										                 	var str="";
										                 	switch(v){ 
										                 	case '0': str='无须参医保';  break;
										                 	case '1': str='必须参医保';  break;
										                 	case '2': str='与医保无关';  break;
										                 	}
									                 		return str;
										                 	}},
	            {header: '基数下限', width: 100, align:'center',dataIndex: 'aab009'},
	            {header: '基数上限', width: 100, align:'center',dataIndex: 'aab010'},
	            
	            
	            {id:'aab006',header: '备注', align:'center',dataIndex: 'aab006'}
	            
	            
	            ]
        }),
        region:'center',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'aab006', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 5 0",
	    style:'border:1px solid',
        title:'期别比例设置',
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
					   			url : "aabcsxx_deleteAabcsxx.do",
					   			params:{ aab001 : record.get("aab001") },
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
				name:'aab002',
				fieldLabel:'期别',
				maxLength :20,
				allowBlank : false
			},{
				xtype:'combo',
						hiddenName:'aab003',
						fieldLabel:'互助方式',
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
						    data : [['0','补助'],['1','救助'],['2','帮扶']]
						})
			},{ 
				name:'aab004',
				fieldLabel:'救助比例',
			 
				allowBlank: false,
				maxLength :4,
  				regex: /^\d+(\.\d{1,2})?$/,
                regexText: '请输入正确的数据类型'     
			},{
				xtype:'combo',
						hiddenName:'aab005',
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
				name:'aab007',
				fieldLabel:'适用用户等级',
				maxLength :30
			},{
				xtype:'combo',
						hiddenName:'aab008',
						fieldLabel:'前提是否参医保',
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
						    data : [['0','无须医保'],['1','必须参医保'],['2','与医保无关']]
						})	
			},{
				name:'aab009',
				fieldLabel:'基数下限',
				maxLength :30
			},{
				name:'aab010',
				fieldLabel:'基数上限',
				maxLength :10
			},{
				xtype:'textarea',
				name:'aab006',
				fieldLabel:'备注',
				height:80,
				maxLength :200
			},{
				xtype : 'hidden',
			    name : 'aab001'
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
						url : 'aabcsxx_saveOrUpdateAabcsxx.do',
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