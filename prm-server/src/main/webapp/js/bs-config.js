// 表格配置
function TableConfig(url, toolbar, columns) {
    this.url = url;
    this.columns = columns;
    this.dataType = 'json'; // 数据格式
    this.toolbar = toolbar; // 工具栏
    this.cache = false; // 关闭缓存
    this.sortable = false; // 是否启用排序
    this.pagination = true; // 分页
    this.pageList = [ 10, 20, 50, 100 ];
    this.singleSelect = false; // 非单选
    this.search = false; // 搜索框
    this.showRefresh = false; // 刷新按钮
    this.sidePagination = 'server'; // 服务端分页
    this.showColumns = false; // 开启选择显示列
    this.smartDisplay = true; // 智能显示
    this.clickToSelect = true; // 单击行选中
    this.rowStyle = function(row, index) { // 隔行换色
        var strclass = '';
        if (index % 2 == 0) {
            strclass = 'info';
        } else {
            strclass = '';
        }
        return {
            classes : '' // 不变色
        };
    };

    this.responseHandler = function(res) { // 返回处理
        if (res.code == 100) {
            return {
                'page' : res.page,
                'total' : res.total,
                'rows' : res.rows
            };
        } else {
            return {
                'page' : 0,
                'total' : 0,
                'rows' : []
            };
        }
    };
}

// 吐司配置
toastr.options = {
    "closeButton" : false,
    "debug" : false,
    "progressBar" : true,
    "positionClass" : "toast-bottom-full-width",
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
