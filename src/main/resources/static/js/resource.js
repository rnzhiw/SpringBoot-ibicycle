var $table = $("#resource_table");
var addTree;
var editTree;

$(function () {

    $table.bootstrapTable({
        url: '/system/resource/list',
        striped: true,
        toolbar: '#toolbar',
        sidePagination: 'server',
        idField: 'id',
        columns: [
            {
                field: 'resName',
                title: '资源名称',
                formatter: function (data) {
                    return "&nbsp;&nbsp;" + data;
                }
            }, {
                field: 'resKey',
                title: '资源标志',
                align: 'center'
            }, {
                field: 'resType',
                title: '资源类型',
                align: 'center'
            }, {
                field: 'menuUrl',
                title: '菜单URL',
                align: 'center',
                formatter: function (value, row, index) {
                    var div = "<div style='overflow:hidden;text-overflow:ellipsis;white-space:nowrap;'>" + value + "</div>";
                    return div;
                }
            }, {
                field: 'funUrls',
                title: '功能URLS',
                align: 'center',
                formatter: function (value, row, index) {
                    var div = "<div style='overflow:hidden;text-overflow:ellipsis;white-space:nowrap;'>" + value + "</div>";
                    return div;
                }
            }, {
                field: 'weight',
                title: '权重',
                align: 'center'
            }, {
                field: 'status',
                title: '状态',
                align: 'center',
                formatter: function (val, row) {
                    return val ? "可用" : "禁用";
                }
            }, {
                field: 'id',
                title: '操作',
                align: 'center',
                formatter: function (value, row, index) {
                    return "<button type='button' onclick='openEditModal(" + row.id + ")' class='btn common-btn'>编辑</button>" +
                        "<button type='button' onclick='openDeleteModal(" + row.id + ")' class='btn common-btn'>删除</button>"
                }
            }
        ],
        treeShowField: 'resName',
        parentIdField: 'parentId',
        onLoadSuccess: function (data) {
            $table.treegrid({
                initialState: 'expanded',//展开
                treeColumn: 0,//指明第几列数据改为树形
                expanderExpandedClass: 'glyphicon glyphicon-triangle-bottom',
                expanderCollapsedClass: 'glyphicon glyphicon-triangle-right',
                onChange: function () {
                    $table.bootstrapTable('resetWidth');
                }
            });
        }
    });

    $table.bootstrapTable('resetView', {
        height: $(window).height() - 155
    });

});

$(window).resize(function () {
    $table.bootstrapTable('resetView', {
        height: $(window).height() - 155
    });
});

//////////////////////////////////////////////////////////////////////////////////////////////////////
//添加资源部分

$("#add_resource_btn").click(function () {
    $("#add_resource_modal").modal("show");

    $.ajax({
        url: '/system/resource/list',
        type: 'get',
        success: function (res) {

            res = res.data;

            var array = [];
            var iterator1 = function (treeNodes) {
                if (!treeNodes || !treeNodes.length) return;
                var parent, i = 0, obj = {};
                while (i < treeNodes.length) {
                    var node = treeNodes[i++];
                    obj[node.id] = node;
                    if (node.parentId) {
                        parent = obj[node.parentId];
                        if (parent.children) {
                            parent.children.push(node);
                        } else {
                            parent.children = [node];
                        }
                    } else {
                        array.push(node);
                    }
                }

                return array;
            };

            iterator1(res);

            // 初始化树状图
            addTree = $("#add_parent_resource_tree").tree({
                uiLibrary: 'bootstrap',
                dataSource: array,
                primaryKey: 'id',
                iconsLibrary: 'materialicons',
                textField: "resName"
            });

            $("#add_parent_resource_tree").hide();
            addTree.expandAll();

            addTree.on('select', function (e, node, id) {
                $("#add_parent_resource").val(node.children().find("span:last-child").html());
                $("#add_parent_resource").attr('data-id', id);
                $("#add_parent_resource_tree").hide();
            });

            addTree.on('unselect', function (e, node, id) {
                $("#add_parent_resource").val("");
                $("#add_parent_resource").attr('data-id', "");
                $("#add_parent_resource_tree").hide();
            });
        }

    });
});

//点击上级资源框，显示下拉树
$("#add_parent_resource").click(function () {
    if ($("#add_parent_resource_tree").is(":hidden")) {
        $("#add_parent_resource_tree").show();
    } else {
        $("#add_parent_resource_tree").hide();
    }
});

//模态框隐藏事件
$('#add_resource_modal').on('hidden.bs.modal', function () {
    $("#add_parent_resource").val("");
    $("#add_name").val("");
    $("#add_resource_key").val("");
    $("#add_select_type").val("FUNCTION");
    $("#add_menu_url").val("");
    $("#add_function_urls").val("");
    $("#add_weight").val("0");
    $("#add_select_status").val("true");
    addTree.destroy();
});

//确认添加按钮
$("#do_add_resource_btn").click(function () {

    $.ajax({
        url: '/system/resource/save',
        type: 'post',
        data: {
            parentId: $("#add_parent_resource").attr("data-id"),
            resName: $("#add_name").val(),
            resKey: $("#add_resource_key").val(),
            resType: $("#add_select_type").val(),
            menuUrl: $("#add_menu_url").val(),
            funUrls: $("#add_function_urls").val(),
            weight: $("#add_weight").val(),
            status: $("#add_select_status").val()
        },
        success: function (res) {
            if (res.success) {
                $("#add_resource_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
                $("#add_resource_modal").modal("hide");
            }
        }
    })
});

//////////////////////////////////////////////////////////////////////////////////////////////////////
//编辑资源部分
function openEditModal(id) {

    $("#edit_resource_modal").modal("show");

    $.ajax({

        url: '/system/resource/get?id=' + id,
        type: 'get',
        success: function (res) {

            if (!res.success) {
                return;
            }

            res = res.data;

            $.ajax({
                url: '/system/resource/list',
                type: 'get',
                success: function (a) {

                    a = a.data;

                    var array = [];
                    var iterator1 = function (treeNodes) {
                        if (!treeNodes || !treeNodes.length) return;
                        var parent, i = 0, obj = {};
                        while (i < treeNodes.length) {
                            var node = treeNodes[i++];
                            obj[node.id] = node;
                            if (node.parentId) {
                                parent = obj[node.parentId];
                                if (parent.children) {
                                    parent.children.push(node);
                                } else {
                                    parent.children = [node];
                                }
                            } else {
                                array.push(node);
                            }
                        }

                        return array;
                    };

                    iterator1(a);

                    // 初始化树状图
                    editTree = $("#edit_parent_resource_tree").tree({
                        uiLibrary: 'bootstrap',
                        dataSource: array,
                        primaryKey: 'id',
                        iconsLibrary: 'materialicons',
                        textField: "resName"
                    });

                    $("#edit_parent_resource_tree").hide();
                    editTree.expandAll();

                    editTree.on('select', function (e, node, id) {
                        $("#edit_parent_resource").val(node.children().find("span:last-child").html());
                        $("#edit_parent_resource").attr('data-id', id);
                        $("#edit_parent_resource_tree").hide();
                    });

                    editTree.on('unselect', function (e, node, id) {
                        $("#edit_parent_resource").val("");
                        $("#edit_parent_resource").attr('data-id', "");
                        $("#edit_parent_resource_tree").hide();
                    });
                }
            });

            $("#edit_id").val(res.id);
            $("#edit_parent_resource").val(res.parentName);
            $("#edit_parent_resource").attr('data-id', res.parentId);
            $("#edit_name").val(res.resName);
            $("#edit_resource_key").val(res.resKey);
            $("#edit_select_type").val(res.resType);
            $("#edit_menu_url").val(res.menuUrl);
            $("#edit_function_urls").val(res.funUrls);
            $("#edit_weight").val(res.weight);
            $("#edit_select_status").val(res.status + "");
        }
    })
}

//模态框隐藏事件
$('#edit_resource_modal').on('hidden.bs.modal', function () {
    $("#edit_parent_resource").val("");
    $("#edit_name").val("");
    $("#edit_resource_key").val("");
    $("#edit_select_type").val("FUNCTION");
    $("#edit_menu_url").val("");
    $("#edit_function_urls").val("");
    $("#edit_weight").val("0");
    $("#edit_select_status").val("true");
    editTree.destroy();
});

//确认编辑按钮
$("#do_edit_resource_btn").click(function () {
    $.ajax({
        url: '/system/resource/update',
        type: 'post',
        data: {
            id: $("#edit_id").val(),
            parentId: $("#edit_parent_resource").attr("data-id"),
            resName: $("#edit_name").val(),
            resKey: $("#edit_resource_key").val(),
            resType: $("#edit_select_type").val(),
            menuUrl: $("#edit_menu_url").val(),
            funUrls: $("#edit_function_urls").val(),
            weight: $("#edit_weight").val(),
            status: $("#edit_select_status").val()
        },
        success: function (res) {
            if (res.success) {
                $("#edit_resource_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
                $("#edit_resource_modal").modal("hide");
            }
        }
    })
});

//点击上级资源框，显示下拉树
$("#edit_parent_resource").click(function () {
    if ($("#edit_parent_resource_tree").is(":hidden")) {
        $("#edit_parent_resource_tree").show();
    } else {
        $("#edit_parent_resource_tree").hide();
    }
});

//删除资源部分（已完成）

function openDeleteModal(id) {

    $("#delete_resource_modal").modal("show");
    $("#delete_resource_id").val(id);
}

$("#do_delete_resource_btn").click(function () {

    $.ajax({
        url: '/system/resource/delete',
        type: 'post',
        data: {
            id: $("#delete_resource_id").val()
        },
        success: function (res) {
            if (res.success) {
                $("#delete_resource_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
                $("#delete_resource_modal").modal("hide");
            }
        }
    })
});

