<#--css布局-->
<link rel="stylesheet" href="/static/css/member.css">
<!-- bootstrap-select -->
<link rel="stylesheet" href="/static/lib/plugin/bootstrap-select/bootstrap-select.min.css">

<#--元素布局-->
<div class="content">
    <div class="search-bar">
        <form class="form-inline">
            <div class="form-group col-sm-4">
                <label for="search_organization_text">组织结构：</label>
                <input type="text" class="form-control organization-item" id="search_organization_text">
            </div>
            <div class="form-group">
                <label for="search_member_text">关键字：</label>
                <input type="text" class="form-control" id="search_member_text">
            </div>
            <div class="form-group">
                <a type="button" class="btn common-btn" id="search_member_btn"><i class="la la-search">搜索</i></a>
                <a type="button" class="btn common-btn" id="reset_member_btn" onclick="reset()"><i class="la la-share">重置</i></a>
                <a type="button" class="btn common-btn" id="add_member_btn" data-toggle="modal" data-target="#add_member_modal">
                    <i class="la la-plus">创建账号</i>
                </a>
            </div>
        </form>
    </div>
    <table id="account_table" class="table table-striped table_list"></table>
</div>

<#--新增用户模态框-->
<div class="modal fade" id="add_member_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="organ" class="col-sm-2 control-label">组织结构</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_organ" style="background-color: #ffffff" data-id=""
                                   required readonly>
                        </div>
                        <div id="add_organ_tree" style="position:absolute;left:123px;top:50px;z-index:2;
                        border:1px solid #cccccc;width: 452px;"></div>
                    </div>
                    <div class="form-group">
                        <label for="add_name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="add_name">
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control" id="add_gender">
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_account" class="col-sm-2 control-label">账号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_account">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role" class="col-sm-2 control-label">用户角色</label>

                        <div class="col-sm-10">
                            <select class="selectpicker form-control" multiple id="add_role">
                                <option value="1">平台管理员</option>
                                <option value="2">代理商管理员</option>
                                <option value="3">运维支持人员</option>
                                <option value="4">单位用户管理员</option>
                                <option value="5">场所管理员</option>
                                <option value="6">设备供应商管理员</option>
                                <option value="7">政府平台管理员</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn" id="do_add_member_btn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#-- 编辑用户模态框 -->
<div class="modal fade" id="edit_member_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelEdit">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input type="hidden" id="member_edit_id">
                        <label for="edit_organ" class="col-sm-2 control-label">所属组织</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_organ" style="background-color: #ffffff" data-id=""
                                   required readonly>
                        </div>
                        <div id="edit_organ_tree" style="position:absolute;left:123px;top:50px;z-index:2;
                        border:1px solid #cccccc;width: 452px;"></div>
                    </div>
                    <div class="form-group">
                        <label for="edit_name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit_name">
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control" id="edit_gender">
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_phone" class="col-sm-2 control-label">手机</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_role" class="col-sm-2 control-label">用户角色</label>
                        <div class="col-sm-10">
                            <select class="selectpicker form-control" multiple id="edit_role">
                                <option value="1">平台管理员</option>
                                <option value="2">代理商管理员</option>
                                <option value="3">运维支持人员</option>
                                <option value="4">单位用户管理员</option>
                                <option value="5">场所管理员</option>
                                <option value="6">设备供应商管理员</option>
                                <option value="7">政府平台管理员</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn"  id="do_edit_member_btn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#-- 删除用户模态框 -->
<div class="modal fade" id="delete_member_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalDelete">删除账户</h4>
            </div>
            <div class="modal-body">
                确定要删除吗？
                <input type="hidden" id="delete_member_id">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn cancel-btn" data-dismiss="modal">关闭</button>
                <button type="button" class="btn common-btn" id="do_delete_member_btn">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#--脚本文件-->
<!-- bootstrap-select -->
<script src="/static/lib/plugin/bootstrap-select/bootstrap-select.min.js"></script>
<script src="/static/lib/plugin/bootstrap-select/defaults-zh_CN.min.js"></script>
<script src="/static/js/member.js"></script>