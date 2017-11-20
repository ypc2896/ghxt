/*!
 * 菜单维护
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var v_lbid="0", v_lbname="", v_start=0, v_limit=20;
	Ext.form.Field.prototype.msgTarget = 'side';
	//功能菜单数据格式
	var MenuxxObj = [
	       		{ name:'menuid', type:'int'},
	       		{ name:'menaname', type:'string'},
	       		{ name:'pid', type:'int'},
	       		{ name:'menuurl', type:'string'},
	       		{ name:'menutype', type:'int'},
	       		{ name:'ordernum', type:'int'},
	       		{ name:'icon', type:'string'},
	       	];
	//功能菜单数据
	var stmenuid ="";
	var menustore = new Ext.data.JsonStore({
	    url: 'role_findMenuByMenuid.do',
	    fields: MenuxxObj,
	  listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit, menuid:stmenuid};}}
	});
	//功能树构建
	var tree = new Ext.tree.TreePanel({
		title:'功能菜单',
		region : 'west',
		width : 180,
		height: 535,
        minSize: 150,
        maxSize: 300,
        split : true,
		useArrows: true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        checked :false,
        containerScroll: true,
        frame:true,
        dataUrl: 'role_findMenu.do',
        root: {
            nodeType: 'async',
            text: '系统功能',
            draggable: false,
            id: '0'
        },
        listeners:{   //动态刷新form
        	click:function(n){
        	       //此处重点在于通过store的load函数的回调函数实现取值，然后通过setValue赋值。
	        	   stmenuid=  n.id;
	        	   menustore.load({
	        			   callback:function(option)
	        			   {
	        		         //些处取出的是一个json对象，可以直接用
	        		         var data1 = option[0].json; 
	        		         addForm.getForm().setValues(data1);
	        			   }
	        	   });
	        	   Ext.getCmp("new").setDisabled(false);
	        	   Ext.getCmp("modify").setDisabled(false);
	        	   Ext.getCmp("delete").setDisabled(false);
	              /*	通过AJAX获取数据。
	        	   Ext.Ajax.request({
			   			url : "role_findMenuByMenuid.do",
			   			params:{ menuid : n.id },
			   			success : function(obj) {
			   				if(obj.responseText){
        	          json串，转换为对象，与之相对应的是encode
			   				    var data = Ext.util.JSON.decode(obj.responseText);
			   				    addForm.getForm().findField("menuid").setValue(data[0].menuid);
			   				    addForm.getForm().findField("menuname").setValue(data[0].menuname);
			   				    addForm.getForm().findField("menutype").setValue(data[0].menutype);
			   				    addForm.getForm().findField("pid").setValue(data[0].pid);
			   				    addForm.getForm().findField("menuurl").setValue(data[0].menuurl);
			   				    addForm.getForm().findField("icon").setValue(data[0].icon);
			   				}
			   				
			   			}
	        	   });*/
        	}
        }
	});
	
	//展开节点
	tree.getRootNode().expand();
 
    //菜单类型数据：
    //我们分三步走：
    //第一步：提供数据：
        var data=[[1,'分类菜单'],[0,'功能菜单']];
   //第二步：导入到store中：
        var cdflstore = new Ext.data.SimpleStore({
            fields: ['value', 'text'],
            data : data
       });
    //第三步 :把store托付给comboBox的store

	//功能表单
    var cllx ="xz";
    var addForm = new Ext.FormPanel({	
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : true,
		region:'center',
		x:180,
		y:0,
		height:350,
		width:975,
		defaults : {
			anchor : '40%'
		},
		items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{anchor : '95%'},
				},
				{
					id:'menuid',
					width : 145,
					xtype : 'textfield',
					name:'menuid',
					fieldLabel:'菜单ID',
					maxLength :50,
					enableKeyEvents:true,
					readOnly :true
				},
				{
					xtype : 'textfield',
					name:'menuname',
					id:"menuname",
					fieldLabel:'功能名称',
					allowBlank : false,
					SelectOnFocus: true,
					maxLength :20,
					readOnly :true
				},
				{
					xtype : 'textfield',
					name:'pid',
					id:'pid',
					fieldLabel:'上级ID',
					maxLength :20,
					readOnly :true
				},
				{
					xtype : 'textfield',
					name:'menuurl',
					id:'menuurl',
					fieldLabel:'URL',
					maxLength :50,
					readOnly :true
				},
				{
					xtype:'combo',
					id:'menutype',
					hiddenName:'menutype',
					fieldLabel:'菜单类型',
					mode: 'local',
					triggerAction: 'all',
					valueField :'value',
					displayField: 'text',
					emptyText:'请选择菜单类型...',
					allowBlank : false,
					editable : false,
					store : cdflstore,
					disabled:true
			    },
				{
					xtype : 'textfield',
					name:'icon',
					id:'icon',
					fieldLabel:'图标',
					maxLength :10,
					readOnly :true
	}],
	buttonAlign : 'center',
    buttons:[{
    	text:'新增',
    	id: "new",
    	disabled: true,
    	handler:function(){
    	czlx="xz";
    	addForm.getForm().findField("menuname").getEl().dom.readOnly = false;;
    	addForm.getForm().findField("menuurl").getEl().dom.readOnly = false;;
    	addForm.getForm().findField("menutype").setDisabled(false);
    	addForm.getForm().findField("icon").getEl().dom.readOnly = false;; 
    	addForm.getForm().reset(); 
    	addForm.getForm().findField("menutype").clearValue();
    	addForm.getForm().findField("pid").setValue(stmenuid);
    	Ext.getCmp("save").setDisabled(false);
    	}
    },{
    	text:'修改',
    	id: "modify",
    	disabled: true,
    	handler:function(){
    	czlx="xg";
    	addForm.getForm().findField("menuname").getEl().dom.readOnly = false;;
    	addForm.getForm().findField("menuurl").getEl().dom.readOnly = false;;
    	addForm.getForm().findField("menutype").setDisabled(false);
    	addForm.getForm().findField("icon").getEl().dom.readOnly = false;; 
    	Ext.getCmp("save").setDisabled(false);
    }
    },{
    	text:'保存',
    	id: "save",
    	disabled: true,
    	handler:function(){
    	if (addForm.getForm().isValid()) {
    		addForm.getForm().submit({
				url : 'menu_saveOrUpdateMenu.do',
				success : function(form, action) {
					
					//创建新节点
					var nodeid = "";
					if(czlx=="xz"){
						nodeid = action.result.message;
						Ext.Msg.alert('信息提示','新增成功');
						form.findField("menuid").setValue(nodeid);
					}else{
						nodeid = form.findField("menuid").getValue();
						Ext.Msg.alert('信息提示',action.result.message);
					}
					var node = new Ext.tree.TreeNode({
						id:nodeid,
						text:form.findField("menuname").getValue(),
						iconCls:'menu-11',
						checked :true,
						leaf:true
					});
					//修改父节点
					var pnode = tree.getNodeById(form.findField("pid").getValue());
					if(czlx=="xz"){
					 pnode.appendChild(node);
					}else
					{
					 var nodeold = tree.getNodeById(form.findField("menuid").getValue())
					 pnode.replaceChild(node,nodeold);	
					}
					pnode.leaf=false;
					pnode.expand();
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
    },{
    	text:'删除',
    	id: "delete",
    	disabled: true,
    	handler:function(){
    	if(stmenuid){
    		var node = tree.getNodeById(stmenuid);
    		var pnode = node.parentNode;
			Ext.MessageBox.confirm("删除提示", "是否删除\""+node.text+"\"菜单？", function(c) {
			   if(c=="yes"){
			   		Ext.Ajax.request({
			   			url : "menu_deleteMenu.do",
			   			params:{ menuid : stmenuid },
			   			success : function(o) {
			   				if(o.responseText=="false"){
			   					Ext.Msg.alert("信息提示","该菜单下有信息，不能删除");
			   				}else{
			   					stmenuid = "0";     //设为默认节点
				   				v_lbname = "";
				   				Ext.getCmp("delete").setDisabled(true);
				   				pnode.removeChild(node);	//删除节点
				   				if(pnode.childNodes.length==0){	//如果无子节点则修改属性
				   					pnode.leaf = true;
				   				}
				   				addForm.getForm().reset(); 
			   				}
			   			}
			   		});
			    }
			});
		}
    }
    }]
	});
	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			items:[tree,addForm]
		}]
	});

});