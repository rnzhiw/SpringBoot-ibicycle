<link rel="stylesheet" href="/static/lib/plugin/bootstrap-datatimepicker/bootstrap-datetimepicker.css">
<link rel="stylesheet" href="/static/css/bicycle-detail.css">
<#--元素布局-->
<div class="content">
    <input type="hidden" value="${id}" id="id">
    <ul id="myTab" class="nav nav-tabs">
        <li><a id="bicycle_manage" style="cursor: pointer;">车辆管理</a></li>
        <li class="active">
            <a href="#information" data-toggle="tab">
                基本信息
            </a>
        </li>
        <li><a href="#location" data-toggle="tab">实时定位</a></li>
        <li><a href="#track" data-toggle="tab">历史轨迹</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="information">
            <form class="form-horizontal col-sm-4" role="form">
                <div class="form-group">
                    <label for="loginName" class="col-sm-4 control-label" >车主姓名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="ownerName" disabled="disabled" placeholder="车主姓名" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-4 control-label">联系电话</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="phone" placeholder="联系电话" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="brand" class="col-sm-4 control-label">品牌</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="brand" placeholder="品牌" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="model" class="col-sm-4 control-label">型号</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="model" placeholder="型号" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="vin" class="col-sm-4 control-label">车架号</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="vin" placeholder="车架号" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="motorNumber" class="col-sm-4 control-label">电机号</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="motorNumber" placeholder="电机号" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="licenseNumber" class="col-sm-4 control-label">牌照</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="licenseNumber" placeholder="牌照" readonly>
                    </div>
                </div>
            </form>
            <form class="form-horizontal col-sm-4" role="form">
                <div class="form-group">
                    <label for="color" class="col-sm-3 control-label">颜色</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="color" placeholder="颜色" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="buyTime" class="col-sm-3 control-label">购买时间</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="buyTime" placeholder="购买时间" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="buyAddress" class="col-sm-3 control-label">购买地址</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="buyAddress" placeholder="购买地址" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="installTime" class="col-sm-3 control-label">安装时间</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="installTime" placeholder="安装时间" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="installOrg" class="col-sm-3 control-label">安装点</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="installOrg" placeholder="安装点" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-sm-3 control-label">当前状态</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" disabled="disabled" id="status" placeholder="当前状态" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-8">
                        <button type="submit" class="btn common-btn confirm">确认修改</button>
                        <button class="btn common-btn confirm">取消</button>
                    </div>
                </div>
            </form>
            <form class="form-horizontal col-sm-4" role="form">
                <div class="form-group">
                    <div class="col-sm-12">
                        <div class="preview" id="preview">
                            <img id="img" alt="">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="location">
            <div class="form-inline">
                <div class="form-group">
                    <label for="last_location_time">最后一次定位时间:</label>
                    <input type="text" class="form-control info" id="last_location_time" readonly>
                    <label for="longitude">经度:</label>
                    <input type="text" class="form-control info" id="longitude" readonly>
                    <label for="latitude">纬度:</label>
                    <input type="text" class="form-control info" id="latitude" readonly>
                    <button class="btn common-btn" id="refresh_location">刷新定位</button>
                </div>
            </div>
            <div id="location_map"></div>
        </div>
        <div class="tab-pane fade" id="track">
            <div class="form-inline">
                <div class="form-group">
                    <label for="startTime" class="sr-only"></label>
                    <input class="form-control time-select" type="text" id="startTime"/>
                    <span class="item">--</span>
                    <label for="endTime" class="sr-only"></label>
                    <input class="form-control time-select" type="text" id="endTime" class="sr-only"/>
                    <button id="search_track" class="btn common-btn search-track">按时间搜索</button>
                </div>
            </div>
            <div id="track_map"></div>
        </div>
    </div>
</div>

<#--脚本文件-->
<script src="/static/lib/plugin/bootstrap-datatimepicker/bootstrap-datetimepicker.js"></script>
<script src="/static/js/bicycle_detail.js"></script>
<script>
    $('#startTime').datetimepicker();
    $('#endTime').datetimepicker();
</script>