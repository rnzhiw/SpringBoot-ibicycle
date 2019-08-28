<link rel="stylesheet" href="/static/css/resource.css">
<#--元素布局-->
<div class="content">
    <div id="toolbar" class="btn-group">
        <a id="add_resource_btn" type="button" data-toggle="modal" class="btn common-btn">
            <i class="la la-plus">添加资源</i>
        </a>
    </div>
    <table id="resource_table" class="table table-striped table_list"></table>
</div>

<#--模态框-->
    <div class="modal fade" id="add_resource_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">添加资源</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="addTopResource" class="col-sm-2 control-label">父资源</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="add_parent_resource"
                                       style="background-color: #ffffff" data-id=""
                                       required readonly>
                            </div>
                            <div id="add_parent_resource_tree" style="position:absolute;left:123px;top:50px;z-index:1;
                        border:1px solid #cccccc;width: 452px;"></div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">资源名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="add_name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_symbol" class="col-sm-2 control-label">资源标识</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="add_resource_key">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add_select-type" class="col-sm-2 control-label">资源类型</label>
                            <div class="col-sm-10">
                                <select class="form-control item" id="add_select_type">
                                    <option value="FUNCTION">功能</option>
                                    <option value="MENU">菜单</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_menu" class="col-sm-2 control-label">菜单URL</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="add_menu_url">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_function" class="col-sm-2 control-label">功能URL</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="add_function_urls" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_weight" class="col-sm-2 control-label">权重</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="add_weight" value="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_status" class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <select class="form-control item" id="add_select_status">
                                    <option value="true">可用</option>
                                    <option value="false">禁用</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn common-btn" id="do_add_resource_btn">添加</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <div class="modal fade" id="edit_resource_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">编辑资源</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="addTopResource" class="col-sm-2 control-label">父资源</label>
                            <div class="col-sm-10">
                                <input type="hidden" id="edit_id">
                                <input type="text" class="form-control" id="edit_parent_resource" style="background-color: #ffffff" data-id=""
                                       required readonly>
                            </div>
                            <div id="edit_parent_resource_tree" style="position:absolute;left:123px;top:50px;z-index:1;
                        border:1px solid #cccccc;width: 452px;"></div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">资源名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_symbol" class="col-sm-2 control-label">资源标识</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_resource_key">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add_select-type" class="col-sm-2 control-label">资源类型</label>
                            <div class="col-sm-10">
                                <select class="form-control item" id="edit_select_type">
                                    <option value="FUNCTION">功能</option>
                                    <option value="MENU">菜单</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_menu" class="col-sm-2 control-label">菜单URL</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_menu_url">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_function" class="col-sm-2 control-label">功能URL</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="edit_function_urls" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_weight" class="col-sm-2 control-label">权重</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_weight" value="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_status" class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <select class="form-control item" id="edit_select_status">
                                    <option value="true">可用</option>
                                    <option value="false">禁用</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn common-btn" id="do_edit_resource_btn">编辑</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <div class="modal fade" id="delete_resource_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalDelete">删除资源</h4>
                </div>
                <div class="modal-body">
                    确定要删除吗？
                    <input type="hidden" id="delete_resource_id">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn common-btn" id="do_delete_resource_btn">删除</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

<#--脚本文件-->
<script src="/static/js/resource.js"></script>