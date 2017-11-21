var northRegion = new Ext.Panel({
    region:'north',
    height:60,
    html:"<div style='font-size:15pt;'>XX平台协同办公系统 (这里我放了一个div，编辑这个div就可以达到预想的效果)</h1>",
	  bbar:[	  
		  {pressed:false,text:'行政办公',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'数据通',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'客户管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'人力资源管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'进销存管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'客户管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:"tbfill"},
		  {pressed:false,text:'刷新',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {pressed:false,text:'帮助',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {pressed:false,text:'退出',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:"combo"}  
	 ]
});