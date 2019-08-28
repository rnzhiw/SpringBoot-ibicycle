<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>智慧电动车管理平台</title>
    <link rel="stylesheet" href="/static/css/ready.css">
    <link rel="stylesheet" href="/static/css/demo.css">
    <link rel="stylesheet" href="/static/css/common.css">
    <link rel="stylesheet" href="/static/css/bootstrap.3.3.7.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/jquery.treegrid.min.css">
    <link rel="stylesheet" href="/static/lib/plugin/gijgo-treeview/core/core.css">
    <link rel="stylesheet" href="/static/lib/plugin/gijgo-treeview/tree/tree.css">
</head>
<body>

<div class="wrapper">
    <div class="header">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">智慧电动车平台</a>
                </div>
                <div style="margin-right: 30px">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="/static/img/profile.jpg" alt="" width="30px" class="img-circle">
                                ${user.realName}
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="user-box">
                                        <div class="u-text">
                                            <h4>${user.realName}</h4>
                                            <p class="text-muted">邮箱</p>
                                            <a href="/logout" class="btn btn-rounded btn-danger btn-sm">${user.email}</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="sidebar">
        <div class="scrollbar-inner sidebar-wrapper">
            <div class="user">
                <div class="photo">
                    <img src="/static/img/profile.jpg">
                </div>
                <div class="info">
                    <a class="" data-toggle="collapse" href="#" aria-expanded="true">
                        <span>
                            帐号
                            <span class="user-level">${user.email}</span>
                        </span>
                    </a>
                </div>
            </div>
            <ul class="nav">
                <#list menus as menu>
                    <#if menu?index == 0>
                        <li class="nav-item active" data-menuUrl="${menu.menuUrl}">
                    <#else>
                        <li class="nav-item" data-menuUrl="${menu.menuUrl}">
                    </#if>
                    <a>
                        <i class="la ${menu.menuUrl?substring(8)}"></i>
                        <p>
                            ${menu.resName}
                        </p>
                    </a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <div id="mainPanel" class="main-panel" style="background: #fcfcfc">

</div>
</body>
<script src="/static/lib/core/jquery.3.2.1.min.js"></script>
<script src="/static/lib/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="/static/lib/core/popper.min.js"></script>
<!-- <script src="/lib/core/bootstrap.min.js"></script> -->
<script src="/static/lib/core/bootstrap3.3.7.min.js"></script>
<script src="/static/lib/plugin/chartist/chartist.min.js"></script>
<script src="/static/lib/plugin/chartist/plugin/chartist-plugin-tooltip.min.js"></script>
<script src="/static/lib/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>
<script src="/static/lib/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="/static/lib/plugin/jquery-mapael/jquery.mapael.min.js"></script>
<script src="/static/lib/plugin/jquery-mapael/maps/world_countries.min.js"></script>
<script src="/static/lib/plugin/chart-circle/circles.min.js"></script>
<script src="/static/lib/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="/static/lib/ready.min.js"></script>
<!-- <script src="/lib/demo.js"></script> -->
<script src="/static/lib/plugin/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/lib/plugin/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/lib/plugin/bootstrap-table/bootstrap-table-treegrid.js"></script>
<script src="/static/lib/core/jquery.treegrid.min.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery-treegrid/0.2.0/js/jquery.treegrid.min.js"></script> -->
<script src="/static/js/index.js"></script>
<script src="/static/lib/plugin/gijgo-treeview/core/core.js" type="text/javascript"></script>
<script src="/static/lib/plugin/gijgo-treeview/tree/tree.js" type="text/javascript"></script>
</html>