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
	$.fn.dlshouwen.grid.lang['zh-tw'] = {
		errors : {
			ajaxLoadError : '資料載入失敗，請檢查您的條件配置是否存在錯誤。'
		},
		buttons : {
			close : '<i class="fa fa-times"></i>&nbsp;&nbsp;關閉'
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
			refreshTitle : '刷新資料',
			fastQuery : '<i class="fa fa-search"></i>',
			fastQueryTitle : '快速查詢',
			advanceQuery : '<i class="fa fa-filter"></i>',
			advanceQueryTitle : '高級查詢',
			exportExcel : '<i class="fa fa-file-excel-o"></i> EXCEL',
			exportExcelTitle : '匯出EXCEL文件',
			exportCsv : '<i class="fa fa-file-o"></i> CSV',
			exportCsvTitle : '匯出CSV文件',
			exportPdf : '<i class="fa fa-file-pdf-o"></i> PDF',
			exportPdfTitle : '匯出PDF文件',
			exportTxt : '<i class="fa fa-file-o"></i> TXT',
			exportTxtTitle : '匯出TXT文件',
			print : '<i class="fa fa-print"></i>',
			printTitle : '列印'
		},
		pageInfo : {
			nothing : '無查詢記錄...',
			info : '查詢出 {recordCount} 條記錄，每頁 {pageSize} 條，共  {pageCount} 頁',
			firstPage : '<i class="fa fa-angle-double-left"></i>',
			prevPage : '<i class="fa fa-angle-left"></i>',
			nextPage : '<i class="fa fa-angle-right"></i>',
			lastPage : '<i class="fa fa-angle-double-right"></i>',
			firstPageTitle : '第一頁',
			prevPageTitle : '上一頁',
			nextPageTitle : '下一頁',
			lastPageTitle : '最後一頁',
			alreadyFirstPage : '已經是第一頁了',
			alreadyLastPage : '已經是最後一頁了',
			nowPage : '{nowPage}',
			nowPageTitle : '第 {nowPage} 頁',
			errors : {
				notANumber : '您輸入的內容不是數位',
				maxPageSize : '每頁顯示數量不得超過 {pageSizeLimit} 條，已還原為原設置'
			}
		},
		fastQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;快速查詢',
			codeTableSelectAll : '全部',
			input : '請輸入',
			selectStart : '請選擇開始',
			selectEnd : '請選擇結束',
			inputStart : '請輸入開始',
			inputEnd : '請輸入結束',
			buttons : {
				reset : '<i class="fa fa-reply"></i>&nbsp;&nbsp;參數重置',
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;執行查詢'
			}
		},
		advanceQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;高級查詢',
			plan : {
				title : '查詢方案',
				advanceQueryName : '方案名稱：',
				advanceQueryNamePlaceHoder : '請輸入方案名稱',
				remark : '備註：',
				remarkPlaceHoder : '請輸入備註',
				buttons : {
					addAdvanceQuery : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增',
					addAdvanceQueryTitle : '新增高級查詢方案',
					editAdvanceQuery : '<i class="fa fa-pencil"></i>&nbsp;&nbsp;編輯',
					editAdvanceQueryTitle : '編輯高級查詢方案',
					copyAdvanceQuery : '<i class="fa fa-copy"></i>&nbsp;&nbsp;複製',
					copyAdvanceQueryTitle : '複製高級查詢方案',
					deleteAdvanceQuery : '<i class="fa fa-trash-o"></i>&nbsp;&nbsp;刪除',
					deleteAdvanceQueryTitle : '刪除高級查詢方案'
				}
			},
			condition : {
				title : '查詢準則',
				table : {
					sequence : '序號',
					operation : '操作',
					leftParentheses : '左括弧',
					field : '欄位',
					condition : '條件',
					value : '值',
					rightParentheses : '右括弧',
					logic : '邏輯',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : '條件上移',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : '條件下移',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : '刪除條件'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增一行',
					insertTitle : '新增一行',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;清空條件',
					clearTitle : '清空條件'
				},
				errors : {
					fieldMustSelect : '查詢準則中第 {sequence} 行的欄位資訊必須選擇！',
					conditionMustSelect : '查詢準則中第 {sequence} 行的條件資訊必須選擇！',
					conditionError : '您的條件有誤，請核實後進行查詢。'
				}
			},
			sort : {
				title : '排序條件',
				logic : {
					asc : '昇冪',
					desc : '降冪'
				},
				table : {
					sequence : '序號',
					operation : '操作',
					field : '欄位',
					logic : '邏輯',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : '排序上移',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : '排序下移',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : '刪除排序'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;新增一行',
					insertTitle : '新增一行',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;清空條件',
					clearTitle : '清空條件'
				},
				errors : {
					fieldMustSelect : '排序條件中第 {sequence} 行的欄位資訊必須選擇！',
					logicMustSelect : '排序條件中第 {sequence} 行的排序邏輯必須選擇！'
				}
			},
			buttons : {
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;執行查詢'
			}
		},
		print : {
			title : '<i class="fa fa-print"></i>&nbsp;&nbsp;列印選項',
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
				print : '<i class="fa fa-print"></i>&nbsp;&nbsp;執行列印'
			}
		},
		'export' : {
			excel : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;匯出EXCEL選項',
				exportType : {
					title : '匯出方式：',
					now : '匯出當前資料',
					all : '匯出全部資料'
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
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;執行匯出EXCEL檔'
				}
			},
			csv : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;匯出CSV選項',
				exportType : {
					title : '匯出方式：',
					now : '匯出當前資料',
					all : '匯出全部資料'
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
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;執行匯出CSV檔'
				}
			},
			pdf : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;匯出PDF選項',
				exportType : {
					title : '匯出方式：',
					now : '匯出當前資料',
					all : '匯出全部資料'
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
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;執行匯出PDF檔'
				}
			},
			txt : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;匯出TXT選項',
				exportType : {
					title : '匯出方式：',
					now : '匯出當前資料',
					all : '匯出全部資料'
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
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;執行匯出TXT檔'
				}
			}
		}
	};
})(jQuery);