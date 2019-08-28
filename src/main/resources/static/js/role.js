$(function () {
    var $table = $("#role_table");
    $table.bootstrapTable({
        url:'/system/role/list',
        striped: true,
        toolbar:'#toolbar',
        idField: 'id',
        height: 800,
        singleSelect : true,
        clickToSelect: true,
        columns: [
            {
                checkbox: true,
                visible: true
            },
            {
                field: 'roleName',
                title: '角色名称',
                align: 'center'
            },
            {
                field: 'description',
                title: '描述',
                align: 'center'
            },
            {
                field: 'orgType',
                title: '组织类型',
                align: 'center',
                formatter: function(value) {
                    if (value == 0) {
                        return "平台";
                    } else if (value == 1) {
                        return "交管局";
                    } else if (value == 2) {
                        return "交管局(运维)";
                    } else if (value == 3) {
                        return "普通用户单位";
                    } else {
                        return value;
                    }
                }
            },
            {
                field: 'status',
                title: '状态',
                align: 'center',
                formatter: function (val, row) {
                    if(val == true){
                        return "可用";
                    } else if(val == false){
                        return "禁用";
                    } else {
                        return val;
                    }
                    // return val ? "可用" : "禁用";
                  }
            }
        ],

        // onClickRow:function (row,$element) {
        //     if(row.id!=-1){
        //
        //         $("#create_table").bootstrapTable('refresh',{
        //             url:"system/role/resource/list?id="+row.id,
        //         })
        //
        //     }
        // },

        onUncheck: function(row, $element){

                $("#create_table").bootstrapTable('refresh',{
                    url:"system/role/resource/list"
                })
        },

        onClickRow: function(row){
            if(row.id != -1){
                $("#create_table").bootstrapTable('refresh',{
                    url:"system/role/resource/list?id="+row.id,
                })
            } else {
                index = $element.data('index');
                $('#role_table').bootstrapTable('check',index);
            }


        },

        onCheck:function(row){
                if(row.id != -1){
                    $("#role_table").bootstrapTable('remove',{field:"id", values:[-1]})
                }
                $("#create_table").bootstrapTable('refresh',{
                    url:"system/role/resource/list?id="+row.id,
                })
        },
    });

    $table.bootstrapTable('resetView',{
        height: $(window).height()-155});

    $(window).resize(function () {
        $table.bootstrapTable('resetView',{
            height: $(window).height()-155});
    });
})

$(function () {
    var $table = $("#create_table");
    $table.bootstrapTable({
        url:'/system/role/resource/list',
        striped:true,
        sidePagenation:'server',
        toolbar:'#right_toolbar',
        idField:'id',
        dataField: 'data',
        clickToSelect: true,
        columns:[
            {
                checkbox: true,
                align: 'center',
                formatter: function (value, row, index) {
                    if(row.hasResource == true){
                        return {
                            checked: true
                        }
                    }
                    return value;
                }
            },{
                field:'resName',
                title:'名称',
            }
        ],
        treeShowField: 'resName',
        parentIdField: 'parentId',
        onLoadSuccess: function(data) {
            $table.treegrid({
                initialState: 'expanded',//收缩
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
        height: $(window).height()-155});

    $(window).resize(function () {
        $table.bootstrapTable('resetView',{
            height: $(window).height()-155});
    });
})

//点击添加角色
$(function () {
    $("#btn_add").click(function () {

        var hasAdd = $("#roleName").val()

        if((typeof(hasAdd) == "undefined")){
            $("#role_table").bootstrapTable('uncheckAll');
            var roleName = '<div class="input-group" style="margin-left: 30px"><input type="text" class="form-control" placeholder="角色名称" id="roleName" data-toggle="tooltip" data-placement="top" title="不能为空!"></div>';
            var des = '<div class="input-group" style="margin-left: 30px"><input type="text" class="form-control" placeholder="描述" id="description" data-toggle="tooltip" data-placement="top" title=""></div>';
            var orgType = '<select class="form-control" id="orgType"><option value="0">平台</option> <option value="1">交管局</option> <option value="2">交管局(运维)</option> <option value="3">普通用户单位</option></select>';
            var status = '<select class="form-control" id="status"> <option value="true">可用</option> <option value="false">禁用</option> </select>';
            var count = $("#role_table").bootstrapTable('getData').length;
            $("#role_table").bootstrapTable('insertRow',{index:count,row:{id:-1,roleName:roleName,description:des,orgType:orgType,status:status}});
        }

        var index = $("#role_table").find("tr").length ;
        $('#role_table').bootstrapTable('check',index-2);
    })

    $("#btn_cancel").click(function () {
        $("#role_table").bootstrapTable('remove',{field:"id", values:[-1]})
    })

    $("[data-toggle='tooltip']").tooltip();

})

//保存角色
$(function () {

    $('#btn_save').click(function () {

        $.ajax({
            type: "post",
            url: "/system/role/save",
            data: {
                roleName:$("#roleName").val(),
                description:$("#description").val(),
                orgType:$("#orgType").val(),
                status: $("#status").val()
            },
            dataType: "json",
            success: function(){
                $("#role_table").bootstrapTable('refresh',{
                    url:'/system/role/list',
                })
            },
        });

        $("#role_table").bootstrapTable('refresh');
    })
})

//刷新
$(function () {
    $("#btn_refresh").click(function () {
        $("#role_table").bootstrapTable('refresh',{
            url:'/system/role/list',
        })
    })
})

//删除角色
$(function () {
    $("#btn_delete").click(function () {
        var row = $("#role_table").bootstrapTable('getSelections')[0];
        $.ajax({
            type: "post",
            url: "/system/role/delete",
            data: {
                id: row.id,
            },
            dataType: "json",
            success: function(){
                $("#role_table").bootstrapTable('refresh',{
                    url:'/system/role/list',
                });
                $("#create_table").bootstrapTable('refresh',{
                    url:"system/role/resource/list?id="+row.id,
                });
            },
        });
    })
})

//保存权限
$(function () {
    $("#resource_save_btn").click(function () {
        var row = $("#role_table").bootstrapTable('getSelections')[0];
        if(typeof(row) == "undefined"){
            alert("请先选择一个角色！")
        }

        var resourceRow = $("#create_table").bootstrapTable('getSelections');
        var resourceIds = new Array();
        for(i = 0; i < resourceRow.length; i++){
            resourceIds.push(resourceRow[i].id);
        }
        $.ajax({
            type: "post",
            url: "/system/role/resource/save",
            dataType: "json",
            // contentType : 'application/json',
            data:{
                resourceIds:resourceIds.toString(),
                roleId: row.id
            },

            // data:JSON.stringify(resourceIds),


            success: function(){
                $("#role_table").bootstrapTable('refresh',{
                    url:'/system/role/list',
                });
                $("#create_table").bootstrapTable('refresh',{
                    url:'/system/role/resource/list',
                })
            },
        });
    })
})















