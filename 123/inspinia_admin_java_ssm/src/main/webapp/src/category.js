//category bussiness js
function del(id) {
        console.log(id);
        $.ajax({
            url:"/admin/category/delete",
            data:{"id":id},
            type:"post",
            dataType:"json",
            success: function (result) {
                if(result.code == 100) {
                    alert("ok");
                    location.reload();
                }
            },
            error: function () {
                alert('错误');
            }
        })
}

function edit(id) {
    $.ajax({
       url:"/admin/category/get",
        data:{"id":id},
        type:"get",
        dataType:"json",
        success:function (result) {
            if(result.code == 100) {
                var imgSrc = "/admin/_upload/img/category/" + result.data.id +".jpg";
                $("#edit_image").attr("src",imgSrc);
                $("#edit_id").val(result.data.id);
                $("#edit_name").val(result.data.name);

                $("#edit_modal").modal('show');

            } else {
              alert("error");
            }
        },
        error:function (result) {
           alert("error");
        }
    });
}

$(function () {
   $('#edit_image').click(function () {
       $('#edit_upload').trigger('click').show();
       $('#edit_image').parent().hide();
   });
});


