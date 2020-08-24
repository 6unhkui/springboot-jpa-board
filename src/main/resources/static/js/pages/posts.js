$(document).ready(function () {
    // 게시글 업로드
    $('#post-write-form').on('submit', function(e) {
        e.preventDefault();
        let formData = $('#post-write-form').serializeArray();
        formData.push({"name" : "userId", "value" : $('#post-write-form #userId').val()})

        var cnt = 0;
        for (const [key, value] of formData.entries()) {
            if(value.value.trim().length == 0) {
                alert(value.name + '를 입력해주세요.');
                cnt++;
                return false;
            }
        }
        if(cnt == 0) {
            $.ajax({
                type: "POST",
                url : '/api/v1/posts',
                data : formData,
                cache: false,
                success : function(data) {
                    location.href = "/";
                },
                error : function(jqXHR, textStatus, errorThrown){
                    console.log(textStatus, errorThrown);
                }
            });
        }
    });

    // 게시글 수정
    $('#post-update-form').on('submit', function(e) {
        e.preventDefault();
        const formData = $('#post-update-form').serializeArray();
        const id = $('#post-update-form #id').val();

        $.ajax({
            type: "PUT",
            url : '/api/v1/posts/' + id,
            data : formData,
            cache: false,
            success : function(data) {
                location.href = "/";
            },
            error : function(jqXHR, textStatus, errorThrown){
                console.log(textStatus, errorThrown);
            }
        });
    });

    $('#search-btn').on('click', function() {
        const value = $('#search-btn').val().trim();
        if(value.length == 0) return false;
    })
});

// function postDelete(id) {
//     if(!confirm("게시글을 정말 삭제하시겠습니까?"))
//         return false;
//
//     $.ajax({
//         type: "DELETE",
//         url : '/api/v1/posts/' + id,
//         cache: false,
//         success : function(data) {
//             location.href = "/";
//         },
//         error : function(jqXHR, textStatus, errorThrown){
//             console.log(textStatus, errorThrown);
//         }
//     });
// }