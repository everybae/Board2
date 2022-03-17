replySave: function(){
    let data={
        boardId : $("#boardId").val(),
        content : $("#reply-content").val()
    };

    $.ajax({
        type:"POST",
        url:'board/api/board/${data.boardId}/reply',
        data: JSON.stringify(data),
        contentType : "application/json; charset=utf-8",
        dataType : "json"
    }).done(function(resp){
            alert("댓글 작성 완료");
            location.href='board/${data.boardId}';
    }).fail(function(error){
            alert(JSON.stringify(error));
    });
},