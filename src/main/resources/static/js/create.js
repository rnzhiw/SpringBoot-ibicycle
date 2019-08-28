$(function () {
    var $table = $("#create_table");
    $table.bootstrapTable({
        url:'url/bootstrap_treegrid.json',
        striped:true,
        sidePagination:'server',
        idField:'id',
        columns:[
            {
                field: 'ck',
                checkbox: true
            },{
                field:'name',
                title:'名称'
            },{
                field:'referred',
                title:'简称'
            }
        ],
        treeShowField: 'name',
        parentIdField: 'pid',
        onLoadSuccess: function(data) {
            $table.treegrid({
                initialState: 'collapsed',//收缩
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