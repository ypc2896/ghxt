 Ext.onReady(function(){
//         var panel=new Ext.Panel({
           new Ext.Viewport({
             layout:'border',
            
//             width:1200,
//             height:700,
//             applyTo:'panel',
               items:[
               {
                  title:'North Panel',
                  html:'上边',
                  region:'north',
                  height:50
               },
               {
                  title:'South Panel',
                  html:'下边',
                  region:'south',
                  height:50
               
               },
               {
                  title:'East Panel',
                  html:'右边',
                  region:'east',
                  height:100
               },
               {
                  title:'West Panel',
                  html:'左边',
                  region:'west',
                  height:100
               },
               {
                  title:'Main Content',
                  html:'中间',
                  region:'center'
               }
               
             ]
             
         });
         
       });
 