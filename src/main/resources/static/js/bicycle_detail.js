var locationMap;
var trackMap;
var point;
var close;

var long = new Array();
var lat = new Array();

var zoom = [50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000];
var longMax = 0;
var longMin = 180;
var latMax = 0;
var latMin = 90;
var i = 0;
var mapLevel;
var pointA;
var pointB;
var pointC;
var pointsLength;
var carMk;
var startLong;
var startLat;
var endLong;
var endLat;

$(function () {
    $("#location_map").css('height', $(window).height() - 230);
    $("#track_map").css('height', $(window).height() - 230);

    $.ajax({
        url:"/system/bicycle/getLastLocation",
        data:{
            id: $("#id").val()
        },
        success:function (res) {

            res = res.data;

            $("#last_location_time").val(res.time);
            $("#longitude").val(res.gpsLng);
            $("#latitude").val(res.gpsLat);

            point = new BMap.Point(res.gpsLng, res.gpsLat);
            //初始化实时定位地图
            locationMap = new BMap.Map("location_map");
            locationMap.enableScrollWheelZoom();
            locationMap.centerAndZoom(point, 18);

            locationMap.addEventListener("tilesloaded", function () {
                updateLocation(point);
            });
        }
    });

    //初始化轨迹地图
    trackMap = new BMap.Map("track_map");
    trackMap.enableScrollWheelZoom();
    trackMap.centerAndZoom(point, 18);

    $.ajax({
        type: "get",
        url: "/system/bicycle/get",
        data: {
            id: $("#id").val()
        },
        dataType: "json",
        success: function (response) {
            $("#ownerName").val(response.ownerName);
            $("#phone").val(response.ownerPhone);
            $("#brand").val(response.brand);
            $("#model").val(response.model);
            $("#vin").val(response.vin);
            $("#motorNumber").val(response.motorNumber);
            $("#licenseNumber").val(response.licenseNumber);
            $("#color").val(response.color);
            $("#buyTime").val(response.buyTime);
            $("#buyAddress").val(response.buyAddress);
            $("#installTime").val(response.installTime);
            $("#installOrg").val(response.installOrgName);
            $("#status").val(response.status);
            $("#img").attr("src",response.imgUrl);
        }
    });
});

$("#search_track").click(function () {
    drawNewTrack();
});

$("#refresh_location").click(function () {
    $.ajax({
        url:"/system/bicycle/getLastLocation",
        data:{
            id: $("#id").val()
        },
        success:function (res) {

            res = res.data;

            $("#last_location_time").val(res.time);
            $("#longitude").val(res.gpsLng);
            $("#latitude").val(res.gpsLat);

            point = new BMap.Point(res.gpsLng, res.gpsLat);
            //初始化实时定位地图
            locationMap = new BMap.Map("location_map");
            locationMap.enableScrollWheelZoom();
            locationMap.centerAndZoom(point, 18);

            locationMap.addEventListener("tilesloaded", function () {
                updateLocation(point);
            });
        }
    });
});

function drawNewTrack() {

    long = new Array();
    lat = new Array();

    var startTime = $("#startTime").val().replace("-", "/");
    startTime = startTime.replace("-", "/");
    var endTime = $("#endTime").val().replace("-", "/");
    endTime = endTime.replace("-", "/");
    console.log(startTime + "  " + endTime)

    i = 0;
    $.ajax({
        url: "/system/bicycle/getTrack",
        type: "get",
        data: {
            id: $("#id").val(),
            start: startTime,
            stop: endTime
        },
        success: function (res) {
            res = res.data;

            //初始化轨迹地图
            trackMap = new BMap.Map("track_map");
            trackMap.enableScrollWheelZoom();

            for (var i = 0; i < res.length; i++) {
                long[i] = res[i].gpsLng;
                lat[i] = res[i].gpsLat;
            }

            updateTrack();
        }
    })
}

//自适应屏幕
$(window).resize(function () {
    $("#location_map").css('height', $(window).height() - 230);
    $("#track_map").css('height', $(window).height() - 230);
});

$("#bicycle_manage").click(function () {
    $("#mainPanel").load('/system/bicycle');
});

function updateLocation(point) {
    locationMap.clearOverlays();
    locationMap.addOverlay(new BMap.Marker(point));
    locationMap.panTo(point);
}

function updateTrack() {

    clearInterval(close);
    //初始化轨迹地图
    trackMap = new BMap.Map("track_map");
    trackMap.enableScrollWheelZoom();

    //画出地图
    drawMap();
    //画出路径
    drawWay();
    //让电动车图标在路径上移动（再画一条一模一样的路线）
    close = setInterval(drawIcon, 300);
}

function drawMap() {

    //找出经纬度中的最大/小值
    for (var i = 0; i < long.length; i++) {
        if (Math.max(longMax, long[i]) == long[i]) {
            longMax = long[i];
        }

        if (Math.min(longMin, long[i]) == long[i]) {
            longMin = long[i];
        }

        if (Math.max(latMax, lat[i]) == lat[i]) {
            latMax = lat[i];
        }

        if (Math.min(latMin, lat[i]) == lat[i]) {
            latMin = lat[i];
        }
    }

    //确定最靠近东北和最靠近西南的点和两点之间的中点
    pointA = new BMap.Point(longMax, latMax);
    pointB = new BMap.Point(longMin, latMin);
    pointC = new BMap.Point((longMax + longMin) / 2, (latMax + latMin) / 2);

    //计算最靠近东北和最靠近西南的点之间的长度
    pointsLength = trackMap.getDistance(pointA, pointB).toFixed(2);

    //根据长度确定地图等级
    for (var i = 0; i < zoom.length; i++) {
        if (zoom[i] - pointsLength > 0) {
            mapLevel = 18 - i + 4;
            break;
        }

    }

    //通过中心点和地图等级画出地图
    trackMap.centerAndZoom(pointC, mapLevel);
    trackMap.addControl(new BMap.MapTypeControl());
    //请填写城市
    // trackMap.setCurrentCity("");
    trackMap.enableScrollWheelZoom(true);
}

function drawWay() {

    //标出起始点
    var startIcon = new BMap.Icon("/img/start.png", new BMap.Size(41, 41));
    var startMarker = new BMap.Marker(new BMap.Point(long[0], lat[0]), {icon: startIcon})
    trackMap.addOverlay(startMarker);

    startMarker.setAnimation(BMAP_ANIMATION_BOUNCE);

    for (var i = 0; i < long.length - 1; i++) {
        startLong = long[i];
        startLat = lat[i];
        endLong = long[i + 1];
        endLat = lat[i + 1];

        //两个点之间连线
        drawRedLine(startLong, startLat, endLong, endLat);
    }

    //标出终点
    var endIcon = new BMap.Icon("/img/end.png", new BMap.Size(41, 41));
    var endMarker = new BMap.Marker(new BMap.Point(endLong, endLat), {icon: endIcon});
    trackMap.addOverlay(endMarker);

    endMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
}

function drawRedLine(startLong, startLat, endLong, endLat) {

    //定义两点之间的连线
    var polyline = new BMap.Polyline([
        new BMap.Point(startLong, startLat),
        new BMap.Point(endLong, endLat)
    ], {
        strokeColor: "red",
        //宽度
        strokeWeight: 3,
        //不透明度
        strokeOpacity: 1
    });

    //在地图上添加一层蒙板，并将连线添加至蒙板上
    trackMap.addOverlay(polyline);
}

function drawIcon() {

    //删除原有图标
    if (carMk) {
        trackMap.removeOverlay(carMk);
    }

    //在路径头部标出电动车图标
    var eBicycleIcon = new BMap.Icon("/img/e_bicycle.png", new BMap.Size(50, 32), {imageOffset: new BMap.Size(0, 0)});
    carMk = new BMap.Marker(new BMap.Point(long[i], lat[i]), {icon: eBicycleIcon});
    trackMap.addOverlay(carMk);

    //重新绘制路径
    drawRedLine(long[i], lat[i], long[i + 1], lat[i + 1]);
    i++;

    //当路线到达终点，停止画线
    if (i == long.length - 1) {
        i = 0;
    }
}