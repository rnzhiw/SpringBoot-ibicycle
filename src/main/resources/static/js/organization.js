var $table = $("#organ_table");
var addMap;
var editMap;
var addTree;
var editTree;
var provinceCode;
var cityCode;

$(function () {
    init();
});

//自适应屏幕
$(window).resize(function () {
    $table.bootstrapTable('resetView', {
        height: $(window).height() - 155
    });
});

//查询组织按钮
$("#search_organization_btn").click(function () {
    if ($("#search_organization_text").val().length == 0) {
        init();
    } else {
        search();
    }
});

//查询框回车查询
$("#search_organization_text").keydown(function (event) {
    if (event.keyCode == 13) {
        if ($("#search_organization_text").val().length == 0) {
            init();
        } else {
            search();
        }
    }
});

//重置按钮
$("#reset_organization_btn").click(function () {
    init();
});

//////////////////////////////////////////////////////////////////////////////////////////////////////
//以下内容为添加组织模态框

//点击添加组织按钮打开模态框
$("#add_organization_btn").click(function () {

    $("#add_organization_modal").modal("show");

    $.ajax({
        url: '/system/area/province',
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#add_select_province").html($("#add_select_province").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
            }
        }
    });

    $.ajax({
        url: '/system/area/city?provinceCode=110000',
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#add_select_city").html($("#add_select_city").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#add_select_city").val("");
            }
        }
    });

    $.ajax({
        url: '/system/organization/list',
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
            addTree = $("#add_parent_organ_tree").tree({
                uiLibrary: 'bootstrap',
                dataSource: array,
                primaryKey: 'id',
                iconsLibrary: 'materialicons',
                textField: "name"
            });

            $("#add_parent_organ_tree").hide();
            addTree.expandAll();

            addTree.on('select', function (e, node, id) {
                $("#add_parent_organ").val(node.children().find("span:last-child").html());
                $("#add_parent_organ").attr('data-id', id);
                $("#add_parent_organ_tree").hide();
            });
            addTree.on('unselect', function (e, node, id) {
                $("#add_parent_organ").val("");
                $("#add_parent_organ").attr('data-id', '');
                $("#add_parent_organ_tree").hide();
            });
        }
    });

    //初始化地图
    addMap = new BMap.Map("add_map");
    addMap.enableScrollWheelZoom();

    //进行具体配置
    setTimeout(function () {
        addUpdateMap();
    }, 500);

    addMap.addEventListener("click", function (e) {
        addMap.clearOverlays();
        var marker = new BMap.Marker(e.point);
        addMap.addOverlay(marker);
        addMap.panTo(new BMap.Point(e.point.lng, e.point.lat));// map.panTo方法，把点击的点设置为地图中心点
        $('#add_gps_lat').val(e.point.lat);
        $('#add_gps_lng').val(e.point.lng);
    });
});

//点击上级组织框，显示下拉树
$("#add_parent_organ").click(function () {
    if ($("#add_parent_organ_tree").is(":hidden")) {
        $("#add_parent_organ_tree").show();
    } else {
        $("#add_parent_organ_tree").hide();
    }
});

//选择省，生成对应的市，更新百度地图
$("#add_select_province").change(function () {

    $("#add_select_city").html("");
    $("#add_select_district").html("");
    $("#add_select_town").html("");

    $.ajax({
        url: '/system/area/city?provinceCode=' + $("#add_select_province").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#add_select_city").html($("#add_select_city").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#add_select_city").val("");
            }
            addUpdateMap();
        }
    })
});

//选择市，生成对应的区，更新百度地图
$("#add_select_city").change(function () {

    $("#add_select_district").html("");
    $("#add_select_town").html("");

    $.ajax({
        url: '/system/area/district?cityCode=' + $("#add_select_city").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#add_select_district").html($("#add_select_district").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#add_select_district").val("");
            }
            addUpdateMap();
        }
    })
});

//选择区，生成对应的街道，更新百度地图
$("#add_select_district").change(function () {

    $("#add_select_town").html("");

    $.ajax({
        url: '/system/area/town?districtCode=' + $("#add_select_district").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#add_select_town").html($("#add_select_town").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#add_select_town").val("");
            }
            addUpdateMap();
        }
    })
});

//选择街道，更新百度地图
$("#add_select_town").change(function () {
    addUpdateMap();
});

//查询详细地址
$("#add_search_address").click(function () {
    addUpdateMap();
});

//在详细地址栏回车更新地图
$("#add_address_detail").keypress(function (event) {
    if (event.keyCode == 13) {
        addUpdateMap();
    }
});

//确认添加按钮
$("#do_add_organization_btn").click(function () {

    $.ajax({
        url: '/system/organization/save',
        type: 'post',
        data: {
            parentId: $("#add_parent_organ").attr("data-id"),
            name: $("#add_name").val(),
            shortName: $("#add_short_name").val(),
            provinceCode: $("#add_select_province").val(),
            cityCode: $("#add_select_city").val(),
            districtCode: $("#add_select_district").val(),
            townCode: $("#add_select_town").val(),
            addressDetail: $("#add_address_detail").val(),
            gpsLng: $("#add_gps_lng").val(),
            gpsLat: $("#add_gps_lat").val(),
            type: $("#add_type").val(),
            status: 1
        },
        success: function (res) {
            if (res.success) {
                $("#add_organization_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
                $("#add_organization_modal").modal("hide");
            }
        }
    });
});

//模态框隐藏事件
$('#add_organization_modal').on('hidden.bs.modal', function () {
    $("#add_parent_organ").val("");
    $("#add_name").val("");
    $("#add_short_name").val("");
    $("#add_type").val(1);
    $("#add_address_detail").val("");
    $("#add_select_province").html("");
    $("#add_select_city").html("");
    $("#add_select_district").html("");
    $("#add_select_town").html("");
    addTree.destroy();
});

//更新添加组织模态框的地图
function addUpdateMap() {
    var province = $("#add_select_province option:selected").text();
    var city = $("#add_select_city option:selected").text();
    var district = $("#add_select_district option:selected").text();
    var town = $("#add_select_town option:selected").text();
    var address = $('#add_address_detail').val();
    var myGeo = new BMap.Geocoder();

    // 将地址解析结果显示在地图上,并调整地图视野
    myGeo.getPoint(province + city + district + town + address, function (point) {
        if (point) {
            addMap.clearOverlays();
            addMap.centerAndZoom(point, 18);
            addMap.addOverlay(new BMap.Marker(point));
            $('#add_gps_lat').val(point.lat);
            $('#add_gps_lng').val(point.lng);
        }
    }, city);
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//以下内容为更新组织模态框
function openEditModal(id) {

    $.ajax({
        url: '/system/organization/get?id=' + id,
        type: 'get',
        success: function (res) {

            if (!res.success){
                return;
            }

            res = res.data;

            $("#edit_organization_modal").modal("show");

            $("#edit_id").val(res.id);
            $("#edit_address_id").val(res.addressId);
            $("#edit_address_detail").val(res.addressDetail);
            $("#edit_type").val(res.type + "");

            //列出上级组织树状下拉框
            $.ajax({
                url: '/system/organization/list',
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
                    editTree = $("#edit_parent_organ_tree").tree({
                        uiLibrary: 'bootstrap',
                        dataSource: array,
                        primaryKey: 'id',
                        iconsLibrary: 'materialicons',
                        textField: "name"
                    });

                    $("#edit_parent_organ_tree").hide();
                    editTree.expandAll();

                    editTree.on('select', function (e, node, id) {
                        $("#edit_parent_organ").val(node.children().find("span:last-child").html());
                        $("#edit_parent_organ").attr('data-id', id);
                        $("#edit_parent_organ_tree").hide();
                    });

                    editTree.on('unselect', function (e, node, id) {
                        $("#edit_parent_organ").val("");
                        $("#edit_parent_organ").attr('data-id', '');
                        $("#edit_parent_organ_tree").hide();
                    });
                }
            });

            $("#edit_parent_organ").val(res.parentName);
            $("#edit_parent_organ").attr('data-id', res.parentId);
            $("#edit_name").val(res.name);
            $("#edit_short_name").val(res.shortName);

            //初始化地图
            editMap = new BMap.Map("edit_map");
            editMap.enableScrollWheelZoom();

            //进行具体配置
            setTimeout(function () {
                editUpdateMap();
            }, 500);

            editMap.addEventListener("click", function (e) {
                editMap.clearOverlays();
                var marker = new BMap.Marker(e.point);
                editMap.addOverlay(marker);
                editMap.panTo(new BMap.Point(e.point.lng, e.point.lat));// map.panTo方法，把点击的点设置为地图中心点
                $('#edit_gps_lat').val(e.point.lat);
                $('#edit_gps_lng').val(e.point.lng);
            });

            //自动生成下拉框
            $.ajax({
                url: '/system/area/province',
                type: 'get',
                success: function (a) {
                    a = a.data;
                    provinceCode = res.areaCode.substring(0, 2) + "0000";
                    for (var i = 0; i < a.length; i++) {
                        if (provinceCode == a[i].code) {
                            $("#edit_select_province").html($("#edit_select_province").html() +
                                "<option value='" + a[i].code + "' selected>" + a[i].name + "</option>");
                        } else {
                            $("#edit_select_province").html($("#edit_select_province").html() +
                                "<option value='" + a[i].code + "'>" + a[i].name + "</option>");
                        }
                    }

                    $.ajax({
                        url: '/system/area/city?provinceCode=' + provinceCode,
                        type: 'get',
                        success: function (a) {
                            a = a.data;
                            cityCode = res.areaCode.substring(0, 4) + "00";
                            for (var i = 0; i < a.length; i++) {
                                if (cityCode == a[i].code) {
                                    $("#edit_select_city").html($("#edit_select_city").html() +
                                        "<option value='" + a[i].code + "' selected>" + a[i].name + "</option>");
                                } else {
                                    $("#edit_select_city").html($("#edit_select_city").html() +
                                        "<option value='" + a[i].code + "'>" + a[i].name + "</option>");
                                }
                            }

                            $.ajax({
                                url: '/system/area/district?cityCode=' + cityCode,
                                type: 'get',
                                success: function (a) {
                                    a = a.data;
                                    for (var i = 0; i < a.length; i++) {
                                        if (res.areaCode == a[i].code) {
                                            $("#edit_select_district").html($("#edit_select_district").html() +
                                                "<option value='" + a[i].code + "' selected>" + a[i].name + "</option>");
                                        } else {
                                            $("#edit_select_district").html($("#edit_select_district").html() +
                                                "<option value='" + a[i].code + "'>" + a[i].name + "</option>");
                                        }
                                    }

                                    $.ajax({
                                        url: '/system/area/town?districtCode=' + res.areaCode,
                                        type: 'get',
                                        success: function (a) {
                                            a = a.data;
                                            if (a != null) {
                                                for (var i = 0; i < a.length; i++) {
                                                    if (res.areaTownCode != null) {
                                                        if (res.areaTownCode == a[i].code) {
                                                            $("#edit_select_town").html($("#edit_select_town").html() +
                                                                "<option value='" + a[i].code + "' selected>" + a[i].name + "</option>");
                                                        } else {
                                                            $("#edit_select_town").html($("#edit_select_town").html() +
                                                                "<option value='" + a[i].code + "'>" + a[i].name + "</option>");
                                                        }
                                                    } else {
                                                        $("#edit_select_town").html($("#edit_select_town").html() +
                                                            "<option value='" + a[i].code + "'>" + a[i].name + "</option>");
                                                        $("#edit_select_town").val("");
                                                    }
                                                }
                                            }
                                            //根据数据库中的经纬度画出百度地图
                                            editMap.clearOverlays();
                                            var marker = new BMap.Marker(new BMap.Point(res.gpsLng, res.gpsLat));
                                            editMap.centerAndZoom(new BMap.Point(res.gpsLng, res.gpsLat), 18);
                                            editMap.addOverlay(marker);
                                            editMap.panTo(new BMap.Point(res.gpsLng, res.gpsLat));// map.panTo方法，把点击的点设置为地图中心点
                                            $('#edit_gps_lat').val(res.gpsLat);
                                            $('#edit_gps_lng').val(res.gpsLng);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });


        }
    });
}

//选择省，生成对应的市，更新百度地图
$("#edit_select_province").change(function () {

    $("#edit_select_city").html("");
    $("#edit_select_district").html("");
    $("#edit_select_town").html("");

    $.ajax({
        url: '/system/area/city?provinceCode=' + $("#edit_select_province").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#edit_select_city").html($("#edit_select_city").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#edit_select_city").val("");
            }
            editUpdateMap();
        }
    })
});

//选择市，生成对应的区，更新百度地图
$("#edit_select_city").change(function () {

    $("#edit_select_district").html("");
    $("#edit_select_town").html("");

    $.ajax({
        url: '/system/area/district?cityCode=' + $("#edit_select_city").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#edit_select_district").html($("#edit_select_district").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#edit_select_district").val("");
            }
            editUpdateMap();
        }
    })
});

//选择区，生成对应的街道，更新百度地图
$("#edit_select_district").change(function () {

    $("#edit_select_town").html("");

    $.ajax({
        url: '/system/area/town?districtCode=' + $("#edit_select_district").val(),
        type: 'get',
        success: function (res) {
            res = res.data;
            for (var i = 0; i < res.length; i++) {
                $("#edit_select_town").html($("#edit_select_town").html() +
                    "<option value='" + res[i].code + "'>" + res[i].name + "</option>");
                $("#edit_select_town").val("");
            }
            editUpdateMap();
        }
    })
});

//选择街道，更新百度地图
$("#edit_select_town").change(function () {
    editUpdateMap();
});

//查询详细地址
$("#edit_search_address").click(function () {
    editUpdateMap();
});

//在详细地址栏回车更新地图
$("#edit_address_detail").keypress(function (event) {
    if (event.keyCode == 13) {
        editUpdateMap();
    }
});

//点击上级组织框，显示下拉树
$("#edit_parent_organ").click(function () {
    if ($("#edit_parent_organ_tree").is(":hidden")) {
        $("#edit_parent_organ_tree").show();
    } else {
        $("#edit_parent_organ_tree").hide();
    }
});

//模态框隐藏事件
$('#edit_organization_modal').on('hidden.bs.modal', function () {
    $("#edit_parent_organ").val("");
    $("#edit_name").val("");
    $("#edit_short_name").val("");
    $("#edit_type").val(1);
    $("#edit_address_detail").val("");
    $("#edit_select_province").html("");
    $("#edit_select_city").html("");
    $("#edit_select_district").html("");
    $("#edit_select_town").html("");
    editTree.destroy();
});

//确认编辑按钮
$("#do_edit_organization_btn").click(function () {

    $.ajax({
        url: '/system/organization/update',
        type: 'post',
        data: {
            id: $("#edit_id").val(),
            addressId: $("#edit_address_id").val(),
            parentId: $("#edit_parent_organ").attr("data-id"),
            name: $("#edit_name").val(),
            shortName: $("#edit_short_name").val(),
            provinceCode: $("#edit_select_province").val(),
            cityCode: $("#edit_select_city").val(),
            districtCode: $("#edit_select_district").val(),
            townCode: $("#edit_select_town").val(),
            addressDetail: $("#edit_address_detail").val(),
            gpsLng: $("#edit_gps_lng").val(),
            gpsLat: $("#edit_gps_lat").val(),
            type: $("#edit_type").val(),
            status: 1
        },
        success: function (res) {
            if (res.success) {
                $("#edit_organization_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
            }
        }
    });
});

//更新编辑组织模态框的地图
function editUpdateMap() {
    var province = $("#edit_select_province option:selected").text();
    var city = $("#edit_select_city option:selected").text();
    var district = $("#edit_select_district option:selected").text();
    var town = $("#edit_select_town option:selected").text();
    var address = $('#edit_address_detail').val();
    var myGeo = new BMap.Geocoder();

    // 将地址解析结果显示在地图上,并调整地图视野
    myGeo.getPoint(province + city + district + town + address, function (point) {
        if (point) {
            editMap.clearOverlays();
            editMap.centerAndZoom(point, 18);
            editMap.addOverlay(new BMap.Marker(point));
            $('#edit_gps_lat').val(point.lat);
            $('#edit_gps_lng').val(point.lng);
        }
    }, city);
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//删除组织
function openDeleteModal(id) {
    $("#delete_organization_modal").modal("show");
    $("#delete_organization_id").val(id);
}

$("#do_delete_organization_btn").click(function () {

    $.ajax({
        url: '/system/organization/delete',
        type: 'post',
        data: {
            id: $("#delete_organization_id").val()
        },
        success: function (res) {
            if (res.success) {
                $("#delete_organization_modal").modal("hide");
                $table.bootstrapTable('refresh');
            } else {
                alert(res.message);
                $("#delete_organization_modal").modal("hide");
            }
        }
    })
});

//////////////////////////////////////////////////////////////////////////////////////////////////////
//初始化表格
function init() {
    $('#search_organization_text').val("");
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: '/system/organization/list',
        striped: true,
        idField: 'id',
        columns: [
            {
                field: 'name',
                title: '组织名称',
                formatter: function (data) {
                    return "&nbsp;&nbsp;" + data;
                }
            }, {
                field: 'shortName',
                title: '简称'
            }, {
                field: 'createTime',
                title: '创建日期'
            }, {
                field: 'typeDesc',
                title: '组织类型'
            }, {
                field: 'status',
                title: '状态',
                formatter: function (value) {
                    if (value == 1) {
                        return "正常";
                    } else if (value == 0) {
                        return "待审核";
                    } else {
                        return "删除";
                    }
                }
            }, {
                field: 'handle',
                title: '操作',
                formatter: function (value, row, index) {
                    return "<a type='button' onclick='openEditModal(" + row.id + ")' class='btn common-btn'>编辑</a>" +
                        "<a type='button' onclick='openDeleteModal(" + row.id + ")' class='btn common-btn'>删除</a>"
                }
            }
        ],
        treeShowField: 'name',
        parentIdField: 'parentId',
        onLoadSuccess: function (data) {
            $table.treegrid({
                initialState: 'expanded',//收缩
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
}

//搜索
function search() {
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: '/system/organization/listByName?name=' + $("#search_organization_text").val(),
        striped: true,
        idField: 'id',
        columns: [
            {
                field: 'name',
                title: '组织名称',
                formatter: function (data) {
                    return "&nbsp;&nbsp;" + data;
                }
            }, {
                field: 'shortName',
                title: '简称'
            }, {
                field: 'createTime',
                title: '创建日期'
            }, {
                field: 'typeDesc',
                title: '组织类型'
            }, {
                field: 'status',
                title: '状态',
                formatter: function (value) {
                    if (value == 1) {
                        return "正常";
                    } else if (value == 0) {
                        return "待审核";
                    } else {
                        return "删除";
                    }
                }
            }, {
                field: 'handle',
                title: '操作',
                formatter: function (value, row, index) {
                    return "<a type='button' onclick='openEditModal(" + row.id + ")' class='btn common-btn'>编辑</a>" +
                        "<a type='button' onclick='openDeleteModal(" + row.id + ")' class='btn common-btn'>删除</a>"
                }
            }
        ],
        treeShowField: null,
        parentIdField: null,
        onLoadSuccess: function (data) {
            $table.treegrid({
                initialState: 'expanded',//收缩
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

}

