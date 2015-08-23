var checkboxSelectionModel_DesktopIcons;
function getGridData()
{
	var data = [['1','控制面版', '控制开心网系统的某些行为，例如，拖动桌面图标，保存图标状态等'],
	            ['2','选择桌面图标', '选择要在桌面上显示的图标'],
	            ['3','电子相册', '上传和管理相片，并和朋友分享'] 
	            ];
	var store = new Ext.data.Store({
		proxy: new Ext.data.MemoryProxy(data),
		reader:new Ext.data.ArrayReader({},[
	          {name:'id'},
		      {name:'desktop-icons'},
		      {name:'description'}
		      ])
		}); 
	store.load();
	return store;
}

function createGridHeader()
{
	var sm = new Ext.grid.CheckboxSelectionModel();
	checkboxSelectionModel_DesktopIcons = sm;
	var cm = new Ext.grid.ColumnModel([	  
	    sm,
	    {header:'索引', dataIndex:'id', width:40},
	    {header:'功能图标', dataIndex:'desktop-icons'},
	    {header:'描述',dataIndex:'description',width:280}
    ]);
	return [cm, sm];
}

function createGrid()
{
	var store = getGridData();
	var cm_sm = createGridHeader();
	var grid = new Ext.grid.GridPanel({
		store:store,
		cm:cm_sm[0],
		sm:cm_sm[1]
	});
	 

	return grid;
}

DesktopIconsWindow = Ext.extend(Ext.app.Module,
{
	id : 'desktop-icons',
	init : function()
	{
		this.launcher =
		{   
			text : '桌面图标',
			iconCls : 'desktopicons',
			handler : this.createWindow,
			scope : this
		}		
	},

	createWindow : function()
	{		
		if (bLogin == false || locked == false)
			return;
		myDesktop = this.app.getDesktop();
		myApp = this.app;
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('desktop-icons');
		if (!win)
		{
			win = desktop.createWindow(
			{
				id : 'desktop-icons',
				title : '桌面图标',
				width : 450,
				height : 300,
				layout:'fit',
				iconCls : 'desktopicons',
				shim : false,
				animCollapse : false,
				constrainHeader : true,				
				items :
				[
			    //  建立一个Grid
			    createGrid()
				],
				buttons :[
				          {text:'确定',
				           handler:function(){
				        	  for(var i = 0; i < desktopIcons.length; i++)
				        	  {
				        		  if(checkboxSelectionModel_DesktopIcons.isSelected(i))
				        		      desktopIcons[i].element.style.display = '';
								  else
									  desktopIcons[i].element.style.display = 'none';				        		  
				        	  } // for				        	    				 
				        	  win.close();
				            } // function	  
				          },  // component 
				          
				          {text:'取消',handler:function(){
				        	  
				        	  win.close();
				        	  
				          }} ]   

			});
		}
		
		win.on("afterlayout", afterlayoutEvent);
		win.show();
      
	}
});
function afterlayoutEvent()
{
  
		for(var i = 0; i < desktopIcons.length; i++)
		{
			if(desktopIcons[i].element.style.display == '')
			    checkboxSelectionModel_DesktopIcons.selectRow(i, true);	
		} 


}
