ControlPanelWindow = Ext
		.extend(
				Ext.app.Module,
				{
					id : 'settings',
					init : function()
					{
						this.launcher =
						{
							text : '控制面版',
							iconCls : 'settings',
							handler : this.createWindow,
							scope : this
						}
					},

					createWindow : function()
					{
						if (bLogin == false)
							return;
						myDesktop = this.app.getDesktop();
						myApp = this.app;
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('settings');
						if (!win)
						{
							win = desktop
									.createWindow(
									{
										id : 'settings',
										title : welcomeMsg,
										width : 300,
										autoHeight : true,
										iconCls : 'settings',
										shim : false,
										animCollapse : false,
										constrainHeader : true,
										items :
										[
									// 定义用于控制图标拖动的checkbox                       
												new Ext.form.RadioGroup(
														{
															xtype : 'checkboxgroup',
															style : 'margin-top:20px;margin-left:20px',
															listeners :
															{

																change : function(
																		radioGroup,
																		radio)
																{

																	if (radio
																			.getId() == "lock")
																	{
																		for ( var i = 0; i < desktopIcons.length; i++)
																		{
																			desktopIcons[i].dd
																					.lock();

																		}
																		locked = true;
																	} else if (radio
																			.getId() == "move")
																	{
																		for ( var i = 0; i < desktopIcons.length; i++)
																		{
																			desktopIcons[i].dd
																					.unlock();
																		}

																		locked = false;

																	}
																}
															},
															columns : 2,
															items :
															[
																	{
																		boxLabel : '锁定桌面图标',
																		id : 'lock',
																		name : 'desktop-icon',
																		checked : locked == true
																	},
																	{
																		boxLabel : '移动桌面图标 ',
																		id : 'move',
																		name : 'desktop-icon',
																		checked : locked == false
																	}

															]
														}),
												// 定义重新排列图标的按钮
												{
													xtype : 'button',
													text : '重新排列桌面图标',
													style : 'margin-top:20px;margin-left:20px',
													handler : function()
													{
														var rowBegin = 10, colBegin = 10;
														for ( var i = 0; i < desktopIcons.length; i++)
														{
															if (desktopIcons[i].element.style.display == '')
															{
																desktopIcons[i].element.style.left = colBegin;
																desktopIcons[i].element.style.top = rowBegin;
																colBegin += 48 + 30;
																if((colBegin + 48 + 30) > document.body.clientWidth)
																{
																	colBegin = 10;
																	rowBegin += 48 + 30;
																}
															}
														}
														
													}

												},
										// 定义保存桌面图标状态的按钮
										{
											xtype : 'button',
											text : '保存桌面图标状态',
											style : 'margin-top:20px;margin-left:20px;margin-bottom:20px',
											handler : function()
											{			
													
											   var settings = "";
											   for(var i = 0; i < desktopIcons.length; i++)
											   {
												   if(desktopIcons[i].element.style.display == '')
												   {
													   settings += i + ":" + desktopIcons[i].element.style.left.replace(/px/gi,'') + ":" + desktopIcons[i].element.style.top.replace(/px/gi,'') + ",";
												   }
											   }
												Ext.Ajax.request(
													{
														url : 'saveDesktopIconsSetting.action?settings=' + settings,
														success : function(response)
														{
															showInfoDialog(response.responseText);
														}
													})
												


											}

										}
								]
									

									});
						}

						win.show();
					}
				});
