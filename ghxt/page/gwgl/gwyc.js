/*!
 * 公文信息维护
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	 var  v_start=0, v_limit=10,v_gwgl="jsgw";
	 
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
			title:"接收公文查询",
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
					text:'发文日期:'
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
							hiddenName:'yc013',
							fieldLabel:'收藏分别',
							mode: 'local',
							triggerAction: 'all',
							valueField :'value',
							displayField: 'text',
							value:'9',
							editable : false,
							store : new Ext.data.SimpleStore({
							    fields: ['value', 'text'],
							    data : [['9','全部'],['0','未收藏'],['1','已收藏']]
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
							ycmxstore.removeAll();//清空明细表是的数据。
							if (f.isValid()) {
								var params = f.getValues();
								params.start=0;
								params.limit =20;
								params.gwgl ='jsgw';
								ycstore.load({params:params});
							}
						}
					}]
				}]
			}]
		}]
	});

	
	//日期 的格式问题导致不法显示。数据 库中日期格式存在问题

	var GwycObj = [

		{ name:'dwbh00', type:'string'},

		{ name:'bz0000', type:'string'},

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
   
		{ name:'gw013',type:'string'},   
		{ name:'gw014',type:'string'},	
	
		{ name:'yc001',type:'string'},   
		{ name:'yc002',type:'string'},   
		{ name:'yc003',type:'string'},   
		{ name:'yc004',type:'string'},   
		{ name:'yc005',type:'string'},  
		 
		{ name:'yc006',type:'date', mapping : 'yc006.time', dateFormat : 'time' },   //日期字段不能为空值，为空值时日期字段显示 错误。
		{ name:'yc007',type:'string'},   
		{ name:'yc008',type:'string'},   
		{ name:'yc009',type:'string'},   
		{ name:'yc010',type:'date', mapping : 'yc010.time', dateFormat : 'time'},   
		{ name:'yc011',type:'int'},   
		{ name:'yc012',type:'string' },   
		{ name:'yc013',type:'string'}   
		   

	];
	/**1
	 * url: 'gwyc_findPagegwyc.do',--------1
	 */
	var ycstore = new Ext.data.JsonStore({
	    url: 'gwyc_findPageGwyc.do',
	    root: 'root',
	    totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:15}},
	    fields: GwycObj,
	    
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}//----
	});
	
	var sm=new Ext.grid.CheckboxSelectionModel();//复选框定义
    var grid = new Ext.grid.GridPanel({
        store: ycstore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : false},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
			   sm,
				//{ header:'ID',width:50,sortable:true, align:'center',dataIndex:'yc001'},   
   				//{ header:'公文ID',width:50,sortable:true, align:'center',dataIndex:'dwbh00'},
   				{ header:'编号',width:50,sortable:true, align:'center',dataIndex:'gw001'},
   				{ header:'文号',width:150,sortable:true, align:'center',dataIndex:'gw001'},
   				{id:'yc003', header:'公文标题',width:350,sortable:true, align:'center',dataIndex:'gw003'},
   				{ header:'发文单位',width:150,sortable:true, align:'center',dataIndex:'gw005'},
   				{ header:'发文日期',width:70,sortable:true, align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'), dataIndex:'gw006'},
//				{ header:'部门',width:250,sortable:true, align:'center',dataIndex:'yc003'},   
//				{ header:'接收人ID',width:150,sortable:true, align:'center',dataIndex:'yc004'}, 
   				 
				{ header:'公文状态',width:60,sortable:true, align:'center',dataIndex:'gw002',
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
				{ header:'阅处状态',width:100,sortable:true, align:'center',dataIndex:'yc005',
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
//				{ header:'阅处日期',width:70,sortable:true, align:'center', dataIndex:'yc006',
//				 renderer : Ext.util.Format.dateRenderer('Y-m-d')
//
//				},   
 				//{ header:'阅处意见',width:150,align:'center',dataIndex:'yc007'},   
 				//{ header:'转发人',width:150,align:'center',dataIndex:'yc008'},   
 				//{ header:'转发人部门',width:150,align:'center',dataIndex:'yc009'},   
 				//{ header:'转发日期',width:150,align:'center',renderer : Ext.util.Format.dateRenderer('Y-m-d'),dataIndex:'yc010'},   
				//{ header:'是否提醒',width:70,sortable:true, align:'center', dataIndex:'yc011'},   
 				//{ header:'转发人编号',width:150,align:'center', dataIndex:'yc012'},   
				{ header:'收藏标志',width:80,sortable:true, align:'center',dataIndex:'yc013',
								                 renderer: function(v){
				                 	var str="";
				                 	switch(v){ 
				                 	case '0': str='未收藏';  break;
				                 	case '1': str='已收藏';  break;

				                 	}
			                 return str;
				                 }
				
				}]       
//	             
	            
        }),
       sm:sm,
        region:'north',
         height:'300',
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        autoExpandColumn: 'yc003', //自动扩展列
		loadMask : true,	//加载时的遮罩
		frame : true,
	    margins:"0 0 0 0",   //调整边距，第三位调整下边距
	    //style:'border:1px solid',//给表格面板外框添加边框线
        title:'接收公文管理',
        iconCls:'menu-51',
        
        tbar:[{
        
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
        		 
        		//ycmxstore.removeAll();//清空明细表是的数据。
         		//alert(b);
         		  ycmxstore.load({params:{yc002:gwrecord1.get("dwbh00"),start:v_start, limit:v_limit}});
         		
        		//ycmxstore.load({params:{yc002:Store.getAt(b).get("dwbh00"),start:v_start, limit:v_limit}});
        		
        		 // listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit,yc002:1411}; //----
        	}
        },       
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: ycstore,
            displayInfo: true
        })
    });

  
  
	
	
                 var root=new Ext.tree.AsyncTreeNode({  
                    id:'10',  
                    text:"阅处状态",  
                    leaf:false, 
					expanded: true, 
                    children:[  
                     {id:'0',text:'0_未阅处',leaf:true},
					 {id:'1',text:'1_已阅处',leaf:true},
					 {id:'2',text:'2_正办理',leaf:true},
					 {id:'3',text:'3_已办结',leaf:true}
 
                    ]  
                });  
  
                var tree=new Ext.tree.TreePanel({  
                    //renderTo:"tree",  
                    animate:true,//以动画形式伸展,收缩子节点  
                    title:"接收公文分类",  
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
                    width:150,
                    listeners:{        	
                        click:function(n){
        		        v_cybz = n.id;
        		        //v_lbname = n.text;
        		        ycmxstore.removeAll();//清空明细表是的数据。
        		        //更新数据
        		        //Ext.Msg.alert('信息提示',v_cybz); 
        		        ycstore.load({params:{start:v_start, limit:v_limit,yc005:v_cybz,gwgl:"jsgw"}});
        		 
        	           }
                     }

                });  
             //tree.getRootNode().expand();
     
 


	
 
	
	 
var myPanel = new Ext.Panel({
        layout : 'fit',
        defaults:{padding:'1 0 0 0'},
        html:'<iframe id="mediasArea" name="ifocus" src="../../help/gwfshelp.html" style="width:100%; height:100%;" frameborder="0"></iframe>',
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