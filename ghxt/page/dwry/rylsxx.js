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
						fieldLabel:"号码/名称",
						anchor:"90%"
					}]
				},{
					width:180,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'sae052',
							fieldLabel:'业务类型',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['','全部'],['0','互助帮扶'],['1','先行垫付'],['2','特殊救助']]
							}),
							width:80
					}]
				},{
					width:180,
					labelWidth:60,
					items:[{
							xtype:'combo',
							hiddenName:'sae005',
							fieldLabel:'期别',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							value:'13',
							displayField: 'text',
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['','全部'],['13','第13期'],['12','第12期'],['11','第11期']]
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

	
	

	var RylsxxObj = [
			{name:'sae041',type:'string'},
			{name:'aac001',type:'string'},
			{name:'aab001',type:'string'},
			{name:'sae031',type:'string'},
			{name:'sae005',type:'string'},
			{name:'sae033',type:'string'},
			{name:'sae034',type:'string'},
			{name:'sae035',type:'string'},
			{name:'sae036',type:'string'},
			{name:'sae037',type:'string'},
			{name:'sae038',type:'string'},
			{name:'sae039',type:'string'},
			{name:'sae040',type:'string'},
			{name:'sae042',type:'string'},
			{name:'sae029',type:'string'},
			{name:'sae030',type:'string'},
			{name:'sae043',type:'string'},
			{name:'sae044',type:'string'},
			{name:'sae047',type:'string'},
			{name:'sae048',type:'string'},
			{name:'sae049',type:'string'},
			{name:'sae050',type:'string'},
			{name:'sae051',type:'string'},
			{name:'sae052',type:'string'},
			{name:'sae045',type:'float'}, 
			{name:'sae046',type:'float'}, 
			{name:'sae032',type:'float'}, 
			{name:'sae053',type:'float'}, 
			{name:'sae054',type:'float'}, 
			{name:'sae055',type:'float'}, 
			{name:'sae056',type:'float'}, 
			{name:'sae057',type:'float'}, 
			{name:'sae058',type:'float'}, 
			{name:'sae059',type:'float'}, 
			{name:'sae060',type:'float'}, 
			{name:'sae061',type:'float'}, 
			{name:'sae062',type:'float'}, 
			{name:'sae063',type:'float'}, 
			{name:'sae064',type:'float'}, 
			{name:'sae065',type:'float'}, 
			{name:'sae066',type:'float'}, 
			{name:'sae067',type:'float'}, 
			{name:'sae068',type:'float'}, 
			{name:'sae069',type:'float'}, 
			{name:'sae070',type:'float'}, 
			{name:'sae099',type:'string'},
			{name:'sae073',type:'string'},
			{name:'sae074',type:'string'},
			{name:'sae075',type:'string'},
			{name:'sae076',type:'string'},
			{name:'sae077',type:'string'},
			{name:'sae078',type:'string'},
			{name:'aac002',type:'string'},
			{name:'aac003',type:'string'},
			{name:'aac006',type:'float'}
 	];
	/**1
	 * url: 'dwxx_findPageDwxx.do',--------1
	 */
	var store = new Ext.data.JsonStore({
	    url: 'rylsxx_findPageRylsxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:10}},
	    fields: RylsxxObj,
	    
	    listeners:{beforeload:function(a){
	    // var
	    	
		var f = searchForm.getForm();  //取查询表单的各项值。
		ycmxstore.removeAll();//将明细表中的信息置为空。
		lsfjmxstore.removeAll();//将明细表中的信息置为空。
		v_cybz="100";   //将左侧公文状态的全局变量设为全部
		if (f.isValid()) {
			var params = f.getValues();
			params.start=v_start;
			params.limit =v_limit;}
			//store.load({params:params});
	    a.baseParams={start:v_start, limit:v_limit,gwgl:v_gwgl,sae075:v_cybz,startdate:params.startdate,enddate:params.enddate,sae005:params.sae005};//aae062为期别，期别查询与缴费状态树邦定。
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
	            {header: '缴费ID', width: 50,sortable:true, align:'center', dataIndex: 'sae041'},
	            {header: '期别', width: 40,sortable:true, align:'center', dataIndex: 'sae005'},
	            {header: '单位编号', width: 60,sortable:true,  align:'center', dataIndex: 'aab001'},
	            {id:'sab033',header:'单位名称',width:150,sortable:true, align:'center',dataIndex:'sae033'},
	            {header: '姓名', width: 60, sortable:true, align:'center',dataIndex: 'aac003'},
	            {header:'身份证号',width:140,sortable:true, align:'center',dataIndex:'aac002'},
	            {header: '医保', width: 30, sortable:true, align:'center',dataIndex: 'sae077',
									renderer : function(v) {return v=="0"?"否":"是";}},
				{header:'医保卡号',width:60,sortable:true, align:'center',dataIndex:'sae043'},
				{header:'农工',width:30,sortable:true, align:'center',dataIndex:'sae076',
									renderer : function(v) {return v=="0"?"否":"是";}}, 
				
								
				{header:'医院',width:60,sortable:true, align:'center',dataIndex:'sae047'},
				{ header:'住院',width:50,sortable:true, align:'center',dataIndex:'sae073'},
				{ header:'出院',width:50,sortable:true, align:'center',dataIndex:'sae074'},
				{header:'登记类型',width:60,sortable:true, align:'center',dataIndex:'sae052',
									renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '0': str='互助';  break;
				                 	case '1': str='先行垫付';  break;
				                 	case '2': str='特殊救助';  break;

				                 	}
			                 		return str;
				                 	}},   
				//{ header:'发文日期',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw006'},   
				{ header:'医疗总额',width:60,align:'center',dataIndex:'sae032'},   
				{ header:'个账支付',width:60,align:'center',dataIndex:'sae055'},   
				{ header:'自付金额',width:60,align:'center',dataIndex:'sae056'},   
				    
				//{ header:'录入日期',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw011'},   
				//{ header:'归档日期',width:150,align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw012'},   
				   
				
				{ header:'审批',width:60,align:'center',sortable:true,dataIndex:'sae075',
				                 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '0': str='未申报';  break;
				                 	case '1': str='待审核';  break;
				                 	case '2': str='审核中';  break;
				                 	case '3': str='已审核';  break;
				                 	case '4': str='待复核';  break;
				                 	case '5': str='已复核';  break;
				                 	case '6': str='待审批';  break;
				                 	case '7': str='已审批';  break;
				                 	case '8': str='待支付';  break;
				                 	case '9': str='已支付';  break;
				                 	case '10': str='已存档';  break;
				                 	}
			                 		return str;
				                 	}
				}
				//{header: '经办人', width:50, sortable:true, align:'center',dataIndex: 'aae064'},
	            //{header: '经办日期', width:50, sortable:true, align:'center',dataIndex: 'aae065'},
				//{id:'aae085',header:'备注',width:40,align:'center',sortable:true,dataIndex:'aae085'}
				]
				
				
				
				       
 
	            
        }),
        sm:sm,
        region:'north',
         
        height:'300',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'sab033', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 0 0",   //调整边距，第三位调整下边距
	    //style:'border:1px solid',//给表格面板外框添加边框线
        title:'病案信息',
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
         		  ycmxstore.load({params:{aac002:gwrecord1.get("aac002"),start:v_start, limit:v_limit}});
         		
         		  lsfjmxstore.load({params:{sae041:gwrecord1.get("sae041"),start:v_start, limit:v_limit}});
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
                    id:'100',  
                    text:"病案状态",  
                    leaf:false, 
					expanded: true, 
                    children:[  
                     {id:'0',text:'0_未申报',leaf:true },
					 {id:'1',text:'1_待审核',leaf:true},
					 {id:'2',text:'2_审核中',leaf:true},
					 {id:'3',text:'3_已审核',leaf:true},
					 {id:'4',text:'4_待复核',leaf:true},
					 {id:'5',text:'5_已复核',leaf:true},
					 {id:'6',text:'6_待审批',leaf:true},
					 {id:'7',text:'7_已审批',leaf:true},
					 {id:'8',text:'8_待支付',leaf:true},
					 {id:'9',text:'9_已支付',leaf:true},
					 {id:'10',text:'10_已存档',leaf:true}   
                    ]  
                });  
  
                var tree=new Ext.tree.TreePanel({  
                    //renderTo:"tree",  
                    animate:true,//以动画形式伸展,收缩子节点  
                    title:"病案审批流程",  
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
        		        store.load({params:{start:v_start, limit:v_limit,sae075:v_cybz}});
        		 
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
var RyjfxxObj = [
			{ name:'aae001',type:'string'}, //缴费ID                          
			{ name:'aac001',type:'string'}, //个人编号                        
			{ name:'aab001',type:'string'}, //单位编号                        
			{ name:'aae005',type:'string'}, //缴费期别                        
			{ name:'aae061',type:'string'}, //单位汇总表ID                    
			{ name:'aae007',type:'string'}, //是否实收0未实收，1财务已实收    
			{ name:'aae008',type:'string'}, //经办人                          
			{ name:'aae009',type:'string'}, //经办日期                        
			{ name:'aab003',type:'string'}, //单位名称                        
			{ name:'aac009',type:'string'}, //医保卡号                        
			{ name:'aac002',type:'string'}, //身份号码                        
			{ name:'aac003',type:'string'}, //姓名                            
			{ name:'aac004',type:'string'}, //性别                            
			{ name:'aae010',type:'string'}, //报名表序号                      
			{ name:'aae011',type:'string'}, //报名备注                        
			{ name:'aae012',type:'string'}, //填表人                          
			{ name:'aae013',type:'string'}, //填表日期                        
			{ name:'aae014',type:'string'}  //报名表盖章办事处名称            
 
  

	];
	
	var ycmxstore = new Ext.data.JsonStore({
	    url: 'ryjfxx_findPageRyjfxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:15}},
	    fields: RyjfxxObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}//----
	    
	});	
	
	
	
 	//var smm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var ycgrid = new Ext.grid.GridPanel({
        store: ycmxstore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			   //smm,
			   { header:'期别',width:60,align:'center',dataIndex:'aae005'},
			    
	            { header: '个人编号', width: 60,sortable:true, align:'center', dataIndex: 'aac001'},
	            {  id:'aac002',header: '身份证号', width: 100,sortable:true,  align:'center', dataIndex: 'aac002'}, 
				{ header:'姓名',width:50,sortable:true, align:'center',dataIndex:'aac003'},  
				{ header:'性别',width:60,sortable:true, align:'center',dataIndex:'aac004',
									renderer : function(v) {return v=="0"?"女":"男";}}, 
				{ header:'医保卡号',width:60,sortable:true, align:'center',dataIndex:'aac009'},
				{ header:'实收',width:50,align:'center',dataIndex:'aae007',
									renderer : function(v) {return v=="0"?"否":"是";}}]
       }),
        //sm:sm,

        region:'center',
        height:'100%',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'aac002', //自动扩展列
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
        	
	
	
	
	
	
	
	

        
        
        
        
        
        
    //理算附件    
        
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
	
	var lsfjmxstore = new Ext.data.JsonStore({
	    url: 'rylsfjxx_findPageRylsfjxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:15}},
	    fields: RylsfjxxObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}//----
	    
	});	
	
	
	
 	//var smm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var lsfjgrid = new Ext.grid.GridPanel({
        store: lsfjmxstore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			   //smm,
			     {header: 'ID号', width: 35,align:'center', dataIndex: 'sae201'},
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
        //sm:sm,

        region:'center',
        height:'100%',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'sae205', //自动扩展列
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
                  title:"个人缴费情况",
				  height:'100%',
				  layout:'border',
				  items:ycgrid
                 
               },
               {
                  title:"附件材料",
				  height:'100%',
				  layout:'border',
				  items:lsfjgrid
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