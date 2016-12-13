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
	$.fn.dlshouwen.grid.lang['en'] = {
		errors : {
			ajaxLoadError : 'Data failed to load, please check your condition options is had some errors.'
		},
		buttons : {
			close : '<i class="fa fa-times"></i>&nbsp;&nbsp;Close'
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
			refreshTitle : 'Refresh',
			fastQuery : '<i class="fa fa-search"></i>',
			fastQueryTitle : 'Fast Query',
			advanceQuery : '<i class="fa fa-filter"></i>',
			advanceQueryTitle : 'Advance Query',
			exportExcel : '<i class="fa fa-file-excel-o"></i> EXCEL',
			exportExcelTitle : 'Export Excel File',
			exportCsv : '<i class="fa fa-file-o"></i> CSV',
			exportCsvTitle : 'Export Csv File',
			exportPdf : '<i class="fa fa-file-pdf-o"></i> PDF',
			exportPdfTitle : 'Export Pdf File',
			exportTxt : '<i class="fa fa-file-o"></i> TXT',
			exportTxtTitle : 'Export Txt File',
			print : '<i class="fa fa-print"></i>',
			printTitle : 'Print'
		},
		pageInfo : {
			nothing : 'No Data...',
			info : 'There is {recordCount} records, {pageSize} records per page, toatal {pageCount} pages.',
			firstPage : '<i class="fa fa-angle-double-left"></i>',
			prevPage : '<i class="fa fa-angle-left"></i>',
			nextPage : '<i class="fa fa-angle-right"></i>',
			lastPage : '<i class="fa fa-angle-double-right"></i>',
			firstPageTitle : 'First Page',
			prevPageTitle : 'Prev Page',
			nextPageTitle : 'Next Page',
			lastPageTitle : 'Last Page',
			alreadyFirstPage : 'Already First Page',
			alreadyLastPage : 'Already Last Page',
			nowPage : '{nowPage}',
			nowPageTitle : 'Page {nowPage}',
			errors : {
				notANumber : 'Your input is not a number',
				maxPageSize : 'The page does not allow more than {pageSizeLimit}, has been restored to original setting'
			}
		},
		fastQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;Fast Query',
			codeTableSelectAll : 'All',
			input : 'Please Input ',
			selectStart : 'Please Select Start ',
			selectEnd : 'Please Select End',
			inputStart : 'Please Input Start ',
			inputEnd : 'Please Input End ',
			buttons : {
				reset : '<i class="fa fa-reply"></i>&nbsp;&nbsp;Reset',
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;Query'
			}
		},
		advanceQuery : {
			title : '<i class="fa fa-search"></i>&nbsp;&nbsp;Advance Query',
			plan : {
				title : 'Advance Query Plan',
				advanceQueryName : 'Advance Query Name:',
				advanceQueryNamePlaceHoder : 'Please Input Advance Query Name',
				remark : 'Advance Query Remark:',
				remarkPlaceHoder : 'Please Input Advance Query Remark',
				buttons : {
					addAdvanceQuery : '<i class="fa fa-plus"></i>&nbsp;&nbsp;Add',
					addAdvanceQueryTitle : 'Add Advance Query Plan',
					editAdvanceQuery : '<i class="fa fa-pencil"></i>&nbsp;&nbsp;Edit',
					editAdvanceQueryTitle : 'Edit Advance Query Plan',
					copyAdvanceQuery : '<i class="fa fa-copy"></i>&nbsp;&nbsp;Copy',
					copyAdvanceQueryTitle : 'Copy Advance Query Plan',
					deleteAdvanceQuery : '<i class="fa fa-trash-o"></i>&nbsp;&nbsp;Delete',
					deleteAdvanceQueryTitle : 'Delete Advance Query Plan'
				}
			},
			condition : {
				title : 'Conditions',
				table : {
					sequence : 'Sequence',
					operation : 'Operation',
					leftParentheses : 'Left (',
					field : 'Field',
					condition : 'Condition',
					value : 'Value',
					rightParentheses : 'Right )',
					logic : 'Logic',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : 'Up Condition',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : 'Down Condition',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : 'Delete Condition'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;Insert',
					insertTitle : 'Insert New Line',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;Clear',
					clearTitle : 'Clear All Conditions'
				},
				errors : {
					fieldMustSelect : 'You must select field on line No. {sequence} in Conditions.',
					conditionMustSelect : 'You must select condition on line No. {sequence} in Conditions.',
					conditionError : 'Condition had some errors, please check.'
				}
			},
			sort : {
				title : 'Sorts',
				logic : {
					asc : 'Asc',
					desc : 'Desc'
				},
				table : {
					sequence : 'Sequence',
					operation : 'Operation',
					field : 'Field',
					logic : 'Logic',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>',
						upTitle : 'Up Sort',
						down : '<i class="fa fa-arrow-circle-down"></i>',
						downTitle : 'Down Sort',
						'delete' : '<i class="fa fa-times"></i>',
						deleteTitle : 'Delete Sort'
					}
				},
				buttons : {
					insert : '<i class="fa fa-plus"></i>&nbsp;&nbsp;Insert',
					insertTitle : 'Insert New Line',
					clear : '<i class="fa fa-eraser"></i>&nbsp;&nbsp;Clear',
					clearTitle : 'Clear All Sorts'
				},
				errors : {
					fieldMustSelect : 'You must select field on line No. {sequence} in Sorts.',
					logicMustSelect : 'You must select logic on line No. {sequence} in Sorts.'
				}
			},
			buttons : {
				query : '<i class="fa fa-search"></i>&nbsp;&nbsp;Query'
			}
		},
		print : {
			title : '<i class="fa fa-print"></i>&nbsp;&nbsp;Print Options',
			table : {
				column : 'Column',
				operation : 'Operation',
				buttons : {
					up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;UP',
					upTitle : 'Up',
					down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;Down',
					downTitle : 'Down'
				}
			},
			buttons : {
				print : '<i class="fa fa-print"></i>&nbsp;&nbsp;Print'
			}
		},
		'export' : {
			excel : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;Export Excel Options',
				exportType : {
					title : 'Export Type:',
					now : 'Export Now Datas',
					all : 'Export All Datas'
				},
				table : {
					column : 'Column',
					operation : 'Operation',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;Up',
						upTitle : 'Up',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;Down',
						downTitle : 'Down'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;Export Excel File'
				}
			},
			csv : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;Export Csv Options',
				exportType : {
					title : 'Export Type:',
					now : 'Export Now Datas',
					all : 'Export All Datas'
				},
				table : {
					column : 'Column',
					operation : 'Operation',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;Up',
						upTitle : 'Up',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;Down',
						downTitle : 'Down'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;Export Csv File'
				}
			},
			pdf : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;Export Pdf Options',
				exportType : {
					title : 'Export Type:',
					now : 'Export Now Datas',
					all : 'Export All Datas'
				},
				table : {
					column : 'Column',
					operation : 'Operation',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;Up',
						upTitle : 'Up',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;Down',
						downTitle : 'Down'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;Export Pdf File'
				}
			},
			txt : {
				title : '<i class="fa fa-download"></i>&nbsp;&nbsp;Export Txt Options',
				exportType : {
					title : 'Export Type:',
					now : 'Export Now Datas',
					all : 'Export All Datas'
				},
				table : {
					column : 'Column',
					operation : 'Operation',
					buttons : {
						up : '<i class="fa fa-arrow-circle-up"></i>&nbsp;&nbsp;Up',
						upTitle : 'Up',
						down : '<i class="fa fa-arrow-circle-down"></i>&nbsp;&nbsp;Down',
						downTitle : 'Down'
					}
				},
				buttons : {
					'export' : '<i class="fa fa-print"></i>&nbsp;&nbsp;Export Txt File'
				}
			}
		}
	};
})(jQuery);