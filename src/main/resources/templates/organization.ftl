<link rel="stylesheet" href="/static/css/organization.css">
<#--元素布局-->
    <div class="content">
        <div class="search-bar">
            <div class="form-inline">
                <div class="form-group">
                    <label for="search">组织名称：</label>
                    <input type="text" class="form-control" id="search_organization_text" placeholder="搜索">
                    <a type="button" class="btn common-btn" id="search_organization_btn"><i class="la la-search">搜索</i></a>
                    <a type="button" class="btn common-btn" id="reset_organization_btn"><i
                                class="la la-share">重置</i></a>
                    <a type="button" class="btn common-btn" id="add_organization_btn" data-toggle="modal"><i
                                class="la la-plus">添加组织</i></a>
                </div>
            </div>
        </div>
        <table id="organ_table" class="table table-striped table_list"></table>
    </div>

<#--模态框-->
<div class="modal fade" id="add_organization_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelAddOrgan">添加组织</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="topOrgan" class="col-sm-2 control-label">上级组织</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_parent_organ" style="background-color: #ffffff" data-id=""
                                   required readonly>
                        </div>
                        <div id="add_parent_organ_tree" style="position:absolute;left:123px;top:50px;z-index:1;
                        border:1px solid #cccccc;width: 452px;"></div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组织全称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_name" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">组织简称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_short_name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">组织类型</label>
                        <div class="col-sm-10">
                            <select class="form-control item" id="add_type">
                                <option value="1">公安</option>
                                <option value="2">运维</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-2 control-label">区域</label>
                        <div class="col-sm-10">
                            <select class="form-control item" id="add_select_province" required>
                            </select>
                            <select class="form-control item" id="add_select_city" required>
                            </select>
                            <select class="form-control item" id="add_select_district" required>
                            </select>
                            <select class="form-control item" id="add_select_town">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">详细地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="add_address_detail" required>
                        </div>
                        <div class="col-sm-1">
                            <a type="button" class="btn common-btn search-address-btn" id="add_search_address">
                                <i class="la la-search">查询</i></a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="add_map" style="width: 580px;height: 300px;"></div>
                        <input id="add_gps_lng" type="hidden">
                        <input id="add_gps_lat" type="hidden">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn" id="do_add_organization_btn">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="edit_organization_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelAddOrgan">编辑组织</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input type="hidden" id="edit_id">
                        <input type="hidden" id="edit_address_id">
                        <label for="topOrgan" class="col-sm-2 control-label">上级组织</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_parent_organ" style="background-color: #ffffff" data-id=""
                                   required readonly>
                        </div>
                        <div id="edit_parent_organ_tree" style="position:absolute;left:123px;top:50px;z-index:1;
                        border:1px solid #cccccc;width: 452px;"></div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组织全称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_name" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">组织简称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_short_name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">组织类型</label>
                        <div class="col-sm-10">
                            <select class="form-control item" id="edit_type">
                                <#if Session.SESSION_INFO.superUser == true>
                                    <option value="0">平台</option>
                                </#if>
                                <option value="1">公安</option>
                                <option value="2">运维</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-2 control-label">区域</label>
                        <div class="col-sm-10">
                            <select class="form-control item" id="edit_select_province" required>
                            </select>
                            <select class="form-control item" id="edit_select_city" required>
                            </select>
                            <select class="form-control item" id="edit_select_district" required>
                            </select>
                            <select class="form-control item" id="edit_select_town">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">详细地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit_address_detail" required>
                        </div>
                        <div class="col-sm-1">
                            <a type="button" class="btn common-btn search-address-btn" id="edit_search_address">
                                <i class="la la-search">查询</i></a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="edit_map" style="width: 580px;height: 300px;"></div>
                        <input id="edit_gps_lng" type="hidden">
                        <input id="edit_gps_lat" type="hidden">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn" id="do_edit_organization_btn">编辑</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="delete_organization_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalDelete">删除组织</h4>
            </div>
            <div class="modal-body">
                确定要删除吗？
                <input type="hidden" id="delete_organization_id">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn" id="do_delete_organization_btn">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="/static/js/organization.js"></script>
