/*!
 * 公文信息维护
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	 var v_lbid="0", v_lbname="", v_start=0, v_limit=10,v_gwgl="fsgw",v_cybz="10";
	 
	 
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
							ycmxstore.removeAll();//将明细表中的信息置为空。
							v_cybz="10";   //将左侧公文状态的全局变量设为全部
							if (f.isValid()) {
								var params = f.getValues();
								params.start=0;
								params.limit =10;
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
		{ name:'gw014',type:'string'},
		{ name:'gw015',type:'string'} 

	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'gwxx_findPageGwxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:10}},
	    fields: GwxxObj,
	    
	    listeners:{beforeload:function(a){
	    // var
	    	
		var f = searchForm.getForm();
		ycmxstore.removeAll();//将明细表中的信息置为空。
		v_cybz="10";   //将左侧公文状态的全局变量设为全部
		if (f.isValid()) {
			var params = f.getValues();
			params.start=v_start;
			params.limit =v_limit;}
			//store.load({params:params});
	    a.baseParams={start:v_start, limit:v_limit,gwgl:v_gwgl,gw002:v_cybz,startdate:params.startdate,enddate:params.enddate};
	    }
	    
	    }
	});
	
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var grid = new Ext.grid.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			    sm,
	            {header: 'ID', width: 50,sortable:true, align:'center', dataIndex: 'dwbh00'},
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
				                 	case '5': str='待阅示';  break;
				                 	case '6': str='已阅示';  break;
				                 	case '7': str='正阅处';  break;
				                 	case '8': str='已办结';  break;
				                 	case '9': str='已归档';  break;
				                 	}
			                 return str;
				                 }
				},
				{id:'bz0000', header:'收发',width:10,align:'center',sortable:true,dataIndex:'gw014',
									renderer : function(v) {return v==0?"收":"发";}
				},
				{header:'共享',width:40,align:'center',sortable:true,dataIndex:'gw015',
									renderer : function(v) {return v==0?"未":"已";}
				}]       
//	            {id:'bz0000',header: '公文备注', sortable:true, align:'center',dataIndex: 'bz0000'}
	            
        }),
        sm:sm,
        region:'north',
         
        height:'300',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'bz0000', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 0 0",   //调整边距，第三位调整下边距
	    //style:'border:1px solid',//给表格面板外框添加边框线
        title:'发送公文管理',
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
                	Ext.Msg.alert('信息提示','请选择要删除的信息');  
				}else{
						if(record.get("gw002")>'0'){
								Ext.Msg.alert('信息提示','公文已发出不能删除！'); 
						}else{
								Ext.MessageBox.confirm('删除提示', '是否删除该信息？', function(c) {
								   if(c=='yes'){
								   	
								   	
								   		Ext.Ajax.request({
								   			url : "gwyc_deleteGwycgwid.do",
								   			params:{ yc002 : record.get("dwbh00") },
								   			success : function() {
								   				Ext.Msg.alert('信息提示','删除1111111111成功'); 
								   				store.reload();
								   			}
								   		});								   	
								   	
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
        	}
        },'-',{
        	  text:'提交拟办',
        	  iconCls:'btn-edit2',
        	  handler:function(){
        		//选择多行时 返回为数组。
        		var  gwrecord= grid.getSelectionModel().getSelections(); 
				if( gwrecord.length==0){
                     Ext.Msg.alert("信息提示","请选择信息");
				}else{
					  gwselect=[].concat(gwrecord);//将选择的公文ID组成的数组，赋给一个公用数组变量，以便于在userxx.js文件中调用
					  
//					 Ext.each(gwselect,function(){
//					    alert(this.get("dwbh00"));
//					 });
					  
					  
						//if(gwrecord.get("gw002")>'0'){
						//		Ext.Msg.alert('信息提示','已进入传阅流程，不能重复提交拟办。'); 
						//}else{
							 bsspWindow.show();
								 
						  //}
				
					}        	  
        	  
        	  }
        	
        },'-',{
        	  text:'提交批示',
        	  iconCls:'btn-list',
        	  handler:function(){
        		var gwrecord= grid.getSelectionModel().getSelected();
				if(!gwrecord){
                	Ext.Msg.alert('信息提示','请选择要提交的信息');  
				}else{
					
					Ext.Msg.alert('信息提示','请8888'); 
					
						if(gwrecord.get("gw002")=='2'){
							Ext.Msg.alert('信息提示','准备提交批示，尚未实现功能。');	
						}else{
								
								Ext.Msg.alert('信息提示','不能重复提交批示或属未拟办公文。'); 
						  }
				
					}        	  
        	  
        	  }
        	
        },'-',{
        	  text:'阅示阅处',
        	  iconCls:'btn-list',
        	  handler:function(){
        		var gwrecord= grid.getSelectionModel().getSelected();
				if(!gwrecord){
                	Ext.Msg.alert('信息提示','请选择要提交的信息');  
				}else{
					
					Ext.Msg.alert('信息提示','请8888'); 
					
						if(gwrecord.get("gw002")=='4'){
							Ext.Msg.alert('信息提示','准备提交阅示阅处，尚未实现功能。');	
						}else{
								
								Ext.Msg.alert('信息提示','不能重复提交阅示阅处或属未批示公文。'); 
						  }
				
					}        	  
        	  
        	  }
        	
        },'->',{
        
        	text:'共享',
        	iconCls:'btn-add',
        	handler:function(){
        		//Ext.Msg.alert('信息提示','共享操作');
            	var record= grid.getSelectionModel().getSelections(); 
				if(record.length==0){
                Ext.Msg.alert("信息提示","请选择信息");
				}else{
            	           Ext.each(record,function(){
								   	
        									Ext.Ajax.request({
								   			url : "gwxx_updateGwxxgx.do",
								   			params:{ dwbh00 :this.get("dwbh00"),gw015:1 },
								   			success : function() {
								   				Ext.Msg.alert('信息提示','共享成功'); 
								   				store.reload();
								   			}
								   		});						   	

								    })
			        }
						  
	      		
        	
        	
        	}
        },'-',{
        
        	text:'取消共享',
        	iconCls:'btn-remove',
          	handler:function(){
        		//Ext.Msg.alert('信息提示','共享操作');
            	var record= grid.getSelectionModel().getSelections(); 
				if(record.length==0){
                Ext.Msg.alert("信息提示","请选择信息");
				}else{
            	           Ext.each(record,function(){
								   	
        									Ext.Ajax.request({
								   			url : "gwxx_updateGwxxgx.do",
								   			params:{ dwbh00 :this.get("dwbh00"),gw015:0 },
								   			success : function() {
								   				Ext.Msg.alert('信息提示','取消共享成功'); 
								   				store.reload();
								   			}
								   		});						   	

								    })
			        }
						  
	      		
        	
        	
        	}
        },'-',{
        
        	text:'帮助',
        	iconCls:'btn-help',
        	handler: function() { 
        			 helpWindow.show();
                  //window.open('gwfshelp.html', 'mywindow1', 'width=500, height=800, menubar=no, toolbar=no,location=no,status=no,directories=no,resizable=no,top=100,left=200, scrollbars=yes');  
                 }  
        }],
        
        listeners:{
        	rowclick:function(a,b){

        		var gwrecord1= grid.getSelectionModel().getSelected();
        		//ycmxstore.removeAll();//移除所有记录。
        		//ycmxstore.removeAll();
         		//alert(b);
         		  ycmxstore.load({params:{yc002:gwrecord1.get("dwbh00"),start:v_start, limit:v_limit}});
         		
         		  
         		//多选时清空明细表，但没能实现。  
         		//var  gwrecord2= grid.getSelectionModel().getSelections(); 
				//if( gwrecord2.length>1){
                     // Ext.Msg.alert("信息提示","选择多个公文，阅处信息将不显示。");
                //     ycmxstore.removeAll(); //如果多选，则要清空传阅明细
				//}
         		  
         		  
         		  
         		  
        		//ycmxstore.load({params:{yc002:Store.getAt(b).get("dwbh00"),start:v_start, limit:v_limit}});
        		
        		 // listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit,yc002:1411}; //----
        	}
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 10,
            store: store,
            displayInfo: true
        })
    });

  
  
	
	
                 var root=new Ext.tree.AsyncTreeNode({  
                    id:'10',  
                    text:"传阅状态",  
                    leaf:false, 
					expanded: true, 
                    children:[  
                     {id:'0',text:'0_未传阅',leaf:true },
					 {id:'1',text:'1_待拟办',leaf:true},
					 {id:'2',text:'2_已拟办',leaf:true},
					 {id:'3',text:'3_待批示',leaf:true},
					 {id:'4',text:'4_已批示',leaf:true},
					 {id:'5',text:'5_待阅示',leaf:true},
					 {id:'6',text:'6_已阅示',leaf:true},
					 {id:'7',text:'7_正阅处',leaf:true},
					 {id:'8',text:'8_已办结',leaf:true},
					 {id:'9',text:'9_已归档',leaf:true}   
                    ]  
                });  
  
                var tree=new Ext.tree.TreePanel({  
                    //renderTo:"tree",  
                    animate:true,//以动画形式伸展,收缩子节点  
                    title:"公文状态",  
                    region : 'west',
                    collapsible:true,//如果为true，panel是可收缩的，并且有一个收起/展开按钮自动被渲染到它的头部工具区域  
                    rootVisible:true,//是否显示根节点  
                    autoScroll:true,  
					useArrows: true,
					split : true,
            　　　     //autoHeight:true,  
                    frame:true,
                    root:root,  
                    loader: new Ext.tree.TreeLoader(),  
                    width:125,
                    listeners:{        	
                        click:function(n){
        		        v_cybz = n.id;
        		        //v_lbname = n.text;
        		        
        		        ycmxstore.removeAll(); //点击后，先清空明细表中的信息
        		        //更新数据
        		        store.load({params:{start:v_start, limit:v_limit,gw002:v_cybz}});
        		 
        	           }
                     }
// 					listeners:{
// 						"click":function(node,e){
// 							alert(node.text+node.id);
// 						
// 						}
// 					
// 					
// 					
// 					}  
                });  
             //tree.getRootNode().expand();
     
 


	

   var combstorezl = new Ext.data.ArrayStore({
	    //url: 'gwlb.json',
	    // root: 'root',
	    //totalProperty: 'total',
	    fields : ['id', 'name'],
	    //autoLoad:true,
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
				
				
				xtype:'combo',
						hiddenName:'gw014',
						fieldLabel:'收发类别',
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
						    data : [['0','收文'],['1','发文']]
						})	
					
					
				},{
					        xtype : 'hidden',
							id:'lbtext',
							//width : 245,							
							//xtype : 'textfield',
							name:'lbname',
							fieldLabel:'机构类别',
							allowBlank : true,
							maxLength :50
							//enableKeyEvents:true
 
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
						value:new Date()
  
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '98%'
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
						width : 332,
						items:[{
							width : 255,
							xtype:'combo',
							hiddenName:'gw009',//hiddenName值为代码，如果为name时，值为文本显示 的值。
							mode:'local',
							fieldLabel:'公文分类1',
							triggerAction:'all',
							valueField:'id',
							displayField:'name',
							emptyText:'请选择',
							value:6,
							allowBlank : false,
							editable:false,
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
 
				},{
					
						id:'gw011',
						xtype:"datefield",
						name:'gw011',
						fieldLabel:"录入日期",
						format:'Y-m-d',
						allowBlank : false,
						value:new Date()
 

				}]
			}]
		},{
			xtype : 'textfield',
			name:'gw003',
			fieldLabel:'文件标题',
			allowBlank : false,
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
        html:'<iframe id="mediasArea" name="ifocus" src="../../help/gwfshelp.html" style="width:100%; height:100%;" frameborder="0"></iframe>',
        frame : true
        })	
var helpWindow = new Ext.Window({

                   title : '帮助窗口',
                    width : 650,
                    height :500,
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
		
	
	
	//--------------日期格式有问题导致不法正常显示 。
		var GwycObj = [
		{ name:'dwbh00',type:'string'},
		{ name:'yc001',type:'string'},   
		//{ name:'yc002',type:'string'},   
		{ name:'yc003',type:'string'},   
		{ name:'yc004',type:'string'},   
		{ name:'yc005',type:'string'},   
		{ name:'yc006',type:'date', mapping : 'yc006.time', dateFormat : 'time' },   
		{ name:'yc007',type:'string'},   
		{ name:'yc008',type:'string'},   
		{ name:'yc009',type:'string'},   
		{ name:'yc010',type:'date', mapping : 'yc010.time', dateFormat : 'time'},   
		{ name:'yc011',type:'int'},   
		{ name:'yc012',type:'string' },   
		{ name:'yc013',type:'string'},
		{ name:'username',type:'string'}
		   

	];
	
	var ycmxstore = new Ext.data.JsonStore({
	    url: 'gwyc_findPageGwyc.do',
	    root: 'root',
	    totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:15}},
	    fields: GwycObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}//----
	    
	});	
	
	
	
 	//var smm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var ycgrid = new Ext.grid.GridPanel({
        store: ycmxstore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			   //smm,
			   { header:'公文ID',width:50,sortable:true, align:'center',dataIndex:'dwbh00'}, 
			   { header:'转发人',width:50,align:'center',dataIndex:'yc008'},
			   { header:'发布日期',width:100,align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'),dataIndex:'yc010'},
			   { header:'收件人',width:50,sortable:true, align:'center',dataIndex:'username'}, 
			   	{id:'yc007', header:'收文意见',width:150,align:'center',dataIndex:'yc007'},
			   { header:'收阅日期',width:80,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'yc006'},   
			   { header:'状态',width:60,sortable:true, align:'center',dataIndex:'yc005',
				                 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '0': str='未阅';  break;
				                 	case '1': str='已阅';  break;
				                 	case '2': str='正办';  break;
				                 	case '3': str='已办';  break;
				                 	}
			                 return str;
				                 }			   
			   },
			   { xtype: 'actioncolumn',header:'操作',width:50,sortable:true,align:'center',menuDisabled : true,
			      items: [{
			      	        icon   : '../../img/btn/remove.gif',  // Use a URL in the icon config
			                tooltip: '删除发送信息。',
			                handler:function(ycgrid, rowIndex, colIndex){
			                	    var record = ycmxstore.getAt(rowIndex);
                    				var yc001 = record.get('yc001');
			                        //alert(yc001);
			                        
			                        
			                        Ext.MessageBox.confirm('删除提示', '是否删除该信息？', function(c) {
								       if(c=='yes'){
			                				Ext.Ajax.request({
								   			url : "gwyc_deleteGwyc.do",
								   			params:{ yc001 : yc001},
								   			success : function() {
								   				Ext.Msg.alert('信息提示','成功删除发送信息。'); 
								   				ycmxstore.reload();
								   			}
								   		});	
								       }
			                        });
			                }
			      }]
			      }

			   //{ header:'ID',width:50,sortable:true, align:'center',dataIndex:'yc001'},   
   				
				//{ header:'部门',width:250,sortable:true, align:'center',dataIndex:'yc003'},   
				//  
				   
				// 				    
 				   
 				//{ header:'转发人部门',width:150,align:'center',dataIndex:'yc009'},   
 				//   
				//{ header:'是否提醒',width:70,sortable:true, align:'center', dataIndex:'yc011'},   
 				//{ header:'转发人编号',width:150,align:'center', dataIndex:'yc012'},   
				//{ header:'收藏标志',width:60,sortable:true, align:'center',dataIndex:'yc013'}
				]       
//	             
	            
        }),
        //sm:sm,

        region:'center',
        height:'100%',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'yc007', //自动扩展列
		loadMask : true,	//加载时的遮罩
		 frame : true,
	    margins:"-1 -1 -1 -1"//,   //调整边距，第三位调整下边距，让边框超出交框，避免线条重叠后加粗，不美观。
	    
	    //无需分页，刷新是会显示所有明细。
//        bbar: new Ext.PagingToolbar({
//            pageSize: 15,
//            store: ycmxstore,
//            displayInfo: true
//        })
        });
        	
	
	
	
	
	
	
	
	
	
	
	
var mytable = new Ext.TabPanel({
                //renderTo: Ext.getBody(),
				 //layout:fit,
  
                region:'center',
                activeTab: 0,//激活的页数
				//autoHeigh:true,
                 //frame: true, //出现渲染的边框
					margins:"5 0 0 0", 
                items: [
               {
                  title:"传阅情况",
				  height:'100%',
				  layout:'border',
				  items:ycgrid
                 
               },
               {
                  title:"查看公文",

                  html:"此功能主要放置公文的附件，扫描件，文本件，数据文件"
               }
               ]
 
            });		
	
 
	
	
var Panelcenter = new Ext.Panel({
         layout : 'border',
		region:'center',
        defaults:{padding:'0 0 0 0'},
         //frame : true,//控制panel边框，无边框。
        //items:[grid,mxgrid]
 		items:[grid,mytable]
        })	
	
	
 
	
	
	
 	
	
	
	
	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			//items:[tree,searchForm,grid]
			 items:[tree,searchForm,Panelcenter]
			 			
			
		}]
	});	
	
	
	
	
	
	

});