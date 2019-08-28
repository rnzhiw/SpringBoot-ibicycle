<link rel="stylesheet" href="/static/css/role.css">
<#--元素布局-->
<div class="content main">
    <div id="toolbar" class="btn-group">
        <a id="btn_add" type="button" class="btn common-btn">
            <i class="la la-plus">创建</i>
        </a>
        <a id="btn_save" type="button" class="btn common-btn">
            <i class="la la-file-text">保存</i>
        </a>
        <a id="btn_delete" type="button" class="btn common-btn" value="-1">
            <i class="la la-trash">删除</i>
        </a>
        <a id="btn_cancel" type="button" class="btn common-btn">
            <i class="la la-share">取消</i>
        </a>
        <a id="btn_refresh" type="button" class="btn common-btn">
            <i class="la la-refresh">刷新</i>
        </a>
    </div>
    <div id="right_toolbar" class="btn-group">
        <a id="resource_save_btn" type="button" class="btn common-btn">
            <i class="la la-file-text">保存权限</i>
        </a>
    </div>
    <div class="col-xs-12 col-md-8 left">
        <table id="role_table" class="table table-striped table_list"></table>
    </div>
    <div class="col-xs-6 col-md-4 right">
        <table id="create_table" class="table table-striped table_list"></table>
    </div>
</div>

<#--模态框-->

<#--脚本文件-->
<script src="/static/js/role.js"></script>