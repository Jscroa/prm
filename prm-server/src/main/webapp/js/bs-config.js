( function ($) {
    var bsTableTemplate = {
            dataType: 'json',
            cache : false, // 缓存
            sortable : false, // 是否启用排序
            pagination : true, // 分页
            pageList : [ 10, 20, 50, 100 ],
            singleSelect : false, // 非单选
            search : true, // 搜索框
            showRefresh : true, // 刷新按钮
            sidePagination : 'server', // 服务端分页
            showColumns : true, // 开启选择显示列
            smartDisplay : true, // 智能显示
            showToggle : false,
            undefinedText : '',
            pageSize : 10,
            
            clickToSelect : false, // 单击行选中
            showExport : true,
            exportDataType : 'all',
            rowStyle : function(row, index) { // 隔行换色
                var strclass = '';
                if (row.state) {
                    strclass = 'info';
                } else {
                    strclass = '';
                }
                return {
                    classes : strclass // 不变色
                };
            },
            responseHandler : function(res) { // 返回处理
                if (res.code == 100) {
                    return {
                        'page' : res.page,
                        'total' : res.total,
                        'rows' : res.rows
                    };
                } else {
                    toastr.warning(res.code + ':' + res.msg);
                    return {
                        'page' : 0,
                        'total' : 0,
                        'rows' : []
                    };
                }
            }
    };
    $.fn.prmTable = function(options){
        $.extend(bsTableTemplate,options);
        this.bootstrapTable(bsTableTemplate);
    }
})(jQuery);


// 吐司配置
toastr.options = {
    "closeButton" : false,
    "debug" : false,
    "progressBar" : true,
// "positionClass" : "toast-bottom-full-width",
    "onclick" : null,
    "showDuration" : "300",
    "hideDuration" : "1000",
    "timeOut" : "5000",
    "extendedTimeOut" : "100",
    "showEasing" : "swing",
    "hideEasing" : "linear",
    "showMethod" : "fadeIn",
    "hideMethod" : "fadeOut"
};

/**
 * 表单数据快速填充
 * 
 * @param obj
 *            数据
 */
function loadData(obj){
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
        
        $("[name='"+key+"'],[name='"+key+"[]']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                    arr = value.split(',');
                    for(var i =0;i<arr.length;i++){
                        if($(this).val()==arr[i]){
                            $(this).attr('checked',true);
                            break;
                        }
                    }
                }else{
                    $(this).val(value);
                }
            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
                $(this).val(value);
            }
            
        });
    }
}
