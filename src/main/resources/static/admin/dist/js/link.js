$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/links/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'blogLinkID', index: 'blogLinkID', width: 50, key: true, hidden: true},
            {label: '网站名称', name: 'blogLinkName', index: 'blogLinkName', width: 100},
            {label: '网站链接', name: 'blogLinkUrl', index: 'blogLinkUrl', width: 120},
            {label: '网站描述', name: 'blogLinkDescription', index: 'blogLinkDescription', width: 120},
            {label: '排序值', name: 'blogLinkRank', index: 'blogLinkRank', width: 30},
            {label: '添加时间', name: 'blogLinkCreateTime', index: 'blogLinkCreateTime', width: 100}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function linkAdd() {
    reset();
    $('.modal-title').html('友链添加');
    $('#linkModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var linkId = $("#blogLinkID").val();
    var linkName = $("#blogLinkName").val();
    var linkUrl = $("#blogLinkUrl").val();
    var linkDescription = $("#blogLinkDescription").val();
    var linkRank = $("#blogLinkRank").val();
    if (!validCN_ENString2_18(linkName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称！");
        return;
    }
    if (!isURL(linkUrl)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的网址！");
        return;
    }
    if (!validCN_ENString2_100(linkDescription)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的描述！");
        return;
    }
    if (isNull(linkRank) || linkRank < 0) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的排序值！");
        return;
    }
    var params = $("#linkForm").serialize();
    var url = '/admin/links/save';
    if (linkId != null && linkId > 0) {
        url = '/admin/links/update';
    }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 && result.data) {
                $('#linkModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#linkModal').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});

function linkEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    reset();
    //请求数据
    $.get("/admin/links/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal
            $("#blogLinkName").val(r.data.linkName);
            $("#blogLinkUrl").val(r.data.linkUrl);
            $("#blogLinkDescription").val(r.data.linkDescription);
            $("#blogLinkRank").val(r.data.linkRank);
            //根据原linkType值设置select选择器为选中状态
            if (r.data.blogLinkType == 1) {
                $("#linkType option:eq(1)").prop("selected", 'selected');
            }
            if (r.data.blogLinkType == 2) {
                $("#linkType option:eq(2)").prop("selected", 'selected');
            }
        }
    });
    $('.modal-title').html('友链修改');
    $('#linkModal').modal('show');
    $("#blogLinkID").val(id);
}

function deleteLink() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/links/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}

function reset() {
    $("#blogLinkName").val('');
    $("#blogLinkUrl").val('');
    $("#blogLinkDescription").val('');
    $("#blogLinkRank").val(0);
    $('#edit-error-msg').css("display", "none");
    $("#linkType option:first").prop("selected", 'selected');
}