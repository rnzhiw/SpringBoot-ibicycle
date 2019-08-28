$(function () {
    var $table = $("#resource_table");
    $table.bootstrapTable({
        url:'url/resource.json',
        striped:true,
        toolbar: '#toolbar',
        sidePagination:'server',
        idField:'id',
        columns:[
            {
                field: 'ck',
                checkbox: true
            },{
                field:'resName',
                title:'资源名称'
            },{
                field:'resKey',
                title:'资源标志',
                align: 'center'
            },{
                field:'resType',
                title:'资源类型',
                align: 'center',
                width:'120px'
            },{
                field:'menuUrl',
                title:'菜单URL',
                align: 'center',
                width:'140px',
                formatter: function(value, row, index){
                    var div = "<div style='width:124px; overflow:hidden; text-overflow:ellipsis; white-space: nowrap;';>"+value+"</div>";
      	            return div;
                }
            },{
                field:'funUrls',
                title:'功能URLS',
                align: 'center',
                width:'180px',
                formatter: function(value, row, index){
                    var div = "<div style='width:160px; overflow:hidden; text-overflow:ellipsis; white-space: nowrap;';>"+value+"</div>";
      	            return div;
                }
            },{
                field:'weight',
                title:'权重',
                align:'center',
                width:'60px'
            },{
                field:'status',
                title:'状态',
                align:'center',
                width:'60px',
                formatter: function (val, row) {
                    return val ? "可用" : "禁用";
                }
            },{
                field:'id',
                title:'操作',
                align: 'center',
                formatter: function(value, row, index){
                    return "<a type='button' data-toggle='modal' data-target='#editResource' class='btn common-btn' onclick='editResource'>编辑</a>"+
                           "<a type='button' data-toggle='modal' data-target='#deleteResource' class='btn common-btn' onclick='deleteResource'>删除</a>"
                }
            }
        ],
        treeShowField: 'resName',
        parentIdField: 'parentId',
        onLoadSuccess: function(data) {
            $table.treegrid({
                initialState: 'expanded',//展开
                treeColumn: 1,//指明第几列数据改为树形
                expanderExpandedClass: 'glyphicon glyphicon-triangle-bottom',
                expanderCollapsedClass: 'glyphicon glyphicon-triangle-right',
                onChange: function() {
                    $table.bootstrapTable('resetWidth');
                }
            });
        }
    });

    $table.bootstrapTable('resetView',{
        height: $(window).height()-160});

    $(window).resize(function () {
        var scrollHeight = document.body.scrollHeight;
        console.log("scrollHeight:"+scrollHeight);
        console.log("innerHeight:"+$(window).innerHeight());
        console.log("height:"+$(window).height());
        $table.bootstrapTable('resetView',{
            height: $(window).height()-160});
    });
})