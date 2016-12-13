/*!
 * DLShouWen Grid v1.2.1
 *
 * includes: jquery, bootstrap, fontawesome, My97 DatePicker
 * Copyright 2016, http://os.dlshouwen.com/grid, http://www.dlshouwen.com
 */
(function($) {
	if(!$.fn.dlshouwen.grid.lang){
		$.fn.dlshouwen.grid.lang = {};
	}
	$.fn.dlshouwen.grid.lang['zh-cn'] = {
		errors : {
			ajaxLoadError : '数据加载失败，请检查您的条件配置是否存在错误。'
		},
		buttons : {
			close : '<i class="fa fa-times"></i>&nbsp;&nbsp;关闭'
		},
		extraColumn : {
			open : '<i class="fa fa-plus"></i>',
			close : '<i class="fa fa-minus"></i>'
		},
		sortColumn : {
			asc : '<i class="fa fa-sort-asc"></i>',
			desc : '<i class="fa fa-sort-desc"></i>'
		},
		toolbar : {
			refresh : '<i class="fa fa-refresh"></i>',
			refreshTitle : '刷新数据',
			fastQuery : '<i class="fa fa-search"></i>',
			fastQueryTitle : '快速查询',
			advanceQuery : '<i class="fa fa-filter"></i>',
			advanceQueryTitle : '高级查询',
			exportExcel : '<i class="fa fa-file-excel-o"></i> EXCEL',
			exportExcelTitle : '导出EXCEL文件',
			exportCsv : '<i class="fa fa-file-o"></i> CSV',
			exportCsvTitle : '导出CSV文件',
			exportPdf : '<i class="fa fa-file-pdf-o"></i> PDF',
			exportPdfTitle : '导出PDF文件',
			exportTxt : '<i class="fa fa-file-o"></i> TXT',
			exportTxtTitle : '导出TXT文件',
			print : '<i class="fa fa-print"></i>',
			printTitle : '打印'
		},
		pageInfo : {
			nothing : '无查询记录...',
			info : '查询出 {recordCount} 条记录，每页 {pageSize} 条，共  {pageCount} 页',
			firstPage : '<i class="fa fa-angle-double-left"></i>',
			prevPage : '<i class="fa fa-angle-left"></i>',
			nextPage : '<i class="fa fa-angle-right"></i>',
			lastPage : '<i class="fa fa-angle-double-right"></i>',
			firstPageTitle : '第一页',
			prevPageTitle : '上一页',
			nextPageTitle : '下一页',
			lastPageTitle : '最后一页',
			alreadyFirstPage : '已经是第一页了',
			alreadyLastPage : '已经是最后一页了',
			nowPage : '{nowPage}',
			nowPageTitle : '第 {nowPage} 页',
			errors : {
				notANumber : '您输入的内容不是数字',
				maxPageSize : '每页显示数量不得超过 {pageSizeLimit} 条，已还原为原设置'
			}
		},
		fastQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;快速查询',
			codeTableSelectAll : '全部',
			input : '请输入',
			selectStart : '请选择开始',
			selectEnd : '请选择结束',
			inputStart : '请输入开始',
			inputEnd : '请输入结束',
			buttons : {
				reset : '<i class="fa fa-reply"></i>&nbsp;&nbsp;参数重置',
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;执行查询'
			}
		},
		advanceQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;高级查询',
			plan : {
				title : '查询方案',
				advanceQueryName : '方案名称：',
				advanceQueryNamePlaceHoder : '请输入方案名称',
				remark : '备注：',
				remarkPlaceHoder : '请输入备注',
				buttons : {
					addAdvanceQuery : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增',
					addAdvanceQueryTitle : '新增高级查询方案',
					editAdvanceQuery : '<i class="fa fa-pencil"></i>&nbsp;&nbsp;编辑',
					editAdvanceQueryTitle : '编辑高级查询方案',
					copyAdvanceQuery : '<i class="fa fa-copy"></i>&nbsp;&nbsp;复制',
					copyAdvanceQueryTitle : '复制高级查询方案',
					deleteAdvanceQuery : '<i class="fa fa-trash-o"></i>&nbsp;&nbsp;删除',
					deleteAdvanceQueryTitle : '删除高级查询方案'
				}
			},
			condition : {
				title : '查询条件',
				table : {
					sequence : '序号',
					operation : '操作',
					leftParentheses : '左括号',
					field : '字段',
					condition : '条件',
					value : '值',
					rightParentheses : '右括号',
					logic : '逻辑',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : '条件上移',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : '条件下移',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : '删除条件'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增一行',
					insertTitle : '新增一行',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;清空条件',
					clearTitle : '清空条件'
				},
				errors : {
					fieldMustSelect : '查询条件中第 {sequence} 行的字段信息必须选择！',
					conditionMustSelect : '查询条件中第 {sequence} 行的条件信息必须选择！',
					conditionError : '您的条件有误，请核实后进行查询。'
				}
			},
			sort : {
				title : '排序条件',
				logic : {
					asc : '升序',
					desc : '降序'
				},
				table : {
					sequence : '序号',
					operation : '操作',
					field : '字段',
					logic : '逻辑',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : '排序上移',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : '排序下移',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : '删除排序'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增一行',
					insertTitle : '新增一行',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;清空条件',
					clearTitle : '清空条件'
				},
				errors : {
					fieldMustSelect : '排序条件中第 {sequence} 行的字段信息必须选择！',
					logicMustSelect : '排序条件中第 {sequence} 行的排序逻辑必须选择！'
				}
			},
			buttons : {
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;执行查询'
			}
		},
		print : {
			title : '<i class="fa fa-print"></i>&nbsp;&nbsp;打印选项',
			table : {
				column : '列',
				operation : '操作',
				buttons : {
					up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;上移',
					upTitle : '上移',
					down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;下移',
					downTitle : '下移'
				}
			},
			buttons : {
				print : '<i class="fa fa-print"></i>&nbsp;&nbsp;执行打印'
			}
		},
		'export' : {
			excel : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;导出EXCEL选项',
				exportType : {
					title : '导出方式：',
					now : '导出当前数据',
					all : '导出全部数据'
				},
				table : {
					column : '列',
					operation : '操作',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;上移',
						upTitle : '上移',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;下移',
						downTitle : '下移'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;执行导出EXCEL文件'
				}
			},
			csv : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;导出CSV选项',
				exportType : {
					title : '导出方式：',
					now : '导出当前数据',
					all : '导出全部数据'
				},
				table : {
					column : '列',
					operation : '操作',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;上移',
						upTitle : '上移',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;下移',
						downTitle : '下移'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;执行导出CSV文件'
				}
			},
			pdf : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;导出PDF选项',
				exportType : {
					title : '导出方式：',
					now : '导出当前数据',
					all : '导出全部数据'
				},
				table : {
					column : '列',
					operation : '操作',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;上移',
						upTitle : '上移',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;下移',
						downTitle : '下移'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;执行导出PDF文件'
				}
			},
			txt : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;导出TXT选项',
				exportType : {
					title : '导出方式：',
					now : '导出当前数据',
					all : '导出全部数据'
				},
				table : {
					column : '列',
					operation : '操作',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;上移',
						upTitle : '上移',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;下移',
						downTitle : '下移'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;执行导出TXT文件'
				}
			}
		}
	};
})(jQuery);