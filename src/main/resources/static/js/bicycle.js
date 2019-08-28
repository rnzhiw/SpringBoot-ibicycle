$(function () {
    var $table = $("#vehicle_table");
    $table.bootstrapTable({
        url:'/system/bicycle/list',
        striped: true,
        idField: 'id',
        // sidePagination: 'server',
        // pagination: true,
        // pageNumber: 1,
        // pageSize: 10,
        // paginationVAlign: 'bottom',
        // paginationHAlign: 'right',
        // paginationDetailHAlign: 'left',
        // paginationPreText:'上一页',
        // paginationNextText:'下一页',
        columns: [
            {
                field: 'brand',
                title: '品牌',
                align: 'center'
            },
            {
                field: 'color',
                title: '颜色',
                align: 'center'
            },
            {
                field: 'licenseNumber',
                title: '车牌号',
                align: 'center'
            },
            {
                field: 'vin',
                title: '车架号',
                align: 'center'
            },
            {
                field: 'motorNumber',
                title: '电机号',
                align: 'center'
            },
            {
                field: 'ownerName',
                title: '车主',
                align: 'center'
            },
            {
                field: 'ownerPhone',
                title: '联系电话',
                align: 'center'
            },
            {
                field: 'installOrgName',
                title: '安装点',
                align: 'center'
            },
            {
                field: 'explain',
                title: '操作',
                align: 'center',
                formatter: function(value, row, index){
                    return "<a type='button'class='btn common-btn' onclick='showDetail("+row.id+")'>查看详情</a>"
                }
            }
        ]
    });

    $table.bootstrapTable('resetView',{
        height: $(window).height()-160});

    $(window).resize(function () {
        $table.bootstrapTable('resetView',{
            height: $(window).height()-160});
    });

});

function showDetail(id){
    $("#mainPanel").load('/system/bicycle/detail?id='+id);
}
