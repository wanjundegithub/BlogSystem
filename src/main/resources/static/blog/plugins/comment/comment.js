$('#commentSubmit').click(function () {
    var blogID = $('#blogID').val();
    var verifyCode = $('#verifyCode').val();
    var blogCommentatorName = $('#blogCommentatorName').val();
    var blogCommentatorEmail = $('#blogCommentatorEmail').val();
    var blogWebsiteUrl = $('#blogWebsiteUrl').val();
    var blogCommentContent = $('#blogCommentContent').val();
    if (isNull(blogID)) {
        swal("参数异常", {
            icon: "warning",
        });
        return;
    }
    if (isNull(blogCommentatorName)) {
        swal("请输入你的称呼", {
            icon: "warning",
        });
        return;
    }
    if (isNull(blogCommentatorEmail)) {
        swal("请输入你的邮箱", {
            icon: "warning",
        });
        return;
    }
    if (isNull(verifyCode)) {
        swal("请输入验证码", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(blogCommentatorName)) {
        swal("请输入符合规范的名称(不要输入特殊字符)", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(blogCommentContent)) {
        swal("请输入符合规范的评论内容(不要输入特殊字符)", {
            icon: "warning",
        });
        return;
    }
    var data = {
        "blogID": blogID, "verifyCode": verifyCode, "blogCommentatorName": blogCommentatorName,
        "blogCommentatorEmail": blogCommentatorEmail, "blogWebsiteUrl": blogWebsiteUrl,
        "blogCommentContent": blogCommentContent
    };
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: '/blog/comment',
        data: data,
        success: function (result) {
            if (result.resultCode == 200) {
                swal("评论提交成功请等待博主审核", {
                    icon: "success",
                });
                $('#blogCommentContent').val('');
                $('#verifyCode').val('');
            }
            else {
                swal(result.message, {
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